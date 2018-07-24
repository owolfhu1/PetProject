package com.catalyte.OrionsPets.models;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "purchases")
public class Purchase {

  @Id
  private String id;
  private String customerId;
  private Date date;
  private PurchaseItem[] items;
  private double totalPrice;

}
