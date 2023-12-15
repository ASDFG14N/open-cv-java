package com.code.controller;

import com.code.model.Date;
import com.code.model.Vehicle;
import com.code.statics.FileManagement;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Gian
 */
public class VehicleFile extends VehicleController implements FileManagement {

    public VehicleFile() {
    }

    @Override
    public <T> T recoverData(String fileName, T data) {
        ArrayList arrayAux = new ArrayList();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                arrayAux.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
        }
        return (T) arrayAux;
    }

    public void add(String fileName) {
        ArrayList lines = recoverData(fileName, new ArrayList());
        for (var line : lines) {
            //id;nombre;placa;fecha;estado
            StringTokenizer st = new StringTokenizer((String) line, ";");
            int ID = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            String plate = st.nextToken();
            Date date = new Date(st.nextToken());
            String state = st.nextToken();
            Vehicle vehicle = new Vehicle(
                    ID,
                    plate,
                    plate,
                    name,
                    date,
                    state
            );
            addLast(vehicle);
        }
    }

}
