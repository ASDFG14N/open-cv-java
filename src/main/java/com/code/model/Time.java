package com.code.model;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 *
 * @author Gian
 */
public class Time {

    private LocalDate currentDate;
    private LocalTime currentTime;
    private int currentMonth;
    private int currentDay;
    private String minuteStr;
    private Calendar calendar;

    public Time() {
        this.currentDate = LocalDate.now();
        this.currentTime = LocalTime.now();
        this.calendar = Calendar.getInstance();
    }

    public String getCurrentDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return currentDate.format(dateFormatter);
    }

    public String getCurrentTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(timeFormatter);
    }

    public String getCurrentTime2() {
        LocalTime currentTimePriv = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTimePriv.format(timeFormatter);
    }

    public int getCurrentMonth() {
        currentMonth = calendar.get(Calendar.MONTH);
        return currentMonth;
    }

    public int getCurrentDay() {
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        return currentDay;
    }

    public String monthName(int month) {
        String[] monthNames = new DateFormatSymbols().getMonths();
        return monthNames[month];
    }

}
