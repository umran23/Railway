package com.railway.demo.service.servicesimpl;

import com.railway.demo.model.Train;
import com.railway.demo.repository.TrainRepository;
import com.railway.demo.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    private TrainRepository trainRepository;

    @Override
    public Page<Train> getAllTrainsPaged(int pageNum){
        return trainRepository.findAll(PageRequest.of(pageNum,5, Sort.by("model")));
    }
    @Override
    public List<Train> getAllTrains(){
        return trainRepository.findAll();
    }
    @Override
    public Train getTrainById(Long trainId){
        return trainRepository.findById(trainId).orElse(null);
    }
    @Override
    public Train saveTrain(Train train){
        if(train==null) throw new IllegalArgumentException();
        return trainRepository.save(train);
    }
    @Override
    public void deleteTrainById(Long trainId){
        trainRepository.deleteById(trainId);
    }

}
