package com.epamtask.facade.impl;

import com.epamtask.model.Trainee;
import com.epamtask.service.TraineeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TraineeFacadeImplTest {

    @Mock
    private TraineeService traineeService;

    @InjectMocks
    private TraineeFacadeImpl traineeFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTrainee() {
        traineeFacade.createTrainee(1L, "John", "Doe", "123 Elm Street", new Date());
        verify(traineeService).createTrainee(eq(1L), eq("John"), eq("Doe"), eq("123 Elm Street"), any());
    }

    @Test
    void testUpdateTrainee() {
        Trainee trainee = new Trainee(1L, "John", "Doe", "123 Elm Street", new Date());
        traineeFacade.updateTrainee(trainee);
        verify(traineeService).updateTrainee(trainee);
    }

    @Test
    void testDeleteTrainee() {
        traineeFacade.deleteTrainee(1L);
        verify(traineeService).deleteTrainee(1L);
    }

    @Test
    void testGetTraineeById() {
        Trainee trainee = new Trainee(1L, "John", "Doe", "123 Elm Street", new Date());
        when(traineeService.getTraineeById(1L)).thenReturn(Optional.of(trainee));

        Optional<Trainee> result = traineeFacade.getTraineeById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getUserId());
        verify(traineeService).getTraineeById(1L);
    }

    @Test
    void testGetAllTrainees() {
        List<Trainee> trainees = List.of(new Trainee(1L, "John", "Doe", "123 Elm Street", new Date()));
        when(traineeService.getAllTrainees()).thenReturn(trainees);

        List<Trainee> result = traineeFacade.getAllTrainees();

        assertEquals(1, result.size());
        verify(traineeService).getAllTrainees();
    }
}