package com.epamtask;

import com.epamtask.facade.CoordinatorFacade;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class GymCRMApp {
    public void start() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epamtask");

        CoordinatorFacade coordinatorFacade = context.getBean(CoordinatorFacade.class);

        System.out.println("\nLoaded Trainees from file:");
        coordinatorFacade.getTraineeFacade().getAllTrainees().forEach(System.out::println);

        try {
            coordinatorFacade.getTraineeFacade().createTrainee(2L, "Alice", "Brown", "123 Elm Street", new Date());
        } catch (Exception e) {
            System.err.println("Expected exception (duplicate trainee ID): " + e.getMessage());
        }

        try {
            coordinatorFacade.getTraineeFacade().createTrainee(300L, "Emma", "Wilson", "789 Maple Street", new Date());
            System.out.println("Successfully created new Trainee with ID 300.");
        } catch (Exception e) {
            System.err.println("Unexpected exception: " + e.getMessage());
        }

        System.out.println("\nAll Trainees after operations:");
        coordinatorFacade.getTraineeFacade().getAllTrainees().forEach(System.out::println);

        context.close();
    }
}