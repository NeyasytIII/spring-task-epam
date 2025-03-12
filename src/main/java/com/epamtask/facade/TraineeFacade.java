package com.epamtask.facade;

import com.epamtask.aspect.Loggable;
import com.epamtask.model.Trainee;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TraineeFacade {
    void createTrainee(Long userId, String firstName, String lastName, String address, Date birthdayDate);
    void updateTrainee(Trainee trainee);
    void deleteTrainee(Long id);
    Optional<Trainee> getTraineeById(Long id);
    Optional<Trainee> getTraineeByUsername(String username);
    List<Trainee> getAllTrainees();
}
