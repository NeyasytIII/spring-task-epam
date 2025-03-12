package com.epamtask.model;

import java.util.Objects;

public class Trainer extends User {
    private Long userId;
    private String specialization;

    public Trainer(Long userId, String firstName, String lastName, String specialization) {
        super(firstName, lastName);
        this.userId = userId;
        this.specialization = specialization;
    }
    public Trainer(){}


    public Long getUserId() {
        return userId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return Objects.equals(userId, trainer.userId) && Objects.equals(specialization, trainer.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, specialization);
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "userId=" + userId +
                ", firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", userName='" + this.getUserName() + '\'' +
                ", Specialization='" + specialization + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", isActive=" + this.isActive() +
                '}';
    }
}
