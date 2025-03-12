package com.epamtask.service.impl;

import com.epamtask.aspect.Loggable;
import com.epamtask.dao.TraineeDAO;
import com.epamtask.dao.TrainerDAO;
import com.epamtask.dao.TrainingDAO;
import com.epamtask.model.Training;
import com.epamtask.model.TrainingType;
import com.epamtask.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainingServiceImpl implements TrainingService {
    private final TrainingDAO trainingDAO;
    private final TraineeDAO traineeDAO;
    private final TrainerDAO trainerDAO;

    @Autowired
    public TrainingServiceImpl(TrainingDAO trainingDAO, TraineeDAO traineeDAO, TrainerDAO trainerDAO) {
        this.trainingDAO = trainingDAO;
        this.traineeDAO = traineeDAO;
        this.trainerDAO = trainerDAO;
    }

    @Loggable
    @Override
    public void createTraining(Long trainingId, Long trainerId, Long traineeId, String trainingName,
                               TrainingType type, Date trainingDate, String trainingDuration) {
        if (trainingId == null || trainingId <= 0) {
            throw new IllegalArgumentException("Training ID must be positive");
        }
        if (trainerId == null || trainerId <= 0) {
            throw new IllegalArgumentException("Trainer ID must be positive");
        }
        if (traineeId == null || traineeId <= 0) {
            throw new IllegalArgumentException("Trainee ID must be a positive number");
        }
        if (trainingName == null || trainingName.isBlank()) {
            throw new IllegalArgumentException("Training name is required");
        }
        if (type == null) {
            throw new IllegalArgumentException("Training type is required");
        }
        if (trainingDate == null) {
            throw new IllegalArgumentException("Training date is required");
        }
        if (trainingDuration == null || trainingDuration.isBlank()) {
            throw new IllegalArgumentException("Training duration is required");
        }
        if (trainingDAO.findById(trainingId).isPresent()) {
            throw new IllegalArgumentException("Training ID " + trainingId + " already exists");
        }
        if (!trainerDAO.findById(trainerId).isPresent()) {
            throw new IllegalArgumentException("Trainer with ID " + trainerId + " does not exist");
        }
        if (!traineeDAO.findById(traineeId).isPresent()) {
            throw new IllegalArgumentException("Trainee with ID " + traineeId + " does not exist");
        }

        Training training = new Training(trainingId, traineeId, trainerId, trainingName, type, trainingDate, trainingDuration);
        trainingDAO.create(trainingId, training);
    }

    @Loggable
    public Optional<Training> getTrainingById(Long trainingId) {
        if (trainingId == null) {
            throw new IllegalArgumentException("Training ID cannot be null");
        }
        return trainingDAO.findById(trainingId);
    }

    @Loggable
    public Map<Long, Training> getTrainingsByTrainerId(Long trainerId) {
        if (trainerId == null) {
            throw new IllegalArgumentException("Trainer ID cannot be null");
        }
        return trainingDAO.findByTrainerId(trainerId);
    }

    @Loggable
    public Map<Long, Training> getTrainingsByTraineeId(Long traineeId) {
        if (traineeId == null) {
            throw new IllegalArgumentException("Trainee ID cannot be null");
        }
        return trainingDAO.findByTraineeId(traineeId);
    }

    @Override
    public List<Training> getAllTrainings() {
        return new ArrayList<>(trainingDAO.getAll().values());
    }
}