package com.catalyte.OrionsPets.services;


import com.catalyte.OrionsPets.models.Customer;
import com.catalyte.OrionsPets.models.InventoryItem;
import com.catalyte.OrionsPets.models.Pet;
import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.models.Purchase;
import com.catalyte.OrionsPets.models.PurchaseItem;
import com.catalyte.OrionsPets.repositories.CustomerRepository;
import com.catalyte.OrionsPets.repositories.InventoryRepository;
import com.catalyte.OrionsPets.repositories.PetRepository;
import com.catalyte.OrionsPets.repositories.PetTypeRepository;
import com.catalyte.OrionsPets.repositories.PurchaseRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    createDummyCustomers();
    createDummyPurchases();
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
    customerRepository.save(new Customer("Orion", "Wolf", "(111) 111-1111", "13653 Boyd St"));
    customerRepository.save(new Customer("Jake", "Smith", "(123) 131-1234", "9837 Main St"));
    customerRepository.save(new Customer("David", "Trinkoff", "(666) 666-6666", "666 Twenty Rd"));
    customerRepository.save(new Customer("Don", "Baker", "(201) 321-9364", "777 Seven Dr"));
    customerRepository.save(new Customer("Mary", "Brett", "(301) 234-7654", "9087 Steet St"));
    customerRepository.save(new Customer("Mitch", "Mitchy", "(876) 354-8547", "987 Hope Dr"));
    customerRepository.save(new Customer("Rigel", "Wolfe", "(301) 746-2985", "3245 Collage Dr"));
    customerRepository.save(new Customer("Beth", "Cratty", "(746) 133-2211", "853 Hell St"));
    customerRepository.save(new Customer("Dan", "Cray", "(902) 456-8765", "758 Trella Blvd"));
  }

  private void createDummyPurchases() {
    String dogInvId = inventoryRepository.findOneByPetTypeId(petTypeRepository.findByType("dog").getId()).getId();
    String catInvId = inventoryRepository.findOneByPetTypeId(petTypeRepository.findByType("cat").getId()).getId();
    String birdInvId = inventoryRepository.findOneByPetTypeId(petTypeRepository.findByType("bird").getId()).getId();
    String reptileInvId = inventoryRepository.findOneByPetTypeId(petTypeRepository.findByType("reptile").getId()).getId();
    String rodentInvId = inventoryRepository.findOneByPetTypeId(petTypeRepository.findByType("rodent").getId()).getId();

    ArrayList<String> ids = new ArrayList<>();
    customerRepository.findAll().forEach(customer -> ids.add(customer.getId()));

    Purchase purchase1 = new Purchase();
    purchase1.setCustomerId(ids.get(0));
    Pet andy = petRepository.findOneByName("Andy");
    Pet charlie = petRepository.findOneByName("Charlie");
    andy.setSold(true);
    charlie.setSold(true);
    petRepository.save(andy);
    petRepository.save(charlie);
    PurchaseItem andyItem = new PurchaseItem(andy.getId(), dogInvId);
    PurchaseItem charlieItem = new PurchaseItem(charlie.getId(), dogInvId);




  }

}
