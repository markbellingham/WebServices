<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="Courses.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Courses CRUD</title>
	<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body style="margin-left:20px">

	<h1>Search Courses</h1>
	<form action="CoursesController">
		<input type="text" name="search" placeholder="Search for courses"></input>
		<select name="format">
			<option value="select">Select</option>
			<option value="json">JSON</option>
			<option value="xml">XML</option>
			<option value="text">text</option>
			<option value="html">HTML</option>
		</select>
		<button class="btn btn-primary" type="submit" name="action" value="search">Submit</button>
		
	</form>
	<br/>
    
    <%
	    CourseDAO dao = new CourseDAO();
	    Course course = new Course();
		List<Course> courses = dao.getAllCourses();
 	    //List<Course> courses = dao.searchCourse("Comp");
 	    //course = dao.getOneCourse("Computing");
 	    //ArrayList<Course> courses = new ArrayList<Course>();
 	    if(request.getParameter("id") != null) {
	        String id = request.getParameter("id");
	        course = dao.getOneCourse(id);
	    }
    %>
    
    <h1>Courses</h1>
    <form action="CoursesController">
    <table class="table">
        <tr>
            <td><input name="id" 		class="form-control" value="<%= ((course.getCourseID()==null)?"":course.getCourseID())%>" placeholder="Course ID"/></td>
            <td><input name="name" 		class="form-control" value="<%= ((course.getCourseName()==null)?"":course.getCourseName())%>" placeholder="Name"/></td>
            <td><input name="credits" 	class="form-control" value="<%= ((course.getCourseCredits()==0)?"":course.getCourseCredits())%>" placeholder="Credits"/></td>
            <td><input name="duration" 	class="form-control" value="<%= ((course.getCourseDuration()==0)?"":course.getCourseDuration())%>" placeholder="Duration"/></td>
            <td><input name="tutor" 	class="form-control" value="<%= ((course.getCourseTutor()==null)?"":course.getCourseTutor())%>" placeholder="Tutor"/></td>
            <td>
                <button class="btn btn-success" name="action" value="insert">
                    Add
                </button>
                <button class="btn btn-primary" name="action" value="update">
                    Update
                </button>
            </td>
        </tr>
    <%
        for(Course cs:courses) {
    %>        <tr>
                <td><%= cs.getCourseID() %></td>
                <td><%= cs.getCourseName() %></td>
                <td><%= cs.getCourseCredits() %></td>
                <td><%= cs.getCourseDuration() %></td>
                <td><%= cs.getCourseTutor() %></td>
                <td>
                    <a class="btn btn-danger"  href="CoursesController?action=delete&id=<%= cs.getCourseID() %>">
                        Delete
                    </a>
                    <a class="btn btn-warning" href="coursesCRUD.jsp?action=select&id=<%= cs.getCourseID() %>">
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