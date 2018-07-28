package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Inventory;
import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.services.AuthenticationServices;
import com.catalyte.OrionsPets.services.InventoryServices;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Orion Wolf_Hubbard on 7/26/2018.
 */
public class InventoryControllerTest {

    @InjectMocks
    private InventoryController classToTest;

    @Mock
    private InventoryServices invServMock;

    @Mock
    private AuthenticationServices authServMock;

    private String USER = "user";
    private String PASS = "pass";

    @Before
    public void before() {
        initMocks(this);
        doReturn(true).when(authServMock).authenticate(USER, PASS, "ADMIN");
    }

    @Test
    public void findAllHappyPath() {
        doReturn(new ArrayList()).when(invServMock).findAll();
        TestCase.assertEquals(new ArrayList(), classToTest.findAll());
    }

    @Test
    public void searchInventoriesHappyPath() throws Exception {
        Inventory expected = new Inventory();
        doReturn(expected).when(invServMock).searchInventoriesByPetType("dog");
        Inventory result = classToTest.searchInventoriesByType("dog");
        assertEquals(expected,result);
    }

    @Test
    public void createInventoryHappyPath() {
        doReturn(true).when(invServMock).createInventory(any(PetType.class),any(Double.class));
        String result = classToTest.createInventory(USER,PASS,"abc",1.99);
        assertEquals("Inventory created",result);
    }

    @Test
    public void deleteInventoryHappyPath() {
        doReturn(true).when(invServMock).deleteInventory("dog");
        String result = classToTest.deleteInventory(USER,PASS,"dog");
        assertEquals("Inventory deleted",result);
    }

}
