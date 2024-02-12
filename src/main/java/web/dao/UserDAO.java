package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    void addUser(User user);

    void editUser(User user, int id);

    void deleteUser(int id);

    User getById(int id);

}
