package com.catalyte.OrionsPets.services;

import com.catalyte.OrionsPets.models.Inventory;
import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.repositories.InventoryRepository;
import com.catalyte.OrionsPets.repositories.PetRepository;
import com.catalyte.OrionsPets.repositories.PetTypeRepository;
import com.catalyte.OrionsPets.resorces.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public List<Inventory> findAll() {
    return inventoryRepository.findAll();
  }

  public Inventory searchInventoriesByPetType(String petType) throws DataNotFoundException {
    if (petTypeRepository.existsByType(petType)) {
      return inventoryRepository.findByPetType(petType);
    } else throw new DataNotFoundException();
  }

  public boolean createInventory(PetType petType, double price) {
    boolean result = false;
    if (!petTypeRepository.existsByType(petType.getType())) {
      String petTypeType = petTypeRepository.save(petType).getType();
      Inventory inventory = new Inventory();
      inventory.setPetType(petTypeType);
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
      petRepository.deleteByPetType(petType);
      inventoryRepository.deleteByPetType(petType);
      petTypeRepository.deleteById(petTypeId);
      result = true;
    }
    return result;
  }

}
