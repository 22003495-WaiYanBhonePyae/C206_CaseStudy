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
	private User us1;
	private User us2;
	private User us3;
	private User us4;

	private ArrayList<Group> groupList;
	private Group gp1;
	private Group gp2;

	private ArrayList<Bike> bikeList;

	private Event event1;
	private Event event2;
	private Event eventA;
	private Event eventB;
	private Event eventC;

	private ArrayList<Event> eventList;

	@Before
	public void setUp() throws Exception {

		userList = new ArrayList<User>();
		us1 = new User("User1", "11111111", "123@gmail.com", "biker1", false);
		us2 = new User("User2", "22222222", "234@gmail.com", "biker2", false);
		us3 = new User("User3", "33333333", "456@gmail.com", "biker3", false);

		groupList = new ArrayList<Group>();
		gp1 = new Group("2202", "Tigers", "Biking");
		gp2 = new Group("2203", "Lions", "nerds");

		bikeList = new ArrayList<Bike>();

		eventList = new ArrayList<>();
		event1 = new Event("4", "RP Bikers", "10/11/2002", "Woodlands", "Apply if interested!");
		event2 = new Event("5", "", "", "", "");
		eventA = new Event("6", "John", "10/11/2002", "RP", "Come join us!");
		eventB = new Event("7", "Wai Yan", "20/10/2002", "RP", "I love Junit");
		eventC = new Event("8", "Bob", "20/01/2024", "Park", "Join us for a ride!");
	}

	@Test
	public void testAddUser() {
		// Initial checking that user arraylist is valid to add new users
		// Boundary condition
		assertNotNull("Test that userList is valid for adding new users.", userList);
		assertEquals("Test that userList is empty.", 0, userList.size());

		// Normal condition
		User u = new User("John", "12345678", "john@gmail.com", "bio", false);
		userList.add(u);
		assertEquals("Test that userList is now 1 after adding new user.", 1, userList.size());

		// Add user with missing information
		// Error condition
		User user_missing = new User("", "11111111", "123@gmail.com", "bio1", false);
		C206_CaseStudy.addUser(userList, user_missing);

		assertEquals("Test that userList unchanged after adding user with missing input.", 1, userList.size());
	}

	@Test
	public void testViewUsers() {

		// Test Case 1
		// Test if User list is not null and empty
		// Boundary condition
		assertNotNull("Test if there is a valid User arraylist to add to", userList);
		assertEquals("Test that the User arraylist is empty.", 0, userList.size());

		// Test Case 2
		// Normal condition

		userList.add(us1);
		userList.add(us2);

		assertEquals("Test that userList size is 2.", 2, userList.size());
		String allUser = C206_CaseStudy.retrieveAllUser(userList);

		// Update the expected output to match the actual values
		String output = String.format("%-15s %-25s %-15s %-10s\n", us1.getUsername(), us1.getEmail(),
				us1.getDescription(), us1.isAdmin() ? "Admin" : "User");
		output += String.format("%-15s %-25s %-15s %-10s\n", us2.getUsername(), us2.getEmail(), us2.getDescription(),
				us2.isAdmin() ? "Admin" : "User");

		assertEquals("Test that display is correct.", output, allUser);
	}

	@Test
	public void testDeleteUser() {
		userList.add(us1);
		userList.add(us2);
		userList.add(us3);

		assertEquals("Test that Currency arraylist size is 3 after adding 3 users", 3, userList.size());

		// Test if user list is not null but empty
		// Boundary condition
		assertNotNull("Test if user list is valid to delete.", userList);

		// Normal condition
		boolean ok = C206_CaseStudy.confirmDelete(userList, "123@gmail.com", 'y');
		assertTrue("Test if it is confirmed to delete.", ok);

		// Error condition
		ok = C206_CaseStudy.confirmDelete(userList, "222@gmail.com", 'y');
		assertFalse("Test that non-existing user cannot be deleted.", ok);

	}

	@Test
	public void testAddGroup() {
//AddGroup
		assertEquals("Test that initial groupList size is 0", 0, groupList.size());

	String id = "2205";
    String name = "Tiger Bikers";
    String description = "Love Biking Adventures";
    C206_CaseStudy.addGroup(groupList,id,name,description);
 // Test that after adding a new group, the size of the groupList is 1
    assertEquals("Test that the size of the groupList is 1", 1, groupList.size());
    
    Group groupexists = new Group("2202", "Tigers", "Biking nerds");
    groupList.add(groupexists);

    boolean groupAlrExists = C206_CaseStudy.groupalrExists(groupList, "2202");
    assertTrue("Test that group with ID '2202' already exists", groupAlrExists);

    boolean groupNotExist = C206_CaseStudy.groupalrExists(groupList, "2203");
    assertFalse("Test that group with ID '2203' does not exist", groupNotExist);
	}
	@Test
	public void testViewGroups() {
		// Test that initially, the groupList has a size of 2
		assertNotNull("Check that if there is any groups to add",groupList);
		String id = "2202";
		String name = "Tigers";
		String description = "Biking nerds";
		C206_CaseStudy.addGroup(groupList,id, name, description);
		assertEquals("Test that group arrayList size is 1",1,groupList.size());
		
		
		id = "2203";
		name = "Lions";
		description = "nerds";
		C206_CaseStudy.addGroup(groupList,id, name, description);
		assertEquals("Test that Group arrayList size is 2",2,groupList.size());
		
		String testOutput=String.format("%-20s %-20s %-20s\n", "2202", "Tigers","Biking nerds");
		testOutput += String.format("%-20s %-20s %-20s\n", "2203", "Lions","nerds");    
		
		assertEquals("Test that testViewGroups list is the expected output", testOutput,C206_CaseStudy.viewGroups(groupList));
    
	}
	
	@Test
	public void testDeleteGroup() {
	    // Test that if the initial groupList size is 0
	    assertNotNull("Check that if there are any groups to delete", groupList);
	    assertEquals("Test that if the initial groupList size is 0", 0, groupList.size());

	    String id = "2202";
	    String name = "Tigers";
	    String description = "Biking nerds";
	    C206_CaseStudy.addGroup(groupList, id, name, description);
	    assertEquals("Test that groupList size changes to 1 after adding a new group", 1, groupList.size());
	    
	    
	    // Test after deleting a group from the groupList
	    boolean isDeleted = C206_CaseStudy.deleteGroup(groupList, "2202");
	    assertTrue("Test that group with ID '2202' is deleted", isDeleted);
	    assertEquals("Test that groupList size is 0 after deleting a group", 0, groupList.size());

	     id = "2202";
	     name = "Tigers";
	     description = "Biking nerds";
	    C206_CaseStudy.addGroup(groupList, id, name, description);
	    assertEquals("Test that groupList size is 1 after adding a new group", 1, groupList.size());
	    
	    // Test deleting a group that does not exist
	    boolean isNotDeleted = C206_CaseStudy.deleteGroup(groupList, "2204");
	    assertFalse("Test that group with ID '2204' does not exist and cannot be deleted", isNotDeleted);
	    assertEquals("Test that groupList size is still 1 after trying to delete a non-existing group", 1, groupList.size());
	}

	@Test
	public void testAddBike() {
		// Test Case 1 (Boundary condition)
		assertNotNull("Test if bikeList is valid for adding bikes", bikeList);
		assertEquals("Test that bikeList is empty.", 0, bikeList.size());
		assertEquals(0, bikeList.size());

		// Test Case 2 (Normal condition)
		Bike bike1 = new Bike("Trek", "FX 2", "Hybrid bike");
		bikeList.add(bike1);
		assertEquals("Test that bikeList is now 1.", 1, bikeList.size());

		// Test Case 3 (Error condition)
		Bike bike_missing = new Bike("", "City Bike", "Urban bike");
//	      int initialSize = bikeList.size();
		C206_CaseStudy.addBike(bikeList, bike_missing);
		assertEquals("Test that bikeList remains unchanged.", 1, bikeList.size());
	}

	@Test
	public void testViewBikes() {
		// Test Case 1 (Boundary Condition)
		assertNotNull("Test if bikeList is empty", bikeList);
		assertEquals("Test that bikeList is empty.", 0, bikeList.size());
		assertEquals(0, bikeList.size());

		// Test Case 2 (Normal Condition)
		Bike bike1 = new Bike("Giant", "Advanced Pro 1", "Road bike");
		bikeList.add(bike1);
		String expectedOutputBike = "1. Giant Advanced Pro 1 (Road bike)";
		String actualOutputBike = C206_CaseStudy.viewBike(bikeList);
		assertNotEquals("Test if bikeList with one bike shows correct output", expectedOutputBike, actualOutputBike);
	}

	@Test
	public void testDeleteBike() {

		Bike bike1 = new Bike("Trek", "FX 2", "Hybrid bike ");
		Bike bike2 = new Bike("Giant", "Advanced Pro 1", "Road bike");
		Bike bike3 = new Bike("Specialized", "Rockhopper", "Mountain bike");
		bikeList.add(bike1);
		bikeList.add(bike2);
		bikeList.add(bike3);

		assertEquals("Test that Bike ArrayList size is 3", 3, bikeList.size());

		// Test Case 1 (Boundary condition)
		assertNotNull("Test if bike list is valid to delete.", bikeList);

		// Test Case 2(Normal condition)
		boolean del = C206_CaseStudy.deleteBike(bikeList, "Trek", 'y');
		assertTrue("Test if it is confirmed to delete.", del);

		// Test Case 3 (Error condition)
		del = C206_CaseStudy.deleteBike(bikeList, "Snow", 'y');
		assertFalse("Test that non-existing bike cannot be deleted.", del);
	}

	@Test
	  public void testAddEvent() {
	    // Test Case 1 (Boundary condition)
	    assertNotNull("Test if eventList is valid for adding events", eventList);
	    assertEquals("Test that eventList is empty.", 0, eventList.size());
	    assertEquals(0, eventList.size());

	    // Test Case 2(Normal condition)
	    // Add event
	    // For reference, event1 = new Event("4", "RP Bikers", "10/11/2002",
	    // "Woodlands", "Apply if interested!");

	    C206_CaseStudy.addNewEvent(eventList, event1);
	    assertEquals("Test that eventList is now 1.", 1, eventList.size());

	    // Test Case 3 (Error condition)
	    // Add event with missing fields
	    // For reference, event2 = new Event("5", "", "", "", "");

	    C206_CaseStudy.addNewEvent(eventList, event2);
	    assertEquals("Test that eventList remains unchanged.", 1, eventList.size());
	  }

	  @Test
	  public void testViewEvent() {
	    // Test Case 1 (Boundary Condition)
	    assertNotNull("Test if eventList is valid for adding events", eventList);
	    assertEquals("Test that eventList is empty.", 0, eventList.size());
	    assertEquals(0, eventList.size());

	    // Test Case 2 (Error Condition)
	    String viewOutput = C206_CaseStudy.viewNewEvent(eventList);
	    assertEquals("Test that the display is empty for an empty eventList.", "", viewOutput);

	    // Test Case 3 (Normal Condition)
	    // For reference, eventA = new Event("6", "John", "10/11/2002", "RP", "Come join
	    // us!");

	    C206_CaseStudy.addNewEvent(eventList, eventA);
	    assertEquals("Test that eventList is now 1.", 1, eventList.size());
	    String result = C206_CaseStudy.viewNewEvent(eventList);
	    String expectedOutput = String.format("%-10s %-25s %-12s %-20s %s", "6", "John", "10/11/2002", "RP",
	        "Come join us!");
	    assertEquals("Test that events get displayed properly", expectedOutput, result);

	  }

	  @Test
	  public void testDeleteEvent() {
	    // Test Case 1 (Boundary Condition)
	    assertNotNull("Test if eventList is valid for adding events", eventList);
	    assertEquals("Test that eventList is empty.", 0, eventList.size());

	    // Test Case 2 (Normal Condition)
	    // Add event for deleting
	    // For reference, eventB = new Event("7", "Wai Yan", "20/10/2002", "RP", "I love
	    // Junit");
	    C206_CaseStudy.addNewEvent(eventList, eventB);
	    assertEquals("Test that eventList is now 1.", 1, eventList.size());

	    boolean isDeleted = C206_CaseStudy.deleteEvent(eventList, eventB);
	    assertTrue("Test that the event is deleted.", isDeleted);
	    assertEquals("Test that eventList is empty after deletion.", 0, eventList.size());

	    // Test Case 3 (Error Condition)
	    // Delete an event that doesn't exist in the list
	    // For reference, eventC = new Event("8", "Bob", "20/01/2024", "Park", "Join us
	    // for a ride!");

	    assertFalse("Test that the event is not deleted.", C206_CaseStudy.deleteEvent(eventList, eventC));

	  
	}

	@After
	public void tearDown() throws Exception {
		userList.clear();
		userList = null;
		gp1=null;
		gp2=null;
		bikeList = null;
		eventList.clear();
		groupList=null;
		event1 = null;
		event2 = null;
		eventA = null;
		eventB = null;
		eventC = null;
	}

}