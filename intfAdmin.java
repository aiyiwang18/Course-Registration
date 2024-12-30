package interfaces;

import java.util.List;

import files.Courses;

public interface intfAdmin {
	public void create(List<Courses> courseList,String name,String id, int maxStudents, String instructor, 
			int section, String location);
	public void delete(List<Courses> courseList, String name);
	public void edit(List<Courses>courseList, String name);
	public void display(List<Courses> courseList);
	public void register(List<Courses> courseList, String courses, int section, String studentName);
	public void view(List<Courses> courseList);
	public void viewFull(List<Courses>courseList);
	public void viewStudentsInCourse(List<Courses>courseList, String courseID);
	public void courseGivenStudent(List<Courses>courseList, String name);
	public void sort(List<Courses>courseList);
}

