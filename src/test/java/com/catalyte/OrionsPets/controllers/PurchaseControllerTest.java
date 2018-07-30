package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Purchase;
import com.catalyte.OrionsPets.services.AuthServices;
import com.catalyte.OrionsPets.services.PurchaseServices;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
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
public class PurchaseControllerTest {

  @InjectMocks
  private PurchaseController classToTest;

  @Mock
  private PurchaseServices purchServMock;

  @Mock
  private AuthServices authServMock;

  private String USER = "user";
  private String PASS = "pass";
  private String dummyId = "DUMMY_ID";

  @Before
  public void before() {
    initMocks(this);
    doReturn(true).when(authServMock).authenticate(USER, PASS, "ADMIN");
  }

  @Test
  public void findAllHappyPath() {
    doReturn(new ArrayList()).when(purchServMock).findAll();
    TestCase.assertEquals(new ArrayList(), classToTest.findAll());
  }

  @Test
  public void searchPurchasesHappyPath() {
    List<Purchase> expected = new ArrayList<>();
    doReturn(expected).when(purchServMock).searchPurchases("id");
    List<Purchase> result = classToTest.searchPurchasesByCustomerId("id");
    assertEquals(expected, result);

  }

  @Test
  public void createPurchaseHappyPath() {
    String[] items = new String[1];
    items[0] = dummyId;
    doReturn(dummyId).when(purchServMock).createPurchase(dummyId,items);
    String result = classToTest.createPurchase(USER,PASS,dummyId,items);
    assertEquals(dummyId,result);
  }
  @Test
  public void createPurchaseSadPath() {
    String[] items = new String[1];
    items[0] = dummyId;
    doReturn(dummyId).when(purchServMock).createPurchase(dummyId,items);
    String result = classToTest.createPurchase(USER,"",dummyId,items);
    assertEquals("Not authorized",result);
  }

  @Test
  public void returnPetHappyPath() {
    doReturn(dummyId).when(purchServMock).returnPet(dummyId,dummyId);
    String result = classToTest.returnPet(USER,PASS,dummyId,dummyId);
    assertEquals(dummyId,result);
  }

  @Test
  public void deletePurchaseHappyPath() {
    doReturn(dummyId).when(purchServMock).deletePurchase(dummyId);
    String result = classToTest.deletePurchase(USER,PASS,dummyId);
    assertEquals(dummyId,result);
  }

}
