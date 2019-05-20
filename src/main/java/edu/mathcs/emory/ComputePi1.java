/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mathcs.emory;

/**
 * http://www.mathcs.emory.edu/~cheung/Courses/170/Syllabus/07/compute-pi.html
 *
 * @author Emory University Math Department
 */
public class ComputePi1{

    public static void main(String[] args) {
        int nThrows = 0;
        int nSuccess = 0;

        double x, y;
        //PACKT NOTE: There are a million data points below, so don't visualize.
        for (int i = 0; i < 1000000; i++) {
            x = Math.random();      // Throw a dart			   
            y = Math.random();
            nThrows++;

            if (x * x + y * y <= 1) {
                nSuccess++;
            }
        }
        
        final double guessed = 4 * (double) nSuccess / (double) nThrows;
        System.out.println("Pi/4 = " + (double) nSuccess / (double) nThrows);
        System.out.println("Pi = " + guessed);
        System.out.println("Actual Pi is " + Math.PI);
        System.out.println(String.format("  Difference is %f%%", ((1 - guessed / Math.PI) * 100)));
    }
}
