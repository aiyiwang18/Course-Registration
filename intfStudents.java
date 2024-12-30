package interfaces;

import java.util.List;

import files.Courses;

public interface intfStudents {
	public void view (List<Courses>courseList);
	public void notFull(List<Courses>courseList);
	public void registerCourse(List<Courses>courseList, String course, int section, String name);
	public void withdraw(List<Courses>courseList, String course, String name);
	public void viewAll(List<Courses>courseList, String name);
	
}
