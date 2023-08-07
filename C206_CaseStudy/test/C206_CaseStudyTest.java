import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class C206_CaseStudyTest {
	private User user1;
	private User user2;
	private User user3;

	private ArrayList<User> userList;

	private Group gp1;
	private Group gp2;

	private ArrayList<Group> groupList;

	private Bike bike1;
	private Bike bike2;
	private Bike bike3;

	private ArrayList<Bike> bikeList;
	private ArrayList<Event> eventList;

	@Before
	public void setUp() throws Exception {
		eventList = new ArrayList<>();
		user1 = new User("John", "12345678", "john123@gmail.com", "Biker enthusiast", false);
		user2 = new User("Mary", "abcdefgh", "mary456@gmail.com", "Biker specialist", false);
		user3 = new User("Admin", "admin123", "admin@gmail.com", "Biker community admin", true);

		userList = new ArrayList<User>();

		groupList = new ArrayList<Group>();
		gp1 = new Group("2202", "Tigers", "Biking");
		gp2 = new Group("2203", "Lions", "nerds");

		bike1 = new Bike("Trek", "FX 2", "Hybrid bike");
		bike2 = new Bike("Giant", "Advanced Pro 1", "Road bike");
		bike3 = new Bike("Specialized", "Rockhopper", "Mountain bike");

		bikeList = new ArrayList<Bike>();
	}

	@Test
	public void testAddUser_NormalConditions() {
		// Test that initially, the userList has a size of zero
		assertEquals("Test that initial userList size is 0", 0, userList.size());

		// Simulate user input and call addUser with the simulated input
		C206_CaseStudy.currentUser = user1; // Simulate the currentUser
		C206_CaseStudy.loggedIn = true; // Simulate logged in state
		C206_CaseStudy.addUser(userList);

		// Test that after calling addUser, the userList has a size of 1
		assertEquals("Test that userList size is 1", 1, userList.size());
		assertEquals("Test that user added is the same as user at index 0", "John", userList.get(0).getUsername());
	}

	@Test
	public void testViewUsers() {
		// Test that initially, the userList has a size of zero
		assertEquals("Test that initial userList size is 0", 0, userList.size());

		// Test that we can view the users in the userList after adding them
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		C206_CaseStudy.viewUsers(userList);

		assertEquals("Test that userList size is 3", 3, userList.size());

		// Test viewing an empty user list
		userList.clear();
		C206_CaseStudy.viewUsers(userList);
		assertEquals("Test that userList size is 0 after clearing", 0, userList.size());
	}

	@Test
	public void testDeleteUser() {
		// Test that initially, the userList has a size of zero
		assertEquals("Test that initial userList size is 0", 0, userList.size());

		// Test deleting a user from the userList
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		assertTrue("Test that user2 exists in userList", userList.contains(user2));

		C206_CaseStudy.deleteUser(userList);
		assertFalse("Test that user2 is removed from userList after deletion", userList.contains(user2));

	}

	@After
	public void tearDown() throws Exception {
		user1 = null;
		user2 = null;
		user3 = null;

		userList = null;

		bike1 = null;
		bike2 = null;
		bike3 = null;

		bikeList = null;
		eventList.clear();
	}

	@Test
	public void testAddGroup() {
		ArrayList<Group> groupList = new ArrayList<>();
		C206_CaseStudy.addGroup(groupList);

		assertEquals(1, groupList.size());
		// Add more assertions as needed based on the expected behavior of the method
	}

	@Test
	public void testViewGroups() {
		ArrayList<Group> groupList = new ArrayList<>();
		Group newGroup = new Group("Group 1", "Description 1", "ID1");
		groupList.add(newGroup);

		C206_CaseStudy.viewGroups(groupList);

		// Assert the output of the method based on the expected behavior
	}

	@Test
	public void testDeleteGroup() {
		ArrayList<Group> groupList = new ArrayList<>();
		Group newGroup = new Group("GroupToDelete", "Description", "ID1");
		groupList.add(newGroup);

		C206_CaseStudy.deleteGroup(groupList);

		assertEquals(0, groupList.size());
		// Add more assertions as needed based on the expected behavior of the method
	}

	@Test
	public void testAddBike() {
		// Test that initially, the bikeList has a size of zero
		assertEquals("Test that initial bikeList size is 0", 0, bikeList.size());

		// Simulate user input and call addBike with the simulated input
		C206_CaseStudy.addBike(bikeList);

		// Test that after calling addBike, the bikeList has a size of 1
		assertEquals("Test that bikeList size is 1", 1, bikeList.size());

		// Test that the bike added is the same as the bike at index 0 in the bikeList
		assertEquals("Test that bike added is same as bike at index 0", "Trek", bikeList.get(0).getBikeBrand());
	}

	@Test
	public void testViewBikes() {
		// Test that initially, the bikeList has a size of zero
		assertEquals("Test that initial bikeList size is 0", 0, bikeList.size());

		// Test that we can view the bikes in the bikeList after adding them
		bikeList.add(bike1);
		bikeList.add(bike2);
		bikeList.add(bike3);
		C206_CaseStudy.viewBikes(bikeList);

		assertEquals("Test that bikeList size is 3", 3, bikeList.size());
	}

	@Test
	public void testDeleteBike() {
		// Test that initially, the bikeList has a size of zero
		assertEquals("Test that initial bikeList size is 0", 0, bikeList.size());

		// Test deleting a bike from the bikeList
		bikeList.add(bike1);
		bikeList.add(bike2);
		bikeList.add(bike3);
		assertTrue("Test that bike2 exists in bikeList", bikeList.contains(bike2));

		C206_CaseStudy.deleteBike(bikeList);
		assertFalse("Test that bike2 is removed from bikeList after deletion", bikeList.contains(bike2));
		assertEquals("Test that bikeList has a size of 2 after deletion", 2, bikeList.size());
	}

	@Test

	public void testAddEvent() {
		// Test Case 1 (Boundary condition)
		assertNotNull("Test if eventList is valid for adding events", eventList);
		assertEquals("Test that eventList is empty.", 0, eventList.size());
		assertEquals(0, eventList.size());

		// Test Case 2 (Normal condition)
		Event a = new Event("1", "RP Bikers", "10/11/2002", "Woodlands", "Apply if interested!");
		eventList.add(a);
		assertEquals("Test that eventList is now 1.", 1, eventList.size());

		// Test Case 3 (Error condition)
		Event event_missing = new Event("", "Biking Event@RP", "09/11/2000", "Yishun", "Register now!");
		C206_CaseStudy.addNewEvent(eventList, event_missing);
		assertEquals("Test that eventList remains unchanged.", 1, eventList.size());
	}

	@Test
	public void testViewEvent() {

		// Test Case 1 (Boundary Condition)
		assertNotNull("Test if eventList is empty", eventList);
		assertEquals("Test that eventList is empty.", 0, eventList.size());
		assertEquals(0, eventList.size());

		// Test Case 2 (Error Condition)
		Event event1 = new Event("4", "RP Bikers", "10/11/2005", "Woodlands", "Apply if interested!");
		eventList.add(event1);
		String wrongOutputEvent = "1 RP Bikers 10/11/2002 Woodlands Apply if interested!";
		String actualOutputEvent = C206_CaseStudy.viewEvent(eventList);
		assertNotEquals("Test if eventList with one event shows correct output", wrongOutputEvent, actualOutputEvent);
	}
}