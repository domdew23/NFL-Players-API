import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QB {
  public int PlayerID;
  public int Season;
  public String Team;
  public int Number;
  public String Name;
  public String Position;
  public int Played;
  public int PassingAttempts;
  public int PassingCompletions;
  public int PassingYards;
  public int PassingCompletionPercentage;
  public int PassingYardsPerAttempt;
  public int PassingYardsPerCompletion;
  public int PassingTouchdowns;
  public int PassingInterceptions;
  public int PassingRating;
  public int PassingLong;
  public int PassingSacks;
  public int PassingSackYards;
  public int RushingAttempts;
  public int RushingYards;
  public int RushingYardsPerAttempt;
  public int RushingTouchdowns;
  public int RushingLong;
  public int FantasyPoints;
  public int Fumbles;
  public int TeamID;
}
