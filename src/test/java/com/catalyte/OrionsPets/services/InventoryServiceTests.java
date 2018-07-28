package com.catalyte.OrionsPets.services;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import com.catalyte.OrionsPets.models.Inventory;
import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.repositories.InventoryRepository;
import com.catalyte.OrionsPets.repositories.PetRepository;
import com.catalyte.OrionsPets.repositories.PetTypeRepository;
import com.catalyte.OrionsPets.resorces.DataNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

public class InventoryServiceTests {


  private InventoryServices classToTest;

  @Mock
  private InventoryRepository invRepoMock;
  @Mock
  private PetTypeRepository typeRepoMock;
  @Mock
  private PetRepository petRepoMock;

  @Before
  public void before() {
    initMocks(this);
    classToTest = new InventoryServices(invRepoMock,typeRepoMock,petRepoMock);
  }

  @Test(expected = DataNotFoundException.class)
  public void searchInventoriesSadPath() throws Exception {
    doReturn(false).when(typeRepoMock).existsByType("type");
    classToTest.searchInventoriesByPetType("type");
  }

  @Test
  public void searchInventoriesHappyPath() throws DataNotFoundException{
    PetType petType = new PetType();
    petType.setId("");
    Inventory inventory = new Inventory();
    doReturn(true).when(typeRepoMock).existsByType("");
    doReturn(petType).when(typeRepoMock).findByType("");
    doReturn(inventory).when(invRepoMock).findByPetTypeId("");
    assertEquals(inventory,classToTest.searchInventoriesByPetType(""));

  }

  @Test
  public void findAllHappyPath() {
    doReturn(new ArrayList()).when(invRepoMock).findAll();
    assertEquals(new ArrayList(), classToTest.findAll());
  }

  @Test
  public void createInventoryHappyPath() {
    PetType petType = new PetType("test");
    petType.setId("id");
    doReturn(false).when(typeRepoMock).existsByType(petType.getType());
    doReturn(petType).when(typeRepoMock).save(petType);
    boolean result = classToTest.createInventory(petType, 1.5);
    assertTrue(result);
  }


  @Test
  public void deleteInventoryHappyPath() {
    doReturn(true).when(typeRepoMock).existsByType("type");
    doReturn(new PetType()).when(typeRepoMock).findByType("type");
    boolean result = classToTest.deleteInventory("type");
    assertTrue(result);
  }


}
