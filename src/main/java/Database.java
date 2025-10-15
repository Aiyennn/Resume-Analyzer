import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Database {

    private final String employerFile;
    private final String jobSeekerFile;

    public Database(String employerFile, String jobSeekerFile) {

        this.employerFile = employerFile;
        this.jobSeekerFile = jobSeekerFile;

        try {
            File employerCSV = new File(employerFile);
            File jobSeekerCSV = new File(jobSeekerFile);

            if (!employerCSV.exists()) {
                try (FileWriter writer = new FileWriter(employerCSV)) {
                    writer.write("Name,Password,Email,Job Title,Skill Qualification,Education Qualification,Experience Qualification\n");
                }
                System.out.println("📁 Created file: " + employerFile);
            } else {
                System.out.println("✔ Found existing file: " + employerFile);
            }

            if (!jobSeekerCSV.exists()) {
                try (FileWriter writer = new FileWriter(jobSeekerCSV)) {
                    writer.write("Name,Password,Email,Skills,Education,Experience\n");
                }
                System.out.println("📁 Created file: " + jobSeekerFile);
            } else {
                System.out.println("✔ Found existing file: " + jobSeekerFile);
            }

            System.out.println("\n\n");

        } catch (IOException e) {
            System.err.println("Error checking or creating CSV files: " + e.getMessage());
        }
    }

    public void saveJobSeeker(JobSeeker user) throws IOException {

        try (PrintWriter writer = new PrintWriter(new FileWriter(jobSeekerFile, true))) {

            String educations = "None";
            String experiences = "None";
            String skills = "None";

            if (user.getResume() != null) {
                if (user.getResume().getEducations() != null)
                    educations = encodeEducations(user.getResume().getEducations());

                if (user.getResume().getExperiences() != null)
                    experiences = encodeExperiences(user.getResume().getExperiences());

                if (user.getResume().getSkills() != null)
                    skills = encodeSkills(user.getResume().getSkills());
            }

            writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                    user.getName(), user.getPassword(), user.getEmail(),
                    educations, experiences, skills);
        }
    }

    // Ad hoc solution dana fix it later
    public void updateJobSeeker(JobSeeker user) throws IOException {
        File inputFile = new File(jobSeekerFile);
        File tempFile = new File(jobSeekerFile + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            boolean isHeader = true;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                // Write header as-is
                if (isHeader) {
                    writer.println(line);
                    isHeader = false;
                    continue;
                }

                // Parse current line to check email
                String[] parts = splitCsvLine(line);
                if (parts.length >= 3) {
                    String currentEmail = parts[2].replace("\"", "");

                    // If this is the user to update, write new data
                    if (currentEmail.equalsIgnoreCase(user.getEmail())) {
                        String educations = "None";
                        String experiences = "None";
                        String skills = "None";

                        if (user.getResume() != null) {
                            if (user.getResume().getEducations() != null)
                                educations = encodeEducations(user.getResume().getEducations());

                            if (user.getResume().getExperiences() != null)
                                experiences = encodeExperiences(user.getResume().getExperiences());

                            if (user.getResume().getSkills() != null)
                                skills = encodeSkills(user.getResume().getSkills());
                        }

                        writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                                user.getName(), user.getPassword(), user.getEmail(),
                                educations, experiences, skills);
                        found = true;
                    } else {
                        // Write original line for other users
                        writer.println(line);
                    }
                } else {
                    // Write malformed lines as-is
                    writer.println(line);
                }
            }

            if (!found) {
                throw new IOException("Job seeker with email " + user.getEmail() + " not found");
            }
        }

        // Replace original file with updated file
        if (!inputFile.delete()) {
            throw new IOException("Could not delete original file");
        }
        if (!tempFile.renameTo(inputFile)) {
            throw new IOException("Could not rename temp file");
        }
    }

    // save if employer or job seeker =>
    public JobSeeker getJobSeekerByEmail(String email, String passwordInput) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(jobSeekerFile))) {
            String line;
            boolean skipHeader = true;

            while ((line = reader.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                String[] parts = splitCsvLine(line);
                if (parts.length < 6) continue;

                String currentEmail = parts[2].replace("\"", "");
                if (currentEmail.equalsIgnoreCase(email)) {

                    String name = parts[0].replace("\"", "");
                    String password = parts[1].replace("\"", "");

                    List<Education> educations = decodeEducations(parts[3]);
                    List<Experience> experiences = decodeExperiences(parts[4]);
                    List<Skill> skills = decodeSkills(parts[5]);

                    Resume resume = new Resume(educations, experiences, skills);
                    return new JobSeeker(name, password, currentEmail, resume);
                }
            }
        }
        return null;
    }



    public boolean userExists(String email) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(jobSeekerFile))) {
            String line;
            boolean skipHeader = true;
            while ((line = reader.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                String[] parts = splitCsvLine(line);
                if (parts.length >= 3 && parts[2].replace("\"", "").equalsIgnoreCase(email)) {
                    return true;
                }
            }
        }
        return false;
    }

    // --- Encoding/Decoding Helpers ---

    private String encodeEducations(List<Education> list) {
        if (list == null) return "";
        List<String> encoded = new ArrayList<>();
        for (Education e : list) {
            String courses = String.join("~", e.getAchievements());
            String start = e.getStartDate() != null ? e.getStartDate().toString() : "";
            String end = e.getEndDate() != null ? e.getEndDate().toString() : "";
            encoded.add(e.getDegree() + "|" + e.getSchool() + "|" + courses + "|" + start + "|" + end);
        }
        return String.join(";", encoded);
    }

    private String encodeExperiences(List<Experience> list) {
        if (list == null) return "";
        List<String> encoded = new ArrayList<>();
        for (Experience e : list) {
            String achievements = String.join("~", e.getAchievements());
            String start = e.getStartDate() != null ? e.getStartDate().toString() : "";
            String end = e.getEndDate() != null ? e.getEndDate().toString() : "";
            encoded.add(e.getRole() + "|" + e.getCompany() + "|" + achievements + "|" + start + "|" + end);
        }
        return String.join(";", encoded);
    }

    private String encodeSkills(List<Skill> list) {
        if (list == null) return "";
        List<String> encoded = new ArrayList<>();
        for (Skill s : list) {
            String achievements = String.join("~", s.getAchievements());
            String start = s.getStartDate() != null ? s.getStartDate().toString() : "";
            String end = s.getEndDate() != null ? s.getEndDate().toString() : "";
            encoded.add(s.getSkill() + "|" + s.getProficiency() + "|" + achievements + "|" + start + "|" + end);
        }
        return String.join(";", encoded);
    }

    // --- Decoding Section ---

    private List<Education> decodeEducations(String str) {
        List<Education> list = new ArrayList<>();
        for (String part : str.replace("\"", "").split(";")) {
            if (part.isBlank()) continue;
            String[] f = part.split("\\|");
            String degree = f.length > 0 ? f[0] : "";
            String school = f.length > 1 ? f[1] : "";
            ArrayList<String> courses = new ArrayList<>();
            if (f.length > 2 && !f[2].isBlank()) {
                courses = new ArrayList<>(List.of(f[2].split("~")));
            }
            LocalDate start = (f.length > 3 && !f[3].isBlank()) ? LocalDate.parse(f[3]) : null;
            LocalDate end = (f.length > 4 && !f[4].isBlank()) ? LocalDate.parse(f[4]) : null;
            list.add(new Education(start, end, courses, degree, school));
        }
        return list;
    }

    private List<Experience> decodeExperiences(String str) {
        List<Experience> list = new ArrayList<>();
        for (String part : str.replace("\"", "").split(";")) {
            if (part.isBlank()) continue;
            String[] f = part.split("\\|");
            String role = f.length > 0 ? f[0] : "";
            String company = f.length > 1 ? f[1] : "";
            ArrayList<String> achievements = new ArrayList<>();
            if (f.length > 2 && !f[2].isBlank()) {
                achievements = new ArrayList<>(List.of(f[2].split("~")));
            }
            LocalDate start = (f.length > 3 && !f[3].isBlank()) ? LocalDate.parse(f[3]) : null;
            LocalDate end = (f.length > 4 && !f[4].isBlank()) ? LocalDate.parse(f[4]) : null;
            list.add(new Experience(start, end, achievements, role, company));
        }
        return list;
    }

    private List<Skill> decodeSkills(String str) {
        List<Skill> list = new ArrayList<>();
        for (String part : str.replace("\"", "").split(";")) {
            if (part.isBlank()) continue;
            String[] f = part.split("\\|");
            String skill = f.length > 0 ? f[0] : "";
            String proficiency = f.length > 1 ? f[1] : "";
            ArrayList<String> achievements = new ArrayList<>();
            if (f.length > 2 && !f[2].isBlank()) {
                achievements = new ArrayList<>(List.of(f[2].split("~")));
            }
            LocalDate start = (f.length > 3 && !f[3].isBlank()) ? LocalDate.parse(f[3]) : null;
            LocalDate end = (f.length > 4 && !f[4].isBlank()) ? LocalDate.parse(f[4]) : null;
            list.add(new Skill(start, achievements, skill, proficiency));
        }
        return list;
    }

    // --- Utils ---
    private String[] splitCsvLine(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') inQuotes = !inQuotes;
            else if (c == ',' && !inQuotes) {
                tokens.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString());
        return tokens.toArray(new String[0]);
    }

    // --- For Employers ---------------------------------------------------------------------------------------

    public void saveEmployer(Employer employer) {



    }

}