import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

public class PlayListServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");

		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();

		boolean signedIn = false;
		if (userName != "" && userName != null){
			session.setAttribute("savedUsername", userName);
			context.setAttribute("savedUsername", userName);
			signedIn = true;
		} else {
			out.println("<script>alert('You most be logged in to view your playlists');</script>");
			signedIn = false;
		}

		if (!signedIn){
			response.sendRedirect("index.html");
		}

		try {
			out.println("<html><head><title>Playlist</title></head>");
			out.println("<body><h1>Welcome, " + session.getAttribute("savedUsername") + "</h1><br><h2>Your Playlist: </h2><br>");
			out.println("<video width=\"600\" height=\"600\" controls><source src=\"vid.mp4\" type=video/mp4></video>");
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
