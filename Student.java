import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Student extends User{

    static Scanner scan = new Scanner(System.in);

    public static ArrayList<Student> students = new ArrayList<Student>();
    protected ArrayList<Course> registeredCourses;
    protected ArrayList<Course> completedCourses;
    protected HashMap<Course, Double> grades = new HashMap<>();

    private int currentSemester;
    private Double cgpa;
    private String complain;
    private boolean complainStatus;

    static{
        new Student("Tanish", "001", "0000", 1);
        new Student("Vansh","002", "0000", 1);
        new Student("Saksham", "003", "0000", 2);
        new Student("Vidhush", "004", "0000", 2 );
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }
    public void setComplainStatus(boolean complainStatus) {
        this.complainStatus = complainStatus;
    }

    public ArrayList<Course> getCompletedCourses() {
        return completedCourses;
    }

    public HashMap<Course, Double> getGrades() {
        return grades;
    }

    public Double getGrade(Course course) {
        return grades.get(course); 
    }

    public void showComplain(){
        if (complainStatus){
            System.out.println("Complain : " + complain + "\nStatus : Resolved" );
            return;
        }
        System.out.println("Complain : " + complain + "\nStatus : Pending");
    }

    public Student(String _name, String _id, String _pass, int _currentSemester){
        super(_name, _id, _pass);
        this.currentSemester = _currentSemester;
        this.registeredCourses = new ArrayList<Course>();
        this.completedCourses = new ArrayList<Course>();
        students.add(this);
    }

    public void setComplain(){
        System.out.print("Enter Your Complain: ");
        String c = scan.nextLine();
        complain = c;
        System.out.println("Complain has been registered");
        complainStatus = false;

    }

    public static void login() {

        while (true) {
            System.out.print("Enter id: ");
            String id = scan.nextLine();
            System.out.print("Enter password: ");
            String password = scan.nextLine();

            boolean isOkay = false;
            Student s = null;

            for (Student student : students) {
                if (student.getId().equals(id) && student.getPass().equals(password)) {
                    isOkay = true;
                    s = student;
                    break;
                }
                
            }
            try{
                if (isOkay) {
                    if (s instanceof TA) {
                        ((TA) s).menu();
                        return;
                    }
                    else{
                        s.menu();
                        return;
                    }
                }
                else {
                    throw new InvalidLoginException("Invalid Student ID or password.");
                }
            }
            catch (InvalidLoginException e){
                System.out.println(e.getMessage());
                System.out.println("1. Try again?\n0. Exit (Press any other key)");
                System.out.print("Your choice --> ");
                int choice = scan.nextInt();
                scan.nextLine();
                if (choice != 1) {
                    Main.welcome();
                    break;
                }
            }

            System.out.println("Student not found");
            System.out.println("1. Try again?\n0. Exit (Press any other key)\n");
            System.out.print("Your choice --> ");
            int choice = scan.nextInt();
            System.out.println();
            scan.nextLine();


            if (choice != 1) {
                Main.welcome();
                break;
            }
        }
    }

    public void setCgpa() {
        double gradeSum = 0.0;
        int totalCourses = grades.size();

        for (Map.Entry<Course, Double> entry : grades.entrySet()) {
            double grade = entry.getValue();
            gradeSum += grade;
        }

        if (totalCourses > 0) {
            this.cgpa = gradeSum / totalCourses;
        } else {
            cgpa = 0.0;
        }
    }



    public static void signup() {
        System.out.print("Enter name: ");
        String name  = scan.nextLine();
        System.out.print("Enter email: ");
        String email = scan.nextLine();
        System.out.print("Enter password: ");
        String password = scan.nextLine();
        System.out.print("Enter semester: ");
        int semester = scan.nextInt();
        scan.nextLine();
        Student student = new Student(name, email, password, semester);
        System.out.println("Student added successfully!");
        System.out.println();
        student.menu();

    }

    public void menu(){
        while (true) {

            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. View Grades");
            System.out.println("4. Submit Complaint");
            System.out.println("5. View Registered Courses");
            System.out.println("6. Drop Course");
            System.out.println("7. Give Feedback");


            System.out.println("0. Logout");

            System.out.print("Your choice --> ");
            int choice = scan.nextInt();
            scan.nextLine();
            System.out.println();


            if (choice == 1) {
                viewAvailableCourses();
            }

            else if (choice == 2) {
                registerCourse();
            }

            else if (choice == 3) {
                viewGrades();
            }

            else if (choice == 4){
                System.out.println("1. Register Complain");
                System.out.println("2. Complain Status");
                System.out.print("Your choice --> ");
                int ch = scan.nextInt();
                System.out.println();
                scan.nextLine();
                if (ch == 1) {
                    setComplain();
                }
                else if (ch == 2) {
                    showComplain();
                }
            }

            else if (choice == 5) {

                viewRegisteredCourses();
            }

            else if (choice == 6) {
                System.out.print("Enter Course Code : ");
                String ch = scan.nextLine();
                for (Course course : registeredCourses) {
                    if (course.getCourseCode().equals(ch)) {
                        registeredCourses.remove(course);
                        System.out.println("Course Dropped Successfully");
                        return;
                    }
                }
                System.out.println("Course not Found");
            }

            else if (choice == 7) {
                giveFeedback();
            }

            else if (choice == 0) {
                Main.welcome();
                break;
            }
        }
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void giveFeedback() {
        System.out.print("Enter Course Code: ");
        String courseCode = scan.nextLine();

        for (Course course : completedCourses) {
            if (course.getCourseCode().equals(courseCode)) {
                System.out.print("1. Enter Rating");
                System.out.print("2. Enter Comment");
                System.out.print("Your choice --> ");
                int ch = scan.nextInt();
                scan.nextLine();

                if (ch == 1) {
                    System.out.print("Enter a numeric rating (1-5): ");
                    int rating = scan.nextInt();
                    scan.nextLine(); 
                    Feedback<Integer> numericFeedback = new Feedback<>(rating);
                    course.addFeedback(numericFeedback);
                } else if (ch == 2) {
                    System.out.print("Enter your text feedback: ");
                    String comment = scan.nextLine();
                    Feedback<String> textFeedback = new Feedback<>(comment);
                    course.addFeedback(textFeedback);
                }
                System.out.println("Feedback submitted for course: " + course.getCourseTitle());
                return;
            }
        }

        System.out.println("Course not found in completed courses.");
    }

    public void viewGrades() {
        if (grades.isEmpty()) {
            System.out.println("No grades available.");
        } else {
            System.out.println("Total CGPA: " + getCgpa());
            for (Map.Entry<Course, Double> entry : grades.entrySet()) {
                Course course = entry.getKey();
                Double grade = entry.getValue();
                System.out.println("Course: " + course.getCourseTitle() + " | Grade: " + grade);
            }
        }
    }

    public void viewRegisteredCourses(){
        if (registeredCourses.isEmpty()) {
            System.out.println("No Registered Courses yet");
            return;
        }
        System.out.println("Rgistered Courses: ");
        for (Course course : registeredCourses) {
            course.courseDescription();
        }
        System.out.println();

    }


    
    public void studentInfo() {
        System.out.println("Id: "+ id + ", Name: " + name + ", Semester: "  + currentSemester);
    }

    private boolean canRegister(Course course) {
        ArrayList<Course> prerequisites = course.getPrerequisiteCourses();
        if (prerequisites.isEmpty()) {
            if (this.getcurrentSemester() == course.getSemester()) {
                return true;
            }
            return false;
        }

        for (Course prereq : prerequisites) {
            boolean completed = false;
            for (Course completedCourse : completedCourses) {
                if (completedCourse.getCourseCode().equals(prereq)) {
                    completed = true;
                    break;
                }
            }
            if (!completed) {
                return false;
            }
        }
        return true;
    }

    public void registerCourse(){
        try{
            System.out.print("Enter the Course Code: ");
            String choice = scan.nextLine();
            for (Course course : Course.courses) {
                if (choice.equals(course.getCourseCode())) {
                    if (registeredCourses.contains(course)) {
                        throw new CourseFullException("You are already registered for this course.");
                    }
                    if (canRegister(course)){
                        registeredCourses.add(course);
                        course.getEnrolledStudents().add(this);
                        System.out.println("Course Added Successfully");
                        System.out.println();
                        return;
                    }

                }
            }
            System.out.println("Course Not Found");
        }
        catch (CourseFullException e){
            System.out.println(e.getMessage());
        }
    }


    public void viewAvailableCourses() {
        System.out.println("Available courses for your semester:");
        for (Course course : Course.courses) {
            if (canRegister(course)) {
                if (registeredCourses.contains(course)) {
                    continue;
                }
                course.courseInfo();
            }

        }
        System.out.println();

    }

    public int getcurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void addGrade(Course course, double grade) {
        grades.put(course, grade); 

        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            completedCourses.add(course);
            System.out.println("Course marked as completed: " + course.getCourseTitle());
        }
    }
}
