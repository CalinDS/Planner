package client.controller;

import lib.dto.UserDto;
import lib.service.UserService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class UserController {

    private final UserService userService;

    private UserController() {
        try {
            var registry = LocateRegistry.getRegistry("localhost", 4545);
            userService = (UserService) registry.lookup("userService");
        } catch (RemoteException |NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private static final class SingletonHolder {
        public static final UserController INSTANCE = new UserController();
    }

    public static UserController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void persist (UserDto userDto) {
        try {
            userService.persist(userDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean isTaken(String name) {
        try {
            return userService.isTaken(name);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean correctCredentials(String name, String password) {
        try {
            return userService.correctCredentials(name, password);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void removeById(int id) {
        try {
            userService.removeById(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public UserDto getByCredentials(String name, String password) {
        try {
            return userService.getByCredentials(name, password);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public UserDto getById(int id) {
        try {
            return userService.getById(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
