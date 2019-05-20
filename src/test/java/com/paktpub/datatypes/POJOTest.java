/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paktpub.datatypes;

import com.packtpub.datatypes.POJO;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.layouters.CurrentLayouter;
import org.openjdk.jol.layouters.Layouter;
import org.openjdk.jol.vm.VM;

/**
 *
 * @author Erik Costlow
 */
public class POJOTest {
    @Test
    public void testObjectSize(){
        final String details = VM.current().details();
        System.out.println(details);
        
        Layouter layout = new CurrentLayouter();
        final ClassLayout pojoLayout = ClassLayout.parseClass(POJO.class, layout);
        System.out.println(pojoLayout.toPrintable());
        System.out.println("---");
        final long instanceSize = pojoLayout.instanceSize();
        System.out.println("Instance size is " + instanceSize);
        final int STAY_BELOW_BYTES=64;
        assertTrue("POJO is getting too big.", instanceSize<STAY_BELOW_BYTES);
    }
}
