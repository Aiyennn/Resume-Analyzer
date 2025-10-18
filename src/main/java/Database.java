import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.*;

public class Database {

    // Main example of abstraction I have no idea how it works

    private final String employerFile;
    private final String jobSeekerFile;

    public Database(String employerFile, String jobSeekerFile) {

        this.employerFile = employerFile;
        this.jobSeekerFile = jobSeekerFile;

        try {
            File employerCSV = new File(employerFile);
            File jobSeekerCSV = new File(jobSeekerFile);

            System.out.println("------------------------------------------------------");

            if (!employerCSV.exists()) {
                try (FileWriter writer = new FileWriter(employerCSV)) {
                    writer.write("Name,Email,Password,JobDescription\n");
                }
                System.out.println("📁 Created file: " + employerFile);
            } else {
                System.out.println("✔ Found existing file: " + employerFile);
            }

            if (!jobSeekerCSV.exists()) {
                try (FileWriter writer = new FileWriter(jobSeekerCSV)) {
                    writer.write("Name,Email,Password,Skills,Education,Experience\n");
                }
                System.out.println("📁 Created file: " + jobSeekerFile);
            } else {
                System.out.println("✔ Found existing file: " + jobSeekerFile);
            }

            System.out.println("------------------------------------------------------");

        } catch (IOException e) {
            System.err.println("Error checking or creating CSV files: " + e.getMessage());
        }
    }

