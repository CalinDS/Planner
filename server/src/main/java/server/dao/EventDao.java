package server.dao;

import server.model.Event;
import server.model.Reminder;

import javax.transaction.Transactional;
import java.util.List;

public interface EventDao {

    void persist(Event event) ;

    void addEventToUser(int id, Event event);

    Event getEventById(int id);

    List<Event> getEventsByUser(int userId);

    void removeById(int id);

    void addReminderToEvent(int id, Reminder reminder);

    Reminder getReminderById(int reminderId);

    void removeReminderById(int reminderId);

    public List<Reminder> getReminderssByEvent(int eventId);

}
