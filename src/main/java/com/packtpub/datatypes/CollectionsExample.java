/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packtpub.datatypes;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Erik Costlow
 */
public class CollectionsExample {
    public static void main(String[] args){
        final POJO person = new POJO();
        person.setName("Someone");
        
        final POJO secondPerson = new POJO();
        secondPerson.setName("Another");
        person.getFriends().add(secondPerson);
        
        final Set<POJO> friends = person.getFriends();
        whyUseDefensiveCollections(person.getFriends());
        
        friends.forEach(System.out::println);
    }

    //It's just confusing the the called method changes your collection.
    private static void whyUseDefensiveCollections(Set friends) {
        try {
            final JAXBContext context = JAXBContext.newInstance(POJO.class);
            friends.add(context);
            System.out.println("It's in there.");
        } catch (JAXBException ex) {
            Logger.getLogger(CollectionsExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
