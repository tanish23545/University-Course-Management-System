import java.util.ArrayList;
import java.util.HashMap;

public class TA extends Student {
    private ArrayList<Course> assignedCourses;

    public TA(Student student, Course course) {
        super(student.getName(), student.getId(), student.getPass(), student.getcurrentSemester());
        this.registeredCourses = new ArrayList<>(student.getRegisteredCourses());
        this.completedCourses = new ArrayList<>(student.getCompletedCourses());
        this.grades = new HashMap<>(student.getGrades());
        this.assignedCourses = new ArrayList<>();
        assignedCourses.add(course);
        course.getTaList().add(this);
    }

    public void viewStudentGrades(Course course) {
        System.out.println("Grades for the course: " + course.getCourseTitle());
        if (course.getEnrolledStudents().isEmpty()) {
            System.out.println("No enrolled Students in Course");
            return;
        }
        
        for (Student student : course.getEnrolledStudents()) {
            Double grade = student.getGrade(course);
            System.out.println("Student: " + student.getName() + ", Grade: " + grade);
        }
    }

    public void assignOrUpdateGrade(Student student, Course course, double grade) {
        if (student.getRegisteredCourses().contains(course)) {
            student.addGrade(course, grade);
            student.setCgpa();
            System.out.println("Grade assigned/updated successfully for " + student.getName());
        } else {
            System.out.println("The student is not enrolled in this course.");
        }
    }

    private Course findCourseByCode(String courseCode) {
        for (Course course : Course.courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    @Override
    public void menu(){
        while (true) {

            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. View Grades");
            System.out.println("4. Submit Complaint");
            System.out.println("5. View Registered Courses");
            System.out.println("6. Drop Course");
            System.out.println("7. Give Feedback");
            System.out.println("8. View Student Grades (for a course)");
            System.out.println("9. Assign/Update Student Grades");


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
                for (Course course : getRegisteredCourses()) {
                    if (course.getCourseCode().equals(ch)) {
                        getRegisteredCourses().remove(course);
                        System.out.println("Course Dropped Successfully");
                        return;
                    }
                }
                System.out.println("Course not Found");
            }

            else if (choice == 7) {
                giveFeedback();
            }

            else if (choice == 8) {
                System.out.print("Enter Course Code: ");
                String courseCode = scan.nextLine();
                for (Course course : assignedCourses) {
                    if (course.getCourseCode().equals(courseCode)) {
                        viewStudentGrades(course);
                    }
                }
                System.out.println("Course not found."); 
            }
            
            else if (choice == 9) {
                System.out.print("Enter Course Code: ");
                String courseCode = scan.nextLine();
                Course course = findCourseByCode(courseCode);
                if (course != null) {
                    System.out.print("Enter Student ID: ");
                    String studentId = scan.nextLine();
                    int flag = 0;
                    for (Student student : course.getEnrolledStudents()) {
                        if (student.getId().equals(studentId)) {
                            flag = 1;
                            System.out.print("Enter grade: ");
                            double grade = scan.nextDouble();
                            assignOrUpdateGrade(student, course, grade);
                        }
                    }
                    if (flag == 0) {
                        System.out.println("Student not found.");
                    }
                }
                else {
                    System.out.println("Course not found.");
                }
            }
            else if (choice == 0) {
                Main.welcome();
                break;
            }
        }
    }
}