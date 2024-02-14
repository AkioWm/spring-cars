package com.garage.cars.controller;

import com.garage.cars.model.Car;
import com.garage.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarController {
    @Autowired
    private CarService carService;
    @GetMapping("/")
    String home(Model model){
        Iterable<Car> carList = carService.getCars();
        model.addAttribute("cars",carList);
        model.addAttribute("car",new Car());

        return "home";
    }
    @GetMapping("/deleteCar/{id}")
    public ModelAndView deleteCar(@PathVariable("id") int id){
    carService.deleteCar(id);
    return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveCar")
    public ModelAndView saveCar(@ModelAttribute Car car) {
        carService.saveCar(car);
        return new ModelAndView("redirect:/");
    }

}
