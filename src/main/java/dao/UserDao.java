package dao;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.User;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class UserDao implements UserDaoService {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class.getSimpleName());
    private static final String USER = "mitch";
    private static final String PASSWORD = "parola123";
    private static final String CONNECTION_URL =
            "mongodb://"+ USER +":" + PASSWORD +"@chattimus-shard-00-00-jgbyp.mongodb.net:27017," +
            "chattimus-shard-00-01-jgbyp.mongodb.net:27017," +
            "chattimus-shard-00-02-jgbyp.mongodb.net:27017/test?ssl=true&replicaSet=Chattimus-shard-0&authSource=admin";

    private final MongoCollection<Document> accounts;

    public UserDao(){
        accounts = getAccounts();
    }

    @Override
    public User getUser(String username, String password) {
        Document account = accounts.find(eq("username", username)).first();
        ObjectMapper mapper = new ObjectMapper();

        try {
            if (account != null) {
                String savedPass = account.getString("password");
                if (password.equals(savedPass)) {
                    String accountJSON = account.toJson();
                    User user = mapper.readValue(accountJSON, User.class);
                    LOGGER.log(Level.INFO, "User connected");
                    return user;
                } else {
                    LOGGER.log(Level.INFO, "Wrong password");
                    return null;
                }
            }
            LOGGER.log(Level.INFO, "User doesn't exist");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.WARNING, "Couldn't deserialize user");
            return null;
        }
    }

    public User createUser(String username, String password) {
        User newUser = new User(username, password);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String accountJSON = mapper.writeValueAsString(newUser);
            Document account = Document.parse(accountJSON);
            accounts.insertOne(account);
            LOGGER.log(Level.INFO, "Account created");
           return newUser;
       }catch (Exception e){
           LOGGER.log(Level.INFO, "Couldn't create account");
            return null;
       }
    }

    @Override
    public boolean updateUser(User user) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String userJSON = mapper.writeValueAsString(user);
            Document account = Document.parse(userJSON);

            accounts.updateOne(eq("username", user.getUsername()),
                    new Document("$set", account));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public User userExists(String username) {
        Document account = accounts.find(eq("username", username)).first();

        if(account != null){
            String userName = account.getString("username");
            User user = new User(userName);
            return user;
        }

        return null;
    }

    private MongoCollection<Document> getAccounts(){
        LOGGER.log(Level.INFO, "Connecting to db");
        MongoClientURI connectionURI = new MongoClientURI(CONNECTION_URL);
        MongoClient mongoClient = new MongoClient(connectionURI);
        MongoDatabase database = mongoClient.getDatabase("Chattimus");
        MongoCollection<Document> collection = database.getCollection("Accounts");
        LOGGER.log(Level.INFO, "Connection successful");
        return collection;
    }

}
