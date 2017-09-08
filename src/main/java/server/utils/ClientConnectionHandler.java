package server.utils;

import dao.UserDao;
import dao.UserDaoService;
import model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientConnectionHandler extends
        ClientConnectionHandlerService {

    private static final Logger LOGGER =
            Logger.getLogger(ClientConnectionHandler.class.getSimpleName());
    private final UserDaoService userDao = new UserDao();

    private final Socket mSocket;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;
    private boolean isConnected;

    public ClientConnectionHandler(Socket socket){
        mSocket = socket;
        try {
            output = new ObjectOutputStream(mSocket.getOutputStream());
            input = new ObjectInputStream(mSocket.getInputStream());
            isConnected = true;
            LOGGER.log(Level.INFO, "User connected");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error: Could't establish IO streams with client");
            isConnected = false;
        }
    }

    @Override
    public void run() {
        while(isConnected){
            acceptRequest();
        }
    }

    @Override
    public void acceptRequest() {
        try {
            LOGGER.log(Level.INFO, "Waiting for request");
            int requestNumber = input.readInt();
            switch (requestNumber){
                case 0:
                    acceptMessage();
                    break;
                case 1:
                    connectUser();
                    break;
                case 2:
                    createUser();
                    break;
                case 3:
                    disconnectUser();
                    break;
                default:
                    LOGGER.log(Level.SEVERE, "Couldn't recognise request");
            }
        } catch (Exception e) {
            isConnected = false;
            LOGGER.log(Level.INFO, "User disconnected");
            disconnectUser();
        }
    }

    @Override
    public void acceptMessage() {
        Message message = null;
        try{
            message = (Message) input.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUser() {
        try {
            LOGGER.log(Level.INFO, "Reading user credentials");
            String username = (String)input.readObject();
            String password = (String)input.readObject();

            Boolean userCreated = userDao.createUser(username, password);
            output.writeBoolean(userCreated);
            output.flush();
            LOGGER.log(Level.INFO, "User created");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error: creating user");
            disconnectUser();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectUser() {
        try {
            LOGGER.log(Level.INFO, "Reading user credentials");
            String username = (String)input.readObject();
            String password = (String)input.readObject();

            Boolean userExist = userDao.getUser(username, password);
            output.writeBoolean(userExist);
            output.flush();
            LOGGER.log(Level.INFO, "User logged");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error: creating user");
            disconnectUser();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnectUser() {
        try {
            input.close();
            output.close();
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
