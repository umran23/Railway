package com.railway.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Train {
    @Id
    @GeneratedValue
    private long trainId;
    private String manufacturer;

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    private String model;
    private Integer numberOfSeats;

    @OneToMany(mappedBy = "train")
    private List<Trip> trips =new ArrayList<>();

    /*Empty method for the class */
    public Train(){}

    public Train(String manufacturer, String model, Integer numberOfSeats){
        /*method for the class objects*/
    this.manufacturer=manufacturer;
    this.model=model;
    this.numberOfSeats=numberOfSeats;
    }


    public long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Trip> getTrips() {
        return trips;
    }


}

