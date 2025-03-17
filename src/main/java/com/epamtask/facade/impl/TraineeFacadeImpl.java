package com.epamtask.facade.impl;

import com.epamtask.aspect.Loggable;
import com.epamtask.facade.TraineeFacade;
import com.epamtask.model.Trainee;
import com.epamtask.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TraineeFacadeImpl implements TraineeFacade {
    private final TraineeService traineeService;

    @Autowired
    public TraineeFacadeImpl(TraineeService traineeService) {
        this.traineeService = traineeService;
    }
    @Loggable
    @Override
    public void createTrainee(Long userId, String firstName, String lastName, String address, Date birthdayDate) {
        traineeService.createTrainee(userId, firstName, lastName, address, birthdayDate);
    }
    @Loggable
    @Override
    public void updateTrainee(Trainee trainee) {
        traineeService.updateTrainee(trainee);
    }
    @Loggable
    @Override
    public void deleteTrainee(Long id) {
        traineeService.deleteTrainee(id);
    }
    @Loggable
    @Override
    public Optional<Trainee> getTraineeById(Long id) {
        return traineeService.getTraineeById(id);
    }
    @Loggable
    @Override
    public Optional<Trainee> getTraineeByUsername(String username) {
        return traineeService.getTraineeByUsername(username);
    }
    @Loggable
    @Override
    public List<Trainee> getAllTrainees() {
        return traineeService.getAllTrainees();
    }
}