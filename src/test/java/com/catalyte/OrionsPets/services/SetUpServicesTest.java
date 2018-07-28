package com.catalyte.OrionsPets.services;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;

import com.catalyte.OrionsPets.models.Customer;
import com.catalyte.OrionsPets.models.Inventory;
import com.catalyte.OrionsPets.models.Pet;
import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.repositories.CustomerRepository;
import com.catalyte.OrionsPets.repositories.InventoryRepository;
import com.catalyte.OrionsPets.repositories.PetRepository;
import com.catalyte.OrionsPets.repositories.PetTypeRepository;
import com.catalyte.OrionsPets.repositories.PurchaseRepository;
import com.catalyte.OrionsPets.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class SetUpServicesTest {

  @InjectMocks
  private SetUpServices classToTest;

  @Mock
  private CustomerRepository custRepoMock;

  @Mock
  private InventoryRepository invRepoMock;

  @Mock
  private PetRepository petRepoMock;

  @Mock
  private PetTypeRepository typeRepoMock;

  @Mock
  private PurchaseRepository purcRepoMock;

  @Mock
  private UserRepository userRepoMock;

  @Before
  public void before() {
    initMocks(this);
  }

  @Test
  public void clearDataHappyPath() {
    assertTrue(classToTest.clearDatabase());
  }


  //TODO this passes but does not hit the line of code i am testing
  @Test(expected = Exception.class)
  public void clearDataSadPath() throws Exception {
    doThrow(new Exception()).when(custRepoMock).deleteAll();
    assertFalse(classToTest.clearDatabase());
  }

  @Test
  public void createDummyDataHappyPath() {
    List list = new ArrayList();
    doReturn(list).when(custRepoMock).findAll();
    doReturn(list).when(invRepoMock).findAll();
    doReturn(list).when(petRepoMock).findAll();
    doReturn(list).when(typeRepoMock).findAll();
    doReturn(list).when(purcRepoMock).findAll();
    doReturn(list).when(userRepoMock).findAll();
    String dummyString = "DUMMY";
    PetType dummyType = new PetType(dummyString);
    dummyType.setId(dummyString);
    Inventory inventory = new Inventory();
    inventory.setPetTypeId(dummyString);
    doReturn(dummyType).when(typeRepoMock).findByType(any(String.class));
    doReturn(inventory).when(invRepoMock).findByPetTypeId(any(String.class));
    doReturn(new Pet()).when(petRepoMock).findOneById(any(String.class));
    doReturn(new Inventory()).when(invRepoMock).findByPetTypeId(null);
    assertTrue(classToTest.createDummyData());
  }

  @Test
  public void createDummyDataSadPath() {
    doReturn(Arrays.asList(new Pet())).when(petRepoMock).findAll();
    assertFalse(classToTest.createDummyData());
  }

}
