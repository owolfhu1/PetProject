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

  /**
   * Find all purchases that exist
   * @return List of Purchase
   */
  @ApiOperation("Navigate here to find all purchases. Access level: none")
  @RequestMapping(value = "/findall", method = RequestMethod.GET)
  public List<Purchase> findAll() {
    return purchaseServices.findAll();
  }

  /**
   * Find purchases by a customer
   * @param customerId customer to search for
   * @return List of Purchase
   */
  @ApiOperation("Search purchases by {customerId}. Access level: none")
  @RequestMapping(value = "/search/{customerId}", method = RequestMethod.GET)
  public List<Purchase> searchPurchasesByCustomerId(@PathVariable String customerId) {
    return purchaseServices.searchPurchases(customerId);
  }

  /**
   * Create a purchase + updates pet's sold value and inventories count
   * @param username login username
   * @param password login password
   * @param customerId customer to make purchase
   * @param petIds Array of ids fo pets to purchase
   * @return String message telling result of action
   */
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

  /**
   * Return a pet: removes the pet from the purchase, updates pet's sold value and inventory's count
   * @param username login username
   * @param password login password
   * @param purchaseId purchaseId to update
   * @param petId petId to return
   * @return String message telling result of action
   */
  @ApiOperation("Return a pet: req username + password in header +" +
          " {purchaseId} and {petId} in uri. Access level: user+")
  @RequestMapping(value = "/return/{purchaseId}/{petId}", method = RequestMethod.PUT)
  public String returnPet(@RequestHeader String username, @RequestHeader String password,
      @PathVariable String purchaseId, @PathVariable String petId) {
    return authServices.authenticate(username,password,"ADMIN") || authServices.authenticate(username,password,"USER") ?
            purchaseServices.returnPet(purchaseId,petId) : "Not authorized";
  }

  /**
   * Deletes a purchase and all associated pets
   * @param username login username
   * @param password login password
   * @param purchaseId purchaseId to delete
   * @return String message telling result of action
   */
  @ApiOperation("Delete a purchase: req username + password in header + purchaseId in uri. Access level: admin")
  @RequestMapping(value = "/delete/{purchaseId}", method = RequestMethod.DELETE)
  public String deletePurchase(@RequestHeader String username,
      @RequestHeader String password, @PathVariable String purchaseId) {
    return authServices.authenticate(username,password,"ADMIN") ?
        purchaseServices.deletePurchase(purchaseId) : "Not authorized";
  }

}
