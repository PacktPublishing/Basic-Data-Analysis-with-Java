/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.section6;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Erik Costlow
 */
@XmlRootElement
public class XCat extends XAnimal{
    @Override
    public void speak() {
        System.out.println("Meow");
    }
}
