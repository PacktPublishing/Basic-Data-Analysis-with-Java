/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.section6;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

/**
 *
 * @author Erik Costlow
 */
@JsonTypeInfo(
        use=JsonTypeInfo.Id.NAME,
        include=As.PROPERTY, property="jsontype")
  @JsonSubTypes({
        @JsonSubTypes.Type(value=JDog.class, name="dog"),
        @JsonSubTypes.Type(value=JCat.class, name="cat")
})
public abstract class JAnimal {
    public abstract void speak();
}
