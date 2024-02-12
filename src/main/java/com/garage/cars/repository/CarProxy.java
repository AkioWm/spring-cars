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
    @Autowired
    private CustomProperties props;

    public Iterable<Car> getCar() {
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

    public Car createCar(Car car){
        String baseApiUrl = props.getApiUrl();
        String createCarUrl = baseApiUrl + "/cars";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Car> request = new HttpEntity<Car>(car);
        ResponseEntity<Car> response = restTemplate.exchange(
                createCarUrl,
                HttpMethod.POST,
                request,
                Car.class);
        log.debug("Create Car call " + response.getStatusCode().toString());
        return response.getBody();

    }




}
