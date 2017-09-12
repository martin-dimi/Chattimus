package dao;

import model.Chatroom;
import model.Message;
import model.User;

import java.util.List;

public interface ChatroomsDAOService {

    Chatroom getChatroom(User[] participants);
    Chatroom createChatroom(User[] participants);
    void addMessage(Message message);
    List<Message> getMessages();

}
