package client.gui.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class StartUpPanel extends JPanel {

    public StartUpPanel(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Login
        JButton existingUser = new JButton();
        existingUser.setText("Existing User");
        existingUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginOnClick();
            }
        });
        this.add(existingUser, gbc);
        //

        this.add(Box.createRigidArea(new Dimension(0, 10)), gbc);

        //CreateUser
        JButton createUser = new JButton();
        createUser.setText("Create User");
        createUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createUserOnClick();
            }
        });
        this.add(createUser, gbc);
        //

    }


    abstract void loginOnClick();
    abstract void createUserOnClick();

}
