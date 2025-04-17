import java.util.ArrayList;
import java.util.List;

abstract class JobRole {
    abstract String getRoleName();
}

class SoftwareEngineer extends JobRole {
    public String getRoleName() {
        return "Software Engineer";
    }
}

class DataScientist extends JobRole {
    public String getRoleName() {
        return "Data Scientist";
    }
}

class ProductManager extends JobRole {
    public String getRoleName() {
        return "Product Manager";
    }
}

class Resume<T extends JobRole> {
    private String candidateName;
    private T jobRole;

    public Resume(String candidateName, T jobRole) {
        this.candidateName = candidateName;
        this.jobRole = jobRole;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public T getJobRole() {
        return jobRole;
    }
}

class ResumeProcessor {
    public static <T extends JobRole> void processResume(Resume<T> resume) {
        System.out.println("Processing resume of " + resume.getCandidateName() + " for role: " + resume.getJobRole().getRoleName());
    }

    public static void processAllRoles(List<? extends JobRole> roles) {
        for (JobRole role : roles) {
            System.out.println("Screening for: " + role.getRoleName());
        }
    }
}

public class ResumeScreeningSystem {
    public static void main(String[] args) {
        Resume<SoftwareEngineer> seResume = new Resume<>("Alice", new SoftwareEngineer());
        Resume<DataScientist> dsResume = new Resume<>("Bob", new DataScientist());
        Resume<ProductManager> pmResume = new Resume<>("Charlie", new ProductManager());

        ResumeProcessor.processResume(seResume);
        ResumeProcessor.processResume(dsResume);
        ResumeProcessor.processResume(pmResume);

        List<JobRole> roles = new ArrayList<>();
        roles.add(new SoftwareEngineer());
        roles.add(new DataScientist());
        roles.add(new ProductManager());
        ResumeProcessor.processAllRoles(roles);
    }
}