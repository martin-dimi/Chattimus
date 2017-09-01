package model;

import java.io.Serializable;

public class Message implements Serializable {

    private int chatRoomId;
    private String from;
    private String to;
    private String messageText;

    public Message(String from, String to, String messageText){
        this.from = from;
        this.to = to;
        this.messageText = messageText;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
