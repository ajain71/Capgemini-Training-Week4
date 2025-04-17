import java.util.ArrayList;
import java.util.List;

abstract class CourseType {
    abstract String getEvaluationType();
}

class ExamCourse extends CourseType {
    public String getEvaluationType() {
        return "Exam-Based";
    }
}

class AssignmentCourse extends CourseType {
    public String getEvaluationType() {
        return "Assignment-Based";
    }
}

class ResearchCourse extends CourseType {
    public String getEvaluationType() {
        return "Research-Based";
    }
}

class Course<T extends CourseType> {
    private String courseName;
    private T courseType;

    public Course(String courseName, T courseType) {
        this.courseName = courseName;
        this.courseType = courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public T getCourseType() {
        return courseType;
    }
}

class CourseManager {
    public static void displayCourses(List<? extends CourseType> courseTypes) {
        for (CourseType type : courseTypes) {
            System.out.println(type.getEvaluationType());
        }
    }
}

public class UniversitySystem {
    public static void main(String[] args) {
        Course<ExamCourse> math = new Course<>("Mathematics", new ExamCourse());
        Course<AssignmentCourse> programming = new Course<>("Java Programming", new AssignmentCourse());
        Course<ResearchCourse> ai = new Course<>("AI Research", new ResearchCourse());

        List<Course<? extends CourseType>> courses = new ArrayList<>();
        courses.add(math);
        courses.add(programming);
        courses.add(ai);

        for (Course<? extends CourseType> course : courses) {
            System.out.println(course.getCourseName() + " | " + course.getCourseType().getEvaluationType());
        }

        List<CourseType> courseTypes = new ArrayList<>();
        courseTypes.add(new ExamCourse());
        courseTypes.add(new AssignmentCourse());
        courseTypes.add(new ResearchCourse());

        CourseManager.displayCourses(courseTypes);
    }
}