package hiber.service;


import hiber.dao.CarDaoImp;
import hiber.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class CarServiceImp {
    @Autowired
    private CarDaoImp carDao;

    @Transactional

    public void add(Car car) {
        carDao.add(car);
    }

    @Transactional(readOnly = true)


    public List<Car> listCars() {
        return carDao.listCars();
    }
}
