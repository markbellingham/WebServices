package courses;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.PreparedStatement;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;


/**
 * @author Mark
 * Data Accessor Object
 * Provides methods for connecting to and accessing the database
 * All SQL statements are in the form of Prepared Statement to protect against SQL injection attacks
 * All methods that insert or retrieve data will close the connection even if the statement fails.
 */

public class CourseDAO {

	Course oneCourse = null;
	java.sql.Statement stmt = null;
	public CourseDAO() {}
	
	
	// Method for connecting to the database, returns an open connection
	private java.sql.Connection openConnection() {
		
		String userid = "bellingm";
		String userpass = "krIsderm2";
		String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:3306/";
		String connectionURL = url + userid;
		java.sql.Connection conn = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionURL, userid, userpass);
			stmt = conn.createStatement();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	// Method for closing the connection to the database
	private void closeConnection(java.sql.Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// Method for inserting a course into the database. 
	// It takes a Course object as a parameter but does not return anything.
	public void insertCourse(Course course) {		
		String sql = "insert into courses " +
					"(courseID, courseName, courseCredits, courseDuration, courseTutor)" +
					" values (?, ?, ?, ?, ?)";
		
		java.sql.Connection conn = openConnection();
		
		try {
			java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, course.getCourseID());
			stmt.setString(2, course.getCourseName());
			stmt.setInt(3, course.getCourseCredits());
			stmt.setInt(4, course.getCourseDuration());
			stmt.setString(5, course.getCourseTutor());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
	}
	
	
	// Method for creating a Course type object from the result set.
	// It is not accessed directly from the controller but used as a sub-routine in other methods
	private Course getNextCourse(java.sql.ResultSet rs) {
		Course thisCourse = null;
		
		try {
			thisCourse = new Course (
					rs.getString("courseID"),
					rs.getString("courseName"),
					rs.getInt("courseCredits"),
					rs.getInt("courseDuration"),
					rs.getString("courseTutor")
					);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thisCourse;
	}
	
	
	// Method that returns a single course from the database when provided with an id.
	public Course getOneCourse(String courseID) {
		Course course = null;
		String sql = "select * from courses where courseID = ?";
		java.sql.Connection conn = openConnection();
		try {
			java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseID);
			java.sql.ResultSet rs1 = stmt.executeQuery();
			if (rs1.next()) {
				courseID = rs1.getString("courseID");
				String name = rs1.getString("courseName");
				int credits = rs1.getInt("courseCredits");
				int duration = rs1.getInt("courseDuration");
				String tutor = rs1.getString("courseTutor");
				course = new Course(courseID, name, credits, duration, tutor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return course;
	}
	
	
	// Method to get all courses from the database.
	// The data is returned as an ArrayList of Course objects
	public ArrayList<Course> getAllCourses() {
		ArrayList<Course> allCourses = new ArrayList<Course>();
		java.sql.Connection conn = openConnection();
		
		try {
			String sql = "select * from courses";
			java.sql.ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				oneCourse = getNextCourse(rs);
				allCourses.add(oneCourse);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return allCourses;
	}
	
	
	// Method that returns one or more courses when provided with a string of letters
	// Searching is against the course name only
	// The search term can either completely or partially match the course name
	public ArrayList<Course> searchCourse(String courseName) {
		ArrayList<Course> allCourses = new ArrayList<Course>();
		String sql = "select * from courses where courseName like ?";
		
		java.sql.Connection conn = openConnection();
		oneCourse = null;
		try {
			java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + courseName + "%");
			java.sql.ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				oneCourse = getNextCourse(rs);
				allCourses.add(oneCourse);
			}
			stmt.close();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return allCourses;
	}
	
	
	// Updates a course record in the database.
	// It uses a complete Course object as argument.
	public void updateCourse(String courseID, Course course) {
		String sql = "update courses " +
					"set courseName = ?, courseCredits = ?, courseDuration = ?, courseTutor = ? " +
					"where courseID = ?";
		java.sql.Connection conn = openConnection();
		
		try {
			java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, course.getCourseName());
			stmt.setInt(2, course.getCourseCredits());
			stmt.setInt(3, course.getCourseDuration());
			stmt.setString(4, course.getCourseTutor());
			stmt.setString(5, courseID);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
 	}
	
	
	// Method to delete a course record from the database when supplied with an id
	public void deleteCourse(String courseID) {
		String sql = "delete from courses where courseID = ?";
		java.sql.Connection conn = openConnection();
		try {
			java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseID);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		
	}
	
	
}
