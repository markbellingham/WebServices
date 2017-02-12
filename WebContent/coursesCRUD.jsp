<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="courses.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Courses</title>
	<link href="css/bootstrap.css" rel="stylesheet"/>
	<script type="text/javascript" src="scripts/jquery.js"></script>
	<script type="text/javascript" src="scripts/javascript.js"></script>
</head>
<body style="margin-left:30px; margin-right:30px">

<!-- Form where users can search for courses. 
Results are displayed in the search-results div (pre for XML) in the format selected in the drop-down box. 
The page does not refresh when performing this action -->
	<h1>Search Courses</h1>
	<form id="searchForm" action="CoursesController" method="get">
		<input type="text" name="searchText" id="searchText" placeholder="Enter all or part of a course name" style="width:225px" />
		<select name="format" id="format">
			<option value="json">JSON</option>
			<option value="xml">XML</option>
			<option value="text">Text</option>
		</select>
		<button class="btn btn-primary" type="submit" name="action" id="search" onclick="">Submit</button>
	</form>
	<br/>
	<div id="search-div" style="width:100%"></div>
	<pre id="search-pre"></pre>
	
<!-- Form to insert records to the database using json or xml -->
	<h1>Insert course using JSON or XML</h1>
	<form action="CoursesController" method="post">
		<textarea name="formattedText" rows="5" cols="180" placeholder="Single course only. For JSON end-tags should be {} - For XML use Course tag and attribute tags"></textarea>
		<button class="btn btn-primary">Submit</button>	
	</form>
	
	
	<%
		CourseDAO dao = new CourseDAO();
		Course course = new Course();
		ArrayList<Course> courses = dao.getAllCourses();
		// Used to put course information into the input fields in the table for CRUD operations
 	    if(request.getParameter("id") != null) {
 	    	String id = request.getParameter("id");
 	    	course = dao.getOneCourse(id);
 	    }
	%>


<!-- Table that displays a list of all courses currently in the database, and performs CRUD operations on them. -->	
	<h1>Courses</h1>
	<form action="CoursesController" id="coursesList">
	<table class="table">
		<tr>
			<td><input type="text" 		name="id"		class="form-control" value="<%= ((course.getCourseID()==null)?"":course.getCourseID()) %>" placeholder="Course ID"/></td>
			<td><input type="text" 		name="name"		class="form-control" value="<%= ((course.getCourseName()==null)?"":course.getCourseName()) %>" placeholder="Course Name"/></td>
			<td><input type="number" 	name="credits"	class="form-control" value="<%= ((course.getCourseCredits()==0)?"":course.getCourseCredits()) %>" placeholder="Course Credits"/></td>
			<td><input type="number" 	name="duration"	class="form-control" value="<%= ((course.getCourseDuration()==0)?"":course.getCourseDuration()) %>" placeholder="Course Duration"/></td>
			<td><input type="text" 		name="tutor"	class="form-control" value="<%= ((course.getCourseTutor()==null)?"":course.getCourseTutor()) %>" placeholder="Course Tutor"/></td>
			<td>
				<button name="action" 	value="insert" class="btn btn-success">Add</button>
				<button name="action" 	value="update" class="btn btn-primary">Update</button>
				<button id="clear" 		value="clear" class="btn">Clear</button>
			</td>			
		</tr>
	<%
		for(Course cs:courses) {
	%>		<tr>
				<td><%= cs.getCourseID() %></td>
				<td><%= cs.getCourseName() %></td>
				<td><%= cs.getCourseCredits() %></td>
				<td><%= cs.getCourseDuration() %></td>
				<td><%= cs.getCourseTutor() %></td>
				<td>
					<a class="btn btn-danger" href="CoursesController?action=delete&id=<%= cs.getCourseID() %>">
						Delete
					</a>
					<a class="btn btn-warning" href="CoursesController?action=select&id=<%= cs.getCourseID() %>">
						Select
					</a>
				</td>
			</tr>
		<%
			}
		%>
	</table>
	</form>
</body>
</html>