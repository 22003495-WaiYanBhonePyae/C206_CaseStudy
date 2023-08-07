import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class C206_CaseStudy {
	public static boolean loggedIn = false;
	public static User currentUser;

	
	public static void main(String[] args) {
		ArrayList<User> userList = new ArrayList<>();
		ArrayList<Event> eventList = new ArrayList<>();
		ArrayList<Discussion> discList = new ArrayList<>();
		ArrayList<Group> groupList = new ArrayList<>();
		ArrayList<Bike> bikeList = new ArrayList<>();
		// Admin is Jame
		userList.add(new User("Jame", "Jame@123", "jame123@gmail.com", "biker", true));
		userList.add(new User("Mary", "Mary@456", "mary456@gmail.com", "biker1", false));
		userList.add(new User("Paul", "Paul@789", "paul789@gmail.com", "biker2", false));
		groupList.add(new Group("T05", "Tigers", "Bike Lovers"));
		eventList.add(new Event("1", "BikerFest", "09/11/2002", "Bedok", "Come join Bikerfest!"));

		discList.add(new Discussion("1", "Engine Overhaul",
				"Here in this discussion we will talk about how to do an engine overhaul"));
//   bikelist
		bikeList.add(new Bike("Trek", "FX 2", "Hybrid bike "));
		bikeList.add(new Bike("Giant", "Advanced Pro 1", "Road bike"));
		bikeList.add(new Bike("Specialized", "Rockhopper", "Mountain bike"));
		bikeList.add(new Bike("Honda", "CBR50P", "Sport bike"));

		int option = 0;
		while (option != 3) {
			displayMenu();
			option = Helper.readInt("Enter an option > ");
			if (option == 1) {
				addUser(userList);
			} else if (option == 2) {
				if (login(userList)) {
					System.out.println("**** Account login successful ****");
					if (currentUser.isAdmin()) {
						adminMenu(userList, eventList, discList, groupList, bikeList);
					} else {
						userMenu(userList);
					}
				} else {
					System.out.println("Login unsuccessful.");
				}
			} else if (option == 3) {
				System.out.println("Bye! Have a great day.");
				break;
			} else {
				System.out.println("Invalid option!");
			}
		}
	}

	public static void adminMenu(ArrayList<User> userList, ArrayList<Event> eventList, ArrayList<Discussion> discList,
			ArrayList<Group> groupList, ArrayList<Bike> bikeList) {
		while (loggedIn) {
			System.out.println();
			System.out.println("**** ADMIN MENU ****");
			System.out.println("1. Add a new user");
			System.out.println("2. View all users");
			System.out.println("3. Delete an existing user");
			System.out.println("4. Add a new bike");
			System.out.println("5. View all bikes");
			System.out.println("6. Delete an existing bike");
			System.out.println("7. Add a new group");
			System.out.println("8. View all groups");
			System.out.println("9. Delete an existing group");
			System.out.println("10. Add a new discussion");
			System.out.println("11. View all discussions");
			System.out.println("12. Delete an existing discussion");
			System.out.println("13. Add a new event");
			System.out.println("14. View all events");
			System.out.println("15. Delete an existing event");
			System.out.println("16. Log out");

			int choose = Helper.readInt("Enter an option > ");
			if (choose == 1) {
				addUser(userList);
			} else if (choose == 2) {
				viewUsers(userList);
			} else if (choose == 3) {
				deleteUser(userList);
			} else if (choose == 4) {
				// Implement method to add a new bike
				addBike(bikeList);
			} else if (choose == 5) {
				// Implement method to view all bikes
				viewBikes(bikeList);
			} else if (choose == 6) {
				// Implement method to delete an existing bike
				deleteBike(bikeList);
			} else if (choose == 7) {
				addGroup(groupList);
				// Implement method to add a new group
			} else if (choose == 8) {
				viewGroups(groupList);
				// Implement method to view all groups
			} else if (choose == 9) {
				deleteGroup(groupList);
				// Implement method to delete an existing group
			} else if (choose == 10) {
				addDiscussion(discList);
				// Implement method to add a new discussion
			} else if (choose == 11) {
				viewDiscussion(discList);
				// Implement method to view all discussions
			} else if (choose == 12) {
				deleteDiscussion(discList);
				// Implement method to delete an existing discussion
			} else if (choose == 13) {
				addEvent(eventList);
				// Implement method to add a new event
			} else if (choose == 14) {
				viewEvents(eventList);
				// Implement method to view all events
			} else if (choose == 15) {
				// Implement method to delete an existing event
				deleteEvent(eventList);
			} else if (choose == 16) {
				System.out.println("Logged out successfully");
				loggedIn = false;
			} else {
				System.out.println("Invalid option!");
			}
		}
	}

	public static void userMenu(ArrayList<User> userList) {
		while (loggedIn) {
			System.out.println();
			System.out.println("**** USER MENU ****");
			System.out.println("1. View all users");
			System.out.println("2. Search users by name");
			System.out.println("3. Log out");

			int choose = Helper.readInt("Enter an option > ");
			if (choose == 1) {
				viewUsers(userList);
			} else if (choose == 2) {
				searchUser(userList);
			} else if (choose == 3) {
				System.out.println("Logged out successfully");
				loggedIn = false;
			} else {
				System.out.println("Invalid option!");
			}
		}
	}

	public static boolean login(ArrayList<User> userList) {
		while (true) {
			String email = Helper.readString("Enter Email address > ");
			boolean emailExists = false;

			for (User user : userList) {
				if (user.getEmail().equalsIgnoreCase(email)) {
					emailExists = true;
					break;
				}
			}

			if (!emailExists) {
				System.out.println("Email does not exist. Please check your email.");
				return false;
			}

			String pass = Helper.readString("Enter password > ");
			for (User user : userList) {
				if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(pass)) {
					loggedIn = true;
					currentUser = user;
					return true;
				}
			}

			System.out.println("Please check your password.");
			return false;
		}
	}

	public static void displayMenu() {
		LocalDate cur = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy");
		String formattedDate = cur.format(formatter);
		System.out.println("Today is " + formattedDate);
		Helper.line(40, "=");
		System.out.println("*** WELCOME TO BIKER COMMUNITY PORTAL ***");
		Helper.line(40, "=");
		System.out.println("1. Create Account");
		System.out.println("2. Login");
		System.out.println("3. Quit");
	}

	public static void addUser(ArrayList<User> userList) {
		String uName = Helper.readString("Enter username > ");
		String email;
		String password;
		String description;

		while (true) {
			email = Helper.readString("Enter email address > ");
			if (!email.contains("@") || !email.contains(".")) {
				System.out.println("Invalid email address! Please enter a valid email.");
			} else {
				boolean emailExists = false;
				for (User user : userList) {
					if (user.getEmail().equalsIgnoreCase(email)) {
						emailExists = true;
						break;
					}
				}
				if (emailExists) {
					System.out.println("Email already exists. Please enter a different email.");
				} else {
					break;
				}
			}
		}

		while (true) {
			password = Helper.readString("Enter strong password > ");
			if (password.length() < 8) {
				System.out.println("Invalid password! Password must have at least 8 characters.");
			} else {
				break;
			}
		}

		description = Helper.readString("Enter your biography > ");
		userList.add(new User(uName, password, email, description, false)); // Set isAdmin to false by default
		loggedIn = true;
		System.out.println("Account created successfully.");
	}

	public static boolean emailExists(ArrayList<User> userList, String email) {
		for (User user : userList) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}

	public static boolean validPassword(String password) {
		return password.length() >= 8;
	}

	// View all Users

	public static void viewUsers(ArrayList<User> userList) {
		System.out.println();
		String output = String.format("%-15s %-25s %-50s %-10s\n", "USERNAME", "EMAIL", "DESCRIPTION", "IS ADMIN");
		for (User user : userList) {
			output += String.format("%-15s %-25s %-50s %-10s\n", user.getUsername(), user.getEmail(),
					user.getDescription(), user.isAdmin() ? "Yes" : "No");
		}
		System.out.println(output);
	}

	public static void searchUser(ArrayList<User> userList) {
		boolean found = false;
		String keyword = Helper.readString("Enter an alphabet to search in usernames > ");
		String output = String.format("%-15s %-25s %-50s %-10s\n", "USERNAME", "EMAIL", "DESCRIPTION", "IS ADMIN");
		for (User user : userList) {
			if (user.getUsername().toLowerCase().contains(keyword.toLowerCase())) {
				output += String.format("%-15s %-25s %-50s %-10s\n", user.getUsername(), user.getEmail(),
						user.getDescription(), user.isAdmin() ? "Yes" : "No");
				found = true;
			}
		}
		System.out.println(output);
		if (!found) {
			System.out.println("No users found with the specified alphabet in their usernames.");
		}
	}

	public static void deleteUser(ArrayList<User> userList) {
		String email = Helper.readString("Enter the email address of the user to delete > ");
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getEmail().equalsIgnoreCase(email)) {
				User userToDelete = userList.get(i);
				System.out.println("User found:");
				userToDelete.display();

				boolean confirmDelete = Helper
						.readBoolean("Are you sure you want to delete this user? (true/false) > ");
				if (confirmDelete) {
					userList.remove(i);
					System.out.println("User deleted successfully.");
				} else {
					System.out.println("User deletion cancelled.");
				}
				return;
			}
		}
		System.out.println("User with the specified email address not found.");
	}

	public static void addGroup(ArrayList<Group> groupList) {

		String groupID = Helper.readString("Enter the group ID >");
		String groupName = Helper.readString("Enter the group name > ");
		String groupDescription = Helper.readString("Enter the group description > ");

		Group newGroup = new Group(groupID, groupName, groupDescription);

		groupList.add(newGroup);

		System.out.println("New group has successfully have been added in the Bikers Community Portal!");

	}

	public static void viewGroups(ArrayList<Group> groupList) {
		int totalGroups = groupList.size();

		Helper.line(60, "=");
		System.out.println(String.format("%-20s %-20s %-20s", "GROUP ID", "GROUP NAME", "DESCRIPTION"));
		Helper.line(60, "=");

		for (Group group : groupList) {
			System.out.println(String.format("%-20s %-20s %-20s", group.getId(), group.getGroupName(),
					group.getGroupDescription()));
		}

		Helper.line(60, "-");
		System.out.println("Group count:" + totalGroups);
		Helper.line(60, "=");
	}

	/*
	 * public static void searchGroup(ArrayList<Group> groupList) { boolean
	 * foundGroup = false; String keywordGroup =
	 * Helper.readString("Enter a keyword to search for Group by its name or ID > "
	 * ); String output = String.format("%-20s %-27s %-50s %-10s\n", "GROUP ID",
	 * "GROUP NAME", "DESCRIPTION");
	 * 
	 * for (Group group : groupList) { if
	 * (group.getId().equalsIgnoreCase(keywordGroup) ||
	 * group.getGroupName().toLowerCase().contains(keywordGroup.toLowerCase())) {
	 * 
	 * output += String.format("%-20s %-27s %-50s %-10d\n", group.getId(),
	 * group.getGroupName(), group.getGroupDescription()); foundGroup = true; } }
	 * 
	 * System.out.println(output);
	 * 
	 * if (!foundGroup) { System.out.
	 * println("There is no group with the keyword of group name or group ID"); } }
	 */

	public static void deleteGroup(ArrayList<Group> groupList) {
		String groupNameToDelete = Helper.readString("Enter the ID of the group to delete > ");
		boolean groupFound = false;

		for (Group group : groupList) {
			if (group.getId().equalsIgnoreCase(groupNameToDelete)) {
				groupFound = true;
				groupList.remove(group);
				System.out.println("Group has been deleted successfully.");
				break;
			}
		}

		if (!groupFound) {
			System.out.println("Group with the specified name is not found.");
		}
	}

	// Add event
	public static boolean addEvent(ArrayList<Event> eventList) {
		String eventID = "";

		// Check for null
		while (eventID.isEmpty() || eventExists(eventList, eventID)) {
			eventID = Helper.readString("Enter event ID > ");
			if (eventID.isEmpty()) {
				System.out.println("Event ID cannot be empty.");
			} else if (eventExists(eventList, eventID)) {
				System.out.println("Event with the same ID already exists. Please enter a unique event ID.");
			}
		}

		String title = "";
		while (title.isEmpty()) {
			title = Helper.readString("Enter event title > ");
			if (title.isEmpty()) {
				System.out.println("Title cannot be empty.");
			}
		}

		String date = "";
		while (true) {
			date = Helper.readString("Enter event date (dd/mm/yyyy) > ");
			if (date.isEmpty()) {
				System.out.println("Date cannot be empty.");
			} else if (isValidDate(date)) {
				break;
			} else {
				System.out.println("Invalid date format. Please enter a valid date in dd/mm/yyyy format.");
			}
		}

		String location = "";
		while (location.isEmpty()) {
			location = Helper.readString("Enter event location > ");
			if (location.isEmpty()) {
				System.out.println("Location cannot be empty.");
			}
		}

		String description = "";
		while (description.isEmpty()) {
			description = Helper.readString("Enter event description > ");
			if (description.isEmpty()) {
				System.out.println("Description cannot be empty.");
			}
		}

		eventList.add(new Event(eventID, title, date, location, description));
		System.out.println("Event added successfully.");
		return true;
	}

	// Check if an event with the given ID already exists
	public static boolean eventExists(ArrayList<Event> eventList, String eventID) {
		for (Event event : eventList) {
			if (event.getEventID().equalsIgnoreCase(eventID)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidDate(String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate.parse(date, formatter);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	// View event
	public static void viewEvents(ArrayList<Event> eventList) {
		System.out.println();
		Helper.line(100, "-");
		System.out.printf("%-10s %-25s %-12s %-20s %s\n", "EVENT ID", "TITLE", "DATE", "LOCATION", "DESCRIPTION");
		Helper.line(100, "-");

		for (Event event : eventList) {
			String eventID = event.getEventID();
			String title = event.getTitle();
			String date = event.getDate();
			String location = event.getLocation();
			String description = event.getDescription();

			String formattedEntry = String.format("%-10s %-25s %-12s %-20s %s", eventID, title, date, location,
					description);
			System.out.println(formattedEntry);
		}

		Helper.line(100, "-");
	}

	// Delete Event
	public static boolean deleteEvent(ArrayList<Event> eventList) {
		String eventID = Helper.readString("Enter the event ID to delete > ");

		for (int i = 0; i < eventList.size(); i++) {
			if (eventList.get(i).getEventID().equalsIgnoreCase(eventID)) {
				Event eventToDelete = eventList.get(i);

				System.out.println("Event found:");
				Helper.line(100, "-");
				System.out.println("Event ID: " + eventToDelete.getEventID());
				System.out.println("Title: " + eventToDelete.getTitle());
				System.out.println("Date: " + eventToDelete.getDate());
				System.out.println("Location: " + eventToDelete.getLocation());
				System.out.println("Description: " + eventToDelete.getDescription());
				Helper.line(100, "-");
				String confirmDeleteInput = Helper.readString("Are you sure you want to delete this event? (y/n) > ");
				if (confirmDeleteInput.equalsIgnoreCase("y")) {
					eventList.remove(i);
					System.out.println("Event deleted successfully.");
					return true; // Event was successfully deleted
				} else {
					System.out.println("Event deletion cancelled.");
					return false; // Deletion was cancelled
				}
			}
		}

		System.out.println("Event with the specified ID not found.");
		return false; // Event was not found
	}

	// Add Discussion
	public static boolean addDiscussion(ArrayList<Discussion> discList) {
		String discID = Helper.readString("Enter Discussion ID > ");

		if (discExists(discList, discID)) {
			System.out.println("Discussion with the same ID already exists. Please enter a unique event ID.");
			return false;
		}

		String discTitle = Helper.readString("Enter Discussion title > ");

		String discDescription = Helper.readString("Enter event description > ");

		discList.add(new Discussion(discID, discTitle, discDescription));
		System.out.println("Discussion added successfully.");
		return true;
	}

	// Helper method to check if an event with the given ID already exists
	public static boolean discExists(ArrayList<Discussion> discList, String discID) {
		for (Discussion disc : discList) {
			if (disc.getDiscID().equalsIgnoreCase(discID)) {
				return true;
			}
		}
		return false;
	}

	// View all Discussion
	public static void viewDiscussion(ArrayList<Discussion> discList) {
		System.out.println();
		Helper.line(100, "-");
		System.out.printf("%-10s %-25s %-12s\n", "DISCUSSION ID", "TITLE", "DESCRIPTION");
		Helper.line(100, "-");

		for (Discussion disc : discList) {
			String discID = disc.getDiscID();
			String title = disc.getTitle();
			String description = disc.getDescription();

			int maxLength = 10;
			String wrappedMessage = wrapMessage(description, maxLength);

			String formattedEntry = String.format("%-10s %-25s %-12s", discID, title, wrappedMessage);
			System.out.println(formattedEntry);
		}

		Helper.line(100, "-");
	}

	// Delete Discussion
	public static boolean deleteDiscussion(ArrayList<Discussion> discList) {
		String delDiscID = Helper.readString("Enter the Discussion ID to delete > ");

		for (int i = 0; i < discList.size(); i++) {
			if (discList.get(i).getDiscID().equalsIgnoreCase(delDiscID)) {
				Discussion discToDelete = discList.get(i);

				System.out.println("Discussion found:");
				Helper.line(100, "-");
				System.out.println("Discussion ID: " + discToDelete.getDiscID());
				System.out.println("Title: " + discToDelete.getTitle());
				System.out.println("Description: " + discToDelete.getDescription());
				Helper.line(100, "-");
				String confirmDeleteInput = Helper
						.readString("Are you sure you want to delete this discussion? (yes/no) > ");
				if (confirmDeleteInput.equalsIgnoreCase("yes")) {
					discList.remove(i);
					System.out.println("Discussion deleted successfully.");
					return true; // Discussion was successfully deleted
				} else {
					System.out.println("Discussion deletion cancelled.");
					return false; // Deletion was cancelled
				}
			}
		}

		System.out.println("Discussion with the specified ID not found.");
		return false; // Discussion was not found
	}

	// Wrap Messages
	public static String wrapMessage(String message, int maxLength) {
		StringBuilder wrappedMessage = new StringBuilder();
		StringBuilder currentLine = new StringBuilder();

		String[] words = message.split(" ");
		for (String word : words) {
			if (currentLine.length() + word.length() + 1 <= maxLength) {
				currentLine.append(word).append(" ");
			} else {
				wrappedMessage.append(currentLine.toString().trim()).append("\n");
				currentLine = new StringBuilder(word).append(" ");
			}
		}

		wrappedMessage.append(currentLine.toString().trim());
		return wrappedMessage.toString();
	}

	// AddBike

	public static void addBike(ArrayList<Bike> bikeList) {

		Helper.line(30, "=");
		System.out.println("**** ADD NEW BIKE ****");
		Helper.line(30, "=");

		String bikeBrand = Helper.readString("Enter bike brand > ");
		String bikeModel = Helper.readString("Enter bike Model > ");
		String bikeDescription = Helper.readString("Enter bike description > ");
		bikeList.add(new Bike(bikeBrand, bikeModel, bikeDescription));
		System.out.println("Bike added successfully.");
	}

	// ViewBike
	public static void viewBikes(ArrayList<Bike> bikeList) {

		Helper.line(30, "=");
		System.out.println("**** DISPLAY BIKE LIST ****");
		Helper.line(30, "=");

		if (!bikeList.isEmpty()) {
			String output = "";
			String table = "_________________________________________________________________";

			output += String.format("| %-15s | %-25s | %-10s \n", "BRAND", "MODEL", "DESCRIPTION");
			output += table + "\n";
			for (int i = 0; i < bikeList.size(); i++) {
				output += String.format("| %-15s | %-25s | %-10s \n", bikeList.get(i).getBikeBrand(),
						bikeList.get(i).getBikeModel(), bikeList.get(i).getBikeDescription());
				output += table + "\n";
			}

			System.out.println(table);
			System.out.println(output);
		} else {
			System.out.println("There is no bike list.");
		
		}
	
	}

	// deletebike
	public static boolean deleteBike(ArrayList<Bike> bikeList) {
		Helper.line(30, "=");
		System.out.println("**** DELETE A BIKE ****");
		Helper.line(30, "=");

		String brand = Helper.readString("Enter bike brand > ");
		String model = Helper.readString("Enter bike model > ");
		System.out.println();
		boolean bikeFound = false;

		for (int i = 0; i < bikeList.size(); i++) {
			if (bikeList.get(i).getBikeBrand().equalsIgnoreCase(brand)
					&& bikeList.get(i).getBikeModel().equalsIgnoreCase(model)) {

				bikeList.get(i).display();
				bikeFound = true;

				char confirmDeletion = Helper.readChar("Confirm deletion? (y/n) > ");

				if (confirmDeletion == 'y') {
					bikeList.remove(i);
					System.out.println("*** Bike has been successfully deleted from your profile***");
					break;
				} else if (confirmDeletion == 'n') {
					System.out.println("*** Bike is not deleted ***");
					break;
				} else {
					System.out.println("Invalid input");
				}
			}
		}
		return bikeFound;
	}

	/* @param eventList*@param event_missing */

	public static void addNewEvent(ArrayList<Event> eventList, Event event_missing) {
		// TODO Auto-generated method stub
		return;
	}

	/*
	 * 
	 * @param eventList
	 * 
	 * @return
	 */
	public static String viewEvent(ArrayList<Event> eventList) {
		// TODO Auto-generated method stub
		return null;
		
	}
}