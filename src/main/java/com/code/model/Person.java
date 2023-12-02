package com.code.model;

/**
 *
 * @author Gian
 */
public class Person {

    private String name;
    private String lastName;
    private int age;
    private int DNI;
    private int qualification = 1000;

    public Person(String name, String lastName, int age, int DNI) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.DNI = DNI;
    }

    public Person(String name, String lastName, int age, int DNI, int qualification) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.DNI = DNI;
        this.qualification = qualification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public int getQualification() {
        return qualification;
    }

    public void setQualification(int qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name
                + ", lastName=" + lastName
                + ", age=" + age
                + ", DNI=" + DNI
                + ", qualification=" + qualification + '}';
    }

}
