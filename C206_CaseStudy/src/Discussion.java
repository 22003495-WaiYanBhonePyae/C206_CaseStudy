
public class Discussion{

	private String discID;
    private String title;
    private String description;
//    private String membersEm;

    public Discussion(String discID, String discTitle, String discDesc/*, String membersEm*/) {

    	this.discID = discID;
        this.title = discTitle;
        this.description = discDesc;
 //       this.membersEm = membersEm;
        
	}
    public String getDiscID() {
        return discID;
    }

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
    }
    


}

