package com.epamtask.dao;
import com.epamtask.model.Training;

import java.util.Map;
import java.util.Optional;

public interface TrainingDAO extends Dao<Long, Training> {
    Map<Long, Training> findByTrainerId(Long trainerId);
    Map<Long, Training> findByTraineeId(Long traineeId);
    Optional<Training> findById(Long trainingId);
}