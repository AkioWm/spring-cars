package com.garage.cars.service;

import com.garage.cars.model.Car;
import com.garage.cars.repository.CarProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class CarService {

    private CarProxy carProxy;
    public CarService(CarProxy carProxy) {
        this.carProxy = carProxy;
    }

    public Car getCar(final long id){
        return carProxy.getCar(id);
    }
    public Iterable<Car> getCars(){
        return carProxy.getCars();
    }

    public void deleteCar(final long id){
      carProxy.deleteCar(id);
    }


    public Car saveCar(Car car) {
        Car savedCar;


        car.setMakeName(car.getMakeName().toUpperCase());

        if(car.getId() == null) {

            savedCar = carProxy.saveCar(car);
        } else {
            savedCar = car;
        }

        return savedCar;
    }

}
