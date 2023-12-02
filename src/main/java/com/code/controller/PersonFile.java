package com.code.controller;

import com.code.model.Person;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.StringTokenizer;

/**
 *
 * @author Gian
 */
public class PersonFile extends PersonController {

    private String fileName;

    public PersonFile(String fileName) {
        this.fileName = fileName;
        recoverData();
    }

    private void recoverData() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ";");
                String name = st.nextToken();
                String lastName = st.nextToken();
                int age = Integer.parseInt(st.nextToken());
                int DNI = Integer.parseInt(st.nextToken());
                int qualification = Integer.parseInt(st.nextToken());
                Person person = new Person(name, lastName, age, DNI, qualification);
                addPerson(person);
            }

        } catch (IOException e) {
            System.out.println("Error en la lectura: " + e.toString());
        }
    }

}
