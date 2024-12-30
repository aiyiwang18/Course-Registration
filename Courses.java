package files;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Courses implements Serializable{
	private String courseName;
	private String courseID;
	private int maximumStudents;
	private int currentStudents; 
	private List<String> listOfNames;
	private String courseInstructor;
	private int courseSectionNumber;
	private String courseLocation; 
	

	public Courses(String courseName, String courseID, int maximumStudents, int currentStudents, 
            List<String> listOfNames, String courseInstructor, int courseSectionNumber, String courseLocation) {
			this.courseName = courseName;
			this.courseID = courseID;
			this.maximumStudents = maximumStudents;
			this.currentStudents = currentStudents; // Set to the provided value
			this.listOfNames = listOfNames != null ? listOfNames : new ArrayList<>(); // Initialize if null
			this.courseInstructor = courseInstructor;
			this.courseSectionNumber = courseSectionNumber;
			this.courseLocation = courseLocation;
	}

	

	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName; 
	}


	public String getCourseID() {
		return courseID;
	}


	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}


	public int getMaximumStudents() {
		return maximumStudents;
	}


	public void setMaximumStudents(int maximumStudents) {
		this.maximumStudents = maximumStudents;
	}


	public int getCurrentStudents() {
		return currentStudents;
	}


	public void setCurrentStudents(int currentStudents) {
		this.currentStudents = currentStudents;
	}


	public List<String> getListOfNames() {
		return listOfNames;
	}


	public void setListOfNames(List<String> listOfNames) {
		this.listOfNames = listOfNames;
	}


	public String getCourseInstructor() {
		return courseInstructor;
	}


	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}


	public int getCourseSectionNumber() {
		return courseSectionNumber;
	}


	public void setCourseSectionNumber(int courseSectionNumber) {
		this.courseSectionNumber = courseSectionNumber;
	}


	public String getCourseLocation() {
		return courseLocation;
	}


	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}
	 


	@Override
	public String toString() {
		return "courses [courseID=" + courseID + ", maximumStudents=" + maximumStudents + ", currentStudents="
				+ currentStudents + ", listOfNames=" + listOfNames + ", courseInstructor=" + courseInstructor
				+ ", courseSectionNumber=" + courseSectionNumber + ", courseLocation=" + courseLocation 
				+ "]";
	}

	
	
}
