/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.section4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

/**
 *
 * @author Erik Costlow
 */
public class ElementaryProbability {

    public static void main(String[] args) {
        problem2();
        System.out.println();
        problem5();
    }

    private static void problem2() {
        //Use Map with keyed suffix solution
        final Map<String, Integer> numbers = new HashMap<>();
        numbers.put("opera_new", 1);
        numbers.put("opera_old", 4 - 1);
        numbers.put("default_new", 30);
        numbers.put("default_old", 139 - 30);

        final Map<String, Integer> combined = numbers.entrySet().stream()
                .filter(p -> p.getValue() > 0)
                .collect(
                        Collectors.groupingBy(
                                entry -> suffix(entry.getKey()),
                                Collectors.summingInt(entry -> entry.getValue())
                        )
                );
        System.out.println(combined);
        
        final double twoA = combined.get("new") /
                numbers.values().stream().mapToDouble(i -> i).sum();
        System.out.println("2a is " + twoA);
        final double twoB = 0.0 + numbers.get("default_new")/Double.valueOf(combined.get("new"));
        System.out.println("2b is " + twoB);
    }

    private static String suffix(String browser) {
        final String retval = browser.substring(browser.lastIndexOf("_") + 1);
        return retval;
    }

    private static void problem5() {
        final double[] x = new double[]{0,1,2};
        final double[] y = new double[]{0,1,3};
        
        final double xBar = Arrays.stream(x).average().getAsDouble();
        final double yBar = Arrays.stream(y).average().getAsDouble();
        
        System.out.println(String.format("Answer to 5a is %f and 5b is %f", xBar, yBar));

        final double sX = s(x, xBar);
        final double sY = s(y, yBar);
        
        System.out.println("5c is " + sX);
        System.out.println("5d is " + sY);
        
        final double correlation = new PearsonsCorrelation().correlation(x, y);
        System.out.println(String.format("Answer to 5e is %f", correlation));
        
        //Problem goes on like this. Lots of details at
        //https://commons.apache.org/proper/commons-math/userguide/stat.html
    }
    
    private static double s(double[] coords, double avg){
        final double topSX = Arrays.stream(coords).map(num -> Math.pow(num-avg, 2)).sum();
        final double retval = Math.sqrt(topSX/2);
        return retval;
    }
}
