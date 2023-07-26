import java.util.ArrayList;

public class C206_CaseStudy {
	  
	public static boolean loggedIn = false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("Jame", "Jame@123", "jame123@gmail.com", "biker"));
        userList.add(new User("Mary", "Mary@456", "mary456@gmail.com", "biker1"));
        userList.add(new User("Paul", "Paul@789", "paul789@gmail.com", "biker2"));

        int option = -1;
        while (option != 3) {
            displayMenu();
            option = Helper.readInt("Enter an option > ");

            if (option == 1) {
                createUser(userList);
            }
            else if(option == 2) {
            	login(userList);
            	while(loggedIn) {
            		System.out.println("1. View all users");
            		System.out.println("2. Search users by name");
            		System.out.println("3. Logout");
            		int choose = Helper.readInt("Enter an option > ");
            		if(choose == 1) {
            			viewUsers(userList);
            		}
            		else if(choose == 2) {
            			searchUser(userList);
            		}
            		else if(choose == 3) {
            			System.out.println("Logged out successfully");
            			loggedIn = false;
            		}
            		else {
            			System.out.println("Invalid!");
            		}
            	}
            	if(!loggedIn) {
            		System.out.println("Login unsuccessful. Please try again.");
            		
            	}
            	
            }
            else if(option == 3) {
            	System.out.println("Bye! Have a great day.");
            	break;
            }
            
        }
	}

public static boolean login(ArrayList<User> userList) {
	 String email = Helper.readString("Enter Email address > ");
     String pass = Helper.readString("Enter password > ");

     for (User user : userList) {
         if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(pass)) {
             System.out.println("**** Account login successful ****");
             loggedIn = true;
             return true;
         }
     }

     return false;
 
}
	
	 public static void displayMenu() {
	        Helper.line(40, "=");
	        System.out.println("*** WELCOME TO BIKER COMMUNITY PORTAL ***");
	        Helper.line(40, "=");
	        System.out.println("1. Create Account");
	        System.out.println("2. Login");
	        System.out.println("3. Quit");
	    }
	 
	 public static void createUser(ArrayList<User> userList) {
	        String uName = Helper.readString("Enter username > ");
	        String email = Helper.readString("Enter email address > ");
	        while (!validEmail(email)) {
	            System.out.println("Email must include '@' and '.'");
	            email = Helper.readString("Enter email address > ");
	        }
	        String password = Helper.readString("Enter strong password >");
	        while (!validPassword(password)) {
	            System.out.println("Password does not meet requirements!");
	            password = Helper.readString("Enter strong password >");
	        }
	        String description = Helper.readString("Enter your biography > ");
	        userList.add(new User(uName, email, password, description));
	        System.out.println("Account created successfully.");
	    }
	 
	 public static boolean validEmail(String email) {
	        return email.contains("@") && email.contains(".");
	    }

	    public static boolean validPassword(String password) {
	        return password.length() >= 4;
	    }

	    public static void viewUsers(ArrayList<User> userList) {
	        
	            String output = String.format("%-10s %-20s %-10s\n", "USERNAME", "EMAIL", "DESCRIPTION");
	            for (User user : userList) {
	                output += String.format("%-10s %-20s %-10s\n", user.getUsername(), user.getEmail(), user.getDescription());
	            }
	            System.out.println(output);
	         
	    }
	    
	    public static void searchUser(ArrayList<User>userList) {
	    	boolean found = false;
	        String keyword = Helper.readString("Enter an alphabet to search in usernames > ");
	        String output = String.format("%-10s %-20s %-10s\n", "USERNAME", "EMAIL", "DESCRIPTION");
	        for (User user : userList) {
	            if (user.getUsername().toLowerCase().contains(keyword.toLowerCase())) {
	                output += String.format("%-10s %-20s %-10s\n", user.getUsername(), user.getEmail(), user.getDescription());
	                found = true;
	            }
	        }
	        System.out.println(output);
	        if (!found) {
	            System.out.println("No users found with the specified alphabet in their usernames.");
	        }
	    
	    }
	    
	   
}
	   