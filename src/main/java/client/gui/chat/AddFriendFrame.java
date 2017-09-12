package client.gui.chat;

import client.utils.ConnectionService;
import model.User;

import javax.swing.*;
import java.awt.*;

public class AddFriendFrame extends JFrame {

    private static final int FRAME_SIZE_X = 200;
    private static final int FRAME_SIZE_Y = 250;

    private JTextField friendUsername;
    private ConnectionService connectionService;

    AddFriendFrame(ConnectionService connectionService){
        this.connectionService = connectionService;
        constructFrame();
        constructPanels();
        setVisible(true);
    }

    private void constructFrame(){
        setSize(FRAME_SIZE_X, FRAME_SIZE_Y);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setResizable(false);
    }

    private void constructPanels(){
        GridBagConstraints gbc = new GridBagConstraints();

        //Username textField
            friendUsername = new JTextField(10);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            gbc.gridheight = 1;
            gbc.insets = new Insets(20, 0,0,0);

            add(friendUsername, gbc);
        //

        //Add friend Button
            JButton addFriend = new JButton("Add friend");
            addFriend.addActionListener(e -> addFriend());

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.insets = new Insets(20, 0,0,0);

            add(addFriend, gbc);
        //
    }

    private void addFriend(){
        String username = friendUsername.getText();
        User updatedUser = connectionService.findFriend(username);
        if(updatedUser != null){
            System.out.println("Friend found");
        }
        else System.out.println("Friend not found");

    }

}
