package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.services.AuthServices;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Orion Wolf_Hubbard on 7/29/2018.
 */
public class AuthControllerTests {

    @InjectMocks
    AuthController classToTest;

    @Mock
    AuthServices authServMock;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test
    public void addUserHappyPath() {
        doReturn(true).when(authServMock).authenticate("","","ADMIN");
        doReturn("return").when(authServMock).addUser("","","");
        assertEquals("return",classToTest.addUser("","","","",""));
    }

    @Test
    public void addUserSadPath() {
        assertEquals("Access denied",classToTest.addUser("","","","",""));
    }

    @Test
    public void deleteUserHappyPath() {
        doReturn(true).when(authServMock).authenticate("","","ADMIN");
        doReturn("return").when(authServMock).deleteUser("");
        assertEquals("return",classToTest.deleteUser("","",""));
    }

    @Test
    public void deleteUserSadPath() {
        assertEquals("Access denied",classToTest.deleteUser("","",""));
    }

    @Test
    public void findAllHappyPath() {
        doReturn(new ArrayList()).when(authServMock).findAll();
        assertEquals(new ArrayList(), classToTest.findAll());
    }

}
