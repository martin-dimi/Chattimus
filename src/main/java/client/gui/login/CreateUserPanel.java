package client.gui.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class CreateUserPanel extends JPanel{

    private JTextField username;
    private JTextField password;

    protected CreateUserPanel(JFrame frame){
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel usernameLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        username = new JTextField();
        password = new JTextField();
        JButton createButton = new JButton();

        //Username label
        usernameLabel.setText("Create username:   ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(usernameLabel, gbc);
        //

        //Username field
        username.setColumns(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(username, gbc);
        //

        //Password label
        passwordLabel.setText("Create password:   ");
        gbc.insets = new Insets(5, 0 , 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(passwordLabel, gbc);
        //

        //Password field
        password.setColumns(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        add(password, gbc);
        //

        //Login button
        createButton.setText("Create User");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createUserOnClick();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 20 , 0, 0);
        add(createButton, gbc);
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

    abstract void createUserOnClick();
    abstract void backOnClick();


}
