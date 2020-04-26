package util;

import model.User;

import java.util.List;

public interface UserDao {

    public boolean addUser( User user);

    public boolean thisUserExists(User user);

    public User getUser(Long id);

    public List<User> getAllUsers();

    public boolean updateUser(User user);

    public boolean deleteUser(Long id);

}
