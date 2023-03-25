package com.automobilefleet.repositories;

import com.automobilefleet.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByAvailable(Boolean available);
    List<Car> findCarsByBrand_Name(String name);

    @Query(nativeQuery = true ,
            value = "SELECT b.brand_name FROM car_entity c JOIN brand_entity b ON c.brand_id = b._id WHERE c._id = ?1"
    )
    Car findBrandNameByCarId(Long carId);

}