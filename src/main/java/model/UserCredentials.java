package model;


public class UserCredentials {

    private int id;
    private String username;
    private String password;

    public UserCredentials(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
