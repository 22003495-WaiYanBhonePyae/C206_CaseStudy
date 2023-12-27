import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class C206_CaseStudy {
	
	private static final int DELETE_BIKE = 6;
	private static final int VIEW_BIKERS = 5;
	private static final int ADD_BIKE = 4;
	private static final int DELETE_EVENT = 13;
	private static final int VIEW_EVENTS = 12;
	private static final int ADD_EVENT = 11;
	private static final int DELETE_USER = 3;
	private static final int VIEW_USERS = 2;
	private static final int LOGIN = 2;
	private static final int ADD_USER = 1;
	private static final int OPTION_QUIT = 3;
	public static boolean loggedIn = false;
	public static User currentUser;
	public static final int ADD_GROUP = 1;
	public static final int VIEW_GROUPS = 2;
	public static final int DELETE_GROUP = 3;

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

		int option = 0;
		while (option != OPTION_QUIT) {
			displayMenu();
			option = Helper.readInt("Enter an option > ");
			if (option == ADD_USER) {
				addUser(userList);
			} else if (option == LOGIN) {
				if (login(userList)) {
					System.out.println("**** Account login successful ****");
					if (currentUser.isAdmin()) {
						adminMenu(userList, eventList, discList, groupList, bikeList);
					} else {
						userMenu(userList, eventList, discList, groupList, bikeList);
					}
				} else {
					System.out.println("Login unsuccessful.");
				}
			} else if (option == OPTION_QUIT) {
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
			System.out.println("7. GroupMenu");
			System.out.println("8. Add a new discussion");
			System.out.println("9. View all discussions");
			System.out.println("10. Delete an existing discussion");
			System.out.println("11. Add a new event");
			System.out.println("12. View all events");
			System.out.println("13. Delete an existing event");
			System.out.println("14. Log out");

			int choose = Helper.readInt("Enter an option > ");
			if (choose == ADD_USER) {
				addUser(userList);
			} else if (choose == VIEW_USERS) {
				viewUsers(userList);
			} else if (choose == DELETE_USER) {
				deleteUser(userList);
			} else if (choose == ADD_BIKE) {
				// Implement method to add a new bike
				addBike(bikeList);
			} else if (choose == VIEW_BIKERS) {
				// Implement method to view all bikes
				viewBikes(bikeList);
			} else if (choose == DELETE_BIKE) {
				// Implement method to delete an existing bike
				deleteBike(bikeList);
			} else if (choose == 7) {
				groupMenu(groupList);
				// Implement method to add a groupMenu
			} else if (choose == 8) {
				addDiscussion(discList);
				// Implement method to add a new discussion
			} else if (choose == 9) {
				viewDiscussion(discList);
				// Implement method to view all discussions
			} else if (choose == 10) {
				deleteDiscussion(discList);
				// Implement method to delete an existing discussion
			} else if (choose == ADD_EVENT) {
				addEvent(eventList);
				// Implement method to add a new event
			} else if (choose == VIEW_EVENTS) {
				viewEvents(eventList);
				// Implement method to view all events
			} else if (choose == DELETE_EVENT) {
				// Implement method to delete an existing event
				deleteEvent(eventList);
			} else if (choose == 14) {
				System.out.println("Logged out successfully");
				loggedIn = false;
			} else {
				System.out.println("Invalid option!");
			}
		}
	}

	public static void userMenu(ArrayList<User> userList, ArrayList<Event> eventList, ArrayList<Discussion> discList,
			ArrayList<Group> groupList, ArrayList<Bike> bikeList) {

		while (loggedIn) {
			System.out.println();
			System.out.println("**** USER MENU ****");
			System.out.println("1. View all users");
			System.out.println("2. Search users by name");
			System.out.println("3. Add a new bike");
			System.out.println("4. View all bikes");
			System.out.println("5. Add a new group");
			System.out.println("6. View all groups");
			System.out.println("7. Add a new discussion");
			System.out.println("8. View all discussions");
			System.out.println("9. Add a new event");
			System.out.println("10. View all events");
			System.out.println("11. Log out");

			int choose = Helper.readInt("Enter an option > ");
			if (choose == 1) {
				viewUsers(userList);
			} else if (choose == 2) {
				searchUser(userList);

			} else if (choose == 3) {
				addBike(bikeList);
			} else if (choose == 4) {
				viewBikes(bikeList);
			} else if (choose == 5) {
				String groupID = Helper.readString("Enter the group ID >");
				String groupName = Helper.readString("Enter the group name > ");
				String groupDescription = Helper.readString("Enter the group description > ");
				addGroup(groupList, groupID, groupName, groupDescription);
			} else if (choose == 6) {
				viewGroups(groupList);
			} else if (choose == 7) {
				addDiscussion(discList);
			} else if (choose == 8) {
				viewDiscussion(discList);
			} else if (choose == 9) {
				addEvent(eventList);
				// Implement method to add a new event
			} else if (choose == 10) {
				viewEvents(eventList);

			} else if (choose == 11) {
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

	// Adding new user
	public static boolean addUser(ArrayList<User> userList) {
		String name = "";
		// Check name for null
		while (name.isEmpty()) {
			name = Helper.readString("Enter usename > ");
			if (name.isEmpty()) {
				System.out.println("Name cannot be empty.");
			}
		}

		String email = "";
		while (true) {
			email = Helper.readString("Enter email > ");
			if (email.isEmpty()) {
				System.out.println("Email cannot be empty.");
			} else if (emailExists(userList, email)) {
				System.out.println("Email is already exists.");
			} else if (!validEmail(email)) {
				System.out.println("Email format is invalid.");
			} else {
				break; // Break out of the loop when a valid email is provided
			}
		}

		String password = "";
		while (true) {
			password = Helper.readString("Enter password > ");
			if (password.isEmpty()) {
				System.out.println("Password cannot be empty.");
			} else if (password.length() < 8) {
				System.out.println("Password must have at least 8 characters.");
			} else {
				break; // Break out of the loop when the password is valid
			}
		}

		String bio = "";

		bio = Helper.readString("Enter bio > ");

		userList.add(new User(name, password, email, bio, false));
		System.out.println("*** Account created successfully ***");
		return true;
	}

	public static void addUser(ArrayList<User> userList, User user_missing) {
		// TODO Auto-generated method stub
		return;

	}

	public static boolean validEmail(String email) {
		return email.contains("@") && email.contains(".");
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

	public static String retrieveAllUser(ArrayList<User> userList) {
		String output = "";
		for (User user : userList) {
			output += String.format("%-15s %-25s %-15s %-10s\n", user.getUsername(), user.getEmail(),
					user.getDescription(), user.isAdmin() ? "Admin" : "User");
		}
		return output;
	}

	// View all Users
	public static void viewUsers(ArrayList<User> userList) {
		System.out.println();
		String output = String.format("%-15s %-25s %-15s %-10s\n", "USERNAME", "EMAIL", "DESCRIPTION", "ROLE");
		output += retrieveAllUser(userList);

		System.out.println(output);
	}

	// search user
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

	// Delete User
	public static boolean confirmDelete(ArrayList<User> userList, String email, char confirm) {
		for (User user : userList) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				if (confirm == 'y' || confirm == 'Y') {
					userList.remove(user);

					return true;
				} else {

					return false;
				}
			}
		}
		System.out.println("User with the specified email address not found.");
		return false;
	}

	public static void deleteUser(ArrayList<User> userList) {
		String email = Helper.readString("Enter email address > ");
		char confirm = Helper.readChar("Are you sure to delete this user? (y/n) > ");
		boolean isDeleted = confirmDelete(userList, email, confirm);

		if (isDeleted) {
			System.out.println("User has been deleted successfully.");
		} else {
			System.out.println("User deletion is cancelled.");
		}
	}

	public static void addGroup(ArrayList<Group> groupList, String groupID, String groupName, String groupDescription) {

		if (groupalrExists(groupList, groupID)) {
			System.out.println("A group with the same GROUP ID already exists. Please enter a different ID .");
			return;
		}

		if (groupName.isEmpty()) {
			System.out.println("Group name cannot be empty.");
			return;
		}

		if (groupDescription.isEmpty()) {
			System.out.println("Group description cannot be empty.");
			return;
		}

		Group newGroup = new Group(groupID, groupName, groupDescription);
		groupList.add(newGroup);

		System.out.println("New group has been successfully added in the Bikers Community Portal!");
	}

	// method to check if a group with the given ID or group name already
	// exists
	public static boolean groupalrExists(ArrayList<Group> groupList, String groupID) {
		for (Group group : groupList) {
			if (group.getId().equalsIgnoreCase(groupID)) {
				return true;
			}
		}
		return false;
	}

	public static String viewGroups(ArrayList<Group> groupList) {
		int totalGroups = groupList.size();
		String output = "";
		Helper.line(60, "=");
		System.out.printf("%-20s %-20s %-20s", "GROUP ID", "GROUP NAME", "DESCRIPTION");
		Helper.line(60, "=");

		for (Group group : groupList) {
			output += String.format("%-20s %-20s %-20s\n", group.getId(), group.getGroupName(),
					group.getGroupDescription());
		}

		Helper.line(60, "=");
		System.out.println(output);
		System.out.println("Group count:" + totalGroups);
		Helper.line(60, "=");

		return output;
	}

	public static boolean deleteGroup(ArrayList<Group> groupList, String groupNameToDelete) {
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
		return groupFound;
	}

	public static void groupMenu(ArrayList<Group> groupList) {
		int option = 0;
		while (option != 4) {
			System.out.println("***** GROUP MENU *****");
			System.out.println("1. Add a new group");
			System.out.println("2. View all groups");
			System.out.println("3. Delete an existing group");
			System.out.println("4. Back to main menu");
			option = Helper.readInt("Enter an option > ");

			if (option == ADD_GROUP) {
				String groupID = Helper.readString("Enter the group ID >");
				String groupName = Helper.readString("Enter the group name > ");
				String groupDescription = Helper.readString("Enter the group description > ");
				addGroup(groupList, groupID, groupName, groupDescription);
			} else if (option == VIEW_GROUPS) {
				viewGroups(groupList);
			} else if (option == DELETE_GROUP) {
				String groupNameToDelete = Helper.readString("Enter the ID of the group to delete > ");
				deleteGroup(groupList, groupNameToDelete);
			} else if (option == 4) {
				System.out.println("Exit main menu");
				break;
			} else {
				System.out.println("Invalid option!");

			}
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

	public static void addBike(ArrayList<Bike> bikeList, Bike bike_missing) {
		// TODO Auto-generated method stub

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

	public static String viewBike(ArrayList<Bike> bikeList) {
		// TODO Auto-generated method stub
		return null;

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
					System.out.println("Inva.lid input");
				}
			}
		}
		return bikeFound;
	}

	public static boolean deleteBike(ArrayList<Bike> bikeList, String brand, char c) {
		for (Bike bike : bikeList) {

			if (bike.getBikeBrand().equalsIgnoreCase(brand)) {
				if (c == 'y' || c == 'Y') {
					bikeList.remove(bike);

					return true;
				} else {

					return false;
				}
			}
		}
		System.out.println("Bike is not deleted");
		return false;
	}

	// Method for Test case "addEvent"
	public static void addNewEvent(ArrayList<Event> eventList, Event newEvent) {
		Event event;
		for (int i = 0; i < eventList.size(); i++) {
			event = eventList.get(i);
			if (event.getEventID().equalsIgnoreCase(event.getEventID()))
				return;
		}
		if ((newEvent.getEventID().isEmpty()) || (newEvent.getDescription().isEmpty())) {
			return;
		}
		eventList.add(newEvent);
	}

	// Method for Test Case "viewEvent"
	public static String viewNewEvent(ArrayList<Event> eventList) {
		String output = "";
		for (int i = 0; i < eventList.size(); i++) {

			output += String.format("%-10s %-25s %-12s %-20s %s", eventList.get(i).getEventID(),
					eventList.get(i).getTitle(), eventList.get(i).getDate(), eventList.get(i).getLocation(),
					eventList.get(i).getDescription());
		}

		return output;
	}

	// Method for Test Case "deleteEvent"
	public static boolean deleteEvent(ArrayList<Event> eventList, Event eventDelete) {
		if (eventList.contains(eventDelete)) {
			eventList.remove(eventDelete);
			return true; // Event successfully deleted
		} else {
			return false; // Event is not found in the list
		}
	}

}
