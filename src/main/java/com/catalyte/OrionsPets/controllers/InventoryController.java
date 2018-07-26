package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Inventory;
import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.services.AuthenticationServices;
import com.catalyte.OrionsPets.services.InventoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

  @RequestMapping(value = "/search/{petType}", method = RequestMethod.GET)
  public Inventory searchInventoriesByType(@PathVariable String petType) throws Exception {
    return inventoryServices.searchInventoriesByPetType(petType);
  }

  @RequestMapping(value = "/create/{petType}/{price}", method = RequestMethod.POST)
  public String createInventory(@RequestHeader String username, @RequestHeader String password,
      @PathVariable String petType, @PathVariable double price) {
    if (authenticationServices.authenticate(username,password,"ADMIN")) {
      PetType pt = new PetType(petType);
      return inventoryServices.createInventory(pt, price) ? "Inventory created"
          : "Inventory already exists.";
    }
    return "Not authorized";
  }

  @RequestMapping(value = "/delete/{petType}", method = RequestMethod.DELETE)
  public String deleteInventory(@RequestHeader String username, @RequestHeader String password,
      @PathVariable String petType) {
    if (authenticationServices.authenticate(username,password,"ADMIN"))
      return inventoryServices.deleteInventory(petType) ? "Inventory deleted" : "Inventory not found.";
    return "Not authorized";
  }

}
