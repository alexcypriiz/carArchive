package ru.podlubny.carArchive.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.podlubny.carArchive.model.Car;
import ru.podlubny.carArchive.repository.InterfaceCarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    final InterfaceCarRepository carRepository;

    public CarService(InterfaceCarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAllCar() {
        return carRepository.findAll();
    }

    public Optional getCarById(String id) {
        Optional<Car> carDB = carRepository.findById(id);
        return carDB.isPresent() ? carDB : null;
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    @Transactional
    public Car updateCar(String id, Car car) {
        Optional<Car> carDB = carRepository.findById(id);
        if (carDB.isPresent()) {
            car.setId(id);
            return carRepository.save(car);
        } else {
            return null;
        }
    }

    @Transactional
    public Optional deleteCar(String id) {
        Optional<Car> carDB = carRepository.findById(id);
        if (carDB.isPresent()) {
            carRepository.delete(carDB.get());
            return carDB;
        } else {
            return null;
        }
    }
}
