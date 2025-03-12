package com.epamtask.facade.impl;

import com.epamtask.model.Trainee;
import com.epamtask.model.Trainer;
import com.epamtask.model.Training;
import com.epamtask.model.TrainingType;
import com.epamtask.service.TraineeService;
import com.epamtask.service.TrainerService;
import com.epamtask.service.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TotalFacadeImplTest {

    @Mock
    private TraineeService traineeService;

    @Mock
    private TrainerService trainerService;

    @Mock
    private TrainingService trainingService;

    @InjectMocks
    private TotalFacadeImpl totalFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTrainee() {
        totalFacade.createTrainee(1L, "John", "Doe", "123 Elm Street", new Date());
        verify(traineeService).createTrainee(eq(1L), eq("John"), eq("Doe"), eq("123 Elm Street"), any());
    }

    @Test
    void testCreateTrainer() {
        totalFacade.createTrainer(2L, "Emily", "Davis", "Yoga");
        verify(trainerService).createTrainer(eq(2L), eq("Emily"), eq("Davis"), eq("Yoga"));
    }

    @Test
    void testCreateTraining() {
        totalFacade.createTraining(10L, 1L, 2L, "Yoga Relax", TrainingType.YOGA, new Date(), "45");
        verify(trainingService).createTraining(eq(10L), eq(1L), eq(2L), eq("Yoga Relax"), eq(TrainingType.YOGA), any(), eq("45"));
    }

    @Test
    void testUpdateTrainee() {
        Trainee trainee = new Trainee(1L, "John", "Doe", "123 Elm Street", new Date());
        totalFacade.updateTrainee(trainee);
        verify(traineeService).updateTrainee(trainee);
    }

    @Test
    void testUpdateTrainer() {
        Trainer trainer = new Trainer(2L, "Emily", "Davis", "Yoga");
        totalFacade.updateTrainer(trainer);
        verify(trainerService).updateTrainer(trainer);
    }

    @Test
    void testDeleteTrainee() {
        totalFacade.deleteTrainee(1L);
        verify(traineeService).deleteTrainee(1L);
    }

    @Test
    void testGetTraineeById() {
        Trainee trainee = new Trainee(1L, "John", "Doe", "123 Elm Street", new Date());
        when(traineeService.getTraineeById(1L)).thenReturn(Optional.of(trainee));

        Optional<Trainee> result = totalFacade.getTraineeById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getUserId());
        verify(traineeService).getTraineeById(1L);
    }

    @Test
    void testGetTrainerById() {
        Trainer trainer = new Trainer(2L, "Emily", "Davis", "Yoga");
        when(trainerService.getTrainerById(2L)).thenReturn(Optional.of(trainer));

        Optional<Trainer> result = totalFacade.getTrainerById(2L);

        assertTrue(result.isPresent());
        assertEquals(2L, result.get().getUserId());
        verify(trainerService).getTrainerById(2L);
    }

    @Test
    void testGetTrainingById() {
        Training training = new Training(10L, 1L, 2L, "Yoga Relax", TrainingType.YOGA, new Date(), "45");
        when(trainingService.getTrainingById(10L)).thenReturn(Optional.of(training));

        Optional<Training> result = totalFacade.getTrainingById(10L);

        assertTrue(result.isPresent());
        assertEquals(10L, result.get().getTrainingId());
        verify(trainingService).getTrainingById(10L);
    }

    @Test
    void testGetAllTrainees() {
        List<Trainee> trainees = List.of(new Trainee(1L, "John", "Doe", "123 Elm Street", new Date()));
        when(traineeService.getAllTrainees()).thenReturn(trainees);

        List<Trainee> result = totalFacade.getAllTrainees();

        assertEquals(1, result.size());
        verify(traineeService).getAllTrainees();
    }

    @Test
    void testGetAllTrainers() {
        List<Trainer> trainers = List.of(new Trainer(2L, "Emily", "Davis", "Yoga"));
        when(trainerService.getAllTrainers()).thenReturn(trainers);

        List<Trainer> result = totalFacade.getAllTrainers();

        assertEquals(1, result.size());
        verify(trainerService).getAllTrainers();
    }

    @Test
    void testGetAllTrainings() {
        List<Training> trainings = List.of(new Training(10L, 1L, 2L, "Yoga Relax", TrainingType.YOGA, new Date(), "45"));
        when(trainingService.getAllTrainings()).thenReturn(trainings);

        List<Training> result = totalFacade.getAllTrainings();

        assertEquals(1, result.size());
        verify(trainingService).getAllTrainings();
    }
}