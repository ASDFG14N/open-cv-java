package com.code.controller;

import com.code.model.Vehicle;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gian
 */
public class VehicleController {

    public static LinkedList<Vehicle> queueOffendingVehicles;

    public VehicleController() {
        queueOffendingVehicles = new LinkedList<>();
    }

    public void addLast(Vehicle vehicle) {
        queueOffendingVehicles.addLast(vehicle);
    }

    public void clearQueue() {
        queueOffendingVehicles.clear();
    }

    public int sizeOffendingVehicles() {
        return queueOffendingVehicles.size();
    }

    public double averageSpeeds(Map<Integer, Double> idSpeed) {
        double average = idSpeed.values().stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        return average;
    }

    public double averageSpeeds(List<Double> idSpeed) {
        double average = idSpeed.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        return average;
    }

    public int sumOfElements(List<Integer> list) {
        int sum = list.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return sum;
    }

    public void showTable(DefaultTableModel model) {
        int c = 1;
        for (Vehicle v : queueOffendingVehicles) {
            Object[] rowData = {
                c,
                v.getID(),
                v.getOwnerName(),
                v.getLicensePlate(),
                v.getDate().getDateFormat(),
                v.getState()
            };
            model.addRow(rowData);
            c++;
        }
    }

    public void searchForState(DefaultTableModel model, String stateStr) {
        AtomicInteger count = new AtomicInteger(1);
        queueOffendingVehicles.stream()
                .filter(v -> v.getState().equals(stateStr))
                .forEach(v -> {
                    Object[] rowData = {
                        count.getAndIncrement(),
                        v.getID(),
                        v.getOwnerName(),
                        v.getLicensePlate(),
                        v.getDate().getDateFormat(),
                        v.getState()
                    };
                    model.addRow(rowData);
                });
    }

    public void searchForDay(DefaultTableModel model, int dayStr) {
        AtomicInteger count = new AtomicInteger(1);
        queueOffendingVehicles.stream()
                .filter(v -> v.getDate().getDay() == (dayStr))
                .forEach(v -> {
                    Object[] rowData = {
                        count.getAndIncrement(),
                        v.getID(),
                        v.getOwnerName(),
                        v.getLicensePlate(),
                        v.getDate().getDateFormat(),
                        v.getState()
                    };
                    model.addRow(rowData);
                });
    }

    public void searchForName(DefaultTableModel model, String input) {
        AtomicInteger count = new AtomicInteger(1);
        String searchInput = input.toLowerCase();
        queueOffendingVehicles.stream()
                .filter(v -> v.getOwnerName().toLowerCase().contains(searchInput))
                .forEach(v -> {
                    Object[] rowData = {
                        count.getAndIncrement(),
                        v.getID(),
                        v.getOwnerName(),
                        v.getLicensePlate(),
                        v.getDate().getDateFormat(),
                        v.getState()
                    };
                    model.addRow(rowData);
                });
    }

    public void searchForPlate(DefaultTableModel model, String input) {
        AtomicInteger count = new AtomicInteger(1);
        String searchInput = input.toLowerCase();
        queueOffendingVehicles.stream()
                .filter(v -> v.getLicensePlate().toLowerCase().contains(searchInput))
                .forEach(v -> {
                    Object[] rowData = {
                        count.getAndIncrement(),
                        v.getID(),
                        v.getOwnerName(),
                        v.getLicensePlate(),
                        v.getDate().getDateFormat(),
                        v.getState()
                    };
                    model.addRow(rowData);
                });
    }

    public Vehicle searchReference(int id, String name, String plate) {
        return queueOffendingVehicles.stream()
                .filter(v
                        -> v.getOwnerName().equals(name)
                && v.getLicensePlate().equals(plate)
                && v.getID() == id
                )
                .findFirst()
                .orElse(null);
    }

}
