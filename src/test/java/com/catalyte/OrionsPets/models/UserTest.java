package com.catalyte.OrionsPets.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */
public class UserTest {

    private User classToTest = new User();

    @Test
    public void getterAndSettersHappyPaths() {
        String[] roles = new String[0];
        classToTest.setId("");
        assertEquals("",classToTest.getId());
        classToTest.setUsername("");
        assertEquals("",classToTest.getUsername());
        classToTest.setPassword("");
        assertEquals("",classToTest.getPassword());
        classToTest.setRoles(roles);
        assertSame(roles, classToTest.getRoles());
    }

}
