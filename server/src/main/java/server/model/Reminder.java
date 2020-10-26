package server.model;

import javax.persistence.*;

@Entity
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    // in reminder am timpul pana la eveniment si evenimentul respectiv
    private int minsToEvent;
    @ManyToOne
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", minsToEvent=" + minsToEvent +
                '}';
    }
}
