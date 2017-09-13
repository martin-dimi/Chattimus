package client.utils;

import model.Chatroom;
import model.User;

import java.util.List;

public interface ConnectionService {

    boolean isConnected();
    User connectUser(String username, String password);
    User createUser(String username, String password);

    boolean sentMessage(String message);
    User addFriend(String username);

    List<Chatroom> getChatrooms(String username);
}