package com.epamtask.facade.impl;

import com.epamtask.aspect.Loggable;
import com.epamtask.facade.TotalFacade;
import com.epamtask.model.Trainee;
import com.epamtask.model.Trainer;
import com.epamtask.model.Training;
import com.epamtask.model.TrainingType;
import com.epamtask.service.TraineeService;
import com.epamtask.service.TrainerService;
import com.epamtask.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TotalFacadeImpl implements TotalFacade {

    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    @Autowired
    public TotalFacadeImpl(TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
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
    public void createTraining(Long trainingId, Long traineeId, Long trainerId, String trainingName, TrainingType type, Date trainingDate, String trainingDuration) {
        trainingService.createTraining(trainingId, traineeId, trainerId, trainingName, type, trainingDate, trainingDuration);
    }
    @Loggable
    @Override
    public Optional<Training> getTrainingById(Long trainingId) {
        return trainingService.getTrainingById(trainingId);
    }
    @Loggable
    @Override
    public Map<Long, Training> getTrainingsByTrainerId(Long trainerId) {
        return trainingService.getTrainingsByTrainerId(trainerId);
    }
    @Loggable
    @Override
    public Map<Long, Training> getTrainingsByTraineeId(Long traineeId) {
        return trainingService.getTrainingsByTraineeId(traineeId);
    }
    @Loggable
    @Override
    public List<Trainee> getAllTrainees() {
        return traineeService.getAllTrainees();
    }

    @Loggable
    @Override
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @Loggable
    @Override
    public List<Training> getAllTrainings() {
        return trainingService.getAllTrainings();
    }


}
