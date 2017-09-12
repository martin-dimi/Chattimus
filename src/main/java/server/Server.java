package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server{

    private static final Logger LOGGER = Logger.getLogger(Server.class.getSimpleName());
    private static final int serverPort = 6667;

    private ServerSocket serverSocket = null;
    private boolean isStopped = false;

    public void start(){
        openServerSocket();
        while(!isStopped()){
            try {
                Thread.sleep(100);
                LOGGER.log(Level.INFO, "Waiting for Client to connect");
                Socket clientSocket = serverSocket.accept();
                connectClient(clientSocket);
            } catch (IOException | InterruptedException e) {
                LOGGER.log(Level.SEVERE, "Error accepting client connection");
            }
        }
        System.out.println("Server Stopped.") ;
    }

    private void connectClient(Socket clientSocket){
        ClientWorker client = new ClientWorker(clientSocket);
        new Thread(client).start();
    }

    private void openServerSocket() {
        try {
            serverSocket = new ServerSocket(serverPort);
            LOGGER.log(Level.INFO, "Server is running");
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
    }

    private synchronized boolean isStopped() {
        return isStopped;
    }
}