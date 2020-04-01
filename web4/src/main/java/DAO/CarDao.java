package DAO;

import model.Car;
import model.DailyReport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public void addCar(Car car) {

        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
    }

    public List<Car> getAllCars() {
        Transaction transaction = session.beginTransaction();
        List<Car> cars = session.createQuery("FROM Car").list();
        transaction.commit();
        session.close();
        return cars;
    }

    public void deleteCar(Car car) {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE Car WHERE id = :id").setParameter("id", car.getId()).executeUpdate();
        transaction.commit();
        session.close();

    }

    public void deleteAll() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Car").executeUpdate();
        transaction.commit();
        session.close();
    }

}
