package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.resorces.DataNotFoundException;
import com.catalyte.OrionsPets.services.PetTypeServices;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Orion Wolf_Hubbard on 7/28/2018.
 */
public class PetTypeControllerTest {

    @InjectMocks
    private PetTypeController classToTest;

    @Mock
    private PetTypeServices typeServMock;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test
    public void searchHappyPath() throws DataNotFoundException {
        PetType type = new PetType();
        doReturn(type).when(typeServMock).search("","");
        assertEquals(type,classToTest.searchTypes("",""));
    }

    @Test
    public void findAllHappyPath() {
        doReturn(new ArrayList()).when(typeServMock).findAll();
        assertEquals(new ArrayList(), classToTest.findAll());
    }

}
