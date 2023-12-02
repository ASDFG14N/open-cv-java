package com.code.model;

import java.util.StringTokenizer;

public class Date {

    private int day;
    private int month;
    private int year;
    private String dateFormat;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String dateFormat) {
        this.dateFormat = dateFormat;
        StringTokenizer st = new StringTokenizer((String) dateFormat, "/");
        this.day = Integer.parseInt(st.nextToken());
        this.month = Integer.parseInt(st.nextToken());
        this.year = Integer.parseInt(st.nextToken());
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String format() {
        return day + "/" + month + "/" + year;
    }

    public String getDateFormat() {
        return dateFormat;
    }

}
