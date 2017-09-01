package client.gui;

import client.Client;
import client.ClientService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Chat extends JPanel {

    private final Calendar calendar;
    private final DateFormat format = new SimpleDateFormat("HH:mm:ss");
    private final ClientService messageService;

    private JTextArea messageHistory;
    private JTextField messageInput;
    private JTextField userReceiver;

    Chat(ClientService messageService){
        this.setLayout(new BorderLayout());
        calendar = Calendar.getInstance();
        this.messageService = messageService;

        if(messageService.isConnected()) {
            //North
            JPanel north = new JPanel(new BorderLayout());
            north.setBorder(new EmptyBorder(20, 10, 5, 10));
            messageHistory = new JTextArea();
            messageHistory.setMargin(new Insets(15, 10, 50, 10));
            messageHistory.setEditable(false);
            messageHistory.setBackground(Color.lightGray);
            north.add(messageHistory, BorderLayout.CENTER);
            this.add(north, BorderLayout.CENTER);
            //

            //South
            JPanel south = new JPanel(new BorderLayout());
            south.setBorder(new EmptyBorder(10, 5, 10, 5));
            messageInput = new JTextField();
            userReceiver = new JTextField(5);

            JButton sentButton = new JButton();
            sentButton.setText("Sent");
            sentButton.addActionListener(new SentButtonListener());

            south.add(messageInput, BorderLayout.CENTER);
            south.add(userReceiver, BorderLayout.WEST);
            south.add(sentButton, BorderLayout.EAST);
            this.add(south, BorderLayout.SOUTH);
            //
        }
        else {
            JPanel error = new JPanel(new FlowLayout());
            JTextPane connection_error = new JTextPane();
            connection_error.setText("Error connecting to server");
            error.add(connection_error);
            this.add(error, BorderLayout.CENTER);
        }
    }

    private class SentButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String messageTyped = messageInput.getText();
            if(!messageTyped.equals("")){
                System.out.println("sent message");
                messageHistory.append(format.format(
                        calendar.getTime())
                        + ": "
                        + messageTyped + "\n");
                messageInput.setText("");
                messageService.sentMessage(messageTyped, userReceiver.getText());
            }
        }
    }

}
