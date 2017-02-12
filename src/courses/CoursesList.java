package courses;

import java.util.ArrayList;

public class CoursesList {
	
	private ArrayList<Course> list;
	
	public CoursesList() {
		list = new ArrayList<Course>();
	}
	
	public void addAll(ArrayList<Course> courses) {
		list.addAll(courses);
	}

}
