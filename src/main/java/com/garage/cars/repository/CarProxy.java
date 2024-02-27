package com.garage.cars.repository;

import com.garage.cars.CustomProperties;
import com.garage.cars.model.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class CarProxy {
    private CustomProperties props;

    public CarProxy(CustomProperties props) {
        this.props = props;
    }

    public Iterable<Car> getCars() {
        String baseApiUrl = props.getApiUrl();
        String getCarsUrl = baseApiUrl + "/cars";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Car>> response = restTemplate.exchange(
                getCarsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Car>>(){}
        );
        log.debug("Get Cars call"+ response.getStatusCode().toString());
        System.out.println(response.getBody().toString());
        return response.getBody();
    }

    public Car getCar(long id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/car/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Car> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Car.class
        );

        log.debug("Get Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }


    public Car saveCar(Car car){
        String baseApiUrl = props.getApiUrl();
        String saveCarUrl = baseApiUrl + "/car";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Car> request = new HttpEntity<Car>(car);
        ResponseEntity<Car> response = restTemplate.exchange(
                saveCarUrl,
                HttpMethod.POST,
                request,
                Car.class);
        log.debug("Save Car call " + response.getStatusCode().toString());
        return response.getBody();

    }

    public Car updateCar(Car car) {
        String baseApiUrl = props.getApiUrl();
        String createCarUrl = baseApiUrl + "/cars/"+car.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Car> request = new HttpEntity<Car>(car);
        ResponseEntity<Car> response = restTemplate.exchange(
                createCarUrl,
                HttpMethod.PUT,
                request,
                Car.class);
        log.debug("Update Car call " + response.getStatusCode().toString());
        return response.getBody();

    }

    public void deleteCar(long id) {
        String baseApiUrl = props.getApiUrl();
        String deleteCarUrl = baseApiUrl + "/car/"+id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteCarUrl,
                HttpMethod.DELETE,
                null,
                Void.class);
        log.debug("Delete Car call " + response.getStatusCode().toString());

    }

}
