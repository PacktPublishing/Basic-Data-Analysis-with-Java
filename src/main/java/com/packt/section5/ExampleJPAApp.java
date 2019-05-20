/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.section5;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author erikc_000
 */
public class ExampleJPAApp {
    private static final String NAME = "My name";
    
    public static void main(String[] args){
        try{
            useJPA();
        }catch(Exception e){
            System.err.println("Catch-all to close: " + e.getMessage());
        }
    }
    
    private static void useJPA(){
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.packtpub.section5");
        final EntityManager em = emf.createEntityManager();
        store(em);
        
        retrieve(em);
        em.close();
        emf.close();
    }

    private static void store(EntityManager em) {
        final ExampleEntity myObject = new ExampleEntity();
        System.out.println("Before persistence, the ID is " + myObject.getId());
        myObject.setName(NAME);
        myObject.setOtherProperty(String.valueOf(LocalDate.now()));
        em.setFlushMode(FlushModeType.AUTO);
        final EntityTransaction t = em.getTransaction();
        t.begin();
        System.out.println("Storing the object");
        em.persist(myObject);
        t.commit();
        System.out.println("After persistence, the ID is " + myObject.getId());
    }
    
    private static void retrieve(EntityManager em) {
        final TypedQuery<ExampleEntity> q = em.createNamedQuery("ExampleEntity.byName", ExampleEntity.class);
        q.setParameter("name", NAME);
        final ExampleEntity obj = q.getSingleResult();
        System.out.println("Other property is " + obj.getOtherProperty());
    }
}
