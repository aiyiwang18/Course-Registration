package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Users.Admin;
import Users.Students;
import files.Courses;
import files.Readers;
 

public class Main {
	//checking if user is Admin or Student 
	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(System.in);
        List<Courses> courseList = new ArrayList<>();
        String fileName = "/Users/tianyongwang/Documents/MyUniversityCourses.csv";

        // Read courses once outside the loop
        courseList = Readers.readCoursesFromCSV(fileName);

        while (true) {
            System.out.println("Are you an Admin or a Student? (Type 'exit' to quit)");
            String userType = scanner.nextLine().trim().toLowerCase();

            // Exit the loop
            if (userType.equals("exit")) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            }

            // Admin logic
            if (userType.equals("admin")) {
                System.out.println("Enter Username: ");
                String adminUsername = scanner.nextLine();
                System.out.println("Enter Password: ");
                String adminPassword = scanner.nextLine();

                if (adminUsername.equals("Admin") && adminPassword.equals("Admin001")) {
                    handleAdminMenu(scanner, courseList);
                } else {
                    System.out.println("Invalid Admin credentials.");
                }

            // Student logic
            } else if (userType.equals("student")) {
                 
                handleStudentMenu(scanner, courseList);

            } else {
                System.out.println("Invalid user type. Please try again.");
            }
        }

        scanner.close();
	}
			
		
		//handles admin method 
		public static void handleAdminMenu(Scanner scanner, List<Courses> courseList) {
			Admin admin = new Admin("Admin", "Admin001", "John", "Doe", "admin@university.com"); 
			boolean exit = false; 
			
			while(!exit) {
				System.out.println("Admin Menu: ");
				System.out.println("1. Create Course");
				System.out.println("2. Delete Course");
				System.out.println("3. Edit Course");
				System.out.println("4. Dsiplay information for a given course");
				System.out.println("5. Register a student.");
				System.out.println("6. View all courses");
				System.out.println("7. View all courses that are FULL"); 
				System.out.println("8. Write to a file the list of courses that are FULL");
				System.out.println("9. View the names of the students being registered is being registered on");
				System.out.println("10. Exit");
				
				int choice = scanner.nextInt();
				scanner.nextLine();
				
				switch(choice) {
					case 1: //create course
						System.out.println("Enter course name, ID, max Students, instructor, section, and location: ");
		                String name = scanner.nextLine();
		                String id = scanner.nextLine();
		                int maxStudents = scanner.nextInt(); 
		                scanner.nextLine();  
		                String instructor = scanner.nextLine();
		                int section = scanner.nextInt();
		                scanner.nextLine();  
		                String location = scanner.nextLine();
		                admin.create(courseList, name, id, maxStudents, instructor, section, location); // Use admin instance
		                System.out.println("Course created successfully.");
		                break; 
					case 2: //delete course
						System.out.println("Enter the course name to delete: ");
						String delete = scanner.nextLine();
						admin.delete(courseList, delete);
						System.out.println("Course deleted successfully.");
						break ;
					
					case 3: //edit course
						System.out.println("Enter the course ID to edit: ");
						String edit = scanner.nextLine();
						admin.edit(courseList, edit);
						break; 
					case 4://display information for a given course 
						System.out.println("Enter the course ID: ");
						String courseId = scanner.nextLine().trim();
						
						boolean found = false; 
						for(Courses course : courseList) {
							if(course.getCourseID().equalsIgnoreCase(courseId)) {
								System.out.println("Course Name: " + course.getCourseName());
		                        System.out.println("Course ID: " + course.getCourseID());
		                        System.out.println("Maximum Students: " + course.getMaximumStudents());
		                        System.out.println("Current Students: " + course.getCurrentStudents());
		                        System.out.println("List of Names: " + course.getListOfNames());
		                        System.out.println("Course Instructor: " + course.getCourseInstructor());
		                        System.out.println("Course Section Number: " + course.getCourseSectionNumber());
		                        System.out.println("Course Location: " + course.getCourseLocation());
		                        System.out.println("List of Registered Students: " + course.getListOfNames());
		                        found = true;
		                        break;
							}
						}
						 
					case 5: // Register a student
					    try {
					        // Prompt for inputs
					        System.out.println("Enter course name:");
					        String coursename = scanner.nextLine().trim();
					        if (coursename.isEmpty()) {
					            System.out.println("Course name cannot be empty.");
					            break;
					        }

					        System.out.println("Enter section number:");
					        if (!scanner.hasNextInt()) {
					            System.out.println("Invalid section number. Please enter a valid integer.");
					            scanner.nextLine(); // Clear invalid input
					            break;
					        }
					        int sectionNumber = scanner.nextInt();
					        scanner.nextLine(); // Consume newline after integer input

					        System.out.println("Enter student name:");
					        String studentName = scanner.nextLine().trim();
					        if (studentName.isEmpty()) {
					            System.out.println("Student name cannot be empty.");
					            break;
					        }

					        // Attempt to register the student
					        boolean exist = false;
					        for (Courses course : courseList) {
					            if (course.getCourseName().equalsIgnoreCase(coursename)
					                    && course.getCourseSectionNumber() == sectionNumber) {
					                found = true;
					                if (course.getCurrentStudents() < course.getMaximumStudents()) {
					                    admin.register(courseList, coursename, sectionNumber, studentName);
					                    System.out.println("Student " + studentName + " successfully registered.");
					                } else {
					                    System.out.println("The course " + coursename + " (Section " + sectionNumber + ") is already full.");
					                }
					                break;
					            }
					        }

					        if (!exist) {
					            System.out.println("Course " + coursename + " with section number " + sectionNumber + " not found.");
					        }
					    } catch (InputMismatchException e) {
					        System.out.println("Invalid input. Please ensure all entries are in the correct format.");
					        scanner.nextLine(); // Clear invalid input
					    }
					    break;

					case 6: //view all courses 
						if (courseList.isEmpty()) {
					        System.out.println("No courses available to view.");
					    } else {
					        System.out.println("List of courses:");
					        for (Courses course : courseList) {
					            System.out.println("Course Name: " + course.getCourseName() + 
					                               ", Course ID: " + course.getCourseID());
					        }
					    }
					    break;
					case 7: //view all courses full; 
						admin.viewFull(courseList); 
						break; 
					case 8: //write to a file courses 
						admin.writeFile(courseList); // Ensure writeFile handles its own messages
					    break;

					case 9: //view name of students in course
						System.out.println("Enter the course ID to view registered students: ");
					    String courseID = scanner.nextLine().trim();
					    admin.viewStudentsInCourse(courseList, courseID);
					    System.out.println("\nReturning to menu...");
					    break;
					case 10: 
						exit = true; 
						break; 
					default: 
						System.out.println("Invalide choice. Try again.");
				}
			}
			 
				 
			
		}
		
		public static void handleStudentMenu(Scanner scanner, List<Courses>courseList) {
			System.out.println("Enter username: "); 
		    String studentUsername = scanner.nextLine();
		    System.out.println("Enter password: "); 
		    String studentPassword = scanner.nextLine();
		    System.out.println("Enter first name:");
		    String firstName = scanner.nextLine();
		    System.out.println("Enter last name: ");
		    String lastName = scanner.nextLine();
		    System.out.println("Enter email: ");
		    String email = scanner.nextLine(); 
		    
		    Students student = new Students(studentUsername, studentPassword, firstName, lastName, email);
		    
		    boolean exit = false; 
		    while (!exit) {
		        System.out.println("\nStudent Menu");
		        System.out.println("1. View all courses");
		        System.out.println("2. View all courses that are not full");
		        System.out.println("3. Register on a course");
		        System.out.println("4. Withdraw from a course");
		        System.out.println("5. View all courses you are registered in");
		        System.out.println("6. Exit");
		        System.out.print("Enter your choice: ");
		        
		        int choice;
		        try {
		            choice = scanner.nextInt();
		            scanner.nextLine(); // clear newline
		        } catch (InputMismatchException e) {
		            System.out.println("Invalid input. Please enter a number between 1 and 6.");
		            scanner.nextLine(); // clear invalid input
		            continue;
		        }
		        
		        switch (choice) {
		            case 1:
		                student.view(courseList);
		                break;
		            case 2:
		                student.notFull(courseList);
		                break;
		            case 3:
		                System.out.println("Enter course name: ");
		                String courseToRegister = scanner.nextLine();
		                System.out.println("Enter course section number: ");
		                try {
		                    int sectionToRegister = scanner.nextInt();
		                    scanner.nextLine(); // clear newline
		                    student.registerCourse(courseList, courseToRegister, sectionToRegister, student.getFirstname());
		                } catch (InputMismatchException e) {
		                    System.out.println("Invalid section number. Please enter a valid number.");
		                    scanner.nextLine(); // clear invalid input
		                }
		                break;
		            case 4:
		                System.out.println("Enter course name to withdraw from: ");
		                String courseToWithdraw = scanner.nextLine();
		                student.withdraw(courseList, courseToWithdraw, student.getFirstname());
		                break;
		            case 5:
		                student.viewAll(courseList, student.getFirstname());
		                break;
		            case 6:
		                exit = true;
		                System.out.println("Exiting Student Menu...");
		                break;
		            default:
		                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
		        }
		    }
		}
	 
}
