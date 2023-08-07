import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class C206_CaseStudyTest {
	private User user1;
	private User user2;
	private User user3;
	
	private ArrayList<User> userList;
	
	private Group gp1;
	private Group gp2;
	
	private ArrayList<User> groupList;
	@Before
	public void setUp() throws Exception {
		user1 = new User("John", "123456", "john123@gmail.com", "Biker enthusiast", false);
		user2 = new User("Mary", "abcdef", "mary456@gmail.com", "Biker specialist", false);
		user3 = new User("Admin", "admin123", "admin@gmail.com", "Biker community admin", true);
        
		userList = new ArrayList<User>();
		
		groupList = new ArrayList<User>();
		gp1 = new Group("2202","Tigers","Biking");
		gp2 = new Group("2203","Lions","nerds");
	}

	@Test
	public void testAddUser() {
	    // Test that initially, the userList has a size of zero
	    assertEquals("Test that initial userList size is 0", 0, userList.size());

	    // Simulate user input and call addUser with the simulated input
	    C206_CaseStudy.currentUser = user1; // Simulate the currentUser
	    C206_CaseStudy.loggedIn = true;     // Simulate logged in state
	    C206_CaseStudy.addUser(userList);

	    // Test that after calling addUser, the userList has a size of 1
	    assertEquals("Test that userList size is 1", 1, userList.size());

	    // Test that the user added is the same as the user at index 0 in the userList
	    assertEquals("Test that user added is same as user at index 0", "John", userList.get(0).getUsername());
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
		assertEquals("Test that userList has a size of 2 after deletion", 2, userList.size());
	}

	@After
	public void tearDown() throws Exception {
		user1 = null;
		user2 = null;
		user3 = null;

		userList = null;
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

}



