
public class Discussion{

    private String title;
    private String description;
    private String membersEm;

    public Discussion(String discTitle, String discDesc, String membersEm) {
		// TODO Auto-generated constructor stub
        this.title = discTitle;
        this.description = discDesc;

        this.membersEm = membersEm;
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

