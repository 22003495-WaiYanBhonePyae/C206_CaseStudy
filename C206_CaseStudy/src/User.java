/**
 * 
 */

/**
 * @author waiya
 *
 */
public class User {
	private String Username;
	private String password;
	private String email;
	private String description;
	
	
	
	public User(String Username, String password,String email, String description) {
		this.Username = Username;
		this.email = email;
		this.password = password;
		this.description = description;
		
	}
	
	
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String UserName) {
		this.Username = UserName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public void display() {
		System.out.println("Username:"+ Username);
		System.out.println("Password: "+ password);
		System.out.println("Email: "+ email);
		System.out.println("Bio: " + description);
	}
	
	
	
	

}
