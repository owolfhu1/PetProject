package com.catalyte.OrionsPets.services;

import com.catalyte.OrionsPets.models.Inventory;
import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.repositories.InventoryRepository;
import com.catalyte.OrionsPets.repositories.PetRepository;
import com.catalyte.OrionsPets.repositories.PetTypeRepository;
import com.catalyte.OrionsPets.resorces.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServices {

  private InventoryRepository inventoryRepository;
  private PetTypeRepository petTypeRepository;
  private PetRepository petRepository;

  @Autowired
  public InventoryServices(
      InventoryRepository inventoryRepository,
      PetTypeRepository petTypeRepository,PetRepository petRepository) {
    this.inventoryRepository = inventoryRepository;
    this.petTypeRepository = petTypeRepository;
    this.petRepository = petRepository;
  }

  public Inventory searchInventoriesByPetType(String petType) throws DataNotFoundException {
    if (petTypeRepository.existsByType(petType)) {
      String petTypeId = petTypeRepository.findByType(petType).getId();
      return inventoryRepository.findByPetTypeId(petTypeId);
    } else throw new DataNotFoundException();
  }

  public boolean createInventory(PetType petType, double price) {
    boolean result = false;
    if (!petTypeRepository.existsByType(petType.getType())) {
      String petTypeId = petTypeRepository.save(petType).getId();
      Inventory inventory = new Inventory();
      inventory.setPetTypeId(petTypeId);
      inventory.setPrice(price);
      inventoryRepository.save(inventory);
      result = true;
    }
    return result;
  }

  public boolean deleteInventory(String petType) {
    boolean result = false;
    if (petTypeRepository.existsByType(petType)) {
      String petTypeId = petTypeRepository.findByType(petType).getId();
      petRepository.deleteByPetTypeId(petTypeId);
      inventoryRepository.deleteByPetTypeId(petTypeId);
      petTypeRepository.deleteById(petTypeId);
      result = true;
    }
    return result;
  }

}
