package com.railway.demo.service.servicesimpl;

import com.railway.demo.model.Station;
import com.railway.demo.repository.StationRepository;
import com.railway.demo.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {
    private StationRepository stationRepository;

    @Autowired
    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Page<Station> getAllStationsPaged(int pageNum){
        return stationRepository.findAll(PageRequest.of(pageNum,5, Sort.by("stationName")));
    }

    @Override
    public List<Station> getAllStations(){return stationRepository.findAll();}

    @Override
    public Station getStationById(Integer stationId){
        return stationRepository.findById(stationId).orElse(null);
    }

    @Override
    public Station saveStation(Station station){
        return stationRepository.save(station);
    }

    @Override
    public void deleteStation(Integer stationId){
        stationRepository.deleteById(stationId);
    }

}
