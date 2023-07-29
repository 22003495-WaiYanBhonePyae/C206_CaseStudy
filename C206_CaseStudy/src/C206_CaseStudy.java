import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class C206_CaseStudy {
    public static boolean loggedIn = false;

    public static void main(String[] args) {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("Jame", "Jame@123", "jame123@gmail.com", "biker"));
        userList.add(new User("Mary", "Mary@456", "mary456@gmail.com", "biker1"));
        userList.add(new User("Paul", "Paul@789", "paul789@gmail.com", "biker2"));

        int option = 0;
        while (option != 3) {
            displayMenu();
            option = Helper.readInt("Enter an option > ");
            if (option == 1) {
                addUser(userList);
            } else if (option == 2) {
                if (login(userList)) {
                    System.out.println("**** Account login successful ****");
                    while (loggedIn) {
                        System.out.println();
                        System.out.println("**** USER MENU ****");
                        System.out.println("1. View all users");
                        System.out.println("2. Search users by name");
                        System.out.println("3. Logout");
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

    public static boolean login(ArrayList<User> userList) {
        String email = Helper.readString("Enter Email address > ");
        String pass = Helper.readString("Enter password > ");

        for (User user : userList) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(pass)) {
                loggedIn = true;
                return true;
            }
        }

        return false;
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
                System.out.println("Invalid password! Password must have at least 4 characters.");
            } else {
                break;
            }
        }

        description = Helper.readString("Enter your biography > ");
        userList.add(new User(uName, password, email, description));
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
        return password.length() >= 4;
    }

    public static void viewUsers(ArrayList<User> userList) {
        System.out.println();
        String output = String.format("%-15s %-25s %-50s\n", "USERNAME", "EMAIL", "DESCRIPTION");
        for (User user : userList) {
            output += String.format("%-15s %-25s %-50s\n", user.getUsername(), user.getEmail(), user.getDescription());
        }
        System.out.println(output);
    }

    public static void searchUser(ArrayList<User> userList) {
        boolean found = false;
        String keyword = Helper.readString("Enter an alphabet to search in usernames > ");
        String output = String.format("%-15s %-25s %-50s\n", "USERNAME", "EMAIL", "DESCRIPTION");
        for (User user : userList) {
            if (user.getUsername().toLowerCase().contains(keyword.toLowerCase())) {
                output += String.format("%-15s %-25s %-50s\n", user.getUsername(), user.getEmail(),
                        user.getDescription());
                found = true;
            }
        }
        System.out.println(output);
        if (!found) {
            System.out.println("No users found with the specified alphabet in their usernames.");
        }
    }
}
