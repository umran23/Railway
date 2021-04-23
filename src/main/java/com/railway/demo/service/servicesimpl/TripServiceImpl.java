package com.railway.demo.service.servicesimpl;


import com.railway.demo.model.Station;
import com.railway.demo.model.Trip;
import com.railway.demo.repository.TripRepository;
import com.railway.demo.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Page<Trip> getAllTripsPaged(int pageNum){
        return tripRepository.findAll(PageRequest.of(pageNum,5, Sort.by("departureStation")));
    }
    @Override
    public List<Trip> getAllTrips(){return tripRepository.findAll();}

    @Override
    public Trip getTripById(Long tripId){
        return tripRepository.findById(tripId).orElse(null);
    }

    @Override
    public Trip saveTrip(Trip trip){return tripRepository.save(trip);}

    @Override
    public void deleteTripById(long tripId){ tripRepository.deleteById(tripId);}

    @Override
    public List<Trip> getAllTripsByStationAndDepartureTime(Station depStation, Station destStation, LocalDate depDate){
        return tripRepository.findAllByDepartureStationEqualsAndDestinationStationEqualsAndDepartureDateEquals(depStation,destStation,depDate);
    }

}
