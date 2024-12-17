
import java.util.*;
public class Course {
    public static Scanner scan = new Scanner(System.in);
    private ArrayList<Feedback<?>> feedbacks = new ArrayList<>();
    private String courseCode;
    private String courseTitle;
    private int credits;
    private int semester;
    private String syllabus;
    private String timings;
    private Professor professor;
    private ArrayList<Course> prerequisiteCourses = new ArrayList<Course>();
    private ArrayList<Student> enrolledStudents = new ArrayList<Student>();
    static ArrayList<Course> courses = new ArrayList<Course>();
    private ArrayList<TA> taList = new ArrayList<>();

    static{
        new Course("CSE101", "Introduction To Programming", 4, 1);
        // c1.setProfessor("MD Shad");
        new Course("MTH101", "Linear Algebra", 4, 1);
        // c2.setProfessor("Subhajit");
        new Course("CSE102", "Data Structures and Algorithms", 4, 2);
        // c3.setProfessor("Debarka");
        new Course("MTH201", "Probability and Statistics", 4, 2);
        // c4.setProfessor("Sanjit");
    }

    public ArrayList<TA> getTaList() {
        return taList;
    }
    
    public ArrayList<Feedback<?>> getFeedbacks() {
        return feedbacks;
    }

    public Course(String _courseCode, String _courseTitle, int _credits, int _semester){
        this.courseCode = _courseCode;
        this.courseTitle = _courseTitle;
        this.credits = _credits;
        this.semester = _semester;
        this.professor = null;
        courses.add(this);
    }

    public <T> void addFeedback(Feedback<T> feedback){
        feedbacks.add(feedback);
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getSemester() {
        return semester;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public void viewEnrolledStudents(){
        if (enrolledStudents.isEmpty()) {
            System.out.println("No Students Enrolled in the course.");
            return;
        }

        System.out.println("Assigned Students: ");
        for (Student student : this.enrolledStudents) {
            student.studentInfo();
        }
        System.out.println();
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }


    public void courseInfo() {
        System.out.println(courseCode + " : " + courseTitle + ", {credits: " + credits + ", semester: " + semester +"}");
    }

    public void courseDescription(){
        String name;
        if (professor == null) {
            name = "null";
        }
        else{
            name = professor.getName();
        }
        System.out.println(courseCode + " : " + courseTitle + " by \"" + name + "\"\nDetails: {credits: " + credits + ", semester: " + semester + ", syllabus: " + getSyllabus() + ", timings: " + getTimings() + "}");
    }


    public void setProfessor(Professor prof) {
        this.professor = prof;
    }

    public Professor getProfessor() {
        return professor;
    }

    public ArrayList<Course> getPrerequisiteCourses() {
        return prerequisiteCourses;
    }

    public void showPrerequisite(){
        for (Course course : prerequisiteCourses) {
            course.courseInfo();
        }
    }
}
