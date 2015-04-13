package servlets.factorial;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( name = "FactorialGet", urlPatterns = { "/factorial" } )
public class FactorialGet extends HttpServlet {
	
	public FactorialGet() {
		super();
	}

	private long factorial(long n) {
		long result = 1;
		
		for (; n > 0; n--) {
			result *= n;
		}
		
		return result;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// request line
		System.out.println("1: " + request.getMethod() + " " + request.getRequestURI() 
				+ " " + request.getProtocol());
		
		// request header fields
		Enumeration<String> headerNames = request.getHeaderNames();
		int i = 2;
		while (headerNames.hasMoreElements()) {
			String headerKey = (String) headerNames.nextElement();
			String headerValue = request.getHeader(headerKey);
			
			System.out.println(i + ": " + headerKey + ": " + headerValue);
			i++;
		}
		
		long value = Long.parseLong(request.getParameter("number"));
		PrintWriter os = response.getWriter();
		os.write(Long.toString(factorial(value)));
		os.close();
	}
}
