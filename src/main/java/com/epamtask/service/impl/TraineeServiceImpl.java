package com.epamtask.service.impl;

import com.epamtask.aspect.Loggable;
import com.epamtask.dao.TraineeDAO;
import com.epamtask.dao.TrainerDAO;
import com.epamtask.model.Trainee;
import com.epamtask.service.TraineeService;
import com.epamtask.utils.PasswordGenerator;
import com.epamtask.utils.UserNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TraineeServiceImpl implements TraineeService {
    private final TraineeDAO traineeDAO;
    private final TrainerDAO trainerDAO;
    private final UserNameGenerator userNameGenerator;

    @Autowired
    public TraineeServiceImpl(TraineeDAO traineeDAO, TrainerDAO trainerDAO, UserNameGenerator userNameGenerator) {
        this.traineeDAO = traineeDAO;
        this.trainerDAO = trainerDAO;
        this.userNameGenerator = userNameGenerator;
    }

    @Loggable
    @Override
    public void createTrainee(Long userId, String firstName, String lastName, String address, Date birthdayDate) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number");
        }
        if (firstName == null || firstName.isBlank() || lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("First name and last name are required");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address is required");
        }
        if (birthdayDate == null) {
            throw new IllegalArgumentException("Birthday date is required");
        }
        if (traineeDAO.findById(userId).isPresent()) {
            throw new IllegalArgumentException("Trainee with ID " + userId + " already exists");
        }

        String uniqueUsername = userNameGenerator.generateUserName(
                firstName, lastName, traineeDAO.getAll(), trainerDAO.getAll()
        );
        String password = PasswordGenerator.generatePassword();

        Trainee trainee = new Trainee(userId, firstName, lastName, address, birthdayDate);
        trainee.setUserName(uniqueUsername);
        trainee.setPassword(password);
        trainee.setActive(true);

        traineeDAO.create(userId, trainee);
    }

    @Loggable
    public void updateTrainee(Trainee trainee) {
        if (trainee == null || trainee.getUserId() == null) {
            throw new IllegalArgumentException("Trainee and User ID cannot be null");
        }
        if (!traineeDAO.findById(trainee.getUserId()).isPresent()) {
            throw new IllegalArgumentException("Trainee with ID " + trainee.getUserId() + " does not exist");
        }
        traineeDAO.update(trainee);
    }

    @Loggable
    public void deleteTrainee(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (!traineeDAO.findById(id).isPresent()) {
            throw new IllegalArgumentException("Trainee with ID " + id + " does not exist");
        }
        traineeDAO.deleteById(id);
    }

    @Loggable
    public Optional<Trainee> getTraineeById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return traineeDAO.findById(id);
    }

    @Loggable
    public Optional<Trainee> getTraineeByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        return traineeDAO.findByUsername(username);
    }

    @Override
    public List<Trainee> getAllTrainees() {
        return new ArrayList<>(traineeDAO.getAll().values());
    }
}