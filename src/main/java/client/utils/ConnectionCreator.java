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
    private final static int REQUEST_ID_SENT_MESSAGE = 0;
    private final static int REQUEST_ID_CONNECT_USER = 1;
    private final static int REQUEST_ID_CREATE_USER = 2;

    private ObjectOutputStream output;
    private ObjectInputStream input;

    public ConnectionCreator(){
        connect();
    }

    @Override
    public boolean connectUser(String username, String password) {
        LOGGER.log(Level.INFO, "Sending request");
        try {
            output.writeInt(REQUEST_ID_CONNECT_USER);
            output.writeObject(username);
            output.writeObject(password);
            output.flush();
            Boolean userConnected = input.readBoolean();
            if(userConnected){
                LOGGER.log(Level.INFO, "User connected successfully");
                return true;
            } else {
                LOGGER.log(Level.WARNING, "Wrong username or password");
                return false;
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Couldn't connect user");
            return false;
        }
    }

    @Override
    public boolean createUser(String username, String password) {
        LOGGER.log(Level.INFO, "Sending request");
        try {
            output.writeInt(REQUEST_ID_CREATE_USER);
            output.writeObject(username);
            output.writeObject(password);
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

    @Override
    public boolean sentMessage(String message) {

        return false;
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

    private void disconnect(){
        LOGGER.log(Level.INFO, "Sending request");
        try {
            output.writeInt(REQUEST_ID_CREATE_USER);
            output.flush();

            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
