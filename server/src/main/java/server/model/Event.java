package server.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    // un eveniment este definit de un titlu, momentul cand are loc, user-ul care a creat
    // evenimentul si reminderele pentru acesta
    private String title;
    private LocalDateTime time;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    private List<Reminder> reminders = new ArrayList<>();

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", reminders=" + reminders +
                '}';
    }
}
