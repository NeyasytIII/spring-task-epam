package com.epamtask.dao.impl;

import com.epamtask.aspect.Loggable;
import com.epamtask.dao.TrainingDAO;
import com.epamtask.model.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TrainingDAOImpl implements TrainingDAO {

    private final Map<Long, Training> trainingStorage;

    @Autowired
    public TrainingDAOImpl(Map<Long, Training> trainingStorage) {
        this.trainingStorage = trainingStorage;
    }

    @Loggable
    @Override
    public void create(Long id, Training training) {
        trainingStorage.put(id, training);
    }

    @Loggable
    @Override
    public Optional<Training> findById(Long id) {
        return Optional.ofNullable(trainingStorage.get(id));
    }

    @Loggable
    @Override
    public Map<Long, Training> findByTrainerId(Long trainerId) {
        return trainingStorage.entrySet().stream()
                .filter(entry -> entry.getValue().getTrainerId().equals(trainerId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Loggable
    @Override
    public Map<Long, Training> findByTraineeId(Long traineeId) {
        return trainingStorage.entrySet().stream()
                .filter(entry -> entry.getValue().getTraineeId().equals(traineeId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    @Loggable
    @Override
    public Map<Long, Training> getAll() {
        return trainingStorage;
    }
}