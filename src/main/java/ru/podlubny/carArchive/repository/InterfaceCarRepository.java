package ru.podlubny.carArchive.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.podlubny.carArchive.model.Car;

public interface InterfaceCarRepository extends MongoRepository<Car,String> {

}
