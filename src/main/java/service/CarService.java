package service;

import DAO.CarDao;
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

    public List<Car> getAllCars() {
        List<Car> list = null;
        CarDao dao = new CarDao( sessionFactory.openSession());
        list = dao.getAllCar();
    return list;
    }

    public int getNumberBrand(String brand) {
        List<Car> list = null;
        int number = 0;
        CarDao dao = new CarDao( sessionFactory.openSession());
        list = dao.getCarByBrand(brand);
        if (list != null) {
            number = list.size();
        }
        return number;
    }

    public void addCar(Car car) {
        CarDao dao = new CarDao(sessionFactory.openSession());
        dao.addCar(car);
    }

    public Car getCarByParametr(String brand, String model, String number) {
        CarDao dao = new CarDao(sessionFactory.openSession());
        return dao.getCarByParametr(brand, model, number);
    }

    public void deleteCar(Car car) {
        new CarDao(sessionFactory.openSession()).deleteCar(car);
    }

    public void deleteAllCar() {
        new CarDao(sessionFactory.openSession()).deleteAllCars();
    }
}
