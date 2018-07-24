package com.catalyte.OrionsPets.services;


import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.repositories.CustomerRepository;
import com.catalyte.OrionsPets.repositories.InventoryRepository;
import com.catalyte.OrionsPets.repositories.PetRepository;
import com.catalyte.OrionsPets.repositories.PetTypeRepository;
import com.catalyte.OrionsPets.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetUpServices {

  CustomerRepository customerRepository;
  InventoryRepository inventoryRepository;
  PetRepository petRepository;
  PetTypeRepository petTypeRepository;
  PurchaseRepository purchaseRepository;

  @Autowired
  public SetUpServices(CustomerRepository customerRepository,
      InventoryRepository inventoryRepository,
      PetRepository petRepository,
      PetTypeRepository petTypeRepository,
      PurchaseRepository purchaseRepository) {
    this.customerRepository = customerRepository;
    this.inventoryRepository = inventoryRepository;
    this.petRepository = petRepository;
    this.petTypeRepository = petTypeRepository;
    this.purchaseRepository = purchaseRepository;
  }

  public void clearDatabase() {
    customerRepository.deleteAll();
    inventoryRepository.deleteAll();
    petRepository.deleteAll();
    petTypeRepository.deleteAll();
    purchaseRepository.deleteAll();
  }

  public void createDummyData() {
    createDummyPetTypes();
    createDummyPets();
  }

  private void createDummyPetTypes(){
    petTypeRepository.save(new PetType("dog"));
    petTypeRepository.save(new PetType("cat"));
    petTypeRepository.save(new PetType("bird"));
    petTypeRepository.save(new PetType("reptile"));
    petTypeRepository.save(new PetType("rodent"));
  }

  private void createDummyPets(){

    String dogId = petTypeRepository.findByType("dog").getId();



  }


}
