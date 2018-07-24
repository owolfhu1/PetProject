package com.catalyte.OrionsPets.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pets")
public class Pet {

  @Id
  private String id;
  private String petTypeId;
  private String name;
  private String color;
  private int age;
  private boolean sold;

  public Pet() {

  }

  public Pet(String petTypeId, String name, int age, String color) {
    this.petTypeId = petTypeId;
    this.name = name;
    this.age = age;
    this.color = color;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getPetTypeId() {
    return petTypeId;
  }

  public void setPetTypeId(String petTypeId) {
    this.petTypeId = petTypeId;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

}
