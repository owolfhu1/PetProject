package com.catalyte.OrionsPets.services;


import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.repositories.PetTypeRepository;
import com.catalyte.OrionsPets.resorces.DataNotFoundException;
import junit.framework.TestCase;
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
public class PetTypeServiceTest {

    @InjectMocks
    private PetTypeServices classToTest;

    @Mock
    private PetTypeRepository typeRepoMock;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test
    public void findAllHappyPath() {
        doReturn(new ArrayList()).when(typeRepoMock).findAll();
        TestCase.assertEquals(new ArrayList(), classToTest.findAll());
    }

    @Test
    public void searchHappyPaths() throws DataNotFoundException {
        PetType petType = new PetType();
        doReturn(true).when(typeRepoMock).existsByType("");
        doReturn(true).when(typeRepoMock).existsById("");
        doReturn(petType).when(typeRepoMock).findByType("");
        doReturn(petType).when(typeRepoMock).findOneById("");
        assertEquals(petType,classToTest.search("type",""));
        assertEquals(petType,classToTest.search("id",""));
    }

    @Test(expected = DataNotFoundException.class)
    public void searchBadCategorySadPaths() throws DataNotFoundException {
        classToTest.search("","");
    }

    @Test(expected = DataNotFoundException.class)
    public void searchBadTypeSadPaths() throws DataNotFoundException {
        doReturn(false).when(typeRepoMock).existsByType("");
        classToTest.search("type","");
    }

    @Test(expected = DataNotFoundException.class)
    public void searchBadIdSadPaths() throws DataNotFoundException {
        doReturn(false).when(typeRepoMock).existsById("");
        classToTest.search("id","");
    }
}
