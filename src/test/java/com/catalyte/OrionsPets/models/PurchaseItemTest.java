package com.catalyte.OrionsPets.models;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */
public class PurchaseItemTest {

    private PurchaseItem classToTest = new PurchaseItem("",1);

    @Test
    public void getterAndSettersHappyPaths() {
        classToTest.setPetId("");
        assertEquals("",classToTest.getPetId());
        classToTest.setPrice(1);
        assertTrue(1 == classToTest.getPrice());
    }

}
