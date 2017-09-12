package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{

    private String username;
    private List<User> friends;

    public User(String username) {
        this.username = username;
        friends = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void addFriend(User user){
        friends.add(user);
    }

    public List<User> getFriends(){
        return this.friends;
    }
}
