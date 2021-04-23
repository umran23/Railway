package com.railway.demo.model;

import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Station {
    @Id
    @GeneratedValue
    private int stationId;
    private String stationCode;
    private String stationName;
    private String city;
    private String state;
    private String country;

    @OneToMany(mappedBy = "departureStation")
    List<Trip> trips = new ArrayList<Trip>();

    public Station() {
    }

    public Station(String stationCode, String stationName, String city, String state, String country) {
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
