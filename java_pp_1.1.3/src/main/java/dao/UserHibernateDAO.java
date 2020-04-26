package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.UserDao;

import java.util.List;

public class UserHibernateDAO implements UserDao {
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public boolean addUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        boolean yes = session.contains(user);
        transaction.commit();
        session.close();
        return yes;
    }

    @Override
    public boolean thisUserExists(User user) {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User WHERE login = :login").setParameter("login", user.getLogin()).list();
        transaction.commit();
        session.close();
        return !users.isEmpty();
    }

    @Override
    public User getUser(Long id) {
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createQuery("FROM User WHERE id = :id").setParameter("id", id);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public boolean updateUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        boolean yes = session.contains(user);
        transaction.commit();
        session.close();
        return yes;
    }

    @Override
    public boolean deleteUser(Long id) {
        Transaction transaction = session.beginTransaction();
        int yes = session.createQuery("DELETE User WHERE id = :id").setParameter("id", id).executeUpdate();
        transaction.commit();
        session.close();
        return yes > 0;
    }
}
