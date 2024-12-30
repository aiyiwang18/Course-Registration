package Users;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import files.Courses;
import interfaces.intfAdmin;

public class Admin extends Users implements intfAdmin{
	
	//constructor
	public Admin(String username, String password, String firstname, String lastname, String email) {
		super(username, password, firstname, lastname, email);
	}
	
	//create course
	public void create(List<Courses> courseList,String name,String id, int maxStudents, String instructor, 
			int section, String location) {
		//adding a new course object 
		Courses newCourse = new Courses(name, id, maxStudents, section, new ArrayList<>(), instructor, section, location);
		courseList.add(newCourse); 
	}
		
	//delete course
	public void delete(List<Courses> courseList, String name) {
		//iterate through the list 
		for(int i = 0; i< courseList.size(); i++) {
			courseList.remove(i);  
		}
	}
	
	//edit a course(anything other than course ID and name) 
	public void edit(List<Courses>courseList, String name) {
		Scanner scanner = new Scanner(System.in);

	    // Find the course to edit
	    Courses courseToEdit = null;
	    for (Courses course : courseList) {
	        if (course.getCourseID().equalsIgnoreCase(name)) {
	            courseToEdit = course;
	            break;
	        }
	    }

	    if (courseToEdit != null) {
	        System.out.println("Which field would you like to edit?");
	        System.out.println("1. Maximum Students");
	        System.out.println("2. Current Students");
	        System.out.println("3. Remove Student");
	        System.out.println("4. Add Student");
	        System.out.println("5. Course Instructor");
	        System.out.println("6. Course Section Number");
	        System.out.println("7. Course Location");

	        int choice = scanner.nextInt();
	        scanner.nextLine();

	        switch (choice) {
	            case 1:
	                System.out.println("Enter new Maximum Students: ");
	                int newMaxStudents = scanner.nextInt();
	                courseToEdit.setMaximumStudents(newMaxStudents);
	                System.out.println("Maximum students updated.");
	                break;
	            case 2:
	                System.out.println("Enter new Current Students: ");
	                int newCurrentStudents = scanner.nextInt();
	                courseToEdit.setCurrentStudents(newCurrentStudents);
	                System.out.println("Current students updated.");
	                break;
	            case 3:
	                System.out.println("Enter Student name to remove: ");
	                String removeStudent = scanner.nextLine();
	                courseToEdit.getListOfNames().remove(removeStudent);
	                System.out.println("Student removed.");
	                break;
	            case 4:
	                System.out.println("Enter new Student name: ");
	                String newStudent = scanner.nextLine();
	                courseToEdit.getListOfNames().add(newStudent);
	                System.out.println("Student added.");
	                break;
	            case 5:
	                System.out.println("Enter new Course Instructor Name: ");
	                String newInstructor = scanner.nextLine();
	                courseToEdit.setCourseInstructor(newInstructor);
	                System.out.println("Instructor updated.");
	                break;
	            case 6:
	                System.out.println("Enter new Section Number: ");
	                int newNumber = scanner.nextInt();
	                courseToEdit.setCourseSectionNumber(newNumber);
	                System.out.println("Section number updated.");
	                break;
	            case 7:
	                System.out.println("Enter new Location: ");
	                String newLocation = scanner.nextLine();
	                courseToEdit.setCourseLocation(newLocation);
	                System.out.println("Location updated.");
	                break;
	            default:
	                System.out.println("Invalid choice. No changes were made.");
	        }
	    } else {
	        System.out.println("Course ID not found. Please try again.");
	    }
		
		
	}
	
	//display information 
	public void display(List<Courses> courseList) {
		for(Courses course: courseList) {
			System.out.println("Course Name: " + course.getCourseName());
			System.out.println("Course ID: " + course.getCourseID());
			System.out.println("Maximum Students: " + course.getMaximumStudents());
			System.out.println("Current Students: " + course.getCurrentStudents());
			System.out.println("List of Names: " + course.getListOfNames());
			System.out.println("Course Instructor: " + course.getCourseInstructor());
			System.out.println("Course Section Number: " + course.getCourseSectionNumber());
			System.out.println("Course Location: " + course.getCourseLocation());
			System.out.println("List of Registered Students: " + course.getListOfNames());
		}
	}
	
