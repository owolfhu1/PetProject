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

  /**
   * Finds all inventories that exist
   * @return List of inventories
   */
  @ApiOperation("Navigate here to find all inventories. Access level: none")
  @RequestMapping(value = "/findall", method = RequestMethod.GET)
  public List<Inventory> findAll() {
    return inventoryServices.findAll();
  }

  /**
   * Search for a inventory by pet type
   * @param petType type of inventory to search for
   * @return An inventory
   * @throws Exception When inventory isn't found
   */
  @ApiOperation("Search for an inventory by {petType} in uri")
  @RequestMapping(value = "/search/{petType}", method = RequestMethod.GET)
  public Inventory searchInventoriesByType(@PathVariable String petType) throws Exception {
    return inventoryServices.searchInventoriesByPetType(petType);
  }

  /**
   * creates a new inventory
   * @param username login username
   * @param password login password
   * @param petType type of inventory to create
   * @param price price for inventory items
   * @return String message telling result of action
   */
  @ApiOperation("Create a new inventory by {petType} + {price} in uri" +
          " + username + password in header. Access level: admin")
  @RequestMapping(value = "/create/{petType}/{price}", method = RequestMethod.POST)
  public String createInventory(@RequestHeader String username, @RequestHeader String password,
      @PathVariable String petType, @PathVariable double price) {
    PetType pt = new PetType(petType);
    return authenticationServices.authenticate(username,password,"ADMIN") ?
            (inventoryServices.createInventory(pt, price) ? "Inventory created" : "Inventory already exists.") :
            "Not authorized";
  }

  /**
   * Deletes an inventory + all pets associated with it
   * @param username login username
   * @param password login password
   * @param petType type of inventory to delete
   * @return String message telling result of action
   */
  @ApiOperation("Delete an inventory by {petType} in uri + username + password in header. Access level: admin")
  @RequestMapping(value = "/delete/{petType}", method = RequestMethod.DELETE)
  public String deleteInventory(@RequestHeader String username, @RequestHeader String password,
      @PathVariable String petType) {
    return authenticationServices.authenticate(username,password,"ADMIN") ?
            (inventoryServices.deleteInventory(petType) ? "Inventory deleted" : "Inventory not found.") : "Not authorized";
  }

}
