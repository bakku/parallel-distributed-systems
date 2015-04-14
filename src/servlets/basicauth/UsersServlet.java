package servlets.basicauth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( name = "UsersServlet", urlPatterns = { "/users" } )
public class UsersServlet extends HttpServlet {
	
	private ArrayList<User> userList;
	
	// super user name
	private String adminName = "aladdin";
	
	// super user password
	private String adminPassword = "open sesame";
	
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
	
	private String[] decodeAuthorization(String auth) {
		if (auth == null) {
			return null;
		}
		
		auth = auth.substring(6);
		
		String decodedAuth = new String(Base64.getDecoder().decode(auth));
		
		return decodedAuth.split(":");
	}
	
	private boolean checkAuthentication(String[] credentials) {
		if (credentials != null && credentials[0].equals(adminName) && credentials[1].equals(adminPassword)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// check authorization
		String auth = request.getHeader("Authorization");
		String[] credentials = decodeAuthorization(auth);
		
		if (checkAuthentication(credentials)) {
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
		
		// wrong credentials
		else {
			response.sendError(401);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// check authorization
		String auth = request.getHeader("Authorization");
		String[] credentials = decodeAuthorization(auth);
		
		if (checkAuthentication(credentials)) {
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
		
		// wrong credentials
		else {
			response.sendError(401);
		}
	}
}
