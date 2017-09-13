package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import model.Chatroom;
import model.Message;
import model.User;
import org.bson.Document;
import sun.misc.resources.Messages_es;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;

public class ChatroomDAO implements ChatroomsDAOService {

    private static final Logger LOGGER = Logger.getLogger(ChatroomDAO.class.getSimpleName());
    private static final String USER = "mitch";
    private static final String PASSWORD = "parola123";
    private static final String CONNECTION_URL =
            "mongodb://"+ USER +":" + PASSWORD +"@chattimus-shard-00-00-jgbyp.mongodb.net:27017," +
            "chattimus-shard-00-01-jgbyp.mongodb.net:27017," +
            "chattimus-shard-00-02-jgbyp.mongodb.net:27017/test?ssl=true&replicaSet=Chattimus-shard-0&authSource=admin";

    private final MongoCollection<Document> chatrooms;

    public ChatroomDAO(){
        chatrooms = getChatrooms();
    }


    @Override
    public Chatroom getChatroom(String[] participants) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            Document chatroomDoc = chatrooms.find(eq("participants", participants)).first();
            Chatroom chatroom = mapper.readValue(chatroomDoc.toJson(), Chatroom.class);
            LOGGER.log(Level.INFO, "Chatroom found.");
            return chatroom;
        } catch (Exception e){
            LOGGER.log(Level.INFO, "Could not find chatroom.");
            return null;
        }

    }

    @Override
    public Chatroom createChatroom(String[] participants) {
        Chatroom chatroom = new Chatroom(participants);
        ObjectMapper mapper = new ObjectMapper();
        LOGGER.log(Level.INFO, "Creating chatroom.");
        try {
            String chatroomJSON = mapper.writeValueAsString(chatroom);
            Document chatroomToBeInserted = Document.parse(chatroomJSON);
            chatrooms.insertOne(chatroomToBeInserted);
            LOGGER.log(Level.INFO, "Chatroom created.");
            return chatroom;
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.WARNING, "Chatroom not created.");
            return null;
        }
    }

    @Override
    public boolean updateChatroom(Chatroom chatroom) {
        return false;
    }

    private  MongoCollection<Document> getChatrooms(){
        LOGGER.log(Level.INFO, "Connecting to db");
        MongoClientURI connectionURI = new MongoClientURI(CONNECTION_URL);
        MongoClient mongoClient = new MongoClient(connectionURI);
        MongoDatabase database = mongoClient.getDatabase("Chattimus");
        MongoCollection<Document> collection = database.getCollection("Chatrooms");
        LOGGER.log(Level.INFO, "Connection successful");
        return collection;
    }
}
