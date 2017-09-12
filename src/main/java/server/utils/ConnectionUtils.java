package server.utils;

import dao.UserDao;
import dao.UserDaoService;
import model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionUtils {

    private static final Logger LOGGER = Logger.getLogger(ConnectionUtils.class.getSimpleName());

    private static final int REQUEST_CONNECT_USER = 1;
    private static final int REQUEST_CREATE_USER = 2;

    private final UserDaoService userDao = new UserDao();

    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;
    private User currentUser;

    public ConnectionUtils(ObjectInputStream input, ObjectOutputStream output){
        this.output = output;
        this.input = input;
    }

    public User acceptConnectionRequest() {
        try {
            LOGGER.log(Level.INFO, "Waiting for connection request");
            int request = input.readInt();
            switch (request) {
                case REQUEST_CONNECT_USER:
                    connectUser();
                    break;
                case REQUEST_CREATE_USER:
                    createUser();
                    break;
                default:
                    LOGGER.log(Level.SEVERE, "Couldn't recognise connection request");
            }
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "User couldn't connect");
        }
        return currentUser;
    }

    private void createUser() {
        try {
            LOGGER.log(Level.INFO, "Reading user credentials");
            String username = (String) input.readObject();
            String password = (String) input.readObject();

            currentUser = userDao.createUser(username, password);
            output.writeObject(currentUser);
            output.flush();
        } catch (IOException| ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error: creating user");
        }
    }

    private void connectUser() {
        try {
            LOGGER.log(Level.INFO, "Reading user credentials");
            String username = (String) input.readObject();
            String password = (String) input.readObject();

            currentUser = userDao.getUser(username, password);
            output.writeObject(currentUser);
            output.flush();
        } catch (IOException| ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error: connecting user");
        }
    }
}


