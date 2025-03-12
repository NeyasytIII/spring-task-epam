package com.epamtask.dao.impl;

import com.epamtask.aspect.Loggable;
import com.epamtask.dao.TraineeDAO;
import com.epamtask.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class TraineeDAOImpl implements TraineeDAO {
    private final Map<Long, Trainee> traineeStorage;
    @Autowired
    public TraineeDAOImpl(Map<Long, Trainee> traineeStorage) {
        this.traineeStorage = traineeStorage;
    }
    @Loggable
    @Override
    public void create(Long id, Trainee trainee) {
        traineeStorage.put(id, trainee);
    }
    @Loggable
    @Override
    public void update(Trainee trainee) {
        traineeStorage.put(trainee.getUserId(), trainee);
    }
    @Loggable
    @Override
    public Optional<Trainee> findById(Long id) {
        return Optional.ofNullable(traineeStorage.get(id));
    }
    @Loggable
    @Override
    public void deleteById(Long id) {
        traineeStorage.remove(id);
    }
    @Loggable
    @Override
    public Optional<Trainee> findByUsername(String username) {
        return traineeStorage.values().stream()
                .filter(t -> t.getUserName().equals(username))
                .findFirst();
    }

    @Loggable
    @Override
    public Map<Long, Trainee> getAll() {
        return traineeStorage;
    }
}

