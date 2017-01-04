package Courses;

import java.io.IOException;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import java.util.*;

/**
 * Servlet implementation class CoursesController
 */
@WebServlet("/CoursesController")
public class CoursesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CoursesController() {
        // TODO Auto-generated constructor stub
        CourseDAO dao = new CourseDAO();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        CourseDAO dao = new CourseDAO();
        Course course = new Course();
        List<Course> courses = null;
        
        PrintWriter out = response.getWriter();
        StringWriter sw = new StringWriter();
        
        String format = request.getParameter("format");
        String output = "";
        String outputPage = "";
        String action = "";
    	
        if(request.getParameter("action") == null) {
        	response.sendRedirect("coursesCRUD.jsp");
        } else {
            action = request.getParameter("action");
        }
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String credits = request.getParameter("credits");
        String duration = request.getParameter("duration");
        String tutor = request.getParameter("tutor");
        String search = request.getParameter("search");
        
        System.out.println("Action = " + action);
        System.out.println("Search = " + search);
        System.out.println("Output = " + format);
        
        if("search".equals(action)) {
        	
        	switch(format) {
        	case "json":
        	{
            	courses = dao.searchCourse(search);
            	output = new Gson().toJson(courses);
            	response.setContentType("text/html");
            	outputPage = "courses.json";
            	out.println(output);
                break;
        	}
        	case "xml":
        	{
            	courses = dao.searchCourse(search);
           		outputPage = "courses.xml";
           		XStream xstream = new XStream();
           		xstream.alias("course", Course.class);
           		xstream.addImplicitCollection(CoursesList.class, "list");
           		
           		CoursesList list = new CoursesList();
           		list.addAll(courses);
           		
           		response.setContentType("application/xhtml+xml");
           		output = xstream.toXML(courses);
           		out.println(output);
                break;
        	}
        	case "text":
        	{
            	output = dao.searchCourse(search).toString();
            	out.println(output);
                break;
        	}
        	case "html":
        	{
            	courses = dao.searchCourse(search);
            	request.getSession().setAttribute("courses", courses);
            	System.out.println("Session value = " + request.getSession().getAttribute("courses"));

                break;
        	}
            default:
            {
            	courses = dao.searchCourse(search);
            	output = new Gson().toJson(courses);
            	response.setContentType("text/html");
            	outputPage = "courses.json";
            	out.println(output);
                break;
            }
        	}
        	
        } else {
        	
        	switch(action) {        	
        	case "insert":
        	{
            	int courseCredits = Integer.parseInt(credits);
            	int courseDuration = Integer.parseInt(duration);
                course = new Course(id, name, courseCredits, courseDuration, tutor);
                dao.insertCourse(course);
                response.sendRedirect("coursesCRUD.jsp");
                break;
        	}
        	case "delete":
        	{
                dao.deleteCourse(id);
                response.sendRedirect("coursesCRUD.jsp");
                break;
        	}
        	case "select":
        	{
                course = dao.getOneCourse(id);
                response.sendRedirect("coursesCRUD.jsp?id=" + id);
                break;
        	}
        	case "update":
        	{
            	int courseCredits = Integer.parseInt(credits);
            	int courseDuration = Integer.parseInt(duration);
                course = new Course(id, name, courseCredits, courseDuration, tutor);
                dao.updateCourse(id, course);
                response.sendRedirect("coursesCRUD.jsp?id=" + id); 
                break;
        	}
        	}

        }
        
        System.out.println("Courses = " + courses);
        System.out.println("Output = " + output);
        
        RequestDispatcher rd = request.getRequestDispatcher(outputPage);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
