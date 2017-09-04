package client.gui.login;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ExistingUserPanel extends JPanel{

    private JTextField username;
    private JTextField password;

    public ExistingUserPanel(JFrame frame){
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();

        //Username label
        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username:   ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(usernameLabel, gbc);
        //

        //Username field
        username = new JTextField();
        username.setColumns(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(username, gbc);
        //

        //Password label
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password:   ");
        gbc.insets = new Insets(5, 0 , 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(passwordLabel, gbc);
        //

        //Password field
        password = new JTextField();
        password.setColumns(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        add(password, gbc);
        //

        //Login button
        JButton loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginOnClick();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 20 , 0, 0);
        add(loginButton, gbc);
        //

        //Back button
        JButton backButton = new JButton();
        backButton.setText("Go back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backOnClick();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(backButton, gbc);
        //

    }


    public String getUsername(){
        return username.getText();
    }

    public String getPassword(){
        return password.getText();
    }

    abstract void loginOnClick();
    abstract void backOnClick();
}
