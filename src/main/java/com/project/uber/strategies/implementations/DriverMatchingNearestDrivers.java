package com.project.uber.strategies.implementations;

import com.project.uber.entities.Driver;
import com.project.uber.entities.RideRequest;
import com.project.uber.repositories.DriverRepository;
import com.project.uber.strategies.DriverMatchingStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DriverMatchingNearestDrivers implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;


    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickUpLocation());
    }
}
