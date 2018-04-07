import static spark.Spark.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.fasterxml.jackson.databind.ObjectMapper;

import spark.Request;

public class main {

	public static void main(String[] args) {
		get("/users", (req, res) -> {
			res.type("application/json;");
			ObjectMapper mapper = new ObjectMapper();
			Model model = new Model();
			
			String query = getUserController(req);
			return viewUser(mapper, model.queryForUser(query));
		});
		
		post("/users", (req, res) -> {
			res.type("application/json;");
			ObjectMapper mapper = new ObjectMapper();
			Model model = new Model();
			
			User user = postUserController(mapper, req);
			String update = "INSERT INTO users (username,firstname,lastname,password,age) VALUES ('"+user.getUserName()+"','"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getPassword()+"',"+user.getAge()+");";
			model.updateModel(update);
			return res.status();
		});
		
		get("/qb", (req, res) -> {
			res.type("application/json");
			ObjectMapper mapper = new ObjectMapper();
			Model model = new Model();
			
			String query = getQBController(req);
			return viewQB(mapper, model.queryForQB(query));
		});
		
		post("/qb", (req, res) -> {
			res.type("application/json");
			ObjectMapper mapper = new ObjectMapper();
			Model model = new Model();
			
			QB player = postQBController(mapper, req);
			String update = "INSERT INTO QB (Team,Number,Name,Season,Passing_Attempts,Passing_Completions,Games_Played,Passing_Yards,Completion_Percentage,Passing_Yards_Per_Attempt,Passing_Yards_Per_Completion,Passing_Touchdowns,Passing_Interceptions,Passing_Rating,Passing_Long,Sacks,Rushing_Attempts,Rushing_Yards,Rushing_Yards_Per_Attempt,Rushing_Touchdowns,Rushing_Long,Fumbles,Fantasy_Points,Team_id) VALUES ('"+player.Team+"',"+player.Number+",'"+player.Name+"',"+player.Season+","+player.PassingAttempts+","+player.PassingCompletions+","+player.Played+","+player.PassingYards+","+player.PassingCompletionPercentage+","+player.PassingYardsPerAttempt+","+player.PassingYardsPerCompletion+","+player.PassingTouchdowns+","+player.PassingInterceptions+","+player.PassingRating+","+player.PassingLong+","+player.PassingSacks+","+player.RushingAttempts+","+player.RushingYards+","+player.RushingYardsPerAttempt+","+player.RushingTouchdowns+","+player.RushingLong+","+player.Fumbles+","+player.FantasyPoints+","+player.TeamID+");";
			model.updateModel(update);
			return res.status();
		});
		
		get("/", (req, res) -> {
			res.type("text/html;");
			return "<html><head><title>Home</title></head><body><h1>NFl API</h1></body></html>";
		});
	}
	
	private static String getUserController(Request req) {
		String query = "";
		
		if (req.queryParams().isEmpty()) {
			query = "SELECT id,username,firstname,lastname,age FROM users";
		} else {
			String items = "";
			int i = 0;
			for (Iterator<String> it = req.queryParams().iterator(); it.hasNext(); i++) {
				if (i > 0) {
					items += " AND ";
				}
				String key = it.next();
				String[] value = req.queryParamsValues(key);
				items += (key += " = " + value[0]);
			}
			query = "SELECT id,username,firstname,lastname,age FROM users WHERE " + items;
		}
		return query;
	}
	
	private static String getQBController(Request req) {
		String query = "";
		if (req.queryParams().isEmpty()){
			query = "SELECT * FROM QB";
		} else {
			String items = "";
			int i = 0;
			for (Iterator<String> it = req.queryParams().iterator(); it.hasNext();){
				if (i > 0){
					items += " AND ";
				}
				String key = it.next();
				String[] value = req.queryParamsValues(key);
				items += (key + " = " + value[0]);
				i++;
			}
			query = "SELECT * FROM QB WHERE " + items;
		}
		return query;
	}
	
	private static String viewUser(ObjectMapper mapper, ArrayList<User> result) {
		String ret = "";
		try {
			for (User user : result) {
				String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
				ret += json + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	

	private static String viewQB(ObjectMapper mapper, ArrayList<QB> result) {
		String ret = "";
		try {
			for (QB qb : result){
				String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(qb);
				ret += json + "\n";
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	
	private static User postUserController(ObjectMapper mapper, Request req) {
		User user = new User();
		if (req.queryParams().isEmpty()){
			try {
				user = mapper.readValue(req.body(), User.class);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			user.setUserName(req.queryParams("username"));
			user.setPassword(req.queryParams("password"));
			user.setFirstName(req.queryParams("firstname"));
			user.setLastName(req.queryParams("lastname"));
			user.setAge(Integer.parseInt(req.queryParams("age")));
		}
		return user;
	}
	
	private static QB postQBController(ObjectMapper mapper, Request req) {
		QB qb = null;
		try {
			 qb = mapper.readValue(req.body(), QB.class);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return qb;
	}
}
