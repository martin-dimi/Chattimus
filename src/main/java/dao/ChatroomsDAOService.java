package dao;

import model.Chatroom;
import model.Message;
import model.User;

import java.util.List;

public interface ChatroomsDAOService {

    Chatroom getChatroom(String[] participants);
    Chatroom createChatroom(String[] participants);
    boolean updateChatroom(Chatroom chatroom);

}
