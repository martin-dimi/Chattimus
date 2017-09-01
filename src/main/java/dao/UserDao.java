package dao;


public class UserDao {

    private static final String user = "mitch";
    private static final String password = "pass123";

    private static final String connectionURL =
            "mongodb://"+ user +":" + password +"@chattimus-shard-00-00-jgbyp.mongodb.net:27017," +
            "chattimus-shard-00-01-jgbyp.mongodb.net:27017," +
            "chattimus-shard-00-02-jgbyp.mongodb.net:27017/test?ssl=true&replicaSet=Chattimus-shard-0&authSource=admin";

    public static void createUser(String username, String password) {


    }

    public static void connect() {


    }

    public static void main(String[] args) {

    }
}
