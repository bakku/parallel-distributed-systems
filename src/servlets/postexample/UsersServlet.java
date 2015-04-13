package servlets.postexample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( name = "UsersServlet", urlPatterns = { "/users" } )
public class UsersServlet extends HttpServlet {
	
	private ArrayList<User> userList;
	
	public UsersServlet() {
		super();
		userList = new ArrayList<User>();
	}
	
	// checks whether the user already exists in the data structure
	private boolean userExists(String name) {
		for (User u: userList) {
			if (u.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String user = request.getParameter("name");
		PrintWriter os = response.getWriter();
		
		// no query parameter "name" --> respond with list of all users
		if (user == null) {
			os.write("List of users:\n");
			
			for (User u: userList) {
				os.write(u.getName() + "\n");
			}
		}
		
		// checks if query parameter user exists
		else {
			if (userExists(user)) {
				os.write("User is registered");
			}
			else {
				os.write("User is unknown");
			}
		}
		
		// send number of users in the header
		response.setHeader("X-number-of-users", userList.size() + "");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// User already exists
		if (userExists(username)) {
			// Status code: Conflict
			response.sendError(409, "User already exists");
		}
		else {
			User temp = new User(username, password);
			userList.add(temp);
			
			// Status code: Created
			response.setStatus(201);
		}
	}
}
