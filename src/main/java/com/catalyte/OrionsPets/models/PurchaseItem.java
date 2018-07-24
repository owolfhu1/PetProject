package com.catalyte.OrionsPets.models;

public class PurchaseItem {

  private String petId;
  private String invenoryItemId;

  public PurchaseItem(String petId, String invenoryItemId) {
    this.petId = petId;
    this.invenoryItemId = invenoryItemId;
  }

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

}
