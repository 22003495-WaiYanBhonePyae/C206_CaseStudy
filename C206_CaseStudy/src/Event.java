
/**
 * I declare that this code was written by me.
 * I will not copy or allow others to copy my code.
 * I understand that copying code is considered as plagiarism.
 *
 * Remus, 2 Aug 2023 3:44:19 pm
 */

/**
 * @author Remus Teo
 *
 */
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
