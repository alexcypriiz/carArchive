package ru.podlubny.carArchive.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.podlubny.carArchive.model.Car;
import ru.podlubny.carArchive.service.CarService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CarService carService;

    List<Car> cars = List.of(new Car("12345", "MITSUBISHI", "LANCER", "XXX3323X33243433X",1200000.0, 105000.0),
            new Car("newId-12345", "HAVAL", "JOLION", "XXX333333333333333X",2400000.0, 0.0));
    @Test
    void findAllCar() throws Exception {
        when(carService.findAllCar()).thenReturn(cars);

        mockMvc.perform(get("/JSON/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Matchers.is("12345")))
                .andExpect(jsonPath("$[0].model", Matchers.is("MITSUBISHI")))
                .andExpect(jsonPath("$[0].mark", Matchers.is("LANCER")))
                .andExpect(jsonPath("$[0].vin", Matchers.is("XXX3323X33243433X")))
                .andExpect(jsonPath("$[0].price", Matchers.is(1200000.0)))
                .andExpect(jsonPath("$[0].mileage", Matchers.is(105000.0)))
                .andExpect(jsonPath("$.length()", Matchers.is(2)));
    }

    @Test
    void getCarById() throws Exception {
        when(carService.getCarById("12345")).thenReturn(Optional.of(cars.get(0)));

        mockMvc.perform(get("/JSON/cars/{id}", "12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is("12345")))
                .andExpect(jsonPath("$.model", Matchers.is("MITSUBISHI")))
                .andExpect(jsonPath("$.mark", Matchers.is("LANCER")))
                .andExpect(jsonPath("$.vin", Matchers.is("XXX3323X33243433X")))
                .andExpect(jsonPath("$.price", Matchers.is(1200000.0)))
                .andExpect(jsonPath("$.mileage", Matchers.is(105000.0)));
    }

    @Test
    void createCar() throws Exception {
        mockMvc.perform(post("/JSON/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cars.get(0))))
                .andExpect(status().isCreated());

    }

    @Test
    void updateCar() throws Exception {
        when(carService.updateCar(cars.get(1).getId(), cars.get(1))).thenReturn(Optional.of(cars.get(1)));

        mockMvc.perform(put("/JSON/cars/{id}", cars.get(1).getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cars.get(1))))
                .andExpect(status().isAccepted());

    }

    @Test
    void deleteCarO() throws Exception {
        when(carService.deleteCar(cars.get(1).getId())).thenReturn(Optional.of(cars.get(1)));

        mockMvc.perform(delete("/JSON/cars/{id}", cars.get(1).getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

    }

}
