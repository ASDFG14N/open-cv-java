package com.code.model;

import static com.code.routes.Routes.SEMAPHORE_IMG_GREEN;
import static com.code.routes.Routes.SEMAPHORE_IMG_RED;
import static com.code.routes.Routes.SEMAPHORE_IMG_YELLOW;
import static com.code.view.Form1.imgSem;
import static java.awt.Image.SCALE_DEFAULT;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

/**
 *
 * @author Gian
 */
public class Semaphore {

    private final String RED;
    private final String YELLOW;
    private final String GREEN;

    private final int RED_TIME = 5000;
    private final int GREEN_TIME = 5000;
    private final int YELLOW_TIME = 2000;

    private enum State {
        RED,
        GREEN,
        YELLOW
    }

    private State currentState;

    public Semaphore() {
        this.currentState = State.RED;
        RED = SEMAPHORE_IMG_RED.getRoute();
        YELLOW = SEMAPHORE_IMG_YELLOW.getRoute();
        GREEN = SEMAPHORE_IMG_GREEN.getRoute();
    }

    public void startSemaphore() {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                changeStatus();
                showStatus();
            }
        }, 0, RED_TIME + GREEN_TIME + YELLOW_TIME);
    }

    private void changeStatus() {
        switch (currentState) {
            case RED ->
                currentState = State.GREEN;
            case GREEN ->
                currentState = State.YELLOW;
            case YELLOW ->
                currentState = State.RED;
        }
    }

    private void showStatus() {
        switch (currentState) {
            case RED ->
                imgSem.setIcon(
                        new ImageIcon(
                                new ImageIcon(RED).getImage()
                                        .getScaledInstance(
                                                165,
                                                40,
                                                SCALE_DEFAULT
                                        )
                        )
                );
            case GREEN ->
                imgSem.setIcon(
                        new ImageIcon(
                                new ImageIcon(GREEN).getImage()
                                        .getScaledInstance(
                                                165,
                                                40,
                                                SCALE_DEFAULT
                                        )
                        )
                );
            case YELLOW ->
                imgSem.setIcon(
                        new ImageIcon(
                                new ImageIcon(YELLOW).getImage()
                                        .getScaledInstance(
                                                165,
                                                40,
                                                SCALE_DEFAULT
                                        )
                        )
                );
        }
    }

    private void logica() {
        int numeroPersonas = 0;  // Obtén este valor de tu fuente OpenCV
        int numeroCarros = 0;    // Obtén este valor de tu fuente OpenCV
        boolean stopVehicles = false;
        boolean stopPeople = false;

        int thresholdFewCars = 5;  // Ajusta este umbral según tus necesidades
        int thresholdManyCars = 20; // Ajusta este umbral según tus necesidades

        int thresholdFewPeople = 2;  // Ajusta este umbral según tus necesidades
        int thresholdManyPeople = 10; // Ajusta este umbral según tus necesidades

        if (currentState == State.RED) {
            stopVehicles = true;
            stopPeople = false;
        } else if (currentState == State.GREEN) {
            // Si hay pocos autos y muchas personas, detener el tráfico de autos
            if (numeroCarros <= thresholdFewCars && numeroPersonas > thresholdManyPeople) {
                stopVehicles = true;
            } // Si hay muchos autos y pocas personas, detener el tráfico de personas
            else if (numeroCarros > thresholdManyCars && numeroPersonas <= thresholdFewPeople) {
                stopPeople = true;
            } // En otros casos, permitir el flujo normal
            else {
                stopVehicles = false;
                stopPeople = false;
            }
        }
    }

}
