package com.railway.demo.repository;

import com.railway.demo.model.Station;
import com.railway.demo.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.jaas.JaasPasswordCallbackHandler;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {
    List<Trip> findAllByDepartureStationEqualsAndDestinationStationEqualsAndDepartureDateEquals(Station depStation, Station destStation, LocalDate depDate);
}
