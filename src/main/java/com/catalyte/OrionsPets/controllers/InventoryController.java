package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Inventory;
import com.catalyte.OrionsPets.services.Authenticate;
import com.catalyte.OrionsPets.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "inventories")
public class InventoryController {

  InventoryService inventoryService;
  Authenticate authenticate;

  @Autowired
  public InventoryController(InventoryService inventoryService,
      Authenticate authenticate) {
    this.inventoryService = inventoryService;
    this.authenticate = authenticate;
  }

  @RequestMapping(value = "/search/{petType}", method = RequestMethod.GET)
  public Inventory searchInventoriesByType(@PathVariable String petType) throws Exception {
    return inventoryService.searchInventoriesByPetType(petType);
  }

  @RequestMapping(value = "/create/{petType}/{price}", method = RequestMethod.POST)
  public String createInventory(@RequestHeader String username, @RequestHeader String password,
      @PathVariable String petType, @PathVariable double price) {
    if (authenticate.authenticate(username,password,"ADMIN"))
      return inventoryService.createInventory(petType,price) ? "Inventory created" : "Inventory already exists.";
    return "Not authorized";
  }

  @RequestMapping(value = "/delete/{petType}", method = RequestMethod.DELETE)
  public String deleteInventory(@RequestHeader String username, @RequestHeader String password,
      @PathVariable String petType) {
    if (authenticate.authenticate(username,password,"ADMIN"))
      return inventoryService.deleteInventory(petType) ? "Inventory deleted" : "Inventory not found.";
    return "Not authorized";
  }

}
