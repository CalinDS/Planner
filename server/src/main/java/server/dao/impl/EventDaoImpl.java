package server.dao.impl;

import jdk.swing.interop.SwingInterOpUtils;
import server.dao.EventDao;
import server.model.Event;
import server.model.Reminder;
import server.model.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class EventDaoImpl implements EventDao {

    private final EntityManager entityManager;

    public EventDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(Event event) {
        entityManager.getTransaction().begin();
        entityManager.persist(event);
        entityManager.getTransaction().commit();
    }

    @Override
    public void addEventToUser(int id, Event event) {
        // updatez campul de user din event si dau refresh si la instanta de user, pentru ca
        // in lista de event-uri va aparea un nou event
        User user = entityManager.find(User.class, id);
        event.setUser(user);
        entityManager.getTransaction().begin();
        entityManager.persist(event);
        entityManager.refresh(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public Event getEventById(int id) {
        return entityManager.find(Event.class, id);
    }

    @Override
    public List<Event> getEventsByUser(int userId) {
        var query = entityManager
                .createQuery("SELECT e FROM Event e JOIN e.user u WHERE u.id = :id", Event.class);
        query.setParameter("id", userId);
        return query.getResultList();
    }

    @Override
    public void removeById(int id) {
        Event event = entityManager.find(Event.class, id);
        User user = entityManager.find(User.class, event.getUser().getId());
        // sterg un event si upadatez user-ul care detinea acel event
        entityManager.getTransaction().begin();
        entityManager.remove(event);
        entityManager.refresh(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void addReminderToEvent(int id, Reminder reminder) {
        // asemanator cu addEventToUser
        Event event = entityManager.find(Event.class, id);
        reminder.setEvent(event);
        entityManager.getTransaction().begin();
        entityManager.persist(reminder);
        entityManager.refresh(event);
        entityManager.getTransaction().commit();
    }

    @Override
    public Reminder getReminderById(int reminderId) {
        Reminder reminder = entityManager.find(Reminder.class, reminderId);
        return reminder;
    }

    @Override
    public void removeReminderById(int reminderId) {
        Reminder reminder = entityManager.find(Reminder.class, reminderId);
        Event event = entityManager.find(Event.class, reminder.getEvent().getId());
        entityManager.getTransaction().begin();
        entityManager.remove(reminder);
        entityManager.refresh(event);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Reminder> getReminderssByEvent(int eventId) {
        var query = entityManager
                .createQuery("SELECT r FROM Reminder r JOIN r.event e WHERE e.id = :id", Reminder.class);
        query.setParameter("id", eventId);
        return query.getResultList();
    }


}
