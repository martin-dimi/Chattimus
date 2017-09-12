package model;

import java.io.Serializable;

public class Message implements Serializable {

    private int chatRoomId;
    private String sender;
    private String receiver;
    private String body;

    public Message(int chatRoomId, String sender, String receiver, String body){
        this.chatRoomId = chatRoomId;
        this.sender = sender;
        this.receiver = receiver;
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
