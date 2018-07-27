package com.catalyte.OrionsPets.DTOs;

import static junit.framework.TestCase.*;

import com.catalyte.OrionsPets.models.Purchase;
import com.catalyte.OrionsPets.models.PurchaseItem;
import org.junit.Test;

public class PurchaseDTOTest {

  Purchase purchase = new Purchase();
  PurchaseDTO classToTest = new PurchaseDTO(purchase);

//  @Test
//  public void HappyPath() {
//    PurchaseItem item = new PurchaseItem("test",1);
//    classToTest.addItem(item);
//    assertEquals(item, purchase.getItems()[0]);
//  }

  @Test
  public void addAndRemoveItemTest() {
    PurchaseItem item = new PurchaseItem("test",1);
    PurchaseItem item2 = new PurchaseItem("other",1);
    classToTest.addItem(item);
    classToTest.addItem(item2);
    boolean result = classToTest.removeItem("test");
    boolean result2 = classToTest.removeItem("test");
    assertTrue(result);
    assertFalse(result2);
  }

}
