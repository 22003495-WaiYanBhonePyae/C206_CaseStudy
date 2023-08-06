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
		// Admin is Jame
		userList.add(new User("Jame", "Jame@123", "jame123@gmail.com", "biker", true));
		userList.add(new User("Mary", "Mary@456", "mary456@gmail.com", "biker1", false));
		userList.add(new User("Paul", "Paul@789", "paul789@gmail.com", "biker2", false));

		eventList.add(new Event("1", "BikerFest", "09/11/2002", "Bedok", "Come join Bikerfest!"));

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
						adminMenu(userList, eventList);
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

	public static void adminMenu(ArrayList<User> userList, ArrayList<Event> eventList) {
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
			} else if (choose == 5) {
				// Implement method to view all bikes
			} else if (choose == 6) {
				// Implement method to delete an existing bike
			} else if (choose == 7) {
				// Implement method to add a new group
			} else if (choose == 8) {
				// Implement method to view all groups
			} else if (choose == 9) {
				// Implement method to delete an existing group
			} else if (choose == 10) {
				// Implement method to add a new discussion
			} else if (choose == 11) {
				// Implement method to view all discussions
			} else if (choose == 12) {
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
			if (!validEmail(email)) {
				System.out.println("Invalid email address! Please enter a valid email.");
			} else {
				String pass = Helper.readString("Enter password > ");
				for (User user : userList) {
					if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(pass)) {
						loggedIn = true;
						currentUser = user;
						return true;
					}
				}
				System.out.println("Please check your email and password.");
				return false;
			}
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
			if (!validEmail(email)) {
				System.out.println("Invalid email address! Please enter a valid email.");
			} else if (emailExists(userList, email)) {
				System.out.println("Email already exists. Please enter a different email.");
			} else {
				break;
			}
		}

		while (true) {
			password = Helper.readString("Enter strong password > ");
			if (!validPassword(password)) {
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

//Add event
	public static boolean addEvent(ArrayList<Event> eventList) {
		String eventID = Helper.readString("Enter event ID > ");

		if (eventExists(eventList, eventID)) {
			System.out.println("Event with the same ID already exists. Please enter a unique event ID.");
			return false;
		}

		String title = Helper.readString("Enter event title > ");

		String date = "";
		while (true) {
			date = Helper.readString("Enter event date (dd/mm/yyyy) > ");
			if (isValidDate(date)) {
				break;
			} else {
				System.out.println("Invalid date format. Please enter a valid date in dd/mm/yyyy format.");
			}
		}

		String location = Helper.readString("Enter event location > ");
		String description = Helper.readString("Enter event description > ");

		eventList.add(new Event(eventID, title, date, location, description));
		System.out.println("Event added successfully.");
		return true;
	}

	// Helper method to check if an event with the given ID already exists
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

//Delete Event
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
}
