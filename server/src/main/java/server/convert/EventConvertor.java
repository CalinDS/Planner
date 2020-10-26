package server.convert;

import lib.dto.EventDto;
import server.model.Event;
import server.model.Reminder;
import server.model.User;

import java.util.ArrayList;
import java.util.List;

public final class EventConvertor {

    private EventConvertor() {
    }

    public static Event convert(EventDto eventDto) {
        var event = new Event();
        event.setId(eventDto.getId());
        event.setTime(eventDto.getTime());
        event.setTitle(eventDto.getTitle());
        return event;
    }

    public static EventDto convert(Event event) {
        var eventDto = new EventDto(event.getTitle(), event.getTime());
        System.out.println(event);
        eventDto.setId(event.getId());
        eventDto.setUserId(event.getUser().getId());
        List<Integer> reminderIds = new ArrayList<>();
        for (Reminder r : event.getReminders()) {
            reminderIds.add(r.getId());
        }
        eventDto.setReminderIds(reminderIds);
        return eventDto;
    }

}
