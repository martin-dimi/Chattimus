package model;

public class ChatRoom {

    private int id;
    private String[] participants;

    public ChatRoom(int id, String[] participants) {
        this.id = id;
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getParticipants() {
        return participants;
    }

    public void setParticipants(String[] participants) {
        this.participants = participants;
    }
}
