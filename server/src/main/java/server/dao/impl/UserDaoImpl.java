package server.dao.impl;

import server.model.Event;
import server.model.User;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements server.dao.UserDao {

    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public User getByName(String name) {
        var query = entityManager.createQuery
                ("SELECT u FROM User u WHERE u.name = :name", User.class);
        query.setParameter("name", name);

        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public User getByCredentials(String name, String password) {
        var query = entityManager.createQuery
                ("SELECT u FROM User u WHERE u.name = :name AND u.password = :password", User.class);
        query.setParameter("name", name);
        query.setParameter("password", password);

        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    // functie ajutatoare pentru remove
    public List<Event> getEventsByUserId(int userId) {
        var query = entityManager
                .createQuery("SELECT e FROM Event e JOIN e.user u WHERE u.id = :id", Event.class);
        query.setParameter("id", userId);
        return query.getResultList();
    }

    @Override
    public void removeById(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.getTransaction().begin();
        List<Event> events = getEventsByUserId(id);
        // trebuie sa sterg si toate evenimentele utilizatorului
        // (aparent cascade si orphanRemoval nu au fost suficiente, tot imi luam eroare)
        events.forEach(e -> entityManager.remove(e));
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

}
