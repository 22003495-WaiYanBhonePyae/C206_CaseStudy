


public class Group {
	private String id;
	private String groupName;
	private String groupDescription;
    
	
	public Group(String GroupID,String groupName, String groupDescription) {
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.id = GroupID;
	    
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

		

}

