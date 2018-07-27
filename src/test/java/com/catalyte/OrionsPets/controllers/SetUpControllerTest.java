package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.services.SetUpServices;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */
public class SetUpControllerTest {

    @InjectMocks
    SetUpController classToTest;

    @Mock
    SetUpServices setUpServMock;

    @Before
    public void before() {
        initMocks(this);
    }


    @Test
    public void clearHappyPath(){
        assertEquals("Database cleared!",classToTest.clearDatabase());
    }

    @Test
    public void createDummyDataHappyPath() {
        doReturn(true).when(setUpServMock).createDummyData();
        assertEquals("Dummy data created",classToTest.createDummyData());
    }


}
