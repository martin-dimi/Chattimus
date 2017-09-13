package model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable{

    private String username;
    private String password;
    private ArrayList<String> friends;

    public User(){
        friends = new ArrayList<>();
    }

    public User(String username) {
        this.username = username;
        friends = new ArrayList<>();
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        friends = new ArrayList<>();
    }


    public void addFriend(String friend){
        friends.add(friend);
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

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

}
