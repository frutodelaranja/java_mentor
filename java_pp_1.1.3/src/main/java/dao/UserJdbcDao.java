package dao;

import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao implements UserDao {
    DBHelper dbHelper = DBHelper.getInstance();
    Connection connection;

    {
        try {
            connection = dbHelper.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public UserJdbcDao() {
    }


    public boolean addUser( User user){
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO users(name, login, password) VALUES (?,?,?)");){
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean thisUserExists(User user) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login=?");) {
            statement.setString(1, user.getLogin());
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//    public User getUser(User user) {
//        User isUser = new User();
//        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");) {
//            statement.setString(1, user.getLogin());
//            statement.setString(2, user.getPassword());
//            ResultSet result = statement.executeQuery();
//            if (result.next()) {
//                isUser.setId(result.getLong(1));
//                isUser.setName(result.getString(2));
//                isUser.setLogin(result.getString(3));
//                isUser.setPassword(result.getString(4));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return isUser;
//    }

    public User getUser(Long id) {
        User isUser = new User();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id=?");) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                isUser.setId(result.getLong(1));
                isUser.setName(result.getString(2));
                isUser.setLogin(result.getString(3));
                isUser.setPassword(result.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUser;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();) {
            statement.executeQuery("select * from users");
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                users.add(new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean updateUser(User user) {

        try (PreparedStatement statement = connection.prepareStatement("UPDATE users SET name=?, login=?, password=? WHERE id=?");) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(String login) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE from users WHERE login=?")) {
            statement.setString(1, login);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE from users WHERE id=?")) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
