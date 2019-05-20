/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.section6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author erikc_000
 */
public class DataFormatApp {
    private static final JAXBContext CONTEXT;
    
    static{
        JAXBContext ctx=null;
        try {
            ctx = JAXBContext.newInstance(XZoo.class);
        } catch (JAXBException ex) {
            Logger.getLogger(DataFormatApp.class.getName()).log(Level.SEVERE, "Unable to initialze context", ex);
        }
        
        CONTEXT=ctx;
    }
    
    public static void main(String[] args) throws JAXBException, JsonProcessingException, IOException{
        xml();
        System.out.println("---");
        json();
    }
    
    private static void xml() throws JAXBException{
        final XCat cat = new XCat();
        final XDog dog = new XDog();
        
        final XZoo zoo = new XZoo();
        zoo.getAnimals().add(dog);
        zoo.getAnimals().add(cat);
        
        final Marshaller m = CONTEXT.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            m.marshal(zoo, bout);
        } catch (JAXBException ex) {
            Logger.getLogger(DataFormatApp.class.getName()).log(Level.SEVERE, "Unable to make XML", ex);
        }
        
        final String s = bout.toString();
        System.out.println("XML output:");
        System.out.println(s);
        
        final Unmarshaller u = CONTEXT.createUnmarshaller();
        final XZoo readBack = (XZoo) u.unmarshal(new StringReader(s));
        readBack.getAnimals().forEach(XAnimal::speak);
    }
    
    private static void json() throws JAXBException, JsonProcessingException, IOException{
        final JCat cat = new JCat();
        final JDog dog = new JDog();
        
        final JZoo zoo = new JZoo();
        zoo.getAnimals().add(dog);
        zoo.getAnimals().add(cat);
        
        final ObjectMapper mapper = new ObjectMapper();
        final String s = mapper.writeValueAsString(zoo);
        
        System.out.println("JSON");
        System.out.println(s);
        
        final JZoo readBack = mapper.readValue(s, JZoo.class);
        readBack.getAnimals().forEach(JAnimal::speak);
    }
}
