package model;

import java.util.ArrayList;
import java.util.List;

public class Chatroom {

    private int id;
    private User[] participants;
    private List<Message> messages;

    public Chatroom(User[] participants) {
        this.participants = participants;
        messages = new ArrayList<>();
    }

    public Chatroom(User[] participants, List<Message> messages) {
        this.participants = participants;
        this.messages = messages;
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public User[] getParticipants() {
        return participants;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
