package client.gui;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends JFrame {

    private JPanel panel;

    private MenuWindow menuWindow;
    private LoginWindow loginWindow;
    private CreateUserWindow createUserWindow;

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
        menuWindow = new MenuWindow(this) {
            @Override
            void loginOnClick() {
                showLogin();
            }

            @Override
            void createUserOnClick() {
                showCreateUser();
            }
        };

        loginWindow = new LoginWindow(this) {
            @Override
            void loginOnClick() {

            }

            void backOnClick() {
                showMenu();
            }
        };

        createUserWindow = new CreateUserWindow(this) {
            @Override
            void createUserOnClick() {

            }

            void backOnClick() {
                showMenu();
            }
        };

        panel.add(menuWindow, "Menu");
        panel.add(loginWindow, "Login");
        panel.add(createUserWindow, "Create_user");
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


















