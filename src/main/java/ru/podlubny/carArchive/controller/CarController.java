package ru.podlubny.carArchive.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.podlubny.carArchive.model.AppStatus;
import ru.podlubny.carArchive.model.Car;
import ru.podlubny.carArchive.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/JSON", produces = "application/json")
@CrossOrigin(origins = "*")
public class CarController {

    final
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> findAllCar() {
        List<Car> cars = carService.findAllCar();
        return ResponseEntity.ok().body(cars);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<?> getCarById(@PathVariable String id) {
        return Optional
                .ofNullable(carService.getCarById(id))
                .map(x -> new ResponseEntity<>(x.get(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new AppStatus(HttpStatus.NOT_FOUND.value(),
                        "Car with id " + id + " not found"),
                        HttpStatus.NOT_FOUND));
    }

    @PostMapping("/cars")
    public ResponseEntity createCar(@RequestBody Car car) {
        carService.createCar(car);
        return new ResponseEntity<>(new AppStatus(HttpStatus.CREATED.value(),
                "Car with id" + car.getId() + " added successfully"), HttpStatus.CREATED);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity updateCar(@PathVariable String id, @RequestBody Car car) {
        return Optional
                .ofNullable(carService.updateCar(id, car))
                .map(x -> new ResponseEntity<>(new AppStatus(HttpStatus.ACCEPTED.value(),
                        "Car with id " + id + " has been successfully changed"), HttpStatus.ACCEPTED))
                .orElse(new ResponseEntity<>(new AppStatus(HttpStatus.NOT_FOUND.value(),
                        "Car with id " + id + " not found"),
                        HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity deleteCar(@PathVariable String id) {
        return Optional
                .ofNullable(carService.deleteCar(id))
                .map(x -> new ResponseEntity<>(new AppStatus(HttpStatus.ACCEPTED.value(),
                        "Car with id " + id + " has been successfully deleted"), HttpStatus.ACCEPTED))
                .orElse(new ResponseEntity<>(new AppStatus(HttpStatus.NOT_FOUND.value(),
                        "Car with id " + id + " not found"),
                        HttpStatus.NOT_FOUND));
    }
}
