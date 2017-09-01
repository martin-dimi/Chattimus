package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server{

    private final Logger logger = Logger.getLogger(Server.class.getSimpleName());

    private int serverPort = 6667;
    private ServerSocket serverSocket = null;
    private boolean isStopped = false;

    public static Map<String, ClientHandler> users = new HashMap<String, ClientHandler>();

    public void start(){
        openServerSocket();
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                connectClient(clientSocket);
                logger.log(Level.INFO, "UserCredentials connected");
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error accepting client connection");
            }
        }
        System.out.println("Server Stopped.") ;
    }

    private void connectClient(Socket clientSocket){
        ClientHandler client = new ClientHandler(clientSocket);
        new Thread(client).start();
        users.put(client.getUserName(), client);
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
            logger.log(Level.INFO, "Server is running");
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
    }

    private synchronized boolean isStopped() {
        return isStopped;
    }
}