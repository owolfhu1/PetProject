package com.catalyte.OrionsPets.models;

public class PurchaseItem {

  private String petId;
  private String invenoryItemId;
  private double price;

  public String getPetId() {
    return petId;
  }

  public void setPetId(String petId) {
    this.petId = petId;
  }

  public String getInvenoryItemId() {
    return invenoryItemId;
  }

  public void setInvenoryItemId(String invenoryItemId) {
    this.invenoryItemId = invenoryItemId;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
