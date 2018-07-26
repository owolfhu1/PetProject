package com.catalyte.OrionsPets.services;

import com.catalyte.OrionsPets.models.Pet;
import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.repositories.PetRepository;
import com.catalyte.OrionsPets.repositories.PetTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Orion Wolf_Hubbard on 7/25/2018.
 */
public class PetServiceTest {

    private PetService classToTest;

    @Mock
    private AuthenticationService authServMock;
    @Mock
    private PetTypeRepository typeRepoMock;
    @Mock
    private PetRepository petRepoMock;

    @Before
    public void before() {
        initMocks(this);
        classToTest = new PetService(authServMock, typeRepoMock, petRepoMock);
    }

    @Test
    public void validateHappyPath() {
        Pet pet = new Pet("petTypeId","name",1,"color","sex");
        String type = "dog";
        PetType petType = new PetType("dog");
        petType.setId("DUMMY_ID_STRING_1234");
        doReturn(true).when(typeRepoMock).existsByType(type);
        doReturn(petType).when(typeRepoMock).findByType(type);
        String result = classToTest.validatePet(pet,type);
        assertEquals(result, "");
    }

    @Test
    public void validateSadPath() {
        Pet pet = new Pet("", "dd", -1, "blue", "male");
        String type = "";
        PetType petType = new PetType("");
        petType.setId("");
        doReturn(true).when(typeRepoMock).existsByType(type);
        doReturn(petType).when(typeRepoMock).findByType(type);
        String result = classToTest.validatePet(pet, type);
        System.out.println("result: '" + result + "'");
        assertNotEquals(result, "");
    }

}




