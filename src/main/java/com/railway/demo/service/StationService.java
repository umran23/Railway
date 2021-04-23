package com.railway.demo.service;

import com.railway.demo.model.Station;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StationService {
    Page<Station> getAllStationsPaged(int pageNum);

    Station getStationById(Integer stationId);

    List<Station> getAllStations();

    Station saveStation(Station station);

    void deleteStation(Integer stationId);
}
