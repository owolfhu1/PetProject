package com.catalyte.OrionsPets.DTOs;

import com.catalyte.OrionsPets.models.Inventory;

public class InventoryDTO {

  private Inventory inventory;

  public InventoryDTO(Inventory inventory) {
    this.inventory = inventory;
  }

  public void addInventory(int number) {
    inventory.setCount(inventory.getCount() + number);
  }

}