    // Fix combine save and update if possible
    public void saveJobSeeker(JobSeeker user) throws IOException {

//        System.out.println("User: " + user.getName() + " Has been saved"); | For debugging
        try (PrintWriter writer = new PrintWriter(new FileWriter(jobSeekerFile, true))) {

            String[] encoded = encodeResumeData(user);
            writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                    user.getName(), user.getEmail(), user.getPassword(),
                    // education, experience, skills

                    // skill. education, experience
                    encoded[2], encoded[0], encoded[1]);

        }
    }

    // /!\ Ad Hoc Solution fix when have time
    public void updateJobSeeker(JobSeeker user) throws IOException {

        File inputFile = new File(jobSeekerFile);
        File tempFile = new File(jobSeekerFile + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            boolean isHeader = true;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    writer.println(line);
                    isHeader = false;
                    continue;
                }

                String[] parts = splitCsvLine(line);
                if (parts.length >= 3) {
                    String currentEmail = parts[1].replace("\"", "");

                    if (currentEmail.equalsIgnoreCase(user.getEmail())) {

                        String[] encoded = encodeResumeData(user);
                        writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                                user.getName(), user.getEmail(), user.getPassword(),
                                encoded[2], encoded[0], encoded[1]);
                        found = true;

                    } else {
                        writer.println(line);
                    }
                } else {
                    writer.println(line);
                }
            }

            if (!found) {
                throw new IOException("Job seeker with email " + user.getEmail() + " not found");
            }
        }

        // Replace original file with temp file safely
        Files.move(tempFile.toPath(), inputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

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

                String currentEmail = parts[1].replace("\"", "");
                if (currentEmail.equalsIgnoreCase(email)) {

                    String name = parts[0].replace("\"", "");
                    String password = parts[2].replace("\"", "");

                    List<Education> educations = decodeEducations(parts[3]);
                    List<Experience> experiences = decodeExperiences(parts[4]);
                    List<Skill> skills = decodeSkills(parts[5]);

                    Resume resume = new Resume(educations, experiences, skills);
                    return new JobSeeker(name, currentEmail, password, resume);
                }
            }
        }
        return null;
    }


    // Swap the name into job seeeker for clarity
    public boolean jobSeekerExists(String email) throws IOException {

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

    private String[] encodeResumeData(JobSeeker user) {

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
        return new String[]{educations, experiences, skills};
    }

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

    // Fix => Combine save and update if possible
    public void saveEmployer(Employer employer) {

        System.out.println("Save employer called");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(employerFile, true))) {
            // Flatten JobDescription list with null checks
            ArrayList<JobDescription> jdList = employer.getJobDescriptions();
            List<String> flattenedJDs = new ArrayList<>();

            // Last commit refactored code
            String combinedJDs = flattenJobDescription(employer.getJobDescriptions());
            // Last commit refactored code

            // Flatten Employer with null checks
            String name = employer.getName() != null ? employer.getName() : "";
            String email = employer.getEmail() != null ? employer.getEmail() : "";
            String password = employer.getPassword() != null ? employer.getPassword() : "";

            String line = name + "," + email + "," + password + "," + combinedJDs;

            writer.write(line);
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ad hoc solution fix later => Nanuyni
    public void updateEmployer(Employer employer) {

        System.out.println("Employer Updated: ");
        System.out.println(employer.name);

        File tempFile = new File("temp_employer_file.txt");
        File inputFile = new File(employerFile);

        try (BufferedReader reader = new BufferedReader(new FileReader(employerFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 4); // split into 4 parts max

                if (parts.length >= 3 && parts[1].equals(employer.getEmail())) {


                    // This is the employer to update, flatten it like saveEmployer
                    ArrayList<JobDescription> jdList = employer.getJobDescriptions();
                    List<String> flattenedJDs = new ArrayList<>();

                    // Last commit refactored code
                    String combinedJDs = flattenJobDescription(employer.getJobDescriptions());
                    // Las commit refactored code

                    String name = employer.getName() != null ? employer.getName() : "";
                    String email = employer.getEmail() != null ? employer.getEmail() : "";
                    String password = employer.getPassword() != null ? employer.getPassword() : "";

                    line = name + "," + email + "," + password + "," + combinedJDs;
                }

                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (!inputFile.delete()) {
            System.err.println("Could not delete original file");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temp file to original file");
        }

    }

    public Employer getEmployerByEmail(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(employerFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 4); // 4 parts max

                // Handle incomplete lines
                if (parts.length < 3) continue; // At minimum need name, password, email

                String name = parts[0];
                String mail = parts[1];
                String pwd = parts[2];

                String jdString = parts.length >= 4 ? parts[3] : ""; // Handle missing job descriptions

                if (mail.equals(email) && pwd.equals(password)) {
                    ArrayList<JobDescription> jdList = new ArrayList<>();

                    // Only parse job descriptions if they exist
                    if (!jdString.isEmpty()) {
                        String[] jdArray = jdString.split("#");
                        for (String jdItem : jdArray) {
                            if (jdItem.isEmpty()) continue; // Skip empty entries

                            String[] jdParts = jdItem.split("\\|", 4);

                            // Handle incomplete job description data
                            String title = jdParts.length > 0 ? jdParts[0] : "";
                            ArrayList<String> skills = jdParts.length > 1 && !jdParts[1].isEmpty()
                                    ? new ArrayList<>(Arrays.asList(jdParts[1].split(";")))
                                    : new ArrayList<>();
                            ArrayList<String> education = jdParts.length > 2 && !jdParts[2].isEmpty()
                                    ? new ArrayList<>(Arrays.asList(jdParts[2].split(";")))
                                    : new ArrayList<>();
                            ArrayList<String> experience = jdParts.length > 3 && !jdParts[3].isEmpty()
                                    ? new ArrayList<>(Arrays.asList(jdParts[3].split(";")))
                                    : new ArrayList<>();

                            jdList.add(new JobDescription(title, skills, education, experience));
                        }
                    }

                    Employer employer = new Employer(name, mail, pwd, jdList);
                    return employer;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // not found
    }

    public boolean employerExists(String email) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(employerFile))) {
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

    // [Fragile] please handle with care band-aid solution
    // Fix when there's time left
    public List<JobDescription> parseJobDescriptions() {

        List<JobDescription> jobList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(employerFile))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",", 4);
                if (parts.length < 4) continue;

                String jobData = parts[3].replace("\"", "");

                if (jobData.trim().isEmpty()) continue;

                String[] jobEntries = jobData.split("\\|\\|");

                for (String jobEntry : jobEntries) {
                    jobEntry = jobEntry.trim();
                    if (jobEntry.isEmpty()) continue;

                    jobEntry = jobEntry.replaceAll("^\\|+", "").trim();

                    jobEntry = jobEntry.replaceAll("#+", "").trim();

                    String[] jobParts = jobEntry.split("\\|");
                    if (jobParts.length == 0 || jobParts[0].trim().isEmpty()) continue;

                    String title = jobParts[0].trim();

                    ArrayList<String> skills = new ArrayList<>();
                    ArrayList<String> experience = new ArrayList<>();
                    ArrayList<String> education = new ArrayList<>();

                    if (jobParts.length > 1 && !jobParts[1].trim().isEmpty()) {
                        for (String s : jobParts[1].split(";")) {
                            if (!s.trim().isEmpty()) skills.add(s.trim());
                        }
                    }

                    if (jobParts.length > 2 && !jobParts[2].trim().isEmpty()) {
                        for (String e : jobParts[2].split(";")) {
                            if (!e.trim().isEmpty()) experience.add(e.trim());
                        }
                    }

                    if (jobParts.length > 3 && !jobParts[3].trim().isEmpty()) {
                        for (String ed : jobParts[3].split(";")) {
                            if (!ed.trim().isEmpty()) education.add(ed.trim());
                        }
                    }

                    System.out.println("Parsed job: " + title + " | skills=" + skills);

                    jobList.add(new JobDescription(title, skills, experience, education));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Here's the job list: " + jobList);
        return jobList;
    }


    private String flattenJobDescription(List<JobDescription> jdList) {

        if (jdList == null) return "";
        List<String> flattened = new ArrayList<>();

        for (JobDescription jd : jdList) {
            if (jd == null) continue;
            String skills = jd.getSkillQualification() != null ? String.join(";", jd.getSkillQualification()) : "";
            String education = jd.getEducationQualification() != null ? String.join(";", jd.getEducationQualification()) : "";
            String experience = jd.getExperienceQualification() != null ? String.join(";", jd.getExperienceQualification()) : "";
            String title = jd.getTitle() != null ? jd.getTitle() : "";
            flattened.add(title + "|" + skills + "|" + education + "|" + experience);
        }

        return String.join("#", flattened);
    }

}