package client;

import client.controller.EventController;
import client.controller.UserController;
import lib.dto.EventDto;
import lib.dto.ReminderDto;
import lib.dto.UserDto;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

// retin identificatorul utilizatorului logat
public class LoggedUser {

    private static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        LoggedUser.id = id;
    }

    // la orice atingere de buton, daca este timpul ca sa apara un remider, el apare
    public static void popUpReminder(JFrame frame) {
        UserDto user = UserController.getInstance().getById(id);
        List<EventDto> events = EventController.getInstance().getEventsByUser(user.getId());
        if (events == null) return;
        for (EventDto event : events) {
            List<ReminderDto> reminders =
                    EventController.getInstance().getReminderssByEvent(event.getId());
            for (ReminderDto reminder : reminders) {
                LocalDateTime localDateTime = LocalDateTime.now();
                localDateTime = localDateTime.plusMinutes(reminder.getMinsToEvent());
                if (localDateTime.compareTo(event.getTime()) > 0 &&
                        LocalDateTime.now().compareTo(event.getTime()) < 0) {
                    JOptionPane.showMessageDialog
                            (frame, event.getTitle() + " " +
                                    event.getTime() + " is happening in " +
                                    reminder.getMinsToEvent() + " minutes");
                    // sterg reminder-ul o data ce acesta s-a intamplat
                    EventController.getInstance().removeReminderById(reminder.getId());
                }
            }
        }
    }

}


