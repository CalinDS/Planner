package server.service;

import lib.dto.EventDto;
import lib.dto.ReminderDto;
import lib.service.EventService;
import server.convert.EventConvertor;
import server.convert.ReminderConvertor;
import server.dao.EventDao;
import server.dao.UserDao;
import server.dao.impl.EventDaoImpl;
import server.dao.impl.UserDaoImpl;
import server.model.Event;

import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventServiceImpl extends UnicastRemoteObject implements EventService {

    private final EventDao eventDao;

    public EventServiceImpl() throws RemoteException {
        var emf = Persistence.createEntityManagerFactory("java2prPU");
        var em = emf.createEntityManager();

        eventDao = new EventDaoImpl(em);
    }

    @Override
    public void persist(EventDto eventDto) throws RemoteException {
        var event = EventConvertor.convert(eventDto);
        eventDao.persist(event);
    }

    @Override
    public void addEventToUser(int id, EventDto eventDto) throws RemoteException {
        Event event = EventConvertor.convert(eventDto);
        eventDao.addEventToUser(id, event);
    }

    @Override
    public EventDto getEventById(int id) throws RemoteException {
        return EventConvertor.convert(eventDao.getEventById(id));
    }

    @Override
    public List<EventDto> getEventsByUser(int userId) throws RemoteException {
        return eventDao.getEventsByUser(userId).stream()
                .map(EventConvertor::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void removeById(int id) throws RemoteException {
        eventDao.removeById(id);
    }

    @Override
    public void addReminderToEvent(int id, ReminderDto reminderDto) throws RemoteException {
        eventDao.addReminderToEvent(id, ReminderConvertor.convert(reminderDto));
    }

    @Override
    public List<ReminderDto> getRemindersByEvent(int eventId) throws RemoteException {
        EventDto eventDto = getEventById(eventId);
        List<ReminderDto> reminderDtos = new ArrayList<>();
        if (eventDto.getReminderIds().size() == 0) {
            return null;
        }
        for (Integer i : eventDto.getReminderIds()) {
            if (eventDao.getReminderById(i) == null) {
                ;
            } else {
                ReminderDto reminderDto = ReminderConvertor.convert(eventDao.getReminderById(i));
                reminderDtos.add(reminderDto);
            }
        }

        return reminderDtos;

    }

    @Override
    public void removeReminderById(int reminderId) throws RemoteException {
        eventDao.removeReminderById(reminderId);
    }

    @Override
    public List<ReminderDto> getReminderssByEvent(int eventId) throws RemoteException {
        return eventDao.getReminderssByEvent(eventId).stream()
                .map(ReminderConvertor::convert)
                .collect(Collectors.toList());
    }
}
