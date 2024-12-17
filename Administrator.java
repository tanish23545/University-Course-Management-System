import java.util.ArrayList;
import java.util.Scanner;

public class Administrator extends User {

    static Scanner scan = new Scanner(System.in);
    public static ArrayList<Administrator> administrators = new ArrayList<>();

    static {
        new Administrator("Admin", "admin", "admin123");
    }

    public Administrator(String name, String id, String pass) {
        super(name, id, pass);
        administrators.add(this);
    }

    public static void login() {
        while (true) {
            System.out.print("Enter admin id: ");
            String id = scan.nextLine();
            System.out.print("Enter password: ");
            String password = scan.nextLine();

            boolean isOkay = false;
            Administrator a = null;

            for (Administrator admin : administrators) {
                if (admin.getId().equals(id) && admin.getPass().equals(password)) {
                    isOkay = true;
                    a = admin;
                    break;
                }
            }

            try{
                if (isOkay) {
                    a.menu();
                    return;
                }
                else {
                    throw new InvalidLoginException("Invalid administrator ID or password.");
                }
            }
            catch (InvalidLoginException e){
                System.out.println(e.getMessage()); // Print the exception message
                System.out.println("1. Try again?\n0. Exit (Press any other key)");
                System.out.print("Your choice --> ");
                int choice = scan.nextInt();
                scan.nextLine();
                if (choice != 1) {
                    Main.welcome(); // Return to the main welcome screen
                    break;
                }
            }
        }
    }
    

    public void menu() {
        while (true) {
            System.out.println("Administrator Menu:");
            System.out.println("1. Manage Course Catalog");
            System.out.println("2. Assign Professors to Courses");
            System.out.println("3. Manage Student Records");
            System.out.println("0. Logout");

            System.out.print("Your choice --> ");
            int choice = scan.nextInt();
            scan.nextLine();

            if (choice == 1) {
                manageCourseCatalog();
            } else if (choice == 2) {
                assignProfessorToCourse();
            } else if (choice == 3) {
                manageStudentRecords();
            } else if (choice == 0) {
                Main.welcome();
                break;
            }
        }
    }

    public void manageCourseCatalog() {
        while (true) {
            System.out.println("1. View Courses");
            System.out.println("2. Add Course");
            System.out.println("3. Delete Course");
            System.out.println("0. Back");
            System.out.print("Your choice --> ");
            int choice = scan.nextInt();
            scan.nextLine();

            if (choice == 1) {
                viewCourses();
            } else if (choice == 2) {
                addCourse();
            } else if (choice == 3) {
                deleteCourse();
            } else{
                break;
            }
        }
    }

    public void viewCourses() {
        if (Course.courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            for (Course course : Course.courses) {
                course.courseInfo();
            }
        }
    }

    public void addCourse() {
        System.out.print("Enter Course Code: ");
        String courseCode = scan.nextLine();
        System.out.print("Enter Course Title: ");
        String courseTitle = scan.nextLine();
        System.out.print("Enter Credits: ");
        int credits = scan.nextInt();
        System.out.print("Enter Semester: ");
        int semester = scan.nextInt();
        scan.nextLine();
        new Course(courseCode, courseTitle, credits, semester);
        System.out.println("Course added successfully!");
    }

    public void deleteCourse() {
        System.out.print("Enter Course Code to delete: ");
        String courseCode = scan.nextLine();
        for (Course course : Course.courses) {
            if (course.getCourseCode().equals(courseCode)) {
                Course.courses.remove(course);
                System.out.println("Course removed successfully!");
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public void assignProfessorToCourse() {
        System.out.print("Enter Professor Name: ");
        String profName = scan.nextLine();
        for (Professor prof : Professor.professors) {
            if (prof.getName().equals(profName)) {
                System.out.print("Enter Course Code: ");
                String courseCode = scan.nextLine();
                for (Course c : Course.courses) {
                    if (c.getCourseCode().equals(courseCode)) {
                        if (prof.getAssignedCourses().contains(c) ) {
                            System.out.println("Course is already assigned");
                            return;
                        }

                        prof.assignCourse(c);
                        System.out.println(prof.getName() + " is Assigned to Course " + c.getCourseTitle());
                        return;
                    }
                }
                System.out.println("Course not found");
                return;
            }
        }
        System.out.println("Professor not found");
    }


    public void manageStudentRecords() {
        System.out.print("Enter Student ID: ");
        String studentId = scan.nextLine();
        Student student = null;
        for (Student stud : Student.students) {
            if (stud.getId().equals(studentId)) {
                student = stud;
                break;
            }
        }

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        while (true) {
            System.out.println("1. View Registered Courses");
            System.out.println("2. Update Student Semester");
            System.out.println("3. Show Grades");
            System.out.println("4. Assign or Update Grades");
            System.out.println("5. Complains");
            System.out.println("0. Back");
            System.out.print("Your choice --> ");
            int choice = scan.nextInt();
            scan.nextLine();

            if (choice == 1) {
                student.viewRegisteredCourses();
            } else if (choice == 2) {
                System.out.print("Enter new semester: ");
                int newSemester = scan.nextInt();
                scan.nextLine();
                student.setCurrentSemester(newSemester);
                System.out.println("Semester updated successfully.");
            }

            if (choice == 3) {

                student.viewGrades();
            }
            else if (choice == 4) {

                System.out.print("Enter Course Code: ");
                String courseCode = scan.nextLine();

                Course course = null;
                for (Course c : student.getRegisteredCourses()) {
                    if (c.getCourseCode().equals(courseCode)) {
                        course = c;
                        break;
                    }
                }

                if (course == null) {
                    System.out.println("Student is not enrolled in this course.");
                    return;
                }

                System.out.print("Enter Grade: ");
                Double grade = scan.nextDouble();
                student.addGrade(course, grade);
                System.out.println("Grade assigned/updated successfully.");
                student.setCgpa();
            }

            else if (choice == 5) {
                student.showComplain();
                System.out.println("1. Resolve Complain");
                System.out.println("0. Back");
                int ch = scan.nextInt();
                if (ch == 1) {
                    student.setComplainStatus(true);
                }
            }

            else if (choice == 0) {
                break;
            }
        }
    }


}
