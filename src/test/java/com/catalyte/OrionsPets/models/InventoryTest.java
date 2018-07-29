package com.catalyte.OrionsPets.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */
public class InventoryTest {

    private Inventory classToTest = new Inventory();

    @Test
    public void getterAndSettersHappyPaths() {
        classToTest.setId("");
        assertEquals("",classToTest.getId());
        classToTest.setCount(1);
        assertEquals(1,classToTest.getCount());
        classToTest.setPetType("");
        assertEquals("",classToTest.getPetType());
        classToTest.setPrice(1);
        assertTrue(1 == classToTest.getPrice());
    }

}
