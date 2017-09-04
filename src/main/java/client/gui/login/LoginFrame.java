package client.gui.login;

import client.utils.ConnectionCreator;
import client.utils.ConnectionService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JPanel panel;
    private CreateUserPanel createUserPanel;
    private ConnectionService connectionService;

    public void start(){
        connectionService = new ConnectionCreator();
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
        StartUpPanel menuPanel = new StartUpPanel() {
            @Override
            void loginOnClick() {
                showLogin();
            }

            @Override
            void createUserOnClick() {
                showCreateUser();
            }
        };

        ExistingUserPanel loginPanel = new ExistingUserPanel(this) {
            @Override
            void loginOnClick() {

            }

            void backOnClick() {
                showMenu();
            }
        };

        createUserPanel = new CreateUserPanel(this) {
            @Override
            void createUserOnClick() {
                createUser();
            }

            void backOnClick() {
                showMenu();
            }
        };

        panel.add(menuPanel, "Menu");
        panel.add(loginPanel, "Login");
        panel.add(createUserPanel, "Create_user");
    }

    private void createUser(){
        String userName = createUserPanel.getUsername();
        connectionService.createUser(userName);
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
}


















