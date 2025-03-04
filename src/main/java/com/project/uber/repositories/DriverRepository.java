package com.project.uber.repositories;

import com.project.uber.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query(value = "SELECT d.*, " +
            "ST_Distance(d.current_location, ?1) AS distance " +
            "FROM driver d " +
            "WHERE d.available = true " +
            "AND ST_Distance(d.current_location, ?1) < 10000 " +
            "ORDER BY distance " +
            "LIMIT 10",
            nativeQuery = true)
    List<Driver> findTenNearestDrivers(Point pickUpLocation);

    @Query(value = "SELECT d.*, " +
        "ST_Distance(d.current_location,?1) AS distance"+
        "FROM driver d "+
        "WHERE d.available = true "+
        "AND ST_Distance(d.current_location,?1) < 10000 "+
        "ORDER BY d.rating DESC "+
        "LIMIT 10",
        nativeQuery = true)
    List<Driver> findTenNearbyTopRatedDrivers(Point pickUpLocation);


}
