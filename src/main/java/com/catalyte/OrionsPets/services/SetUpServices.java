package com.catalyte.OrionsPets.services;


import com.catalyte.OrionsPets.models.Customer;
import com.catalyte.OrionsPets.models.InventoryItem;
import com.catalyte.OrionsPets.models.Pet;
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
    createDummyInventory();
  }

  private void createDummyPetTypes() {
    petTypeRepository.save(new PetType("dog"));
    petTypeRepository.save(new PetType("cat"));
    petTypeRepository.save(new PetType("bird"));
    petTypeRepository.save(new PetType("reptile"));
    petTypeRepository.save(new PetType("rodent"));
  }

  private void createDummyPets() {
    String dogId = petTypeRepository.findByType("dog").getId();
    String catId = petTypeRepository.findByType("cat").getId();
    String birdId = petTypeRepository.findByType("bird").getId();
    String reptileId = petTypeRepository.findByType("reptile").getId();
    String rodentId = petTypeRepository.findByType("rodent").getId();
    petRepository.save(new Pet(dogId,"Andy",5,"black"));
    petRepository.save(new Pet(dogId,"Charlie",7,"tan"));
    petRepository.save(new Pet(dogId,"Tofu",10,"yellow"));
    petRepository.save(new Pet(catId,"Kitty",1,"white"));
    petRepository.save(new Pet(catId,"Tchaalla",4,"tan"));
    petRepository.save(new Pet(catId,"Elmo",6,"red"));
    petRepository.save(new Pet(birdId,"Mary",3,"white"));
    petRepository.save(new Pet(birdId,"Mr Birdie",2,"red"));
    petRepository.save(new Pet(birdId,"Tweety bird",4,"yellow"));
    petRepository.save(new Pet(reptileId,"Snookie",6,"gray"));
    petRepository.save(new Pet(reptileId,"Turtle",85,"brown"));
    petRepository.save(new Pet(reptileId,"Lizzy",3,"pink"));
    petRepository.save(new Pet(rodentId,"RatFace",1,"white"));
    petRepository.save(new Pet(rodentId,"Jimmy",3,"tan"));
    petRepository.save(new Pet(rodentId,"Tim",2,"yellow"));
  }

  private void createDummyInventory() {
    String dogId = petTypeRepository.findByType("dog").getId();
    String catId = petTypeRepository.findByType("cat").getId();
    String birdId = petTypeRepository.findByType("bird").getId();
    String reptileId = petTypeRepository.findByType("reptile").getId();
    String rodentId = petTypeRepository.findByType("rodent").getId();

    InventoryItem dogs = new InventoryItem();
    dogs.setPetTypeId(dogId);
    dogs.setCount(3);
    dogs.setPrice(100.00);

    InventoryItem cats = new InventoryItem();
    cats.setPetTypeId(catId);
    cats.setCount(3);
    cats.setPrice(40.00);

    InventoryItem birds = new InventoryItem();
    birds.setPetTypeId(birdId);
    birds.setCount(3);
    birds.setPrice(120.99);

    InventoryItem reptiles = new InventoryItem();
    reptiles.setPetTypeId(reptileId);
    reptiles.setCount(3);
    reptiles.setPrice(35.99);

    InventoryItem rodents = new InventoryItem();
    rodents.setPetTypeId(rodentId);
    rodents.setCount(3);
    rodents.setPrice(2.99);

    inventoryRepository.save(dogs);
    inventoryRepository.save(cats);
    inventoryRepository.save(birds);
    inventoryRepository.save(reptiles);
    inventoryRepository.save(rodents);
  }

  private void createDummyCustomers() {

    Customer cust1 = new Customer();

  }

}
