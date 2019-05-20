/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.section4;

import java.util.IntSummaryStatistics;
import java.util.Random;

/**
 *
 * @author Erik Costlow
 */
public class DescriptiveStatisticsExample {

    public static void main(String[] args) {
        final Random random = new Random();
        final int total = 100;
        final IntSummaryStatistics stats = random.ints(total, 0, 1000).parallel()
                //Peek is a nice way to look inside your stream,
                //but System.out.println serializes                
                //.peek(number -> System.out.println("Number " + number))
                .map(Integer::valueOf)
                .summaryStatistics();

        final double mean = stats.getAverage();
        final int max = stats.getMax();
        final int min = stats.getMin();
        final long count = stats.getCount();
        final long sum = stats.getSum();

        System.out.println("Descriptive Statistics:");
        System.out.println(format("Average", mean));
        System.out.println(format("Max", max));
        System.out.println(format("Min", min));
        System.out.println(format("Count", count));
        System.out.println(format("Sum", sum));
    }

    private static final String FORMAT = "  %s: %d";

    private static String format(String s, int num) {
        return String.format(FORMAT, s, num);
    }

    private static String format(String s, double num) {
        return String.format("  %s: %f", s, num);
    }

    private static String format(String s, long num) {
        return String.format(FORMAT, s, num);
    }
}
