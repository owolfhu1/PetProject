package com.catalyte.OrionsPets.models;

public class PurchaseItem {

  private String petId;
  private double price;

  public PurchaseItem(String petId, double price) {
    this.petId = petId;
    this.price = price;
  }

  public String getPetId() {
    return petId;
  }

  public void setPetId(String petId) {
    this.petId = petId;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
