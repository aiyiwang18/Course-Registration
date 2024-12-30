package Users;

import java.util.List;

import files.Courses;
import interfaces.intfStudents;

public class Students extends Users implements intfStudents{

	//constructor
	public Students(String username, String password, String firstname, String lastname, String email) {
		super(username, password, firstname, lastname, email);
	}

	 
	
	//view all courses
	public void view (List<Courses>courseList) {
		for(Courses course: courseList) {
			System.out.println("Course Name: " + course.getCourseName());
			System.out.println("Section Number: " +course.getCourseSectionNumber());
			System.out.println("Maxinum: "+ course.getMaximumStudents());
			System.out.println("Current: " + course.getCurrentStudents());
			System.out.println("Instructor: " + course.getCourseInstructor());
			System.out.println("Location: " + course.getCourseLocation());
			System.out.println("---------------------------------------");
			}
	}
	
	//view all courses that are not full
	public void notFull(List<Courses>courseList) {
		for(Courses course: courseList) {
			if(course.getCurrentStudents()< course.getMaximumStudents()){
				System.out.println(course.getCourseName());
			}
		}
	}
	
	//register on a course
	public void registerCourse(List<Courses>courseList, String course, int section, String name) {
		for(Courses thiscourse : courseList) {
		    if (thiscourse.getCourseName().equals(course)) {
		        if (thiscourse.getListOfNames().contains(name)) {
		            thiscourse.setCurrentStudents(thiscourse.getCurrentStudents() - 1);
		            thiscourse.getListOfNames().remove(name);
		            System.out.println("Successfully withdrawn from the course.");
		        } else {
		            System.out.println("You are not registered in this course.");
		        }
		        return;
		    }
		}
		System.out.println("Course not found.");

	}
	
	//withdraw from a course. Enter student name, course, student take off the course list. 
	public void withdraw(List<Courses>courseList, String course, String name) {
		for(Courses thiscourse:courseList) {
			if (thiscourse.getCourseName().equals(course)) {
				//remove 1 current students; remove student name from list
				thiscourse.setCurrentStudents(thiscourse.getCurrentStudents()-1);
				thiscourse.getListOfNames().remove(name);
			}
		}
	}
	
	//view all courses student being registered in 
	public void viewAll(List<Courses>courseList, String name) {
		for(Courses course: courseList) {
			if(course.getListOfNames().contains(name)) {
				System.out.println(course.getCourseName()); 
			}
		}
	}
}









