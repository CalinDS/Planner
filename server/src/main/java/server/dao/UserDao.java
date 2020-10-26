package server.dao;

import server.model.Event;
import server.model.User;

public interface UserDao {

    void persist(User user);

    User getByName(String name);

    User getByCredentials(String name, String password);

    void removeById(int id);

    User getById(int id);


}
