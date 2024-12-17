import java.util.Scanner;
public class Main {
    static Scanner scan = new Scanner(System.in);

    static void welcome(){

        while (true) {
            try{
                System.out.println("Welcome to the University Course Management System");
                System.out.println("1. Login as Student");
                System.out.println("2. Login as Professor");
                System.out.println("3. Login as Administrator");
                System.out.println("4. Signup as Student");
                System.out.println("5. Signup as Professor");
                System.out.println("0. Exit");

                System.out.print("Your choice --> ");
                int choice = scan.nextInt();
                scan.nextLine();

                if (choice == 1) {
                    Student.login();
                } else if (choice == 2) {
                    Professor.login();
                } else if (choice == 3) {
                    Administrator.login();
                } else if (choice == 4) {
                    Student.signup();
                } else if (choice == 5) {
                    Professor.signup();
                } else if (choice == 0) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid choice, try again.");
                }
            }
            catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.");
                scan.nextLine();
            }
        }


    }

    public static void main(String[] args) {


        welcome();

    }
}