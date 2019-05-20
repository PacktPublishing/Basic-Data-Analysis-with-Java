/*
 * Copyright Erik Costlow.
 * Not authorized for use or view by others.
 */
package com.packtpub.numeric;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Erik Costlow
 */
public class StreamsBasic {
    public static void main(String[] args){
        final List<Integer> numbers = new ArrayList<>();
        final Random random = new Random();
        for(int i=1; i<10000; i++){
            numbers.add(random.nextInt(1000));
        }
        
        //Single threaded
        double sum=0;
        for(Integer i : numbers){
            sum+=i;
        }
        final double singleDouble = sum/numbers.size();
        System.out.println("Average is " + singleDouble);
        
        final double d = numbers.parallelStream()
                .mapToInt(Integer::valueOf)
                .average().getAsDouble();
        System.out.println("Average is " + d);
        
        final IntSummaryStatistics stats = numbers.parallelStream()
                .mapToInt(Integer::valueOf)
                .summaryStatistics();
        
        int min = stats.getMin();
        int max = stats.getMax();
        double average = stats.getAverage();
        long statsSum = stats.getSum();
        System.out.println(String.format("Between %d and %d with average %f and sum %d", min, max, average, statsSum));
    }
}
