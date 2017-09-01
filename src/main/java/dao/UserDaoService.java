package dao;

public interface UserDaoService {

    void createUser(String username);
    void getUser(String username, String password);

}
