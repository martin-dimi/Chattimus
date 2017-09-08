package client.gui.login;

import client.gui.chat.MainFrame;
import client.utils.ConnectionCreator;
import client.utils.ConnectionService;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JPanel panel;
    private UserExistPanel userExistPanel;
    private UserCreatePanel userCreatePanel;
    private ConnectionService connectionService;

    private MainFrame mainFrame;

    public LoginFrame(MainFrame mainFrame, ConnectionService connectionService){
        this.mainFrame = mainFrame;
        this.connectionService = connectionService;
    }
    public void start(){
        constructJFrame();
        constructWindows();
        showMenu();
        setVisible(true);
    }

    private void constructJFrame(){
        setSize(300, 350);
        panel = new JPanel(new CardLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(panel);
        setResizable(false);
        setVisible(true);

    }
    private void constructWindows(){
        UserSelectPanel menuPanel = new UserSelectPanel() {
            @Override
            void loginOnClick() {
                showLogin();
            }

            @Override
            void createUserOnClick() {
                showCreateUser();
            }
        };

        userExistPanel = new UserExistPanel(this) {
            @Override
            void loginOnClick() {
                boolean connected = loginUser();
                if(connected)
                    startApplication();
            }

            void backOnClick() {
                showMenu();
            }
        };

        userCreatePanel = new UserCreatePanel(this) {
            @Override
            void createUserOnClick() {
                boolean connected = createUser();
                if(connected)
                    startApplication();
            }

            void backOnClick() {
                showMenu();
            }
        };

        panel.add(menuPanel, "Menu");
        panel.add(userExistPanel, "Login");
        panel.add(userCreatePanel, "Create_user");
    }

    private Boolean loginUser(){
        String userName = userExistPanel.getUsername();
        String password = userExistPanel.getPassword();
        return connectionService.connectUser(userName, password);
    }

    private Boolean createUser(){
        String userName = userCreatePanel.getUsername();
        String password = userCreatePanel.getPassword();
        return connectionService.createUser(userName, password);
    }

    private void showMenu(){
        CardLayout layout = (CardLayout) panel.getLayout();
        layout.show(panel, "Menu");
    }

    private void showLogin(){
        CardLayout layout = (CardLayout) panel.getLayout();
        layout.show(panel, "Login");
    }

    private void showCreateUser(){
        CardLayout layout = (CardLayout) panel.getLayout();
        layout.show(panel, "Create_user");
    }

    private void startApplication(){
        dispose();
        mainFrame.start();
    }
}


















