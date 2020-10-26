package lib.service;

import jdk.jfr.Event;
import lib.dto.EventDto;
import lib.dto.ReminderDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface EventService extends Remote {

    void persist(EventDto eventDto) throws RemoteException;

    void addEventToUser(int id, EventDto eventDto) throws RemoteException;

    EventDto getEventById(int id) throws RemoteException;

    List<EventDto> getEventsByUser(int userId) throws RemoteException;

    void removeById(int id) throws RemoteException;

    void addReminderToEvent(int id, ReminderDto reminderDto) throws RemoteException;

    List<ReminderDto> getRemindersByEvent(int eventId) throws RemoteException;

    void removeReminderById(int reminderId) throws RemoteException;

    List<ReminderDto> getReminderssByEvent(int eventId) throws RemoteException;

}
