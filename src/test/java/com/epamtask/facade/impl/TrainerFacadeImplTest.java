package com.epamtask.facade.impl;


import com.epamtask.model.Trainer;
import com.epamtask.service.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerFacadeImplTest {

    @Mock
    private TrainerService trainerService;

    @InjectMocks
    private TrainerFacadeImpl trainerFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTrainer() {
        trainerFacade.createTrainer(2L, "Emily", "Davis", "Yoga");
        verify(trainerService).createTrainer(eq(2L), eq("Emily"), eq("Davis"), eq("Yoga"));
    }

    @Test
    void testUpdateTrainer() {
        Trainer trainer = new Trainer(2L, "Emily", "Davis", "Yoga");
        trainerFacade.updateTrainer(trainer);
        verify(trainerService).updateTrainer(trainer);
    }

    @Test
    void testGetTrainerById() {
        Trainer trainer = new Trainer(2L, "Emily", "Davis", "Yoga");
        when(trainerService.getTrainerById(2L)).thenReturn(Optional.of(trainer));

        Optional<Trainer> result = trainerFacade.getTrainerById(2L);

        assertTrue(result.isPresent());
        assertEquals(2L, result.get().getUserId());
        verify(trainerService).getTrainerById(2L);
    }

    @Test
    void testGetAllTrainers() {
        List<Trainer> trainers = List.of(new Trainer(2L, "Emily", "Davis", "Yoga"));
        when(trainerService.getAllTrainers()).thenReturn(trainers);

        List<Trainer> result = trainerFacade.getAllTrainers();

        assertEquals(1, result.size());
        verify(trainerService).getAllTrainers();
    }
}