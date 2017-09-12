package dao;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import model.User;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;


public class UserDao implements UserDaoService {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class.getSimpleName());
    private static final String USER = "mitch";
    private static final String PASSWORD = "parola123";
    private static final String CONNECTION_URL =
            "mongodb://"+ USER +":" + PASSWORD +"@chattimus-shard-00-00-jgbyp.mongodb.net:27017," +
            "chattimus-shard-00-01-jgbyp.mongodb.net:27017," +
            "chattimus-shard-00-02-jgbyp.mongodb.net:27017/test?ssl=true&replicaSet=Chattimus-shard-0&authSource=admin";

    @Override
    public User getUser(String username, String password) {
        MongoCollection<Document> accounts = getAccounts();
        Document account = accounts.find(eq("username", username)).first();

        if(account != null){
            String savedPass = account.getString("password");
            if (password.equals(savedPass)) {
                LOGGER.log(Level.INFO, "User connected");
                User user = new User(username);
                return user;
            }else {
                LOGGER.log(Level.INFO, "Wrong password");
                return null;
            }
        }
        LOGGER.log(Level.INFO, "User doesn't exist");
        return null;
    }

    public User createUser(String username, String password) {
        User user = new User(username);
       try {
           MongoCollection<Document> accounts = getAccounts();
           Document account = new Document();
           account.append("username", username);
           account.append("password", password);
           account.append("friends", user.getFriends());
           accounts.insertOne(account);
           LOGGER.log(Level.INFO, "Account created");
           return user;
       }catch (Exception e){
           LOGGER.log(Level.INFO, "Couldn't create account");
            return null;
       }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            MongoCollection<Document> accounts = getAccounts();
            String username = user.getUsername();

            System.out.println(user.getFriends().size());
            Document accountUpdate = new Document();
            accountUpdate.append("username", user.getUsername());
            accountUpdate.append("friends", user.getFriends());

            List<Document> friends = new ArrayList<>();
            for(User fr : user.getFriends()){
                friends.add(new Document().append("username", fr.getUsername()));
            }

            System.out.println("Trying to update");
            UpdateResult result = accounts.updateOne(eq("username", username),
                    new Document("$set", new Document("account", "**Updated**")
                            .append("username", user.getUsername())
                            .append("friends", friends)));


            System.out.println("Number of record updated:- " + result.getModifiedCount());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public User userExists(String username) {
        MongoCollection<Document> accounts = getAccounts();
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
