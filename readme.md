# University Course Management System

This project implements a **University Course Management System**, where various user roles interact with the system to manage courses, student enrollments, grades, and feedback. The system is designed to support multiple types of users, such as students, professors, teaching assistants, and administrators, each with different privileges and functionalities.

## System Overview

The **University Course Management System** is designed to facilitate interactions between students, professors, administrators, and teaching assistants (TAs) in a university environment. The system allows for:

- **Course Registration**
- **Course Management**
- **Grading**
- **Feedback Submission**
- **Administrative Oversight**

The architecture follows a user-centric approach, where each user role has specific tasks and privileges. The system maintains a clear separation of concerns between user roles while providing core functionalities such as course registration, feedback submission, and student record management.

## Core Components

### User Roles

The system defines multiple user roles, each inheriting from a base `User` class. These roles include:

- **Student**: A student can:
  - Register for courses
  - View grades
  - Provide feedback on completed courses
  - File complaints

- **Teaching Assistant (TA)**: A student who is also assigned as a TA for a course gains additional privileges, such as:
  - Viewing and managing grades for students in the assigned course

- **Professor**: Professors can:
  - Manage courses
  - View enrolled students
  - Assign grades
  - Appoint TAs

- **Administrator**: Administrators have the highest level of control, enabling them to:
  - Manage the entire course catalog
  - Assign professors to courses
  - Handle student records

### Course Management

Courses are central to the system, with each course having attributes such as course code, title, credits, and enrolled students. The system supports:

- **Course Registration**: Students can register for available courses based on prerequisites and semester eligibility.
- **Course Assignment**: Professors are assigned to courses, and TAs can be appointed to assist with specific courses.
- **Grading**: Professors and TAs can assign and update grades for students enrolled in their courses.

### Feedback System

The feedback system allows students to submit feedback for completed courses, providing either numeric ratings or textual comments. Professors can review this feedback to assess the course performance and student satisfaction.

## Exceptions

To ensure robust handling of invalid operations and error cases, the system includes custom exceptions such as:

- **InvalidLoginException**: Thrown when a user attempts to log in with incorrect credentials.
- **CourseAlreadyRegisteredException**: Thrown when a student attempts to register for a course they are already enrolled in.
- **CourseNotFoundException**: Thrown when a student tries to register for a non-existent course.

These exceptions ensure smooth flow control and user feedback when errors occur.

## Key Features

- **Role-based Access**: Each user role (student, professor, administrator, TA) has specific access and privileges within the system.
- **Course Registration and Management**: Students can register for courses, while professors can manage the courses they are assigned.
- **Grading and Feedback**: Professors and TAs can manage student grades, and students can submit feedback for completed courses.
- **Administrative Control**: Administrators manage the course catalog, assign professors, and handle student records, including updating student grades and resolving complaints.

## Running the System

### Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/university-course-management-system.git
   cd university-course-management-system
