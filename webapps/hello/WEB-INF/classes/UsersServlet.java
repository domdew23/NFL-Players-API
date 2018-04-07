import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.Iterator;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UsersServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		Model model = new Model();

		String query = getController(request);
		view(mapper, model.queryModelForUser(query), out);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		Model model = new Model();
		
		User user = postController(request, mapper);
		String update = "INSERT INTO users (username,firstname,lastname,password,age) VALUES ('"+user.getUserName()+"','"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getPassword()+"',"+user.getAge()+");";
		model.updateModel(update);
		response.sendRedirect("signedup.html");
		out.flush();
	}

	private String getController(HttpServletRequest request){
		String query = "";

		if (request.getParameterMap().isEmpty()){
			query = "SELECT id,username,firstname,lastname,age FROM users";
		} else {
			String items = "";
			int i = 0;
			for (Iterator<String> it = request.getParameterMap().keySet().iterator(); it.hasNext();){
				if (i > 0){
					items += " AND ";
				}
				String key = it.next();
				String value = request.getParameterMap().get(key)[0];
				items += (key + " = " + value);
				i++;
			}
			query = "SELECT id,username,firstname,lastname,age FROM users WHERE " + items;
		}
		return query;
	}

	private User postController(HttpServletRequest request, ObjectMapper mapper) throws IOException {
		String s = "";
		User user = new User();
		StringBuilder sb = new StringBuilder();

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String age = request.getParameter("age");

		if ((userName == "" || userName == null) || (password == "" || password == null) || (firstName == "" || firstName == null) ||
			(lastName == "" || lastName == null) || (age == "" || age == null)){
			while ((s = request.getReader().readLine()) != null){
				sb.append(s);
			}
			user = mapper.readValue(sb.toString(), User.class);
		} else {
			user.setUserName(userName);
			user.setPassword(password);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setAge(Integer.parseInt(age));
		}
		return user;
	}

	private void view(ObjectMapper mapper, ArrayList<User> result, PrintWriter out) throws IOException {
		for (User user : result){
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
			out.println(json);
		}
	}
}
