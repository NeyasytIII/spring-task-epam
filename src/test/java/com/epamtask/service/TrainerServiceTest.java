package com.epamtask.service;

import com.epamtask.dao.TraineeDAO;
import com.epamtask.dao.TrainerDAO;
import com.epamtask.model.Trainer;
import com.epamtask.service.impl.TrainerServiceImpl;
import com.epamtask.utils.UserNameGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TrainerServiceTest {

    @Mock
    private TrainerDAO trainerDAO;

    @Mock
    private TraineeDAO traineeDAO;

    @Mock
    private UserNameGenerator userNameGenerator;

    @InjectMocks
    private TrainerServiceImpl trainerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTrainer_Success() {
        Long userId = 200L;
        String firstName = "Anna";
        String lastName = "Smith";
        String specialization = "Yoga";

        Map<Long, Trainer> fakeTrainerStorage = new HashMap<>();
        Map<Long, com.epamtask.model.Trainee> fakeTraineeStorage = new HashMap<>();

        when(trainerDAO.findById(userId)).thenReturn(Optional.empty());
        when(trainerDAO.getAll()).thenReturn(fakeTrainerStorage);
        when(traineeDAO.getAll()).thenReturn(fakeTraineeStorage);
        when(userNameGenerator.generateUserName(eq(firstName), eq(lastName), eq(fakeTraineeStorage), eq(fakeTrainerStorage)))
                .thenReturn("Anna.Smith");

        trainerService.createTrainer(userId, firstName, lastName, specialization);

        verify(trainerDAO).create(eq(userId), any(Trainer.class));
        verify(userNameGenerator).generateUserName(eq(firstName), eq(lastName), eq(fakeTraineeStorage), eq(fakeTrainerStorage));
    }

    @Test
    void testCreateTrainer_NullId_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> trainerService.createTrainer(null, "Anna", "Smith", "Yoga"));
        verifyNoInteractions(trainerDAO);
    }

    @Test
    void testCreateTrainer_NullFirstName_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> trainerService.createTrainer(200L, null, "Smith", "Yoga"));
        verifyNoInteractions(trainerDAO);
    }

    @Test
    void testCreateTrainer_ExistingTrainer_ShouldThrowException() {
        when(trainerDAO.findById(200L)).thenReturn(Optional.of(new Trainer(200L, "Anna", "Smith", "Yoga")));

        assertThrows(IllegalArgumentException.class, () -> trainerService.createTrainer(200L, "Anna", "Smith", "Yoga"));
        verify(trainerDAO).findById(200L);
    }

    @Test
    void testUpdateTrainer_Success() {
        Trainer trainer = new Trainer(300L, "Bob", "Jones", "Strength Training");

        when(trainerDAO.findById(300L)).thenReturn(Optional.of(trainer));

        trainerService.updateTrainer(trainer);

        verify(trainerDAO).update(trainer);
    }

    @Test
    void testUpdateTrainer_NotFound_ShouldThrowException() {
        Trainer trainer = new Trainer(400L, "John", "Doe", "Cardio");

        when(trainerDAO.findById(400L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> trainerService.updateTrainer(trainer));
        verify(trainerDAO).findById(400L);
        verifyNoMoreInteractions(trainerDAO);
    }

    @Test
    void testDeleteTrainer_Success() {
        when(trainerDAO.findById(500L)).thenReturn(Optional.of(new Trainer()));

        trainerService.deleteTrainer(500L);

        verify(trainerDAO).deleteById(500L);
    }

    @Test
    void testDeleteTrainer_NotFound_ShouldThrowException() {
        when(trainerDAO.findById(600L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> trainerService.deleteTrainer(600L));
        verify(trainerDAO).findById(600L);
        verifyNoMoreInteractions(trainerDAO);
    }

    @Test
    void testGetTrainerById_Success() {
        Trainer mockTrainer = new Trainer(700L, "Alice", "Brown", "Pilates");

        when(trainerDAO.findById(700L)).thenReturn(Optional.of(mockTrainer));

        Optional<Trainer> result = trainerService.getTrainerById(700L);

        assertTrue(result.isPresent());
        assertEquals(700L, result.get().getUserId());
        verify(trainerDAO).findById(700L);
    }

    @Test
    void testGetTrainerByUsername_Success() {
        Trainer mockTrainer = new Trainer(800L, "Steve", "White", "CrossFit");
        mockTrainer.setUserName("Steve.White");

        when(trainerDAO.findByUsername("Steve.White")).thenReturn(Optional.of(mockTrainer));

        Optional<Trainer> result = trainerService.getTrainerByUsername("Steve.White");

        assertTrue(result.isPresent());
        assertEquals("Steve.White", result.get().getUserName());
        verify(trainerDAO).findByUsername("Steve.White");
    }

    @Test
    void testGetTrainerByUsername_EmptyUsername_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> trainerService.getTrainerByUsername(" "));
        verifyNoInteractions(trainerDAO);
    }
}