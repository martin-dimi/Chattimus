package client;

public interface ClientService {

    void sentMessage(String messageText, String to);
    void disconnect();
    boolean isConnected();
}