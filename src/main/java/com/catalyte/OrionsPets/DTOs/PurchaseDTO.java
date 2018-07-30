package com.catalyte.OrionsPets.DTOs;

import com.catalyte.OrionsPets.models.Purchase;
import com.catalyte.OrionsPets.models.PurchaseItem;

public class PurchaseDTO {

  private Purchase purchase;

  public PurchaseDTO(Purchase purchase) {
    this.purchase = purchase;
  }

  /**
   * Adds a PurchaseItem to purchase
   * @param item item to add
   */
  public void addItem(PurchaseItem item) {
    PurchaseItem[] newItems = new PurchaseItem[purchase.getItems().length + 1];
    System.arraycopy(purchase.getItems(), 0, newItems, 0, purchase.getItems().length);
    newItems[purchase.getItems().length] = item;
    purchase.setItems(newItems);
    double total = 0;
    for (PurchaseItem i : newItems)
      total += i.getPrice();
    purchase.setTotalPrice(total);
  }

  private boolean hasItem(String petId) {
    for (PurchaseItem item : purchase.getItems())
      if (item.getPetId().equals(petId))
        return true;
    return false;
  }

  /**
   * removes a pet from a purchase
   * @param petId id of pet to remove
   * @return boolean to tell if was successful
   */
  public boolean removeItem(String petId) {
    if (hasItem(petId)) {
      PurchaseItem[] items = new PurchaseItem[purchase.getItems().length - 1];
      int index = 0;
      for (PurchaseItem item : purchase.getItems()) {
        if(!item.getPetId().equals(petId)) {
          items[index] = item;
          index++;
        }
      }
      purchase.setItems(items);
      return true;
    }
    return false;
  }

}
