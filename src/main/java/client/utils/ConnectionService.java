package client.utils;

public interface ConnectionService {
    boolean connectUser(String username);
    boolean createUser(String username);
}