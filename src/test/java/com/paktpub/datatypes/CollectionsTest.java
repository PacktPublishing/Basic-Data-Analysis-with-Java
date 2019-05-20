/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paktpub.datatypes;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author erikc_000
 */
public class CollectionsTest {
    
    @Test
    public void testList(){
        final Person a=new Person("Andy", "Alligator");
        final Person b=new Person("Bill", "Bear");
        final Person c=new Person("Charlie", "Crocodile");
        
        final List<Person> people = new ArrayList<>();
        people.add(a);
        people.add(b);
        people.add(c);
        
        final Person first = people.get(0);
        assertEquals("Incorrect order", a, first);
    }
}
