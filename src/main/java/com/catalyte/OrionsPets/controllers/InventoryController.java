package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Inventory;
import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.services.AuthenticationServices;
import com.catalyte.OrionsPets.services.InventoryServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "inventories")
public class InventoryController {

  private InventoryServices inventoryServices;
  private AuthenticationServices authenticationServices;

  @Autowired
  public InventoryController(InventoryServices inventoryServices,
                             AuthenticationServices authenticationServices) {
    this.inventoryServices = inventoryServices;
    this.authenticationServices = authenticationServices;
  }

  /*                        ** NOTE **

      Inventories are not updated directly from this controller.
      It is updated through adding/selling/deleting pets
      This happens in: PetController + PurchaseController

   */

  @ApiOperation("Navigate here to find all inventories")
  @RequestMapping(value = "/findall", method = RequestMethod.GET)
  public List<Inventory> findAll() {
    return inventoryServices.findAll();
  }

  @ApiOperation("Search for an inventory by {petType} in uri")
  @RequestMapping(value = "/search/{petType}", method = RequestMethod.GET)
  public Inventory searchInventoriesByType(@PathVariable String petType) throws Exception {
    return inventoryServices.searchInventoriesByPetType(petType);
  }

  @ApiOperation("Create a new inventory by {petType} + {price} in uri + username + password in header")
  @RequestMapping(value = "/create/{petType}/{price}", method = RequestMethod.POST)
  public String createInventory(@RequestHeader String username, @RequestHeader String password,
      @PathVariable String petType, @PathVariable double price) {
    PetType pt = new PetType(petType);
    return authenticationServices.authenticate(username,password,"ADMIN") ?
            (inventoryServices.createInventory(pt, price) ? "Inventory created" : "Inventory already exists.") :
            "Not authorized";
  }

  @ApiOperation("Delete an inventory by {petType} in uri + username + password in header")
  @RequestMapping(value = "/delete/{petType}", method = RequestMethod.DELETE)
  public String deleteInventory(@RequestHeader String username, @RequestHeader String password,
      @PathVariable String petType) {
    return authenticationServices.authenticate(username,password,"ADMIN") ?
            (inventoryServices.deleteInventory(petType) ? "Inventory deleted" : "Inventory not found.") : "Not authorized";
  }

}
