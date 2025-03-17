package com.epamtask.facade.impl;

import com.epamtask.aspect.Loggable;
import com.epamtask.facade.TrainerFacade;
import com.epamtask.model.Trainer;
import com.epamtask.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerFacadeImpl implements TrainerFacade {
    private final TrainerService trainerService;

    @Autowired
    public TrainerFacadeImpl(TrainerService trainerService) {
        this.trainerService = trainerService;
    }
    @Loggable
    @Override
    public void createTrainer(Long userId, String firstName, String lastName, String specialization) {
        trainerService.createTrainer(userId, firstName, lastName, specialization);
    }
    @Loggable
    @Override
    public void updateTrainer(Trainer trainer) {
        trainerService.updateTrainer(trainer);
    }
    @Loggable
    @Override
    public Optional<Trainer> getTrainerById(Long id) {
        return trainerService.getTrainerById(id);
    }
    @Loggable
    @Override
    public Optional<Trainer> getTrainerByUsername(String username) {
        return trainerService.getTrainerByUsername(username);
    }
    @Loggable
    @Override
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }
}