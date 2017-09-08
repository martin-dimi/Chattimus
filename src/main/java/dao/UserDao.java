package dao;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDao implements UserDaoService {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class.getSimpleName());
    private static final String user = "mitch";
    private static final String password = "parola123";
    private static final String connectionURL =
            "mongodb://"+ user +":" + password +"@chattimus-shard-00-00-jgbyp.mongodb.net:27017," +
            "chattimus-shard-00-01-jgbyp.mongodb.net:27017," +
            "chattimus-shard-00-02-jgbyp.mongodb.net:27017/test?ssl=true&replicaSet=Chattimus-shard-0&authSource=admin";

    @Override
    public Boolean getUser(String username, String password) {
        MongoCollection<Document> accounts = getAccounts();
        Document account = accounts.find(Filters.eq("username", username)).first();
        if(account != null){
            String savedPass = account.getString("password");
            if (password.equals(savedPass)) {
                LOGGER.log(Level.INFO, "User connected");
                return true;
            }else {
                LOGGER.log(Level.INFO, "Wrong password");
                return false;
            }
        }
        LOGGER.log(Level.INFO, "User doesn't exist");
        return false;
    }

    public Boolean createUser(String username, String password) {
       try {
           MongoCollection<Document> accounts = getAccounts();
           Document account = new Document("account", "MongoDB");
           account.append("username", username);
           account.append("password", password);
           accounts.insertOne(account);
           LOGGER.log(Level.INFO, "Account created");
           return true;
       }catch (Exception e){
           LOGGER.log(Level.INFO, "Couldn't create account");
            return false;
       }
    }

    private MongoCollection<Document> getAccounts(){
        LOGGER.log(Level.INFO, "Connecting to db");
        MongoClientURI connectionURI = new MongoClientURI(connectionURL);
        MongoClient mongoClient = new MongoClient(connectionURI);
        MongoDatabase database = mongoClient.getDatabase("Chattimus");
        MongoCollection<Document> collection = database.getCollection("Accounts");
        LOGGER.log(Level.INFO, "Connection successful");
        return collection;
    }
}
