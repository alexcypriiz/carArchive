package ru.podlubny.carArchive.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.podlubny.carArchive.model.Car;
import ru.podlubny.carArchive.repository.InterfaceCarRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CarServiceTest {

    @InjectMocks
    private CarService carService;
    @Mock
    private InterfaceCarRepository interfaceCarRepository;

    List<Car> cars = List.of(new Car("12345", "MITSUBISHI", "LANCER", "XXX3323X33243433X", 1200000.0, 105000.0),
            new Car("newId-12345", "HAVAL", "JOLION", "XXX333333333333333X", 2400000.0, 0.0));

    @Test
    void findAllCar() throws Exception {
        when(interfaceCarRepository.findAll()).thenReturn(cars);
        assertEquals(cars.size(), carService.findAllCar().size());
    }

    @Test
    void getCarById() throws Exception {
        when(interfaceCarRepository.findById("12345")).thenReturn(Optional.of(cars.get(0)));
        assertEquals(cars.get(0), carService.getCarById("12345").get());
    }

    @Test
    void createCar() throws Exception {
        when(interfaceCarRepository.save(cars.get(0))).thenReturn(cars.get(0));
        assertEquals(cars.get(0), carService.createCar(cars.get(0)));
    }

    @Test
    void updateCar() throws Exception {
        when(interfaceCarRepository.findById("12345")).thenReturn(Optional.of(cars.get(0)));
        when(interfaceCarRepository.save(cars.get(0))).thenReturn(cars.get(0));
        assertEquals(cars.get(0), carService.updateCar("12345", cars.get(0)).get());
    }

    @Test
    void deleteCar() throws Exception {
        when(interfaceCarRepository.findById("12345")).thenReturn(Optional.of(cars.get(0)));
        when(interfaceCarRepository.save(cars.get(0))).thenReturn(cars.get(0));
        assertEquals(cars.get(0), carService.deleteCar("12345").get());
    }
}
