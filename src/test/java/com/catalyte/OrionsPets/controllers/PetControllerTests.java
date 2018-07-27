package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Pet;
import com.catalyte.OrionsPets.services.AuthenticationServices;
import com.catalyte.OrionsPets.services.PetServices;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

public class PetControllerTests {

    @InjectMocks
    PetController classToTest;

    @Mock
    PetServices petServMock;

    @Mock
    private AuthenticationServices authServMock;

    private String USER = "user";
    private String PASS = "pass";

    @Before
    public void before() {
        initMocks(this);
        doReturn(true).when(authServMock).authenticate(USER,PASS,"ADMIN");
    }

    @Test
    public void searchPetsHappyPath() {
        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet());
        doReturn(pets).when(petServMock).searchPets("type","value");
        List<Pet> result = classToTest.searchPets("type","value");
        assertEquals(pets,result);
    }

    @Test
    public void createPetHappyPath() {

    }

    @Test
    public void updatePetsHappyPath() {

    }

    @Test
    public void deletePetsHappyPath() {

    }




}
