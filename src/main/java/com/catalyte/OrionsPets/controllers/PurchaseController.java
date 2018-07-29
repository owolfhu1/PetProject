package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Purchase;
import com.catalyte.OrionsPets.services.AuthenticationServices;
import com.catalyte.OrionsPets.services.PurchaseServices;
import java.util.List;

import io.swagger.annotations.ApiOperation;
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
  private AuthenticationServices authServices;

  @Autowired
  public PurchaseController(PurchaseServices purchaseServices,
      AuthenticationServices authServices) {
    this.purchaseServices = purchaseServices;
    this.authServices = authServices;
  }

  @ApiOperation("Navigate here to find all purchases. Access level: none")
  @RequestMapping(value = "/findall", method = RequestMethod.GET)
  public List<Purchase> findAll() {
    return purchaseServices.findAll();
  }

  @ApiOperation("Search purchases by {customerId}. Access level: none")
  @RequestMapping(value = "/search/{customerId}", method = RequestMethod.GET)
  public List<Purchase> searchPurchasesByCustomerId(@PathVariable String customerId) {
    return purchaseServices.searchPurchases(customerId);
  }

  @ApiOperation("Create a purchase: req username + password in header +" +
          " {customerId} in uri + array of petIds to purchase in body. Access level: user+")
  @RequestMapping(value = "/create/{customerId}", method = RequestMethod.POST)
  public String createPurchase(@RequestHeader String username,
      @RequestHeader String password,@PathVariable String customerId,
      @RequestBody String[] petIds) {
    return authServices.authenticate(username,password,"ADMIN") ||
            authServices.authenticate(username,password,"USER") ?
            purchaseServices.createPurchase(customerId,petIds) : "Not authorized";
  }

  @ApiOperation("Return a pet: req username + password in header +" +
          " {purchaseId} and {petId} in uri. Access level: user+")
  @RequestMapping(value = "/return/{purchaseId}/{petId}", method = RequestMethod.PUT)
  public String returnPet(@RequestHeader String username, @RequestHeader String password,
      @PathVariable String purchaseId, @PathVariable String petId) {
    return authServices.authenticate(username,password,"ADMIN") || authServices.authenticate(username,password,"USER") ?
            purchaseServices.returnPet(purchaseId,petId) : "Not authorized";
  }

  @ApiOperation("Delete a purchase: req username + password in header + purchaseId in uri. Access level: admin")
  @RequestMapping(value = "/delete/{purchaseId}", method = RequestMethod.DELETE)
  public String deletePurchase(@RequestHeader String username,
      @RequestHeader String password, @PathVariable String purchaseId) {
    return authServices.authenticate(username,password,"ADMIN") ?
        purchaseServices.deletePurchase(purchaseId) : "Not authorized";
  }

}
