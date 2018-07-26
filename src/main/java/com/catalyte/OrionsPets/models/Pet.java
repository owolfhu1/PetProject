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
  private String sex;
  private boolean sold = false;

  public Pet() {
  }

  public Pet(String petTypeId, String name, int age, String color, String sex) {
    this.petTypeId = petTypeId;
    this.name = name;
    this.age = age;
    this.color = color;
    this.sex = sex;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
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

  public boolean isSold() {
    return sold;
  }

  public void setSold(boolean sold) {
    this.sold = sold;
  }

}
