package client.gui.chat;

import client.utils.ConnectionService;
import model.User;

import javax.swing.*;
import java.awt.*;

public class AddFriendFrame extends JFrame {

    private static final int FRAME_SIZE_X = 200;
    private static final int FRAME_SIZE_Y = 200;

    private JTextField friendUsernameTextField;
    private JLabel error;
    private ConnectionService connectionService;
    private MainFrame mainFrame;

    AddFriendFrame(MainFrame mainFrame, ConnectionService connectionService){
        this.connectionService = connectionService;
        this.mainFrame = mainFrame;
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

        //Error message
            error = new JLabel();
            error.setText("User doesn't exist");
            error.setForeground(Color.red);
            error.setVisible(false);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 0,0,0);

            add(error, gbc);
        //

        //Username textField
            friendUsernameTextField = new JTextField(10);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            gbc.gridheight = 1;
            gbc.insets = new Insets(5, 0,0,0);

            add(friendUsernameTextField, gbc);
        //

        //Add friend Button
            JButton addFriend = new JButton("Add friend");
            addFriend.addActionListener(e -> addFriend());

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.insets = new Insets(5, 10,0,0);

            add(addFriend, gbc);
        //
    }

    private void addFriend(){
        String username = friendUsernameTextField.getText();
        User updatedUser = connectionService.addFriend(username);
        if(updatedUser != null){
            mainFrame.updateUser(updatedUser);
            dispose();
        }
        else{
            error.setVisible(true);
        }

    }

}
