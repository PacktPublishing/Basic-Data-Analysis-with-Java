/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.section6;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erik Costlow
 */
public class JZoo {
    private String name;
    
    private List<JAnimal> animals = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JAnimal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<JAnimal> animals) {
        this.animals = animals;
    }
}
