import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html><head><title>Home</title></head>");
			out.println("<img src=\"cat.jpg\" height=\"500\" width=\"1000\">");
			out.println("</body></html>");
		} finally {
			out.close();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html><title>Hello</title><head><script>alert('hi');</script><h1>Hello " + request.getParameter("userName") + "</h1></head></html>");
	}
}
