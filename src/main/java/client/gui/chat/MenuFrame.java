package client.gui.chat;

import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {

    private static final int FRAME_WIDTH = 550;
    private static final int FRAME_HEIGHT = 400;

    public MenuFrame(){
        constructJFrame();
        constructFriendsListPanel();
        constructChatPanel();
    }

    private void constructJFrame(){
            setSize(FRAME_WIDTH, FRAME_HEIGHT);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            setResizable(false);
            setVisible(true);
    }

    private void constructFriendsListPanel(){
        JPanel fList = new JPanel();
        fList.setBackground(Color.darkGray);
        fList.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 100));

        add(fList, BorderLayout.WEST);
    }

    private void constructChatPanel(){
        JPanel chat = new JPanel();
        chat.setBackground(Color.lightGray);

        add(chat, BorderLayout.CENTER);
    }

}
