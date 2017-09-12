package client.utils;

import model.User;

public interface ConnectionService {

    boolean connectService();
    User connectUser(String username, String password);
    User createUser(String username, String password);

    boolean sentMessage(String message);
    User findFriend(String username);
}