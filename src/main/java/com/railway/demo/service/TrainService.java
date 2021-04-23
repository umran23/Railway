package com.railway.demo.service;

import com.railway.demo.model.Train;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TrainService {
    Page<Train> getAllTrainsPaged(int pageNum);

    List<Train> getAllTrains();

    Train getTrainById(Long trainId);

    Train saveTrain(Train train);

    void deleteTrainById(Long trainId);
}
