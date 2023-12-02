package com.code.controller;

import com.code.model.Person;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Comparator.comparingInt;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Gian
 */
public class PersonController {

    protected ArrayList<Person> arrayPersons;

    public PersonController() {
        arrayPersons = new ArrayList<>();
    }

    public void addPerson(Person person) {
        arrayPersons.add(person);
    }

    public void clearArray() {
        arrayPersons.clear();
    }

    public int sizeArrayPerson() {
        return arrayPersons.size();
    }

    /**
     *
     * @return
     */
    public LinkedList<Person> rankPeopleMostQualification() {
        List<Person> rankedPeople = arrayPersons.stream()
                .sorted(comparingInt(Person::getQualification).reversed())
                .limit(15)
                .collect(Collectors.toList());
        return new LinkedList<>(rankedPeople);
    }

    public LinkedList<Person> rankPeopleLeastQualification() {
        List<Person> rankedPeople = arrayPersons.stream()
                .sorted(comparingInt(Person::getQualification))
                .limit(15)
                .collect(Collectors.toList());
        Collections.reverse(rankedPeople);
        return new LinkedList<>(rankedPeople);
    }

}
