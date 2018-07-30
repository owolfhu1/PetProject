package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.services.SetUpServices;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.bind.annotation.RequestHeader;


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
    public void clearHappyPath() {
        doReturn(true).when(setUpServMock).clearDatabase("");
        assertEquals("Database cleared!",classToTest.clearDatabase(""));
    }

    @Test
    public void createHappyPath(){
        doReturn(1).when(setUpServMock).createDummyData("",100,100,100);
        assertEquals("Dummy data created", classToTest.createDummyData("",100,100,100));
    }

    @Test
    public void createSadPaths() {
        doReturn(-1).when(setUpServMock).createDummyData("",100,100,100);
        doReturn(-2).when(setUpServMock).createDummyData(SetUpServices.SUPER_SECRET_PASSWORD,100,100,100);
        assertEquals("Access denied",classToTest.createDummyData("",100,100,100));
        assertEquals("There is already data in the database, please clear it before proceeding."
                ,classToTest.createDummyData(SetUpServices.SUPER_SECRET_PASSWORD,100,100,100));
    }

    @Test
    public void createEmptyHappyPath(){
        doReturn(1).when(setUpServMock).createEmptyData("","","");
        assertEquals("Database setup complete", classToTest.createEmptyData("","",""));
    }

    @Test
    public void createEmptySadPath(){
        doReturn(-1).when(setUpServMock).createEmptyData("","","");
        doReturn(-2).when(setUpServMock).createEmptyData(SetUpServices.SUPER_SECRET_PASSWORD,"","");
        assertEquals("Access denied",classToTest.createEmptyData("","",""));
        assertEquals("There is already data in the database, please clear it before proceeding."
                ,classToTest.createEmptyData(SetUpServices.SUPER_SECRET_PASSWORD,"",""));
    }




}
