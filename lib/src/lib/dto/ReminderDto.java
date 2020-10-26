package lib.dto;

import java.io.Serializable;

public class ReminderDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int minsToEvent;
    private int eventId;

    public ReminderDto(int minsToEvent) {
        this.minsToEvent = minsToEvent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinsToEvent() {
        return minsToEvent;
    }

    public void setMinsToEvent(int minsToEvent) {
        this.minsToEvent = minsToEvent;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "minsToEvent=" + minsToEvent;
    }
}
