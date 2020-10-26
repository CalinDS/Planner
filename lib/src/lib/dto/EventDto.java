package lib.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private LocalDateTime time;
    // nu imi trebuie obiectele intregi, doar idenificatoarele
    private int userId;
    private List<Integer> reminderIds = new ArrayList<>();

    public EventDto(String title, LocalDateTime time) {
        this.title = title;
        this.time = time;
    }

    public List<Integer> getReminderIds() {
        return reminderIds;
    }

    public void setReminderIds(List<Integer> reminderIds) {
        this.reminderIds = reminderIds;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return title + "    " + time;
    }
}
