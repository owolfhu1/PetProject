package com.catalyte.OrionsPets.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventories")
public class Inventory {

  @Id
  private String id;
  private int count = 0;
  private String petTypeId;
  private double price;

  public void addInventory(int number) {
    count += number;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getPetTypeId() {
    return petTypeId;
  }

  public void setPetTypeId(String petTypeId) {
    this.petTypeId = petTypeId;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}