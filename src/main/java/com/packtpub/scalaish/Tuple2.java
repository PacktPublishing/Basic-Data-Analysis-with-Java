/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packtpub.scalaish;

/**
 * Java class like Scala's tuple
 * (http://www.scala-lang.org/api/2.10.4/scala/Tuple2.html) See
 * https://msdn.microsoft.com/en-us/library/system.tuple.aspx for a good
 * explanation about what a Tuple is.
 *
 * @author Erik Costlow
 */
public class Tuple2<A, B> {

    private A a;

    private B b;

    public Tuple2() {

    }

    public Tuple2(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

}
