package client.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionCreator implements ConnectionService {

    private static final Logger LOGGER = Logger.getLogger(ConnectionService.class.getSimpleName());

    private final static String IP = "localhost";
    private final static int PORT = 6667;

    private ObjectOutputStream output;
    private ObjectInputStream input;

    public boolean connectUser(String username) {
       return false;
    }

    public boolean createUser(String username) {
        connect();
        LOGGER.log(Level.INFO, "Sending request");
        int requestNumber = 1;
        try {
            output.writeInt(requestNumber);
            output.writeObject(username);
            output.flush();
            Boolean userCreated = input.readBoolean();
            if(userCreated){
                LOGGER.log(Level.INFO, "User created successfully");
                return true;
            } else {
                LOGGER.log(Level.WARNING, "User couldn't be created");
                return false;
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Couldn't create user");
            return false;
        }
    }

    private void connect(){
        try {
            Socket serverSocket = new Socket(IP, PORT);
            output = new ObjectOutputStream(serverSocket.getOutputStream());
            input = new ObjectInputStream(serverSocket.getInputStream());
            LOGGER.log(Level.INFO, "Connected successfully");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Could not connect to server: " + IP);
        }
    }
}
