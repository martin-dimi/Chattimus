package client.gui;

import client.gui.chat.MainFrame;
import client.gui.login.LoginFrame;
import client.utils.ConnectionUtils;
import client.utils.ConnectionService;

public class Chattimus {

    public static void main(String[] args) {
        ConnectionService connectionService = new ConnectionUtils();
        MainFrame mainFrame = new MainFrame();
        LoginFrame loginFrame = new LoginFrame(mainFrame, connectionService);
        loginFrame.start();
       // mainFrame.start(connectionService);
    }
}
