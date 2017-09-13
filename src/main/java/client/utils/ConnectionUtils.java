package client.utils;

import model.Chatroom;
import model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionUtils implements ConnectionService {

    private static final Logger LOGGER = Logger.getLogger(ConnectionService.class.getSimpleName());

    private static final String IP = "localhost";
    private static final int PORT = 6667;
    private static final int REQUEST_CONNECT_USER_ID = 1;
    private static final int REQUEST_CREATE_USER_ID = 2;
    private static final int REQUEST_DISCONNECT_USER_ID = 3;
    private static final int REQUEST_SENT_MESSAGE_ID = 10;
    private static final int REQUEST_ADD_FRIEND_ID = 11;
    private static final int REQUEST_GET_CHATROOMS_ID = 20;



    private ObjectOutputStream output;
    private ObjectInputStream input;
    private boolean isConnected;

    public ConnectionUtils(){
        connectService();
    }

    @Override
    public User connectUser(String username, String password) {
        LOGGER.log(Level.INFO, "Sending request");
        try {
            output.writeInt(REQUEST_CONNECT_USER_ID);
            output.writeObject(username);
            output.writeObject(password);
            output.flush();

            User user = (User)input.readObject();
            if(user != null){
                LOGGER.log(Level.INFO, "User connected successfully");
                return user;
            } else {
                LOGGER.log(Level.WARNING, "Wrong username or password");
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Couldn't connect user");
            return null;
        }
    }

    @Override
    public User createUser(String username, String password) {
        LOGGER.log(Level.INFO, "Sending request");
        try {
            output.writeInt(REQUEST_CREATE_USER_ID);
            output.writeObject(username);
            output.writeObject(password);
            output.flush();

            User userCreated = (User)input.readObject();
            if(userCreated != null){
                LOGGER.log(Level.INFO, "User created successfully");
                return userCreated;
            } else {
                LOGGER.log(Level.WARNING, "User couldn't be created");
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Couldn't create user");
            return null;
        }
    }

    @Override
    public boolean sentMessage(String message) {

//        LOGGER.log(Level.INFO, "Sending request");
//        try {
//            output.writeInt(REQUEST_ID_SENT_MESSAGE);
//            output.writeObject(message);
//            output.flush();
//
//            Boolean messageReceived = input.readBoolean();
//            if(messageReceived){
//                LOGGER.log(Level.INFO, "Message sent");
//                return true;
//            } else {
//                LOGGER.log(Level.WARNING, "Message was not sent");
//                return false;
//            }
//        } catch (IOException e) {
//            LOGGER.log(Level.SEVERE, "Couldn't send message");
//            return false;
//        }
        return false;
    }

    @Override
    public User addFriend(String username) {
        LOGGER.log(Level.INFO, "Sending request");
        try {
            output.writeInt(REQUEST_ADD_FRIEND_ID);
            output.writeObject(username);
            output.flush();

            boolean friendExists = input.readBoolean();
            if(friendExists){
                User updatedUser = (User) input.readObject();
                LOGGER.log(Level.INFO, "Friend found.");
                return updatedUser;
            }else {
                LOGGER.log(Level.INFO, "Friend not found.");
                return null;
            }

        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Couldn't find user");
            return null;
        }
    }

    @Override
    public List<Chatroom> getChatrooms(String username) {
        LOGGER.log(Level.INFO, "Sending request");
        try {
            output.writeInt(REQUEST_GET_CHATROOMS_ID);
            output.writeObject(username);
            output.flush();

            return Arrays.asList((Chatroom[]) input.readObject());
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Could not get chatrooms");
            return null;
        }
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    private void connectService(){
        try {
            Socket serverSocket = new Socket(IP, PORT);
            output = new ObjectOutputStream(serverSocket.getOutputStream());
            input = new ObjectInputStream(serverSocket.getInputStream());
            LOGGER.log(Level.INFO, "Connected successfully");
            isConnected = true;
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Could not connect to server: " + IP);
            isConnected=false;
        }
    }

    public void disconnect(){
        LOGGER.log(Level.INFO, "Sending request");
        try {
            output.writeInt(REQUEST_DISCONNECT_USER_ID);
            output.flush();

            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
