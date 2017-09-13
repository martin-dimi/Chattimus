package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Chatroom {

    private String[] participants;
    private List<Message> messages;

    public Chatroom(String[] participants) {
        this.participants = participants;
        messages = new ArrayList<>();
    }

    public Chatroom(String[] participants, List<Message> messages) {
        this.participants = participants;
        this.messages = messages;
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public String[] getParticipants() {
        return participants;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
