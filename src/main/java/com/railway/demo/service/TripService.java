package com.railway.demo.service;

import com.railway.demo.model.Station;
import com.railway.demo.model.Trip;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface TripService {
    Page<Trip> getAllTripsPaged(int pageNum);

    List<Trip> getAllTrips();

    Trip getTripById(Long tripId);

    Trip saveTrip(Trip trip);

    void deleteTripById(long tripId);

    List<Trip> getAllTripsByStationAndDepartureTime(Station depStation, Station destStation, LocalDate depDate);
}
