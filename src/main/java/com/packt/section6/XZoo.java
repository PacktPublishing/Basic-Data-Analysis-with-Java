/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.section6;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Erik Costlow
 */
@XmlRootElement
public class XZoo {
    private String name;
    
    private List<XAnimal> animals = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElementWrapper(name = "xmlanimals")
    @XmlElementRef
    public List<XAnimal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<XAnimal> animals) {
        this.animals = animals;
    }
}
