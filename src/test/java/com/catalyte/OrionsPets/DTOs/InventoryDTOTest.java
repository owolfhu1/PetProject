package com.catalyte.OrionsPets.DTOs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.catalyte.OrionsPets.models.Inventory;
import org.junit.Test;

public class InventoryDTOTest {

  Inventory inventory = new Inventory();
  InventoryDTO classToTest = new InventoryDTO(inventory);

  @Test
  public void happyInventoryDtoTest() {
    classToTest.addInventory(1);
    assertEquals(inventory.getCount(), 1);
  }

  @Test
  public void sadInventoryDtoTest() {
    classToTest.addInventory(100);
    assertFalse(inventory.getCount() == 0);
  }

}
