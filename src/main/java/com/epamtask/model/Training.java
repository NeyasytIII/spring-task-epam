package com.epamtask.model;

import java.util.Date;
import java.util.Objects;

public class Training {

    private Long trainingId;
    private Long traineeId;
    private Long trainerId;
    private String trainingName;
    private TrainingType type;
    private Date trainingDate;
    private String trainingDuration;

    public Training() {
    }


    public Training(Long trainingId, Long traineeId, Long trainerId, String trainingName, TrainingType type, Date trainingDate, String trainingDuration) {
        this.trainingId = trainingId;
        this.traineeId = traineeId;
        this.trainerId = trainerId;
        this.trainingName = trainingName;
        this.type = type;
        this.trainingDate = trainingDate;
        this.trainingDuration = trainingDuration;
    }


    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }

    public Long getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Long traineeId) {
        this.traineeId = traineeId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public TrainingType getType() {
        return type;
    }

    public void setType(TrainingType type) {
        this.type = type;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public String getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(String trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ( this == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return Objects.equals(trainingId, training.trainingId) &&
                Objects.equals(traineeId, training.traineeId) &&
                Objects.equals(trainerId, training.trainerId) &&
                Objects.equals(trainingName, training.trainingName) &&
                type == training.type &&
                Objects.equals(trainingDate, training.trainingDate) &&
                Objects.equals(trainingDuration, training.trainingDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainingId, traineeId, trainerId, trainingName, type, trainingDate, trainingDuration);
    }

    @Override
    public String toString() {
        return "Training{" +
                "trainingId=" + trainingId +
                ", traineeId=" + traineeId +
                ", trainerId=" + trainerId +
                ", trainingName='" + trainingName + '\'' +
                ", type=" + type +
                ", trainingDate=" + trainingDate +
                ", trainingDuration='" + trainingDuration + '\'' +
                '}';
    }
}