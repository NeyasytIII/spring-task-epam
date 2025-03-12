package com.epamtask;

import com.epamtask.facade.TotalFacade;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class GymCRMApp {
    public void start() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epamtask");
        TotalFacade facade = context.getBean(TotalFacade.class);

        System.out.println("\nLoaded Trainees from file:");
        facade.getAllTrainees().forEach(System.out::println);

        try {
            facade.createTrainee(2L, "Alice", "Brown", "123 Elm Street", new Date());
        } catch (Exception e) {
            System.err.println("Expected exception (duplicate trainee ID): " + e.getMessage());
        }

//        try {
//            facade.createTrainee(200L, "Bob", "Smith", "456 Oak Avenue", new Date());
//        } catch (Exception e) {
//            System.err.println("Expected exception (duplicate trainee ID): " + e.getMessage());
//        }

        try {
            facade.createTrainee(300L, "Emma", "Wilson", "789 Maple Street", new Date());
            System.out.println("Successfully created new Trainee with ID 200.");
        } catch (Exception e) {
            System.err.println("Unexpected exception: " + e.getMessage());
        }

        System.out.println("\nAll Trainees after operations:");
        facade.getAllTrainees().forEach(System.out::println);

        context.close();
    }
}