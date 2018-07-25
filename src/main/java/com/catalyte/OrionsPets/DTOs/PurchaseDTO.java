package com.catalyte.OrionsPets.DTOs;

import com.catalyte.OrionsPets.models.Purchase;
import com.catalyte.OrionsPets.models.PurchaseItem;

public class PurchaseDTO {

  private Purchase purchase;

  public PurchaseDTO(Purchase purchase) {
    this.purchase = purchase;
  }

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

}
