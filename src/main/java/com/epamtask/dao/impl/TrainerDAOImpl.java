package com.epamtask.dao.impl;

import com.epamtask.aspect.Loggable;
import com.epamtask.dao.TrainerDAO;
import com.epamtask.model.Trainee;
import com.epamtask.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class TrainerDAOImpl implements TrainerDAO {
    private final Map<Long, Trainer> trainerStorage;

    @Autowired
    public TrainerDAOImpl(Map<Long, Trainer> trainerStorage) {
        this.trainerStorage = trainerStorage;
    }

    @Loggable
    @Override
    public void create(Long id, Trainer trainer) {
        trainerStorage.put(id, trainer);
    }

    @Loggable
    @Override
    public Optional<Trainer> findById(Long id) {
        return Optional.ofNullable(trainerStorage.get(id));
    }

    @Loggable
    @Override
    public Optional<Trainer> findByUsername(String username) {
        return trainerStorage.values().stream()
                .filter(t -> t.getUserName().equals(username))
                .findFirst();
    }

    @Loggable
    @Override
    public void update(Trainer trainer) {
        trainerStorage.put(trainer.getUserId(), trainer);
    }

    @Loggable
    @Override
    public Map<Long, Trainer> getAll() {
        return trainerStorage;
    }

    @Loggable
    @Override
    public void deleteById(Long id) {
        trainerStorage.remove(id);
    }
}