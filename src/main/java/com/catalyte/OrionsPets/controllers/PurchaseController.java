package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Purchase;
import com.catalyte.OrionsPets.services.AuthenticationServices;
import com.catalyte.OrionsPets.services.PurchaseServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */
@RestController
@RequestMapping(value = "purchases")
public class PurchaseController {

  private PurchaseServices purchaseServices;
  private AuthenticationServices authenticationServices;

  @Autowired
  public PurchaseController(PurchaseServices purchaseServices,
      AuthenticationServices authenticationServices) {
    this.purchaseServices = purchaseServices;
    this.authenticationServices = authenticationServices;
  }

  @RequestMapping(value = "/search/{customerId}", method = RequestMethod.GET)
  public List<Purchase> searchPurchasesByCustomerId(@PathVariable String customerId) {
    return purchaseServices.searchPurchases(customerId);
  }

  @RequestMapping(value = "/create/{purchaseId}", method = RequestMethod.POST)
  public String createPurchase(@RequestHeader String username,
      @RequestHeader String password,@PathVariable String purchaseId,
      @RequestBody String[] petIds) {
    return authenticationServices.authenticate(username,password,"ADMIN") ?
        purchaseServices.createPurchase(purchaseId,petIds) : "Not authorized";
  }

  @RequestMapping(value = "/return/{purchaseId}/{petId}", method = RequestMethod.PUT)
  public String returnPet(@RequestHeader String username, @RequestHeader String password,
      @PathVariable String purchaseId, @PathVariable String petId) {
    return authenticationServices.authenticate(username,password,"ADMIN") ?
        purchaseServices.returnPet(purchaseId,petId) : "Not authorized";
  }

  @RequestMapping(value = "/delete/{purchaseId}", method = RequestMethod.DELETE)
  public String deletePurchase(@RequestHeader String username,
      @RequestHeader String password, @PathVariable String purchaseId) {
    return authenticationServices.authenticate(username,password,"ADMIN") ?
        purchaseServices.deletePurchase(purchaseId) : "Not authorized";
  }

}
