package com.epamtask.model;

import java.util.Date;
import java.util.Objects;

public class Trainee extends User {
    private Long userId;
    private String address;
    private Date birthdayDate;

    public Trainee(Long userId, String firstName, String lastName, String address, Date birthdayDate) {
        super(firstName, lastName);
        this.userId = userId;
        this.address = address;
        this.birthdayDate = birthdayDate;
    }

    public Trainee() {
    }

    public Long getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainee trainee = (Trainee) o;
        return Objects.equals(userId, trainee.userId) && Objects.equals(address, trainee.address) && Objects.equals(birthdayDate, trainee.birthdayDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, address, birthdayDate);
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "userId=" + userId +
                ", firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", userName='" + this.getUserName() + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthdayDate + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", isActive=" + this.isActive() +
                '}';
    }
}
