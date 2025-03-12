package com.epamtask.service.impl;

import com.epamtask.aspect.Loggable;
import com.epamtask.dao.TraineeDAO;
import com.epamtask.dao.TrainerDAO;
import com.epamtask.model.Trainer;
import com.epamtask.service.TrainerService;
import com.epamtask.utils.PasswordGenerator;
import com.epamtask.utils.UserNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {
    private final TrainerDAO trainerDAO;
    private final TraineeDAO traineeDAO;
    private final UserNameGenerator userNameGenerator;

    @Autowired
    public TrainerServiceImpl(TrainerDAO trainerDAO, TraineeDAO traineeDAO, UserNameGenerator userNameGenerator) {
        this.trainerDAO = trainerDAO;
        this.traineeDAO = traineeDAO;
        this.userNameGenerator = userNameGenerator;
    }

    @Loggable
    @Override
    public void createTrainer(Long userId, String firstName, String lastName, String specialization) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number");
        }
        if (firstName == null || firstName.isBlank() || lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("First name and last name are required");
        }
        if (specialization == null || specialization.isBlank()) {
            throw new IllegalArgumentException("Specialization is required");
        }
        if (trainerDAO.findById(userId).isPresent()) {
            throw new IllegalArgumentException("Trainer with ID " + userId + " already exists");
        }

        String uniqueUsername = userNameGenerator.generateUserName(
                firstName,
                lastName,
                traineeDAO.getAll(),
                trainerDAO.getAll()
        );

        Trainer trainer = new Trainer(userId, firstName, lastName, specialization);
        trainer.setUserName(uniqueUsername);
        trainer.setPassword(PasswordGenerator.generatePassword());
        trainer.setActive(true);

        trainerDAO.create(userId, trainer);
    }

    @Loggable
    public void updateTrainer(Trainer trainer) {
        if (trainer == null || trainer.getUserId() == null) {
            throw new IllegalArgumentException("Trainer and User ID cannot be null");
        }
        if (!trainerDAO.findById(trainer.getUserId()).isPresent()) {
            throw new IllegalArgumentException("Trainer with ID " + trainer.getUserId() + " does not exist");
        }

        trainerDAO.update(trainer);
    }

    @Loggable
    public Optional<Trainer> getTrainerById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return trainerDAO.findById(id);
    }

    @Loggable
    public Optional<Trainer> getTrainerByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        return trainerDAO.findByUsername(username);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return new ArrayList<>(trainerDAO.getAll().values());
    }

    @Loggable
    public void deleteTrainer(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        if (!trainerDAO.findById(id).isPresent()) {
            throw new IllegalArgumentException("Trainer with ID " + id + " does not exist");
        }

        trainerDAO.deleteById(id);
    }
}