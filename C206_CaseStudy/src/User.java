public class User {
    private String username;
    private String password;
    private String email;
    private String description;
    private boolean isAdmin;

    public User(String username, String password, String email, String description, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.description = description;
        this.isAdmin = isAdmin;
    }

    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void display() {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Email: " + email);
        System.out.println("Bio: " + description);
        System.out.println("Is Admin: " + isAdmin);
    }
}
