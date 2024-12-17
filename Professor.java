import java.util.ArrayList;
import java.util.Scanner;

public class Professor extends User {

    static Scanner scan = new Scanner(System.in);
    public static ArrayList<Professor> professors = new ArrayList<Professor>();
    private ArrayList<Course> assignedCourses;

    static {
        new Professor("MD Shad","001", "0000");
        new Professor("Subhajit", "002", "0000");
        new Professor("Debarka", "003", "0000");
        new Professor("Sanjit", "004", "0000");
    }

    public Professor(String name, String id, String pass) {
        super(name, id, pass);
        this.assignedCourses = new ArrayList<>();
        professors.add(this);
    }

    public void assignStudentAsTA(Course course, Student student) {
        TA ta = new TA(student, course);
        int index = course.getEnrolledStudents().indexOf(student);
        course.getEnrolledStudents().set(index, ta);
        index = Student.getStudents().indexOf(student);
        Student.getStudents().set(index, ta);
    }

    public ArrayList<Course> getAssignedCourses() {
        return assignedCourses;
    }
    
    public static void login() {
        while (true) {
            System.out.print("Enter id: ");
            String id = scan.nextLine();
            System.out.print("Enter password: ");
            String password = scan.nextLine();

            boolean isOkay = false;
            Professor p = null;

            for (Professor x : professors) {
                if (x.getId().equals(id) && x.getPass().equals(password)) {
                    isOkay = true;
                    p = x;
                    break;
                }
            }
            try{
                if (isOkay) {
                    p.menu();
                    return;
                }
                else {
                    throw new InvalidLoginException("Invalid Professor ID or password.");
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
        }
    }

    public static void signup() {
        System.out.print("Enter name: ");
        String name  = scan.nextLine();
        System.out.print("Enter id: ");
        String id = scan.nextLine();
        System.out.print("Enter password: ");
        String password = scan.nextLine();
        Professor professor = new Professor(name,id, password);
        System.out.println("Professor added successfully!");
        System.out.println();
        professor.menu();
    }

    // Professor menu to manage courses and view students
    public void menu() {
        while (true) {
            System.out.println("1. View Assigned Courses");
            System.out.println("2. Manage Course (Update Syllabus, Timings, etc.)");
            System.out.println("3. View Enrolled Students");
            System.out.println("0. Logout");

            System.out.print("Your choice --> ");
            int choice = scan.nextInt();
            System.out.println();
            scan.nextLine();

            if (choice == 1) {
                viewAssignedCourses();
            } else if (choice == 2) {
                manageCourse();
            } else if (choice == 3) {
                viewEnrolledStudents();
            } else if (choice == 0) {
                Main.welcome();
                break;
            }
        }
    }

    public void viewAssignedCourses() {
        if (assignedCourses.isEmpty()) {
            System.out.println("No Courses Assigned yet.");
            return;
        }

        System.out.println("Assigned courses: ");
        for (Course course : assignedCourses) {
            course.courseInfo();
        }
        System.out.println();
    }

    public void manageCourse() {
        if (assignedCourses.isEmpty()) {
            System.out.println("No courses to manage.");
            return;
        }

        System.out.println("Enter the Course Code to manage: ");
        String courseCode = scan.nextLine();

        for (Course course : assignedCourses) {
            if (courseCode.equals(course.getCourseCode())) {
                System.out.println("Managing course: " + course.getCourseCode());
                System.out.println("1. Update Syllabus");
                System.out.println("2. Update Class Timings");
                System.out.println("3. Update Credits");
                System.out.println("4. View Feedbacks");
                System.out.println("5. Make Course TA");
                System.out.println("0. Back");

                System.out.print("Your choice --> ");
                int option = scan.nextInt();
                scan.nextLine();

                if (option == 1) {
                    System.out.print("Enter new syllabus: ");
                    String newSyllabus = scan.nextLine();
                    course.setSyllabus(newSyllabus);
                    System.out.println("Syllabus updated successfully.");
                } else if (option == 2) {
                    System.out.print("Enter new class timings: ");
                    String newTimings = scan.nextLine();
                    course.setTimings(newTimings);
                    System.out.println("Timings updated successfully.");
                }
                else if (option == 3) {
                    System.out.print("Enter new Credits Points: ");
                    int newCredits = scan.nextInt();
                    course.setCredits(newCredits);
                    System.out.println("Credits updated successfully.");
                }
                else if (option == 4) {
                    if (course.getFeedbacks().isEmpty()) {
                        System.out.println("No Feedbacks Available");
                        return;
                    }
                    for (Feedback<?> feedback : course.getFeedbacks()) {
                        System.out.println(feedback.getFeedback());
                    }
                }
                else if (option == 5) {
                    course.viewEnrolledStudents();
                    if (course.getEnrolledStudents().isEmpty()) {
                        return;
                    }
                    System.out.println("Enter Student Code to make TA: ");
                    String id = scan.nextLine();
                    int flag = 0;
                    for (Student std : course.getEnrolledStudents()) {
                        if (std.getId().equals(id)) {
                            assignStudentAsTA(course, std);
                            System.out.println("Appointed " + std.getName() + " as TA for Course: " + course.getCourseTitle());
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        System.out.println("Student not Found");
                    }
                    
                }
                
                return;
            }
        }

        System.out.println("Course not found.");
    }

    public void viewEnrolledStudents() {
        System.out.print("Enter the Course code to view Enrolled Students: ");
        String choice = scan.nextLine();
        for (Course course : assignedCourses) {
            if (course.getCourseCode().equals(choice)) {
                course.viewEnrolledStudents();
                return;
            }
        }
        System.out.println("That Course is not Assigned");
    }

    public void assignCourse(Course course) {
        assignedCourses.add(course);
        course.setProfessor(this);
    }

}
