package courses;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

/**
 * Servlet implementation class CoursesController
 * Provides the link between the user interface and the DAO or other logic, and returns results to the user once processing has been completed.
 */
@WebServlet("/CoursesController")
public class CoursesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Instantiate the objects to use later
	CourseDAO dao = new CourseDAO();
	Course course = new Course();
	ArrayList<Course> courses = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoursesController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the parameters from the request and create the Strings
		String output = "";
		String action = "";
		String format = "";
		// If we somehow arrived here without specifying an action, return to the CRUD page
		if(request.getParameter("action") == null || request.getParameter("action") == "") {
			response.sendRedirect("coursesCRUD.jsp");
		} else {
			action = request.getParameter("action");
		}
		if(request.getParameter("format") == null || request.getParameter("format") == "") {
			format = "json";
		} else {
			format = request.getParameter("format");
		}
		String search 	= request.getParameter("searchText");
		String id 		= request.getParameter("id");
		String name 	= request.getParameter("name");
		String credits 	= request.getParameter("credits");
		String duration = request.getParameter("duration");
		String tutor 	= request.getParameter("tutor");
		
		System.out.println("search = " + search);
		
		// If the user performs a Search action, get a list of courses from the search query
		// and format the result according to the request.
		if("search".equals(action)) {
			
			courses = dao.searchCourse(search);
			
			switch(format) {
			case "json":
			{
				// Uses the GSON library to convert from java object to JSON notation
				output = new Gson().toJson(courses);
				System.out.println("json output: " + output);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(output);
				break;
			}
			case "xml":
			{
				// Uses the XStream library to convert from java object to XML notation
				XStream xstream = new XStream();
				xstream.alias("course", Course.class);
				xstream.addImplicitCollection(CoursesList.class, "list");
				
				response.setContentType("application/xhtml+xml");
				output = xstream.toXML(courses);
				System.out.println("xml output: " + output);
				response.getWriter().write(output);
				break;
			}
			case "text":
			{
				// Returns a list of courses to be formatted by JavaScript
				output = dao.searchCourse(search).toString();
				response.setContentType("text/html");
				response.getWriter().write(output);
				break;
			}
			default:
				// If no format is specified, just use JSON
				output = new Gson().toJson(courses);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(output);
				break;
			}
		} else {
			
			// If the requested action is one of CRUD, perform the query on the database 
			// and return the user back to the CRUD page 
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
			
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the parameters from the POST request
		Enumeration paramNames = request.getParameterNames();
		
		while(paramNames.hasMoreElements()) {
			// First get the parameter name, then use it to get the values
			// Since there is only one, put it into a String
			String paramName = (String)paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			String data = paramValues[0];
			
			// Check the first character to see if submission is JSON or XML
			// Then parse the data into a Course object and submit it to the DAO
			if(data.charAt(0) == '{') {
				Course course = new Gson().fromJson(data, Course.class);
				dao.insertCourse(course);
				response.sendRedirect("coursesCRUD.jsp");
				
			} else if (data.charAt(0) == '<') {
				Course course = JAXB.unmarshal(new StringReader(data), Course.class);
				dao.insertCourse(course);
				response.sendRedirect("coursesCRUD.jsp");
			}
		}

	}

}
