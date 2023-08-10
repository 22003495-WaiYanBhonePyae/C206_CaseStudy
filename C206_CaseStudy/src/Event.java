public class Event {
	private String eventID;
	private String title;
	private String date;
	private String location;
	private String description;

	public Event(String eventID, String title, String date, String location, String description) {
		this.eventID = eventID;
		this.title = title;
		this.date = date;
		this.location = location;
		this.description = description;
	}

	public String getEventID() {
		return eventID;
	}

	public String getTitle() {
		return title;
	}

	public String getDate() {
		return date;
	}

	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}
}
