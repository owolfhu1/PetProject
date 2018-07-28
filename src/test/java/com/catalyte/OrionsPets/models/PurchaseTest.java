package com.catalyte.OrionsPets.models;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */
public class PurchaseTest {

    private Purchase classToTest = new Purchase();

    @Test
    public void getterAndSettersHappyPaths() {

        Date date = new Date();
        PurchaseItem[] items = new PurchaseItem[0];
        classToTest.setId("");
        assertEquals("",classToTest.getId());
        classToTest.setCustomerId("");
        assertEquals("",classToTest.getCustomerId());
        classToTest.setDate(date);
        assertEquals(date,classToTest.getDate());
        classToTest.setTotalPrice(1);
        assertTrue(1 == classToTest.getTotalPrice());
        classToTest.setItems(items);
        assertSame(items, classToTest.getItems());
    }
}
