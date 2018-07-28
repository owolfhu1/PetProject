package com.catalyte.OrionsPets.services;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */

import com.catalyte.OrionsPets.DTOs.InventoryDTO;
import com.catalyte.OrionsPets.DTOs.PurchaseDTO;
import com.catalyte.OrionsPets.models.Inventory;
import com.catalyte.OrionsPets.models.Pet;
import com.catalyte.OrionsPets.models.Purchase;
import com.catalyte.OrionsPets.models.PurchaseItem;
import com.catalyte.OrionsPets.repositories.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServices {

  private PurchaseRepository purchaseRepository;
  private PetRepository petRepository;
  private InventoryRepository inventoryRepository;
  private CustomerRepository customerRepository;

  @Autowired
  public PurchaseServices(PurchaseRepository purchaseRepository, PetRepository petRepository,
      InventoryRepository inventoryRepository, CustomerRepository customerRepository) {
    this.purchaseRepository = purchaseRepository;
    this.petRepository = petRepository;
    this.inventoryRepository = inventoryRepository;
    this.customerRepository = customerRepository;
  }


  public List<Purchase> findAll() {
    return purchaseRepository.findAll();
  }

  public List<Purchase> searchPurchases(String customerId) {
    return purchaseRepository.findByCustomerId(customerId);
  }

  public String createPurchase(String customerId, String[] petIds) {
    if (!customerRepository.existsById(customerId))
      return "Bad customerId";
    for (String id : petIds)
      if (!petRepository.existsById(id))
        return id + " is a bad petId";
    for (String id : petIds)
      if (petRepository.findOneById(id).isSold())
        return "Pet with " + id + " is already sold";
    Purchase purchase = new Purchase();
    purchase.setCustomerId(customerId);
    PurchaseDTO purchaseDto = new PurchaseDTO(purchase);
    for (String id : petIds) {
      Pet pet = petRepository.findOneById(id);
      pet.setSold(true);
      Inventory inventory = inventoryRepository.findByPetTypeId(pet.getPetTypeId());
      InventoryDTO invDto = new InventoryDTO(inventory);
      invDto.addInventory(-1);
      PurchaseItem item = new PurchaseItem(pet.getId(),inventory.getPrice());
      purchaseDto.addItem(item);
      petRepository.save(pet);
      inventoryRepository.save(inventory);
    }
    purchaseRepository.save(purchase);
    return "Purchase created";
  }

  public String returnPet(String purchaseId, String petId) {
    if (!purchaseRepository.existsById(purchaseId))
      return "bad purchaseId";
    if (!petRepository.existsById(petId))
      return "bad petId";
    Purchase purchase = purchaseRepository.findOneById(purchaseId);
    PurchaseDTO purchaseDto = new PurchaseDTO(purchase);
    boolean result = purchaseDto.removeItem(petId);
    if(result) {
      Pet pet = petRepository.findOneById(petId);
      pet.setSold(false);
      petRepository.save(pet);
      purchaseRepository.save(purchase);
    }
    return result ? "Pet returned" : "error";
  }

  public String deletePurchase(String purchaseId) {
    if (!purchaseRepository.existsById(purchaseId))
      return "Purchase does not exist";
    Purchase purchase = purchaseRepository.findOneById(purchaseId);
    for (PurchaseItem item : purchase.getItems())
      petRepository.deleteById(item.getPetId());
    purchaseRepository.deleteById(purchaseId);
    return "Purchase deleted";
  }

}
