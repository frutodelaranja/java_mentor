package service;

import dao.UserDao;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static UserService userService;
    private UserDao dao;
    private UserService(){
        dao = new UserDao();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public boolean addUser(User user) {
        if (!thisUserExists(user)){
            dao.addUser(user);
            return true;
        }
        return false;
    }


    public User getUser(User user) {
        return dao.getUser(user);
    }
    public User getUser(Long id) {
        return dao.getUser(id);
    }

    public boolean thisUserExists(User user) {
        if (dao.thisUserExists(user)){
            return true;
        }else
            return false;
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }


    public boolean updateUser(User user) {
        return dao.updateUser(user);
    }
    public boolean deleteUser(String login) {
        return dao.deleteUser(login);
    }
    public boolean deleteUser(Long id) {
        return dao.deleteUser(id);
    }

}
