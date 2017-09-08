package dao;

public interface UserDaoService {

    Boolean getUser(String username, String password);
    Boolean createUser(String username, String password);
}
