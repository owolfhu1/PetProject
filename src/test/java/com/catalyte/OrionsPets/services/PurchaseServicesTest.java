package com.catalyte.OrionsPets.services;

import com.catalyte.OrionsPets.DTOs.PurchaseDTO;
import com.catalyte.OrionsPets.models.Inventory;
import com.catalyte.OrionsPets.models.Pet;
import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.models.Purchase;
import com.catalyte.OrionsPets.models.PurchaseItem;
import com.catalyte.OrionsPets.repositories.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */
public class PurchaseServicesTest {

    @InjectMocks
    private PurchaseServices classToTest;

    @Mock
    private PurchaseRepository purcRepoMock;

    @Mock
    private PetRepository petRepoMock;

    @Mock
    private InventoryRepository invRepoMock;

    @Mock
    private CustomerRepository custRepoMock;


    @Before
    public void before() {
        initMocks(this);
    }

    @Test public void searchPurchasesHappyTest(){
        List<Purchase> expected = new ArrayList<>();
        doReturn(expected).when(purcRepoMock).findByCustomerId("id");
        List<Purchase> result = classToTest.searchPurchases("id");
        assertEquals(expected,result);
    }

    @Test
    public void createPurchaseHappyPath(){
        String dummyId = "DUMMY_ID";
        String[] petIds = new String[1];
        petIds[0] = dummyId;
        Pet pet = new Pet();
        pet.setPetTypeId(dummyId);
        Inventory inventory = new Inventory();
        inventory.setPetTypeId(dummyId);
        doReturn(true).when(custRepoMock).existsById(dummyId);
        doReturn(true).when(petRepoMock).existsById(dummyId);
        doReturn(pet).when(petRepoMock).findOneById(dummyId);
        doReturn(inventory).when(invRepoMock).findByPetTypeId(dummyId);
        String result = classToTest.createPurchase(dummyId,petIds);
        assertEquals("Purchase created",result);
    }

    @Test
    public void createPurchaseSadPaths() {
        String[] petIds = new String[1];
        petIds[0] = "";
        Pet pet = new Pet();
        pet.setSold(true);
        doReturn(pet).when(petRepoMock).findOneById("");
        assertEquals("Bad customerId",classToTest.createPurchase("",petIds));
        doReturn(true).when(custRepoMock).existsById("");
        assertEquals(" is a bad petId",classToTest.createPurchase("",petIds));
        doReturn(true).when(petRepoMock).existsById("");
        assertEquals("Pet with  is already sold",classToTest.createPurchase("",petIds));
    }

    @Test
    public void returnPetHappyPath(){
        String dummyId = "DUMMY_ID";
        Purchase purchase =  new Purchase();
        purchase.setId(dummyId);
        PurchaseDTO dto = new PurchaseDTO(purchase);
        dto.addItem(new PurchaseItem(dummyId,1.99));
        doReturn(true).when(purcRepoMock).existsById(dummyId);
        doReturn(true).when(petRepoMock).existsById(dummyId);
        doReturn(purchase).when(purcRepoMock).findOneById(dummyId);
        doReturn(new Pet()).when(petRepoMock).findOneById(dummyId);
        String result = classToTest.returnPet(dummyId,dummyId);
        assertEquals(result,"Pet returned");

    }

    @Test
    public void returnPetSadPaths() {
        assertEquals("bad purchaseId",classToTest.returnPet("",""));

        doReturn(true).when(purcRepoMock).existsById("");
        assertEquals("bad petId",classToTest.returnPet("",""));
    }


    @Test
    public void deletePurchaseHappyPath(){
        String dummyId = "DUMMY_ID";
        Purchase purchase = new Purchase();
        purchase.setId(dummyId);
        PurchaseItem item = new PurchaseItem(dummyId,1.99);
        PurchaseDTO dto = new PurchaseDTO(purchase);
        dto.addItem(item);
        doReturn(true).when(purcRepoMock).existsById(dummyId);
        doReturn(purchase).when(purcRepoMock).findOneById(dummyId);
        String result = classToTest.deletePurchase(dummyId);
        assertEquals("Purchase deleted", result);
    }

    @Test
    public void deletePurchaseSadPAth(){
        assertEquals("Purchase does not exist", classToTest.deletePurchase(""));
    }


}
