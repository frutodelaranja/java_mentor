package service;

import dao.UserHibernateDAO;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelperHibernate;
import util.Service;

import java.util.List;

public class UserServiceHibernate implements Service {
    private static UserServiceHibernate service;
    private SessionFactory sessionFactory;

    private UserServiceHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserServiceHibernate getInstance(){
        if (service == null) {
            service = new UserServiceHibernate(DBHelperHibernate.getSessionFactory());
        }
        return service;
    }

    @Override
    public boolean addUser(User user) {
        if (!thisUserExists(user)){
            new UserHibernateDAO(sessionFactory.openSession()).addUser(user);
            return true;
        }
        return false;
    }

    @Override
    public User getUser(Long id) {
        return new UserHibernateDAO(sessionFactory.openSession()).getUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return new UserHibernateDAO(sessionFactory.openSession()).getAllUsers();
    }

    @Override
    public boolean updateUser(User user) {
        return new UserHibernateDAO(sessionFactory.openSession()).updateUser(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return new UserHibernateDAO(sessionFactory.openSession()).deleteUser(id);
    }

    @Override
    public boolean thisUserExists(User user) {
        if (new UserHibernateDAO(sessionFactory.openSession()).thisUserExists(user)){
            return true;
        }else
            return false;
    }
}