	//register a student
	public void register(List<Courses> courseList, String courses, int section, String studentName) {
		for(Courses course: courseList) {
			if(course.getCourseName().equalsIgnoreCase(courses) && course.getCourseSectionNumber()==section) {
				if(course.getCurrentStudents()>= course.getMaximumStudents()) {
					System.out.println("Course is full. Registration failed");
					return;
				}
			}
			if (course.getListOfNames().contains(studentName)) {
				System.out.println("Student is already registered in this course");
				return;
			}
			course.getListOfNames().add(studentName);
			course.setCurrentStudents((course.getCurrentStudents() + 1));
			System.out.println("Student " + studentName + " successfully registered for the course " + courses);
			return; 
		}
	}
	
	//view all courses: list of course name, course id, number of students registered,
	//maximum number of students allowed to be registered. 
	public void view(List<Courses> courseList) {
		for(Courses courses: courseList) {
			System.out.println("Course Name: " + courses.getCourseName());
			System.out.println("Course ID: " + courses.getCourseID()); 
			System.out.println("Students Registered" + courses.getCurrentStudents());
			System.out.println("Maximum Students: " + courses.getMaximumStudents());
			System.out.println(); 
		}
	}

	//view all courses that are full
	public void viewFull(List<Courses>courseList) {
		boolean hasFullCourses = false; 
		
		System.out.println("List of Full Courses: ");
		for (Courses course : courseList) {
			if(course.getCurrentStudents() >= course.getMaximumStudents()) {
				System.out.println("Course Name: " + course.getCourseName()
				+ ", Course ID: " + course.getCourseID()
				+ ", Section: " + course.getCourseSectionNumber()
				+", Instructor: " + course.getCourseInstructor());
				hasFullCourses = true;
			}
		}
		if(!hasFullCourses) {
			System.out.println("No courses are currently full,"); 
		}
	}
	
	//view students in a specific course
	public void viewStudentsInCourse(List<Courses> courseList, String courseID) {
	    boolean found = false;

	    for (Courses course : courseList) {
	        if (course.getCourseID().equalsIgnoreCase(courseID)) {
	            found = true;
	            if (course.getListOfNames().isEmpty()) {
	                System.out.println("No students are currently registered for this course.");
	            } else {
	                System.out.println("List of registered students:");
	                for (String student : course.getListOfNames()) {
	                    System.out.println(student);
	                }
	            }
	            break;
	        }
	    }

	    if (!found) {
	        System.out.println("Course with ID " + courseID + " not found.");
	    }
	}

	
	public void courseGivenStudent(List<Courses> courseList, String name) {
	    boolean hasCourses = false;

	    System.out.println("Courses for student " + name + ":");
	    for (Courses course : courseList) {
	        if (course.getListOfNames().contains(name)) {
	            System.out.println(course.getCourseName());
	            hasCourses = true;
	        }
	    }

	    if (!hasCourses) {
	        System.out.println("No courses found for student " + name + ".");
	    }
	}
	
	//sort courses based on the current number of students registers
	//collection method source: chatGPT
	public void sort(List<Courses> courseList) {
	    Collections.sort(courseList, new Comparator<Courses>() {
	        public int compare(Courses course1, Courses course2) {
	            return Integer.compare(course2.getCurrentStudents(), course1.getCurrentStudents());
	        }
	    });

	    System.out.println("Courses sorted by the number of current students:");
	    for (Courses course : courseList) {
	        System.out.println("Course Name: " + course.getCourseName()
	                           + ", Current Students: " + course.getCurrentStudents()
	                           + "/" + course.getMaximumStudents());
	    }
	}
	
	public void writeFile(List<Courses> courseList) {
	    String filename = "FullCourses.txt"; // Updated file path for simplicity

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
	        boolean hasFullCourses = false;
	        for (Courses course : courseList) {
	            if (course.getCurrentStudents() >= course.getMaximumStudents()) {
	                writer.write("Course Name: " + course.getCourseName()
	                            + ", ID: " + course.getCourseID()
	                            + ", Section: " + course.getCourseSectionNumber() + "\n");
	                hasFullCourses = true;
	            }
	        }
	        if (hasFullCourses) {
	            System.out.println("Full courses written to file: " + filename);
	        } else {
	            System.out.println("No full courses found. File not updated.");
	        }
	    } catch (IOException e) {
	        System.out.println("Error writing to file: " + e.getMessage());
	    }
	}


	 
	
	

	 

	 

	 
	 
}
