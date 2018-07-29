package com.catalyte.OrionsPets.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */
public class PetTest {

    private Pet classToTest = new Pet();

    @Test
    public void getterAndSettersHappyPaths() {
        classToTest.setId("");
        assertEquals("",classToTest.getId());
        classToTest.setPetType("");
        assertEquals("",classToTest.getPetType());
        classToTest.setSex("");
        assertEquals("",classToTest.getSex());
        classToTest.setName("");
        assertEquals("",classToTest.getName());
        classToTest.setSold(true);
        assertTrue(classToTest.isSold());
        classToTest.setColor("");
        assertEquals("",classToTest.getColor());
        classToTest.setAge(1);
        assertTrue(1 == classToTest.getAge());
    }

}
