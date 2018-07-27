package com.catalyte.OrionsPets.services;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import com.catalyte.OrionsPets.models.Inventory;
import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.repositories.CustomerRepository;
import com.catalyte.OrionsPets.repositories.InventoryRepository;
import com.catalyte.OrionsPets.repositories.PetRepository;
import com.catalyte.OrionsPets.repositories.PetTypeRepository;
import com.catalyte.OrionsPets.repositories.PurchaseRepository;
import com.catalyte.OrionsPets.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class SetUpServicesTest {

  @InjectMocks
  private SetUpServices classToTest;

  @Mock
  private CustomerRepository customerRepository;

  @Mock
  private InventoryRepository mockInventoryRepository;

  @Mock
  private PetRepository petRepository;

  @Mock
  private PetTypeRepository petTypeRepository;

  @Mock
  private PurchaseRepository purchaseRepository;

  @Mock
  private UserRepository userRepository;

  @Before
  public void before() {
    initMocks(this);
  }

  @Test
  public void clearDataHappyPath() {
    assertTrue(classToTest.clearDatabase());
  }

  @Test
  public void createDummyDataHappyPath() {
    List list = new ArrayList();
    doReturn(list).when(customerRepository).findAll();
    doReturn(list).when(mockInventoryRepository).findAll();
    doReturn(list).when(petRepository).findAll();
    doReturn(list).when(petTypeRepository).findAll();
    doReturn(list).when(purchaseRepository).findAll();
    doReturn(list).when(userRepository).findAll();
    String dummyString = "DUMMY";
    PetType dummyType = new PetType(dummyString);
    Inventory inventory = new Inventory();
    inventory.setPetTypeId(dummyString);
    doReturn(dummyType).when(petTypeRepository).findByType(any(String.class));
    doReturn(inventory).when(mockInventoryRepository).findByPetTypeId(any(String.class));



    assertTrue(classToTest.createDummyData());
  }

}
