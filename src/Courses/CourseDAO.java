package Courses;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

/**
 * @author mark
 * Methods in this class:
 * openConnection, closeConnection, insertCourse, getNextCourse, getOneCourse,
 * getAllCourses, searchCourse, updateCourse, deleteCourse
 * */
public class CourseDAO {

	Course oneCourse = null;
	Statement stmt = null;
	public CourseDAO() {}


	private Connection openConnection(){

		String userid = "bellingm";
		String userpass = "krIsderm2";
		String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:3306/";
		String connectionURL = url + userid;        
		Connection conn = null;

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


	private void closeConnection(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void insertCourse(Course course) {

		String sql = "insert into courses " + 
					"(courseID, courseName, courseCredits, courseDuration, courseTutor)" +
				" values (?, ?, ?, ?, ?)";

		Connection conn = openConnection();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
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


	private Course getNextCourse(ResultSet rs){
		Course thisCourse=null;

		try {
			thisCourse = new Course(
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
	
	
	public Course getOneCourse(String courseID) {
		Course course = null;
		String sql = "select * from courses where courseID = ?";
		Connection conn = openConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseID);
			ResultSet rs1 = stmt.executeQuery();
			if(rs1.next()) {
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


	public ArrayList<Course> getAllCourses(){

		ArrayList<Course> allCourses = new ArrayList<Course>();
		Connection conn = openConnection();

		try{
			String sql = "select * from courses";
			ResultSet rs1 = stmt.executeQuery(sql);
			while(rs1.next()){
				oneCourse = getNextCourse(rs1);
				allCourses.add(oneCourse);
			}
			stmt.close();
		} catch(SQLException se) {
			System.out.println(se);
		} finally {
			closeConnection(conn);         
		}
		return allCourses;
	}


	public ArrayList<Course> searchCourse(String courseName){

		ArrayList<Course> allCourses = new ArrayList<Course>();
		String sql = "select * from courses where courseName like ?";

		Connection conn = openConnection();
		oneCourse = null;
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + courseName + "%");            
			ResultSet rs1 = stmt.executeQuery();

			while(rs1.next()){
				oneCourse = getNextCourse(rs1);
				allCourses.add(oneCourse);
			}
			stmt.close();
		} catch(SQLException se) {
			System.out.println(se);
		} finally {
			closeConnection(conn);        	
		}        
		return allCourses;
	}


	public void updateCourse(String courseID, Course course) {
		String sql = "update courses " + 
				"set courseName = ?, courseCredits = ?, courseDuration = ?, courseTutor = ? " + 
				"where courseID = ?";
		Connection conn = openConnection();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
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


	public void deleteCourse(String courseID) {
		String sql = "delete from courses where courseID = ?";
		//System.out.println("Delete statement = " + sql);
		Connection conn = openConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseID);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
	}
}