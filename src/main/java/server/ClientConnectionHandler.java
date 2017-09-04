package server;

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
    public void acceptRequest() {
        try {
            LOGGER.log(Level.INFO, "Waiting for request");
            int requestNumber = input.readInt();
            switch (requestNumber){
                case 0:
                    acceptMessage();
                    break;
                case 1:
                    createUser();
                    break;
                case 2:
                    connectUser();
                    break;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error: could't accept client request");
            e.printStackTrace();
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
            String username = (String)input.readObject();
            System.out.println("USER " + username + " CREATED!!!");
            output.writeBoolean(true);
            output.flush();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error: creating user");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectUser() {
        System.out.println("USER CONNECTED!!!");
    }

    @Override
    public void disconnectUser() {

    }

    @Override
    public void run() {
        while(isConnected){
            acceptRequest();
        }
    }
}
