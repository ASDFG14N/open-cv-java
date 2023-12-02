package com.code.statics;

import static com.code.routes.Routes.PATH_DATA_MONTH;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author Gian
 */
public interface FileManagement {

    default <T> void saveTextFile(String fileName, T data) {
        try (FileWriter fr = new FileWriter(fileName); PrintWriter pw = new PrintWriter(fr)) {
            switch (data) {
                case String string ->
                    pw.println(string);
                case Map<?, ?> map -> {
                    StringBuilder result = new StringBuilder();
                    map.forEach((key, value)
                            -> result.append(key)
                                    .append(";")
                                    .append(value)
                                    .append("\n")
                    );
                    pw.print(result.toString());
                }
                default ->
                    throw new IllegalArgumentException("Tipo de dato no soportado");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.toString());
        }
    }

    default <T> T recoverData(String fileName, T data) {
        return null;
    }

    default List<List> recoverData(String fileName) {
        List<List> obj = new ArrayList();
        List<Integer> totalVehiculosArray = new ArrayList();
        List<Integer> totalInfraccionesArray = new ArrayList();
        List<Double> velocidades = new ArrayList();
        List<Integer> puntosAumentadosArray = new ArrayList();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(PATH_DATA_MONTH.getRoute() + fileName), UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                //Dia;total vehiculos;total infracciones;velocidad promedio;puntos aumentados;puntos descontados
                StringTokenizer st = new StringTokenizer(line, ";");
                int day = Integer.parseInt(st.nextToken());
                int totalVehiculos = Integer.parseInt(st.nextToken());
                totalVehiculosArray.add(totalVehiculos);
                int totalInfracciones = Integer.parseInt(st.nextToken());
                totalInfraccionesArray.add(totalInfracciones);
                double velocidadPromedio = Double.parseDouble(st.nextToken());
                velocidades.add(velocidadPromedio);
                int puntosAumentados = Integer.parseInt(st.nextToken());
                puntosAumentadosArray.add(puntosAumentados);
                String nom = st.nextToken();
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error en la lectura: " + e.toString());
        }
        obj.add(totalVehiculosArray);
        obj.add(totalInfraccionesArray);
        obj.add(velocidades);
        obj.add(puntosAumentadosArray);
        return obj;
    }

    static boolean existFile(String fileName) {
        boolean exist = false;
        File file = new File(fileName);
        exist = file.exists();
        return exist;
    }

}
