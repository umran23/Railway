package com.railway.demo.model;

import org.apache.velocity.util.ArrayListWrapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trip {
    @Id
    @GeneratedValue
    private long tripId;
    private String tripNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;
    private String departureTime;
    private String arrivalTime;
    private double price;

    @ManyToOne
    private Station departureStation;
    @ManyToOne
    private Station destinationStation;

    @ManyToOne
    Train train;
    @OneToMany
    List<Passenger> passengers =new ArrayList<>();

    public Trip(){}

    public Trip(String tripNumber, LocalDate departureDate, LocalDate arrivalDate, String departureTime, String arrivalTime, double price, Station departureStation, Station destinationStation) {
        this.tripNumber = tripNumber;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.departureStation = departureStation;
        this.destinationStation = destinationStation;
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public String getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(String tripNumber) {
        this.tripNumber = tripNumber;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Station getDepartureStaion() {
        return departureStation;
    }

    public void setDepartureStaion(Station departureStaion) {
        this.departureStation = departureStaion;
    }

    public Station getDestinationStaion() {
        return destinationStation;
    }

    public void setDestinationStaion(Station destinationStaion) {
        this.destinationStation = destinationStaion;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
