package dao;

import model.User;

public interface UserDaoService {
    User getUser(String username, String password);
    User createUser(String username, String password);
    boolean updateUser(User user);

    User userExists(String username);
}
