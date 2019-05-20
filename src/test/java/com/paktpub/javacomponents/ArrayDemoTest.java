/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paktpub.javacomponents;

import java.util.Arrays;
import java.util.stream.Collectors;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author erikc_000
 */
public class ArrayDemoTest {
    @Test
    public void testArray(){
        final String alphabet="abcdefghijklmnopqrstuvwxyz";
        final String[] a = new String[]{"Pakt", "Publishing"};
        final String[] letters = new String[26];
        for(int i=0; i<alphabet.length();i++){
            letters[i] = String.format("%d %c", i, alphabet.charAt(i));
        }
        assertEquals("Wrong letter", "4 e", letters[4]);
        
        final String serial = Arrays.stream(letters)
                .collect(Collectors.joining(", "));
        final String parallel = Arrays.stream(letters).parallel()
                .collect(Collectors.joining(", "));
        System.out.println("S " + serial);
        System.out.println("P " + parallel);
        if(serial.equals(parallel)){
            System.out.println("Small sets with no processing lag are often serial anyways.");
        }
        final ArrayDemoTest[] demos = new ArrayDemoTest[]{this};
    }
}
