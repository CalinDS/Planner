package server.convert;

import lib.dto.ReminderDto;
import server.model.Reminder;

public final class ReminderConvertor {

    private ReminderConvertor() {}

    public static Reminder convert(ReminderDto reminderDto) {
        var reminder = new Reminder();
        reminder.setId(reminderDto.getId());
        reminder.setMinsToEvent(reminderDto.getMinsToEvent());
        return reminder;
    }

    public static ReminderDto convert(Reminder reminder) {
        var reminderDto = new ReminderDto(reminder.getMinsToEvent());
        reminderDto.setId(reminder.getId());
        reminderDto.setEventId(reminder.getEvent().getId());
        return reminderDto;
    }
}
