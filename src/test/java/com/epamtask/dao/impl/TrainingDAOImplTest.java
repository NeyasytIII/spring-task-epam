package com.epamtask.dao.impl;


import com.epamtask.model.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TrainingDAOImplTest {

    private Map<Long, Training> trainingStorage;
    private TrainingDAOImpl trainingDAO;

    @BeforeEach
    void setUp() {
        trainingStorage = new HashMap<>();
        trainingDAO = new TrainingDAOImpl(trainingStorage);
    }

    @Test
    void testCreate() {
        Training training = new Training();
        training.setTrainingId(1L);
        training.setTrainerId(100L);
        training.setTraineeId(200L);

        trainingDAO.create(1L, training);

        assertTrue(trainingStorage.containsKey(1L));
        assertEquals(100L, trainingStorage.get(1L).getTrainerId());
        assertEquals(200L, trainingStorage.get(1L).getTraineeId());
    }

    @Test
    void testFindById() {
        Training training = new Training();
        training.setTrainingId(2L);
        training.setTrainerId(101L);
        training.setTraineeId(201L);
        trainingStorage.put(2L, training);

        Optional<Training> result = trainingDAO.findById(2L);

        assertTrue(result.isPresent());
        assertEquals(101L, result.get().getTrainerId());
        assertEquals(201L, result.get().getTraineeId());
    }

    @Test
    void testFindByTrainerId() {
        Training t1 = new Training();
        t1.setTrainingId(3L);
        t1.setTrainerId(300L);
        t1.setTraineeId(400L);

        Training t2 = new Training();
        t2.setTrainingId(4L);
        t2.setTrainerId(301L);
        t2.setTraineeId(400L);

        trainingStorage.put(3L, t1);
        trainingStorage.put(4L, t2);

        Map<Long, Training> result = trainingDAO.findByTrainerId(300L);

        assertEquals(1, result.size());
        assertTrue(result.containsKey(3L));
    }

    @Test
    void testFindByTraineeId() {
        Training t1 = new Training();
        t1.setTrainingId(5L);
        t1.setTrainerId(500L);
        t1.setTraineeId(600L);

        Training t2 = new Training();
        t2.setTrainingId(6L);
        t2.setTrainerId(501L);
        t2.setTraineeId(601L);

        Training t3 = new Training();
        t3.setTrainingId(7L);
        t3.setTrainerId(502L);
        t3.setTraineeId(600L);

        trainingStorage.put(5L, t1);
        trainingStorage.put(6L, t2);
        trainingStorage.put(7L, t3);

        Map<Long, Training> result = trainingDAO.findByTraineeId(600L);

        assertEquals(2, result.size());
        assertTrue(result.containsKey(5L));
        assertTrue(result.containsKey(7L));
    }
}