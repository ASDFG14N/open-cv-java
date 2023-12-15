package com.code.model;

import static com.code.algorithms.MotionDetection.countCarsInArea;
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

    public static enum State {
        RED,
        GREEN,
        YELLOW
    }

    public static State currentState;

    public Semaphore() {
        currentState = State.RED;
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
                //Semaforo en rojo
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
                //Semaforo en verde
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
                //Semaforo en amarillo
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
        int numberPeople = 0;  // Obtén este valor de tu fuente OpenCV
        int numberCars = countCarsInArea;
        boolean stopVehicles = false;
        boolean stopPeople = false;

        int thresholdFewCars = 5;
        int thresholdManyCars = 20;

        int thresholdFewPeople = 2;
        int thresholdManyPeople = 10;

        if (currentState == State.RED) {
            stopVehicles = true;
            stopPeople = false;
        } else if (currentState == State.GREEN) {
            // Si hay pocos autos y muchas personas, detener el tráfico de autos
            if (numberCars <= thresholdFewCars && numberPeople > thresholdManyPeople) {
                stopVehicles = true;
            } // Si hay muchos autos y pocas personas, detener el tráfico de personas
            else if (numberCars > thresholdManyCars && numberPeople <= thresholdFewPeople) {
                stopPeople = true;
            } else {
                stopVehicles = false;
                stopPeople = false;
            }
        }
    }

}
