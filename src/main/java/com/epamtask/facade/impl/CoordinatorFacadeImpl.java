package com.epamtask.facade.impl;

import com.epamtask.aspect.Loggable;
import com.epamtask.facade.CoordinatorFacade;
import com.epamtask.facade.TraineeFacade;
import com.epamtask.facade.TrainerFacade;
import com.epamtask.facade.TrainingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordinatorFacadeImpl implements CoordinatorFacade {
    private final TraineeFacade traineeFacade;
    private final TrainerFacade trainerFacade;
    private final TrainingFacade trainingFacade;

    @Autowired
    public CoordinatorFacadeImpl(TraineeFacade traineeFacade, TrainerFacade trainerFacade, TrainingFacade trainingFacade) {
        this.traineeFacade = traineeFacade;
        this.trainerFacade = trainerFacade;
        this.trainingFacade = trainingFacade;
    }
    @Loggable
    @Override
    public TraineeFacade getTraineeFacade() {
        return traineeFacade;
    }
    @Loggable
    @Override
    public TrainerFacade getTrainerFacade() {
        return trainerFacade;
    }
    @Loggable
    @Override
    public TrainingFacade getTrainingFacade() {
        return trainingFacade;
    }
}