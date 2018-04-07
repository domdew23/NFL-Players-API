import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	private String userName = null;
	private String firstName = null;
	private String lastName = null;
	private String password = null;
	private int age = 0;
	private int id = 0;

	public String getUserName() { return this.userName; }
	public String getFirstName() { return this.firstName; }
	public String getLastName() { return this.lastName; }
	public String getPassword() { return this.password; }
	public int getAge() { return this.age; }
	public int getID() { return this.id; }

	public void setUserName(String userName) { this.userName = userName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setPassword(String password) { this.password = password; }
	public void setAge(int age) { this.age = age; }
	public void setID(int id) { this.id = id; }

}