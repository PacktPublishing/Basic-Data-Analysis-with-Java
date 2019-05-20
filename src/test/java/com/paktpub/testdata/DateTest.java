/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paktpub.testdata;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author erikc_000
 */
public class DateTest {

    @Test
    public void testRandomDaysLastYear() {
        final int numberOfDays = 10;
        final LocalDate thisYearJan1 = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1);
        final LocalDate lastYearJan1 = thisYearJan1.minus(1, ChronoUnit.YEARS);
        
        final boolean wasLeapYear = lastYearJan1.isLeapYear();
        
        final String wasWasNot = wasLeapYear ? "was" : "was not";
        //Normally you're safe assuming a year has 365 or 366 days
        final int reasonableExpectationDaysInYear = wasLeapYear ? 366 : 365;
        //Assume it didn't, how can you get days between two times?
        final long daysBetween = Duration.between(lastYearJan1.atTime(0, 0), thisYearJan1.atTime(0, 0)).toDays();
        
        final String s = String.format("Last year was %d and %s a leap year. It had %d days", lastYearJan1.getYear(), wasWasNot, daysBetween);
        System.out.println(s);
        
        assertTrue("Unexpected number of days", daysBetween==365 || daysBetween==366);
        System.out.println(String.format("Last year had %d days", daysBetween));
        
        
        final int lastYear = lastYearJan1.getYear();
        final SortedSet<LocalDate> days = randomDaysBetween(numberOfDays, lastYearJan1, thisYearJan1);
        
        //Make sure they're all in the same year.
        days.stream()
                .filter(date -> date.getYear() != lastYear)
                .findAny()
                .ifPresent(date -> {
                    final String failMsg = String.format("Date %s is in wrong year", date.format(DateTimeFormatter.ISO_DATE));
                    fail(failMsg);
                });

        days.stream()
                .map(date -> date.format(DateTimeFormatter.ISO_DATE))
                .forEach(System.out::println);
    }
    
    @Test
    public void testRandomDaysWithinAMonth(){
        final LocalDate today = LocalDate.now();
        final LocalDate aMonthAgo = today.minus(1, ChronoUnit.MONTHS);
        final int daysBetween = (int) Duration.between(aMonthAgo.atTime(0, 0), today.atTime(0, 0)).toDays();
        
        assertTrue("Invalid month", daysBetween>=28 && daysBetween<=31);
        final SortedSet<LocalDate> dates = randomDaysBetween(10, aMonthAgo, today);
        System.out.println(String.format("Random days between %s and %s", aMonthAgo.format(DateTimeFormatter.ISO_DATE), today.format(DateTimeFormatter.ISO_DATE)));
        dates.stream()
                .map(date -> date.format(DateTimeFormatter.ISO_DATE))
                .forEach(System.out::println);
        
    }
    
    private static SortedSet<LocalDate> randomDaysBetween(int howMany, LocalDate start, LocalDate end){
        final int daysBetween = (int) Duration.between(start.atTime(0, 0), end.atTime(0, 0)).toDays();
        
        if(daysBetween<0 || daysBetween<howMany){
            final String exc = "Unable to get " + howMany + " days between "
                    + start.format(DateTimeFormatter.ISO_DATE) + " and "
                    + end.format(DateTimeFormatter.ISO_DATE);
            throw new IllegalArgumentException(exc);
        }
        
        final Random random = new Random();
        
        final SortedSet<LocalDate> days = new TreeSet<>();
        while (days.size() < howMany) {
            final int randomDay = random.nextInt((int) daysBetween);
            final LocalDate randomDate = start.plusDays(randomDay);
            if (!days.contains(randomDate)) {
                days.add(randomDate);
            }
        }
        return days;
    }
}
