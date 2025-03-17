package com.epamtask.facade.impl;

import com.epamtask.model.Training;
import com.epamtask.model.TrainingType;
import com.epamtask.service.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingFacadeImplTest {

    @Mock
    private TrainingService trainingService;

    @InjectMocks
    private TrainingFacadeImpl trainingFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTraining() {
        trainingFacade.createTraining(10L, 1L, 2L, "Yoga Relax", TrainingType.YOGA, new Date(), "45");
        verify(trainingService).createTraining(eq(10L), eq(1L), eq(2L), eq("Yoga Relax"), eq(TrainingType.YOGA), any(), eq("45"));
    }

    @Test
    void testGetTrainingById() {
        Training training = new Training(10L, 1L, 2L, "Yoga Relax", TrainingType.YOGA, new Date(), "45");
        when(trainingService.getTrainingById(10L)).thenReturn(Optional.of(training));

        Optional<Training> result = trainingFacade.getTrainingById(10L);

        assertTrue(result.isPresent());
        assertEquals(10L, result.get().getTrainingId());
        verify(trainingService).getTrainingById(10L);
    }

    @Test
    void testGetAllTrainings() {
        List<Training> trainings = List.of(new Training(10L, 1L, 2L, "Yoga Relax", TrainingType.YOGA, new Date(), "45"));
        when(trainingService.getAllTrainings()).thenReturn(trainings);

        List<Training> result = trainingFacade.getAllTrainings();

        assertEquals(1, result.size());
        verify(trainingService).getAllTrainings();
    }
}