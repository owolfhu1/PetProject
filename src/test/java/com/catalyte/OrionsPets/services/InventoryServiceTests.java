package com.catalyte.OrionsPets.services;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

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
