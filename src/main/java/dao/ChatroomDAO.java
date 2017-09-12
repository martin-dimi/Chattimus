package dao;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatroomDAO implements ChatroomsDAOService {

    private static final Logger LOGGER = Logger.getLogger(ChatroomDAO.class.getSimpleName());
    private static final String USER = "mitch";
    private static final String PASSWORD = "parola123";
    private static final String CONNECTION_URL =
            "mongodb://"+ USER +":" + PASSWORD +"@chattimus-shard-00-00-jgbyp.mongodb.net:27017," +
            "chattimus-shard-00-01-jgbyp.mongodb.net:27017," +
            "chattimus-shard-00-02-jgbyp.mongodb.net:27017/test?ssl=true&replicaSet=Chattimus-shard-0&authSource=admin";

    @Override
    public Chatroom getChatroom(User[] participants) {
        MongoCollection<Document> collection = getChatrooms();
        Document chatroom = collection
                .find(Filters.eq("participants", participants))
                .first();

        List<Message> messages = Arrays.asList(chatroom.get("participants", Message[].class));

        return new Chatroom(participants, messages);
    }

    @Override
    public Chatroom createChatroom(User[] participants) {
        MongoCollection<Document> collection = getChatrooms();

        Document chatroom = new Document();
        chatroom.append("participants", participants);
        chatroom.append("messages", new ArrayList());
        collection.insertOne(chatroom);

        return new Chatroom(participants);
    }

    @Override
    public void addMessage(Message message) {
        MongoCollection<Document> collection = getChatrooms();
    }

    @Override
    public List<Message> getMessages() {
        return null;
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
