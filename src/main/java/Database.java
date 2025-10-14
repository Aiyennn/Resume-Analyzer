import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Database {

    public static void saveUser(User user, String filename) throws IOException {
        boolean fileExists = new File(filename).exists();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            if (!fileExists) {
                writer.println("name,age,password,email,educations,experiences,skills");
            }

            String educations = encodeEducations(user.getResume().getEducations());
            String experiences = encodeExperiences(user.getResume().getExperiences());
            String skills = encodeSkills(user.getResume().getSkills());

            writer.printf("\"%s\",%d,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                    user.getName(), user.getAge(), user.getPassword(), user.getEmail(),
                    educations, experiences, skills);
        }
    }

    public static User getUserByEmail(String filename, String email) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean skipHeader = true;

            while ((line = reader.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                String[] parts = splitCsvLine(line);
                if (parts.length < 7) continue;

                String currentEmail = parts[3].replace("\"", "");
                if (currentEmail.equalsIgnoreCase(email)) {

                    String name = parts[0].replace("\"", "");
                    int age = Integer.parseInt(parts[1]);
                    String password = parts[2].replace("\"", "");

                    List<Education> educations = decodeEducations(parts[4]);
                    List<Experience> experiences = decodeExperiences(parts[5]);
                    List<Skill> skills = decodeSkills(parts[6]);

                    Resume resume = new Resume(educations, experiences, skills);
                    return new User(name, age, password, currentEmail, resume);
                }
            }
        }
        return null;
    }

    public static boolean userExists(String filename, String email) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean skipHeader = true;
            while ((line = reader.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                String[] parts = splitCsvLine(line);
                if (parts.length >= 4 && parts[3].replace("\"", "").equalsIgnoreCase(email)) {
                    return true;
                }
            }
        }
        return false;
    }

    // --- Encoding/Decoding Helpers ---

    private static String encodeEducations(List<Education> list) {
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

    private static String encodeExperiences(List<Experience> list) {
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

    private static String encodeSkills(List<Skill> list) {
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

    private static List<Education> decodeEducations(String str) {
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
            // Education constructor: (LocalDate startDate, LocalDate endDate, ArrayList<String> achievements, String degree, String school)
            list.add(new Education(start, end, courses, degree, school));
        }
        return list;
    }

    private static List<Experience> decodeExperiences(String str) {
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
            // Experience constructor: (LocalDate startDate, LocalDate endDate, ArrayList<String> achievements, String role, String company)
            list.add(new Experience(start, end, achievements, role, company));
        }
        return list;
    }

    private static List<Skill> decodeSkills(String str) {
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
            // Skill constructor: (LocalDate acquiredDate, ArrayList<String> achievements, String skill, String proficiency)
            list.add(new Skill(start, achievements, skill, proficiency));
        }
        return list;
    }

    // --- Utils ---
    private static String[] splitCsvLine(String line) {
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
}