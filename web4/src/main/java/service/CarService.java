package service;

import DAO.CarDao;
import DAO.DailyReportDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public boolean addCar(Car car) {
        if (validate(car)) {
            new CarDao(sessionFactory.openSession()).addCar(car);
            return true;
        }
        return false;
    }

    public List<Car> getAllCars() {
        return new CarDao(sessionFactory.openSession()).getAllCars();
    }

    public void deleteCar(Car car) {
        new CarDao(sessionFactory.openSession()).deleteCar(car);
    }

    public void deleteAll() {
        new CarDao(sessionFactory.openSession()).deleteAll();
    }

    public boolean validate(Car car) {
        int i = 0;
        for (Car carIs :
                getAllCars()) {
            if (i == 9) {
                return false;
            } else if (carIs.getBrand().equals(car.getBrand())) {
                i++;
            }
        }
        return true;
    }

}
