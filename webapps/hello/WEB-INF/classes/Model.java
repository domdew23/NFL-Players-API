import java.sql.*;
import java.util.ArrayList;

public class Model {
	public Model(){
	}

	public ArrayList<QB> queryModelForQB(String query){
		Connection conn = null;
		PreparedStatement stmt = null;
		ArrayList<QB> qbs = new ArrayList<QB>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfl_players?useSSL=false", Settings.username, Settings.pass);

			stmt = conn.prepareStatement(query);
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()){
				qbs.add(makeQB(rset));
			}
		} catch (SQLException ex){
			ex.printStackTrace();
		} catch (ClassNotFoundException ex){
			ex.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException ex){
				ex.printStackTrace();
			}
			return qbs;
		}
	}

	public ArrayList<User> queryModelForUser(String query){
		Connection conn = null;
		PreparedStatement stmt = null;
		ArrayList<User> users = new ArrayList<User>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfl_players?useSSL=false", Settings.username, Settings.pass);

			stmt = conn.prepareStatement(query);
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()){
				users.add(makeUser(rset));
			}

		} catch (SQLException ex){
			ex.printStackTrace();
		} catch (ClassNotFoundException ex){
			ex.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException ex){
				ex.printStackTrace();
			}
			return users;
		}
	}

	public void updateModel(String update){
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfl_players?useSSL=false", Settings.username, Settings.pass);
			stmt = conn.prepareStatement(update);
			int result = stmt.executeUpdate(update);
		} catch (SQLException ex){
			ex.printStackTrace();
		} catch (ClassNotFoundException ex){
			ex.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException ex){
				ex.printStackTrace();
			}
		}
	}

	private QB makeQB(ResultSet rset) throws SQLException {
		QB qb = new QB();
		qb.PlayerID = rset.getInt("id");
		qb.Team = rset.getString("Team");
		qb.Number = rset.getInt("Number");
		qb.Name = rset.getString("Name");
		qb.Season = rset.getInt("Season");
		qb.PassingAttempts = rset.getInt("Passing_Attempts");
		qb.PassingCompletions = rset.getInt("Passing_Completions");
		qb.Played = rset.getInt("Games_Played");
		qb.PassingYards = rset.getInt("Passing_Yards");
		qb.PassingCompletionPercentage = rset.getInt("Completion_Percentage");
		qb.PassingYardsPerAttempt = rset.getInt("Passing_Yards_Per_Attempt");
		qb.PassingYardsPerCompletion = rset.getInt("Passing_Yards_Per_Completion");
		qb.PassingTouchdowns = rset.getInt("Passing_Touchdowns");
		qb.PassingInterceptions = rset.getInt("Passing_Interceptions");
		qb.PassingRating = rset.getInt("Passing_Rating");
		qb.PassingLong = rset.getInt("Passing_Long");
		qb.PassingSacks = rset.getInt("Sacks");
		qb.RushingAttempts = rset.getInt("Rushing_Attempts");
		qb.RushingYardsPerAttempt = rset.getInt("Rushing_Yards_Per_Attempt");
		qb.RushingTouchdowns = rset.getInt("Rushing_Touchdowns");
		qb.RushingLong = rset.getInt("Rushing_Long");
		qb.Fumbles = rset.getInt("Fumbles");
		qb.FantasyPoints = rset.getInt("Fantasy_Points");
		qb.TeamID = rset.getInt("Team_id");
		return qb;
	}

	private User makeUser(ResultSet rset) throws SQLException {
		User user = new User();
		user.setID(rset.getInt("id"));
		user.setUserName(rset.getString("username"));
		user.setFirstName(rset.getString("firstname"));
		user.setLastName(rset.getString("lastname"));
		user.setAge(rset.getInt("age"));
		return user;
	}
}
