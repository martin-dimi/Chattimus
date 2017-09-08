package client.gui;

import client.gui.chat.MainFrame;
import client.gui.login.LoginFrame;
import client.utils.ConnectionCreator;
import client.utils.ConnectionService;

public class Chattimus {

    public static void main(String[] args) {
        ConnectionService connectionService = new ConnectionCreator();
        MainFrame mainFrame = new MainFrame();
        LoginFrame loginFrame = new LoginFrame(mainFrame, connectionService);

        loginFrame.start();
    }



}
