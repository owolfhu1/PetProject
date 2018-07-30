package com.catalyte.OrionsPets.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */
public class CustomerTest {

    private Customer classToTest = new Customer();

    @Test
    public void getterAndSettersHappyPaths() {
        classToTest.setId("");
        assertEquals("",classToTest.getId());
        classToTest.setAddress("");
        assertEquals("",classToTest.getAddress());
        classToTest.setFirstname("");
        assertEquals("",classToTest.getFirstname());
        classToTest.setLastname("");
        assertEquals("",classToTest.getLastname());
        classToTest.setPhone("");
        assertEquals("",classToTest.getPhone());
        classToTest.setEmail("");
        assertEquals("",classToTest.getEmail());
    }

}
