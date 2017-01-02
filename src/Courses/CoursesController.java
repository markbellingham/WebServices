package Courses;

import java.io.IOException;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
    	
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String credits = request.getParameter("credits");
        String duration = request.getParameter("duration");
        String tutor = request.getParameter("tutor");
        Course course = new Course();
        
        if("insert".equals(action)) {
        	int courseCredits = Integer.parseInt(credits);
        	int courseDuration = Integer.parseInt(duration);
            course = new Course(id, name, courseCredits, courseDuration, tutor);
            dao.insertCourse(course);
            response.sendRedirect("coursesCRUD.jsp");
        } else if("delete".equals(action)){
            dao.deleteCourse(id);
            response.sendRedirect("coursesCRUD.jsp");
        } else if("select".equals(action)) {
            course = dao.getOneCourse(id);
            response.sendRedirect("coursesCRUD.jsp?id=" + id);
        } else if("update".equals(action)) {
        	int courseCredits = Integer.parseInt(credits);
        	int courseDuration = Integer.parseInt(duration);
            course = new Course(id, name, courseCredits, courseDuration, tutor);
            dao.updateCourse(id, course);
            response.sendRedirect("coursesCRUD.jsp?id=" + id);
        }
        
//        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
