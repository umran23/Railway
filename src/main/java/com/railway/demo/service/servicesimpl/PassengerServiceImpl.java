package com.railway.demo.service.servicesimpl;

import com.railway.demo.model.Passenger;
import com.railway.demo.repository.PassengerRepository;
import com.railway.demo.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PassengerServiceImpl implements PassengerService {

    private PassengerRepository passengerRepository;
    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }


    @Override
    public Page<Passenger> getAllPassengersPaged(int pageNum){
        return passengerRepository.findAll(PageRequest.of(pageNum,5, Sort.by("lastName")));
    }
    @Override
    public List<Passenger> getAllPassengers(){return passengerRepository.findAll();}

    @Override
    public Passenger getPassengerById(Long passengerId){
        return passengerRepository.findById(passengerId).orElse(null);
    }

    @Override
    public Passenger savePassenger(Passenger passenger){
        return passengerRepository.save(passenger);
    }
    @Override
    public void deletePassengerById(Long passengerId){
        passengerRepository.deleteById(passengerId);
    }
}
