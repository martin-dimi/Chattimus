package server;

public abstract class ClientConnectionHandlerService implements Runnable{

    abstract void acceptRequest();

    abstract void createUser();
    abstract void connectUser();
    abstract void acceptMessage();
    abstract void disconnectUser();

}
