package lib.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDto implements Serializable {

    private int id;
    private String name;
    private String password;
    // e suficient sa retin aici doar id-urile event-urilor
    private List<Integer> eventIds = new ArrayList<>();

    public UserDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public List<Integer> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<Integer> eventIds) {
        this.eventIds = eventIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", eventIds=" + eventIds +
                '}';
    }
}
