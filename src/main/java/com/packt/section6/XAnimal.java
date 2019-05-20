/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.section6;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author erikc_000
 */
@XmlRootElement
@XmlSeeAlso(value =
        {XCat.class, XDog.class})
public abstract class XAnimal {
    
    public abstract void speak();
}
