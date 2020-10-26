package lib.service;

import lib.dto.EventDto;
import lib.dto.UserDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote {

    void persist(UserDto userDto) throws RemoteException;

    boolean isTaken(String name) throws RemoteException;

    boolean correctCredentials(String name, String password) throws RemoteException;

    void removeById(int id) throws RemoteException;

    UserDto getByCredentials(String name, String password) throws RemoteException;

    UserDto getById(int id) throws RemoteException;

}
