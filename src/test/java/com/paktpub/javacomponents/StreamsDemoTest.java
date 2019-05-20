/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paktpub.javacomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

/**
 *
 * @author erikc_000
 */
public class StreamsDemoTest {
    @Test
    public void testStream(){
        final List<Number> numbers = randomList();
        assertFalse("List should have something", numbers.isEmpty());
        System.out.println(String.format("There are %d numbers", numbers.size()));
        
        final int maxCheck = 100;
        final long numbersBelowMax = numbers.parallelStream()
                .mapToInt(num -> num.intValue()) //The collection is object Number, so we need to map into primitive
                .filter(num -> num < maxCheck).count();
        assertFalse("Should be at least one under", numbersBelowMax==0);
        System.out.println(String.format("  %d of those numbers are below %d", numbersBelowMax, maxCheck));
        
        //The folling code does nothing, but use your IDE's ctrl+enter to
        //automatically convert to lambda
        for(Number number : numbers){
            ignore(number);
        }
        numbers.forEach((number) -> {
            ignore(number);
        });
    }

    private List<Number> randomList() {
        final Random random = new Random();
        final int size = 1+random.nextInt(10000);
        final List<Number> retval = new ArrayList<>(size);
        for(int i=0; i<size; i++){
            retval.add(random.nextInt());
        }
        return retval;
    }
    
    private static void ignore(Object obj){};
}
