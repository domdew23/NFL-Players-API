import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import java.sql.*;
import java.util.Iterator;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class QBServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		Model model = new Model();

		String query = getController(request);
		view(mapper, model.queryModelForQB(query), out);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		Model model = new Model();

		QB player = mapper.readValue(postController(request), QB.class);
		String update = "INSERT INTO QB (Team,Number,Name,Season,Passing_Attempts,Passing_Completions,Games_Played,Passing_Yards,Completion_Percentage,Passing_Yards_Per_Attempt,Passing_Yards_Per_Completion,Passing_Touchdowns,Passing_Interceptions,Passing_Rating,Passing_Long,Sacks,Rushing_Attempts,Rushing_Yards,Rushing_Yards_Per_Attempt,Rushing_Touchdowns,Rushing_Long,Fumbles,Fantasy_Points,Team_id) VALUES ('"+player.Team+"',"+player.Number+",'"+player.Name+"',"+player.Season+","+player.PassingAttempts+","+player.PassingCompletions+","+player.Played+","+player.PassingYards+","+player.PassingCompletionPercentage+","+player.PassingYardsPerAttempt+","+player.PassingYardsPerCompletion+","+player.PassingTouchdowns+","+player.PassingInterceptions+","+player.PassingRating+","+player.PassingLong+","+player.PassingSacks+","+player.RushingAttempts+","+player.RushingYards+","+player.RushingYardsPerAttempt+","+player.RushingTouchdowns+","+player.RushingLong+","+player.Fumbles+","+player.FantasyPoints+","+player.TeamID+");";
		model.updateModel(update);
	}

	private String postController(HttpServletRequest request) throws IOException {
		StringBuilder buffer = new StringBuilder();
		String s = "";
		while ((s = request.getReader().readLine()) != null){
			buffer.append(s);
		}
		return buffer.toString();
	}

	private String getController(HttpServletRequest request){
		String query = "";
		if (request.getParameterMap().isEmpty()){
			query = "SELECT * FROM QB";
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
			query = "SELECT * FROM QB WHERE " + items;
		}
		return query;
	}

	private void view(ObjectMapper mapper, ArrayList<QB> result, PrintWriter out) throws IOException{
		for (QB qb : result){
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(qb);
			out.println(json);
		}
	}
}