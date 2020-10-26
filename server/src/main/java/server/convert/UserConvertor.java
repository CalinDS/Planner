package server.convert;

import lib.dto.EventDto;
import server.model.Event;
import server.model.User;
import lib.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public final class UserConvertor {

    private UserConvertor() {
    }

    public static User convert(UserDto userDto) {
        var user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static UserDto convert (User user) {
        var userDto = new UserDto(
                user.getName(),
                user.getPassword());
        userDto.setId(user.getId());
        List<Integer> eventIds = new ArrayList<>();
        for (Event e : user.getEvents()) {
            eventIds.add(e.getUser().getId());
        }
        userDto.setEventIds(eventIds);
        return userDto;
    }

}
