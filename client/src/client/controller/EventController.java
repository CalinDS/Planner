package client.controller;

import lib.dto.EventDto;
import lib.dto.ReminderDto;
import lib.service.EventService;
import lib.service.UserService;
import org.w3c.dom.events.Event;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EventController {

    private final EventService eventService;

    private EventController() {
        try {
            var registry = LocateRegistry.getRegistry("localhost", 4545);
            eventService = (EventService) registry.lookup("eventService");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final class SingletonHolder {
        public static final EventController INSTANCE = new EventController();
    }

    public static EventController getInstance() {
        return EventController.SingletonHolder.INSTANCE;
    }

    public void persist(EventDto eventDto) {
        try {
            eventService.persist(eventDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void addEventToUser(int id, EventDto eventDto) {
        try {
            eventService.addEventToUser(id, eventDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public EventDto getEventById(int id) {
        try {
            return eventService.getEventById(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<EventDto> getEventsByUser(int userId) {
        try {
            return eventService.getEventsByUser(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void removeById(int id) {
        try {
            eventService.removeById(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void addReminderToEvent(int id, ReminderDto reminderDto) {
        try {
            eventService.addReminderToEvent(id, reminderDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<ReminderDto> getRemindersByEvent(int eventId) {
        try {
            var result = eventService.getRemindersByEvent(eventId);
            if (result == null) return null;
            result.sort(Comparator.comparing(ReminderDto::getMinsToEvent));
            return result;
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void removeReminderById(int reminderId) {
        try {
            eventService.removeReminderById(reminderId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public List<EventDto> getEventsByUserAndDay(int userId, LocalDate localDate) {
        var eventDtos = getEventsByUser(userId);
        // iau doar evenimentele din ziua respectiva si le ordonez
        var result =  eventDtos.stream().filter(e -> e.getTime().toLocalDate().equals(localDate)).
                collect(Collectors.toList());
        result.sort(Comparator.comparing(EventDto::getTime));
        return result;
    }

    public List<ReminderDto> getReminderssByEvent(int eventId) {
        try {
            var result = eventService.getReminderssByEvent(eventId);
            result.sort(Comparator.comparing(ReminderDto::getMinsToEvent));
            return result;
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
