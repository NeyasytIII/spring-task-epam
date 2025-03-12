package com.epamtask.service;

import com.epamtask.dao.TraineeDAO;
import com.epamtask.dao.TrainerDAO;
import com.epamtask.model.Trainee;
import com.epamtask.model.Trainer;
import com.epamtask.service.impl.TraineeServiceImpl;
import com.epamtask.utils.UserNameGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TraineeServiceTest {

    @Mock
    private TraineeDAO traineeDAO;
    @Mock
    private TrainerDAO trainerDAO;
    @Mock
    private UserNameGenerator userNameGenerator;
    @InjectMocks
    private TraineeServiceImpl traineeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTrainee_Success() {
        Long userId = 100L;
        String firstName = "John";
        String lastName = "Doe";
        String address = "123 Street";
        Date birthday = new Date();
        Map<Long, Trainee> fakeTraineeStorage = new HashMap<>();
        Map<Long, Trainer> fakeTrainerStorage = new HashMap<>();

        when(traineeDAO.getAll()).thenReturn(fakeTraineeStorage);
        when(trainerDAO.getAll()).thenReturn(fakeTrainerStorage);
        when(userNameGenerator.generateUserName(eq(firstName), eq(lastName), eq(fakeTraineeStorage), eq(fakeTrainerStorage)))
                .thenReturn("John.Doe");
        doNothing().when(traineeDAO).create(anyLong(), any(Trainee.class));

        traineeService.createTrainee(userId, firstName, lastName, address, birthday);

        ArgumentCaptor<Trainee> captor = ArgumentCaptor.forClass(Trainee.class);
        verify(traineeDAO).create(eq(userId), captor.capture());
        Trainee created = captor.getValue();
        assertEquals("John.Doe", created.getUserName());
        assertNotNull(created.getPassword());
        assertTrue(created.isActive());
    }

    @Test
    void testCreateTrainee_NullUserId_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                traineeService.createTrainee(null, "John", "Doe", "Some address", new Date())
        );
        verifyNoInteractions(traineeDAO);
    }

    @Test
    void testUpdateTrainee_Success() {
        Trainee trainee = new Trainee(200L, "Test", "User", "Address", new Date());
        when(traineeDAO.findById(200L)).thenReturn(Optional.of(trainee));
        doNothing().when(traineeDAO).update(any(Trainee.class));

        traineeService.updateTrainee(trainee);

        verify(traineeDAO).update(trainee);
    }

    @Test
    void testUpdateTrainee_NullTrainee_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> traineeService.updateTrainee(null));
        verifyNoInteractions(traineeDAO);
    }

    @Test
    void testUpdateTrainee_NotFound_ShouldThrowException() {
        Trainee trainee = new Trainee(300L, "Test", "User", "Address", new Date());
        when(traineeDAO.findById(300L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> traineeService.updateTrainee(trainee));
        verify(traineeDAO).findById(300L);
        verifyNoMoreInteractions(traineeDAO);
    }

    @Test
    void testDeleteTrainee_Success() {
        when(traineeDAO.findById(999L)).thenReturn(Optional.of(new Trainee()));
        doNothing().when(traineeDAO).deleteById(999L);

        traineeService.deleteTrainee(999L);

        verify(traineeDAO).deleteById(999L);
    }

    @Test
    void testDeleteTrainee_NullId_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> traineeService.deleteTrainee(null));
        verifyNoInteractions(traineeDAO);
    }

    @Test
    void testDeleteTrainee_NotFound_ShouldThrowException() {
        when(traineeDAO.findById(888L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> traineeService.deleteTrainee(888L));
        verify(traineeDAO).findById(888L);
        verifyNoMoreInteractions(traineeDAO);
    }

    @Test
    void testGetTraineeById_Success() {
        Trainee mockTrainee = new Trainee(500L, "Test", "User", "Address", new Date());
        when(traineeDAO.findById(500L)).thenReturn(Optional.of(mockTrainee));
        Optional<Trainee> result = traineeService.getTraineeById(500L);
        assertTrue(result.isPresent());
        assertEquals(500L, result.get().getUserId());
        verify(traineeDAO).findById(500L);
    }

    @Test
    void testGetTraineeById_NullId_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> traineeService.getTraineeById(null));
        verifyNoInteractions(traineeDAO);
    }

    @Test
    void testGetTraineeByUsername_Success() {
        Trainee mockTrainee = new Trainee(600L, "Alice", "Wonder", "Address", new Date());
        mockTrainee.setUserName("Alice.Wonder");
        when(traineeDAO.findByUsername("Alice.Wonder")).thenReturn(Optional.of(mockTrainee));
        Optional<Trainee> result = traineeService.getTraineeByUsername("Alice.Wonder");
        assertTrue(result.isPresent());
        assertEquals("Alice.Wonder", result.get().getUserName());
        verify(traineeDAO).findByUsername("Alice.Wonder");
    }

    @Test
    void testGetTraineeByUsername_NullUsername_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> traineeService.getTraineeByUsername(null));
        verifyNoInteractions(traineeDAO);
    }

    @Test
    void testGetTraineeByUsername_EmptyUsername_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> traineeService.getTraineeByUsername(" "));
        verifyNoInteractions(traineeDAO);
    }
}