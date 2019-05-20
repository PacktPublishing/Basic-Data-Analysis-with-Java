/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paktpub.datatypes;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Erik Costlow
 */
public class CountingThreadProblemTest {

    private int counter;

    private ExecutorService executor;
    
    @Before
    public void before(){
        executor = Executors.newCachedThreadPool(r -> {
            final Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });
    }
    
    @After
    public void after(){
        executor.shutdown();
    }

    /**
     * This test intentionally fails due to a multi-threading problem with
     * the increment++ operator.
     * @throws InterruptedException 
     */
    //@Test //uncomment to watch it fail.
    public void testIncrementIncorrectly() throws InterruptedException {
        final int expectedCount=1000;
        for (int i = 0; i < expectedCount; i++) {
            final int threadNumber=i;
            final Runnable r = () -> {
                Random random = new Random();
                try {
                    Thread.sleep(random.nextInt(25));
                } catch (InterruptedException ex) {
                    Logger.getLogger(CountingThreadProblemTest.class.getName()).log(Level.SEVERE, "Unable to sleep", ex);
                }
                counter++;
                System.out.println("Incorrect counter thread " + threadNumber + " has " + counter);
            };
            executor.submit(r);
        }
        
        System.out.println("A little delay for the threads...");
        Thread.sleep(10000);

        assertEquals(expectedCount, counter);
    }
    
    /**
     * This test uses thread-safe counters
     * @throws InterruptedException 
     */
    @Test
    public void testIncrementCorrectly() throws InterruptedException {
        final int expectedCount=1000;
        final AtomicInteger intCounter = new AtomicInteger();
        for (int i = 0; i < expectedCount; i++) {
            final int threadNumber=i;
            final Runnable r = () -> {
                Random random = new Random();
                try {
                    Thread.sleep(random.nextInt(25));
                } catch (InterruptedException ex) {
                    Logger.getLogger(CountingThreadProblemTest.class.getName()).log(Level.SEVERE, "Unable to sleep", ex);
                }
                intCounter.incrementAndGet();
                System.out.println("Correct counter thread " + threadNumber + " has " + intCounter.get());
            };
            executor.submit(r);
        }
        
        System.out.println("A little delay for the threads...");
        Thread.sleep(10000);

        assertEquals(expectedCount, intCounter.get());
    }

}
