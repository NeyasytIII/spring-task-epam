package com.epamtask.service;

import com.epamtask.dao.TraineeDAO;
import com.epamtask.dao.TrainerDAO;
import com.epamtask.dao.TrainingDAO;
import com.epamtask.model.Training;
import com.epamtask.model.TrainingType;

import com.epamtask.service.impl.TrainingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingServiceTest {

    @Mock
    private TrainingDAO trainingDAO;

    @Mock
    private TrainerDAO trainerDAO;

    @Mock
    private TraineeDAO traineeDAO;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTraining_Success() {
        Long trainingId = 1L;
        Long trainerId = 10L;
        Long traineeId = 20L;
        String trainingName = "Strength Training";
        TrainingType type = TrainingType.STRENGTH;
        Date trainingDate = new Date();
        String trainingDuration = "60";

        when(trainingDAO.findById(trainingId)).thenReturn(Optional.empty());
        when(trainerDAO.findById(trainerId)).thenReturn(Optional.of(mock(com.epamtask.model.Trainer.class)));
        when(traineeDAO.findById(traineeId)).thenReturn(Optional.of(mock(com.epamtask.model.Trainee.class)));

        trainingService.createTraining(trainingId, trainerId, traineeId, trainingName, type, trainingDate, trainingDuration);

        verify(trainingDAO).create(eq(trainingId), any(Training.class));
    }

    @Test
    void testCreateTraining_TrainingIdExists_ShouldThrowException() {
        when(trainingDAO.findById(1L)).thenReturn(Optional.of(new Training()));

        assertThrows(IllegalArgumentException.class, () ->
                trainingService.createTraining(1L, 10L, 20L, "Strength Training", TrainingType.STRENGTH, new Date(), "60"));
    }

    @Test
    void testCreateTraining_InvalidParameters_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                trainingService.createTraining(null, 10L, 20L, "Strength Training", TrainingType.STRENGTH, new Date(), "60"));

        assertThrows(IllegalArgumentException.class, () ->
                trainingService.createTraining(1L, null, 20L, "Strength Training", TrainingType.STRENGTH, new Date(), "60"));

        assertThrows(IllegalArgumentException.class, () ->
                trainingService.createTraining(1L, 10L, null, "Strength Training", TrainingType.STRENGTH, new Date(), "60"));

        assertThrows(IllegalArgumentException.class, () ->
                trainingService.createTraining(1L, 10L, 20L, null, TrainingType.STRENGTH, new Date(), "60"));

        assertThrows(IllegalArgumentException.class, () ->
                trainingService.createTraining(1L, 10L, 20L, "Strength Training", null, new Date(), "60"));

        assertThrows(IllegalArgumentException.class, () ->
                trainingService.createTraining(1L, 10L, 20L, "Strength Training", TrainingType.STRENGTH, null, "60"));

        assertThrows(IllegalArgumentException.class, () ->
                trainingService.createTraining(1L, 10L, 20L, "Strength Training", TrainingType.STRENGTH, new Date(), null));
    }

    @Test
    void testGetTrainingById_Success() {
        Training training = new Training();
        training.setTrainingId(2L);

        when(trainingDAO.findById(2L)).thenReturn(Optional.of(training));

        Optional<Training> result = trainingService.getTrainingById(2L);

        assertTrue(result.isPresent());
        assertEquals(2L, result.get().getTrainingId());
        verify(trainingDAO).findById(2L);
    }

    @Test
    void testGetTrainingById_NullId_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> trainingService.getTrainingById(null));
        verifyNoInteractions(trainingDAO);
    }

    @Test
    void testGetTrainingsByTrainerId_Success() {
        Map<Long, Training> mockTrainings = new HashMap<>();
        Training training = new Training();
        training.setTrainingId(3L);
        mockTrainings.put(3L, training);

        when(trainingDAO.findByTrainerId(10L)).thenReturn(mockTrainings);

        Map<Long, Training> result = trainingService.getTrainingsByTrainerId(10L);

        assertEquals(1, result.size());
        assertTrue(result.containsKey(3L));
        verify(trainingDAO).findByTrainerId(10L);
    }

    @Test
    void testGetTrainingsByTrainerId_NullId_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> trainingService.getTrainingsByTrainerId(null));
        verifyNoInteractions(trainingDAO);
    }

    @Test
    void testGetTrainingsByTrainerId_NotFound() {
        when(trainingDAO.findByTrainerId(11L)).thenReturn(new HashMap<>());

        Map<Long, Training> result = trainingService.getTrainingsByTrainerId(11L);

        assertTrue(result.isEmpty());
        verify(trainingDAO).findByTrainerId(11L);
    }

    @Test
    void testGetTrainingsByTraineeId_Success() {
        Map<Long, Training> mockTrainings = new HashMap<>();
        Training training = new Training();
        training.setTrainingId(4L);
        mockTrainings.put(4L, training);

        when(trainingDAO.findByTraineeId(20L)).thenReturn(mockTrainings);

        Map<Long, Training> result = trainingService.getTrainingsByTraineeId(20L);

        assertEquals(1, result.size());
        assertTrue(result.containsKey(4L));
        verify(trainingDAO).findByTraineeId(20L);
    }

    @Test
    void testGetTrainingsByTraineeId_NullId_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> trainingService.getTrainingsByTraineeId(null));
        verifyNoInteractions(trainingDAO);
    }

    @Test
    void testGetAllTrainings() {
        List<Training> trainings = List.of(new Training(10L, 1L, 2L, "Yoga Relax", TrainingType.YOGA, new Date(), "45"));
        when(trainingDAO.getAll()).thenReturn(Map.of(10L, trainings.get(0)));

        List<Training> result = trainingService.getAllTrainings();

        assertEquals(1, result.size());
        verify(trainingDAO).getAll();
    }
}