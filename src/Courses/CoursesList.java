package Courses;

import java.util.ArrayList;
import java.util.List;

public class CoursesList {

	private List<Course> list;
	
	public CoursesList() {
		list = new ArrayList<Course>();
	}
	
	public void addAll(List<Course> courses) {
		list.addAll(courses);
	}
}
