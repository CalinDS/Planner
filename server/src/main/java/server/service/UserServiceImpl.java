package server.service;

import lib.dto.UserDto;
import lib.service.UserService;
import server.convert.UserConvertor;
import server.dao.UserDao;
import server.dao.impl.UserDaoImpl;

import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() throws RemoteException {
        var emf = Persistence.createEntityManagerFactory("java2prPU");
        var em = emf.createEntityManager();

        userDao = new UserDaoImpl(em);
    }

    @Override
    public void persist(UserDto userDto) throws RemoteException {
        var user = UserConvertor.convert(userDto);
        userDao.persist(user);
    }

    // verific daca numele de utilizator este deja luat
    @Override
    public boolean isTaken(String name) throws RemoteException {
        var user = userDao.getByName(name);
        if (user == null) {
            return false;
        }
        return true;
    }

    // verific daca datele de login sunt valide
    @Override
    public boolean correctCredentials(String name, String password) throws RemoteException {
        var user = userDao.getByCredentials(name, password);
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    public void removeById(int id) throws RemoteException {
         userDao.removeById(id);
    }

    @Override
    public UserDto getByCredentials(String name, String password) throws RemoteException {
        return UserConvertor.convert(userDao.getByCredentials(name, password));
    }

    @Override
    public UserDto getById(int id) throws RemoteException {
        return UserConvertor.convert(userDao.getById(id));
    }


}
