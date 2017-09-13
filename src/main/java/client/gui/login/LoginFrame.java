package client.gui.login;

import client.gui.chat.MainFrame;
import client.utils.ConnectionService;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private static final int FRAME_SIZE_X = 300;
    private static final int FRAME_SIZE_Y = 350;

    private JPanel loginPanel;
    private UserExistPanel userExistPanel;
    private UserCreatePanel userCreatePanel;
    private ErrorPanel errorPanel;

    private final ConnectionService connectionService;

    private MainFrame mainFrame;

    public LoginFrame(MainFrame mainFrame, ConnectionService connectionService){
        this.mainFrame = mainFrame;
        this.connectionService = connectionService;
    }
    public void start(){
        constructJFrame();
        constructWindows();
        showLoading();
        new Thread(){
            @Override
            public void run() {
                super.run();
                if(connectionService.isConnected())
                    showMenu();
                else
                    showError();
            }
        }.start();
    }

    private void constructJFrame(){
        setSize(FRAME_SIZE_X, FRAME_SIZE_Y);
        loginPanel = new JPanel(new CardLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(loginPanel);
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
                User user = loginUser();
                if(user != null)
                    startApplication(user);
            }

            void backOnClick() {
                showMenu();
            }
        };
        userCreatePanel = new UserCreatePanel(this) {
            @Override
            void createUserOnClick() {
                User user = createUser();
                if(user != null)
                    startApplication(user);
            }

            void backOnClick() {
                showMenu();
            }
        };
        errorPanel = new ErrorPanel();

        loginPanel.add(menuPanel, "Menu");
        loginPanel.add(userExistPanel, "Login");
        loginPanel.add(userCreatePanel, "Create_user");
        loginPanel.add(errorPanel, "Error");
    }

    private User loginUser(){
        String userName = userExistPanel.getUsername();
        String password = userExistPanel.getPassword();
        return connectionService.connectUser(userName, password);
    }

    private User createUser(){
        String userName = userCreatePanel.getUsername();
        String password = userCreatePanel.getPassword();
        return connectionService.createUser(userName, password);
    }

    private void showMenu(){
        CardLayout layout = (CardLayout) loginPanel.getLayout();
        layout.show(loginPanel, "Menu");
    }

    private void showLogin(){
        CardLayout layout = (CardLayout) loginPanel.getLayout();
        layout.show(loginPanel, "Login");
    }

    private void showCreateUser(){
        CardLayout layout = (CardLayout) loginPanel.getLayout();
        layout.show(loginPanel, "Create_user");
    }

    private void showError(){
        errorPanel.showError();
        CardLayout layout = (CardLayout) loginPanel.getLayout();
        layout.show(loginPanel, "Error");
    }

    private void showLoading(){
        errorPanel.showLoading();
        CardLayout layout = (CardLayout) loginPanel.getLayout();
        layout.show(loginPanel, "Error");
    }

    private void startApplication(User user){
        dispose();
        mainFrame.start(connectionService, user);
    }

    class ErrorPanel extends JPanel{
        private JLabel connectionStatus;

        ErrorPanel(){
            connectionStatus = new JLabel("");
            add(connectionStatus);
        }

        public void showLoading(){
            connectionStatus.setText("Connecting to server..");
        }

        public void showError(){
            connectionStatus.setText("Could not connect to server.");
        }
    }
}


















