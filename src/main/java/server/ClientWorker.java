package server;

import dao.UserDao;
import dao.UserDaoService;
import model.User;
import server.utils.ConnectionUtils;
import server.utils.UserUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientWorker extends Thread{

    private static final Logger LOGGER = Logger.getLogger(ClientWorker.class.getSimpleName());

    private Socket mSocket;
    private ConnectionUtils connectionUtils;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private final UserDaoService userDao;

    ClientWorker(Socket socket){
        mSocket = socket;
        userDao = new UserDao();
        try {
            output = new ObjectOutputStream(mSocket.getOutputStream());
            input = new ObjectInputStream(mSocket.getInputStream());
            connectionUtils = new ConnectionUtils(input, output, userDao);
            LOGGER.log(Level.INFO, "User connected");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error: Could't establish IO streams with client");
        }
    }

    @Override
    public void run() {
        User currentUser = connectionUtils.acceptConnectionRequest();
        if(currentUser != null) {
            boolean isConnected = true;
            UserUtils userUtils = new UserUtils(currentUser, input, output, userDao);
            while (isConnected) {
                userUtils.acceptUserRequest();
                isConnected = userUtils.isConnected();
            }
            disconnect();
        }
    }

    private void disconnect() {
        try {
            input.close();
            output.close();
            mSocket.close();
            LOGGER.log(Level.INFO, "User disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
