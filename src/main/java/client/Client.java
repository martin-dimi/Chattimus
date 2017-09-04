package client;

import model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client{

    private static final Logger logger = Logger.getLogger(Client.class.getSimpleName());

    private final String ip = "localhost";
    private final int port = 6667;
    private Socket serverSocket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private boolean isConnected;
    private String username;

    public Client(){
        connect();
    }


    public void sentMessage(String messageText, String to){
        try {
            Message message = new Message(username, to, messageText);
            output.writeObject(message);
            logger.log(Level.INFO, "Message sent");
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Could not send message");
        }
    }

    public void disconnect(){
        try {
            serverSocket.close();
            logger.log(Level.INFO, "Disconnected");
            isConnected = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected(){
        return this.isConnected;
    }


    private void connect(){
        try {
            serverSocket = new Socket(ip, port);
            output = new ObjectOutputStream(serverSocket.getOutputStream());
            input = new ObjectInputStream(serverSocket.getInputStream());
            output.writeObject(username);
            isConnected = true;
            logger.log(Level.INFO, "Connected successfully");
        } catch (IOException e) {
            e.printStackTrace();
            isConnected = false;
            logger.log(Level.SEVERE, "Could not connect to server: " + ip);
        }
    }
}
