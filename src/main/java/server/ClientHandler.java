package server;

import model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler extends Thread{

    private static final Logger logger = Logger.getLogger(ClientHandler.class.getSimpleName());

    private Socket userSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    private String userName;

    ClientHandler(Socket userSocket){
        this.userSocket = userSocket;
    }

    public void run() {
        connectUser();
        messageListener();
    }

    private void connectUser(){
        try {
            input = new ObjectInputStream(userSocket.getInputStream());
            output = new ObjectOutputStream(userSocket.getOutputStream());
            userName = (String) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Could not establish streams");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void messageListener() {
        while(true){
            try {
                Message message = (Message) input.readObject();
                String messageText = message.getMessageText();
                String messageReceiver = message.getTo();
                if (messageText != null && !messageText.equals("")) {
                    //Server.users.get(message.getTo()).sentMessage(message);
                    System.out.println(messageText);
                    if(messageReceiver != null)
                        System.out.println(messageReceiver);
                }
            } catch (IOException e) {
                logger.log(Level.INFO,"UserCredentials disconnected");
                disconnect();
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                disconnect();
                break;
            }
        }
    }

    private void sentMessage(Message message) throws IOException {
        output.writeObject(message);
    }

    private void disconnect(){
        try {
            userSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String getUserName(){
        return userName;
    }


}
