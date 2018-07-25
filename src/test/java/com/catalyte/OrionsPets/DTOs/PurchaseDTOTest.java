package com.catalyte.OrionsPets.DTOs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.catalyte.OrionsPets.models.Purchase;
import com.catalyte.OrionsPets.models.PurchaseItem;
import org.junit.Test;

public class PurchaseDTOTest {

  Purchase purchase = new Purchase();
  PurchaseDTO classToTest = new PurchaseDTO(purchase);

  @Test
  public void happyPurchaseDtoTest() {
    PurchaseItem item = new PurchaseItem("test",1);
    classToTest.addItem(item);
    assertEquals(item, purchase.getItems()[0]);
  }

  @Test
  public void sadPurchaseDtoTest() {
    PurchaseItem item = new PurchaseItem("test",1);
    classToTest.addItem(item);
    assertNotEquals(0, purchase.getItems().length);
  }

}
