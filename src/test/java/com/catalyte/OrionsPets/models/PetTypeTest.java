package com.catalyte.OrionsPets.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */
public class PetTypeTest {

    private PetType classToTest = new PetType();

    @Test
    public void getterAndSettersHappyPaths() {
        classToTest.setId("");
        assertEquals("",classToTest.getId());
        classToTest.setType("");
        assertEquals("",classToTest.getType());
    }
}
