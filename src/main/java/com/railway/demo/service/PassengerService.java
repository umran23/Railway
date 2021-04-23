package com.railway.demo.service;

import com.railway.demo.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PassengerService {

    Page<Passenger> getAllPassengersPaged(int pageNum);

    List<Passenger> getAllPassengers();

    Passenger getPassengerById(Long passengerId);

    Passenger savePassenger(Passenger passenger);

    void deletePassengerById(Long passengerId);
}
