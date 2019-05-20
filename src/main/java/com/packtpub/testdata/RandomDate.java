/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packtpub.testdata;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Generate N random dates from this year
 * @author erikc_000
 */
public class RandomDate {
    
    /**
     * 
     * @param howMany
     * @return Unique random days from the current year.
     */
    public static SortedSet<LocalDate> makeRandomDates(int howMany){
        final int year = LocalDate.now().getYear();
        final LocalDate start = LocalDate.of(year, Month.JANUARY, 1);
        final LocalDate end = LocalDate.of(year, Month.DECEMBER, 31);
        final int maxDays = (int) Duration.between(start.atStartOfDay(), end.atStartOfDay()).toDays();
        if(howMany<0 || howMany>maxDays){
            throw new IllegalArgumentException("Only " + maxDays + " days, cannot obtain " + howMany);
        }
        
        Random random = new Random();
        
        final SortedSet<LocalDate> dates = new TreeSet<>();
        while(dates.size()<howMany){
            final int day = random.nextInt(maxDays-1)+1; //adjust because there's no day 0
            final LocalDate date = LocalDate.ofYearDay(year, day);
            dates.add(date);
        }
        return dates;
    }
    
    public static void main(String[] args){
        final Set<LocalDate> days = makeRandomDates(100);
        days.stream().map(RandomDate::prettyPrint).forEach(System.out::println);
    }
    
    private static String prettyPrint(LocalDate date){
        final String formatted = date.format(DateTimeFormatter.ISO_DATE);
        
        final String type = DayOfWeek.SATURDAY.equals(date.getDayOfWeek()) || DayOfWeek.SUNDAY.equals(date.getDayOfWeek()) ? "Weekend" : "Weekday";
        
        return String.format("%s is a %s", formatted, type);
    }
}
