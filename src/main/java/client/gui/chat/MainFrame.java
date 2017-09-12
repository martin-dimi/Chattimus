/*
 * Created by JFormDesigner on Wed Sep 06 10:23:47 EEST 2017
 */

package client.gui.chat;

import client.utils.ConnectionService;
import model.Message;
import model.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;


public class MainFrame extends JFrame {

    private ConnectionService connectionService;
    private User user;

    public void start(ConnectionService service, User user){
        connectionService = service;
        this.user = user;
        setVisible(true);
        initComponents();
    }

    private void sentMessageButtonActionPerformed(ActionEvent e) {
        String message = messageField.getText();
        messageField.setText("");
        historyMessagesArea.append(message + "\n");

        connectionService.sentMessage(message);
    }


    private void createUIComponents() {
        accountNameLabel = new JLabel(user.getUsername());
    }

    private void addFriendButtonActionPerformed(ActionEvent e) {
        AddFriendFrame addFriendFrame = new AddFriendFrame(connectionService);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - martin dimitrov
        createUIComponents();

        accountInformationPanel = new JPanel();
        personalInfoPanel = new JPanel();
        friendsListPanel = new JScrollPane();
        list1 = new JList();
        addFriendButton = new JButton();
        ChatPanel = new JPanel();
        messageField = new JTextField();
        historyScrollPane = new JScrollPane();
        historyMessagesArea = new JTextArea();
        sentMessageButton = new JButton();
        panel1 = new JPanel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 300));
        Container contentPane = getContentPane();

        //======== accountInformationPanel ========
        {
            accountInformationPanel.setBackground(new Color(0, 51, 102));
            accountInformationPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            accountInformationPanel.setMinimumSize(new Dimension(100, 200));
            accountInformationPanel.setPreferredSize(new Dimension(150, 389));

            // JFormDesigner evaluation mark
            accountInformationPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), accountInformationPanel.getBorder())); accountInformationPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


            //======== personalInfoPanel ========
            {
                personalInfoPanel.setBackground(new Color(0, 51, 102));

                //---- accountNameLabel ----
                accountNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                accountNameLabel.setBackground(Color.white);
                accountNameLabel.setForeground(Color.white);

                GroupLayout personalInfoPanelLayout = new GroupLayout(personalInfoPanel);
                personalInfoPanel.setLayout(personalInfoPanelLayout);
                personalInfoPanelLayout.setHorizontalGroup(
                    personalInfoPanelLayout.createParallelGroup()
                        .addGroup(personalInfoPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(accountNameLabel, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addContainerGap())
                );
                personalInfoPanelLayout.setVerticalGroup(
                    personalInfoPanelLayout.createParallelGroup()
                        .addGroup(personalInfoPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(accountNameLabel)
                            .addContainerGap(64, Short.MAX_VALUE))
                );
            }

            //======== friendsListPanel ========
            {
                friendsListPanel.setViewportView(list1);
            }

            //---- addFriendButton ----
            addFriendButton.setText("Add friend");
            addFriendButton.setBackground(new Color(0, 0, 51));
            addFriendButton.setForeground(Color.white);
            addFriendButton.addActionListener(e -> addFriendButtonActionPerformed(e));

            GroupLayout accountInformationPanelLayout = new GroupLayout(accountInformationPanel);
            accountInformationPanel.setLayout(accountInformationPanelLayout);
            accountInformationPanelLayout.setHorizontalGroup(
                accountInformationPanelLayout.createParallelGroup()
                    .addGroup(accountInformationPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(accountInformationPanelLayout.createParallelGroup()
                            .addComponent(personalInfoPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(friendsListPanel)
                            .addGroup(GroupLayout.Alignment.TRAILING, accountInformationPanelLayout.createSequentialGroup()
                                .addGap(0, 26, Short.MAX_VALUE)
                                .addComponent(addFriendButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)))
                        .addContainerGap())
            );
            accountInformationPanelLayout.setVerticalGroup(
                accountInformationPanelLayout.createParallelGroup()
                    .addGroup(accountInformationPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(personalInfoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(addFriendButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(friendsListPanel, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                        .addContainerGap())
            );
        }

        //======== ChatPanel ========
        {
            ChatPanel.setBackground(new Color(153, 153, 153));

            //---- messageField ----
            messageField.setBackground(new Color(204, 204, 204));
            messageField.setForeground(Color.black);

            //======== historyScrollPane ========
            {

                //---- historyMessagesArea ----
                historyMessagesArea.setBackground(new Color(204, 204, 204));
                historyMessagesArea.setEditable(false);
                historyMessagesArea.setForeground(Color.black);
                historyScrollPane.setViewportView(historyMessagesArea);
            }

            //---- sentMessageButton ----
            sentMessageButton.setText("Send");
            sentMessageButton.setBackground(new Color(153, 153, 153));
            sentMessageButton.setForeground(Color.black);
            sentMessageButton.addActionListener(e -> sentMessageButtonActionPerformed(e));

            GroupLayout ChatPanelLayout = new GroupLayout(ChatPanel);
            ChatPanel.setLayout(ChatPanelLayout);
            ChatPanelLayout.setHorizontalGroup(
                ChatPanelLayout.createParallelGroup()
                    .addGroup(ChatPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ChatPanelLayout.createParallelGroup()
                            .addComponent(historyScrollPane, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                            .addGroup(ChatPanelLayout.createSequentialGroup()
                                .addComponent(messageField, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sentMessageButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
            );
            ChatPanelLayout.setVerticalGroup(
                ChatPanelLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, ChatPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(historyScrollPane, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addGroup(ChatPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(messageField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                            .addComponent(sentMessageButton))
                        .addGap(20, 20, 20))
            );
        }

        //======== panel1 ========
        {

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGap(0, 366, Short.MAX_VALUE)
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGap(0, 88, Short.MAX_VALUE)
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(accountInformationPanel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                        .addComponent(ChatPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(accountInformationPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(ChatPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - martin dimitrov
    private JPanel accountInformationPanel;
    private JPanel personalInfoPanel;
    private JLabel accountNameLabel;
    private JScrollPane friendsListPanel;
    private JList list1;
    private JButton addFriendButton;
    private JPanel ChatPanel;
    private JTextField messageField;
    private JScrollPane historyScrollPane;
    private JTextArea historyMessagesArea;
    private JButton sentMessageButton;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
