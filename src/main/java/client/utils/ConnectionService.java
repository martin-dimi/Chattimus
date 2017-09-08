package client.utils;

public interface ConnectionService {

    boolean connectUser(String username, String password);
    boolean createUser(String username, String password);

    boolean sentMessage(String message);
}