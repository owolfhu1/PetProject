package com.catalyte.OrionsPets.services;


import com.catalyte.OrionsPets.models.Customer;
import com.catalyte.OrionsPets.models.Inventory;
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

  public boolean createDummyData() {
    if (petTypeRepository.findAll().size() == 0 &&
            inventoryRepository.findAll().size() == 0 &&
            petRepository.findAll().size() == 0 &&
            customerRepository.findAll().size() == 0 &&
            purchaseRepository.findAll().size() == 0) {
      createDummyPetTypes();
      createDummyPets();
      createDummyInventory();
      createDummyCustomers();
      createDummyPurchases();
      return true;
    }
    return false;
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
    petRepository.save(new Pet(dogId,"Andy",5,"black","male"));//sold
    petRepository.save(new Pet(dogId,"Charlie",7,"tan","male"));//sold
    petRepository.save(new Pet(dogId,"Tofu",10,"yellow","male"));
    petRepository.save(new Pet(dogId,"Danny",1,"brown","male"));//sold
    petRepository.save(new Pet(catId,"Kitty",1,"white","female"));
    petRepository.save(new Pet(catId,"Tchaalla",4,"tan","male"));//sold
    petRepository.save(new Pet(catId,"Elmo",6,"red","male"));//sold
    petRepository.save(new Pet(catId,"Sarah",2,"gray","female"));//sold
    petRepository.save(new Pet(birdId,"Mary",3,"white","female"));//sold
    petRepository.save(new Pet(birdId,"Mr Birdie",2,"red","male"));//sold
    petRepository.save(new Pet(birdId,"Tweety bird",4,"yellow","female"));
    petRepository.save(new Pet(birdId,"Zooey",2,"black","female"));//sold
    petRepository.save(new Pet(reptileId,"Snookie",6,"gray","female"));//sold
    petRepository.save(new Pet(reptileId,"Turtle",85,"brown","male"));
    petRepository.save(new Pet(reptileId,"Lizzy",3,"pink","female"));//sold
    petRepository.save(new Pet(reptileId,"Missy",2,"white","female"));
    petRepository.save(new Pet(rodentId,"RatFace",1,"white","male"));//sold
    petRepository.save(new Pet(rodentId,"Jimmy",3,"tan","male"));
    petRepository.save(new Pet(rodentId,"Tim",2,"yellow","male"));//sold
    petRepository.save(new Pet(rodentId,"Spot",1,"spotted","male"));//sold
  }

  private void createDummyInventory() {
    String dogId = petTypeRepository.findByType("dog").getId();
    String catId = petTypeRepository.findByType("cat").getId();
    String birdId = petTypeRepository.findByType("bird").getId();
    String reptileId = petTypeRepository.findByType("reptile").getId();
    String rodentId = petTypeRepository.findByType("rodent").getId();

    Inventory dogs = new Inventory();
    dogs.setPetTypeId(dogId);
    dogs.setCount(4);
    dogs.setPrice(100.00);

    Inventory cats = new Inventory();
    cats.setPetTypeId(catId);
    cats.setCount(4);
    cats.setPrice(40.00);

    Inventory birds = new Inventory();
    birds.setPetTypeId(birdId);
    birds.setCount(4);
    birds.setPrice(120.99);

    Inventory reptiles = new Inventory();
    reptiles.setPetTypeId(reptileId);
    reptiles.setCount(4);
    reptiles.setPrice(35.99);

    Inventory rodents = new Inventory();
    rodents.setPetTypeId(rodentId);
    rodents.setCount(4);
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
    customerRepository.save(new Customer("Ana", "Hamilton", "(235) 434-4345", "13653 Boyd St"));
  }

  private void createDummyPurchases() {
    Inventory dogs = inventoryRepository.findOneByPetTypeId(petTypeRepository.findByType("dog").getId());
    Inventory cats = inventoryRepository.findOneByPetTypeId(petTypeRepository.findByType("cat").getId());
    Inventory birds = inventoryRepository.findOneByPetTypeId(petTypeRepository.findByType("bird").getId());
    Inventory reptiles = inventoryRepository.findOneByPetTypeId(petTypeRepository.findByType("reptile").getId());
    Inventory rodents = inventoryRepository.findOneByPetTypeId(petTypeRepository.findByType("rodent").getId());

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
    dogs.addInventory(-2);
    purchase1.addItem(new PurchaseItem(andy.getId(), dogs.getPrice()));
    purchase1.addItem(new PurchaseItem(charlie.getId(), dogs.getPrice()));

    Purchase purchase2 = new Purchase();
    purchase2.setCustomerId(ids.get(1));
    Pet mary = petRepository.findOneByName("Mary");
    Pet mrBirdie = petRepository.findOneByName("Mr Birdie");
    mary.setSold(true);
    mrBirdie.setSold(true);
    petRepository.save(mary);
    petRepository.save(mrBirdie);
    birds.addInventory(-2);
    purchase2.addItem(new PurchaseItem(mary.getId(), birds.getPrice()));
    purchase2.addItem(new PurchaseItem(mrBirdie.getId(), birds.getPrice()));

    Purchase purchase3 = new Purchase();
    purchase3.setCustomerId(ids.get(2));
    Pet elmo = petRepository.findOneByName("Elmo");
    elmo.setSold(true);
    petRepository.save(elmo);
    cats.addInventory(-1);
    purchase3.addItem(new PurchaseItem(elmo.getId(), cats.getPrice()));

    Purchase purchase4 = new Purchase();
    purchase4.setCustomerId(ids.get(3));
    Pet ratFace = petRepository.findOneByName("RatFace");
    Pet zooey = petRepository.findOneByName("Zooey");
    Pet lizzy = petRepository.findOneByName("Lizzy");
    ratFace.setSold(true);
    zooey.setSold(true);
    lizzy.setSold(true);
    petRepository.save(ratFace);
    petRepository.save(zooey);
    petRepository.save(lizzy);
    rodents.addInventory(-1);
    birds.addInventory(-1);
    reptiles.addInventory(-1);
    purchase4.addItem(new PurchaseItem(ratFace.getId(), rodents.getPrice()));
    purchase4.addItem(new PurchaseItem(zooey.getId(), birds.getPrice()));
    purchase4.addItem(new PurchaseItem(lizzy.getId(), reptiles.getPrice()));

    Purchase purchase5 = new Purchase();
    purchase5.setCustomerId(ids.get(4));
    Pet tchaalla = petRepository.findOneByName("Tchaalla");
    tchaalla.setSold(true);
    petRepository.save(tchaalla);
    cats.addInventory(-1);
    purchase5.addItem(new PurchaseItem(tchaalla.getId(), cats.getPrice()));

    Purchase purchase6 = new Purchase();
    purchase6.setCustomerId(ids.get(5));
    Pet danny = petRepository.findOneByName("Danny");
    Pet snookie = petRepository.findOneByName("Snookie");
    danny.setSold(true);
    petRepository.save(danny);
    petRepository.save(snookie);
    dogs.addInventory(-1);
    reptiles.addInventory(-1);
    purchase6.addItem(new PurchaseItem(danny.getId(), dogs.getPrice()));
    purchase6.addItem(new PurchaseItem(snookie.getId(), reptiles.getPrice()));

    Purchase purchase7 = new Purchase();
    purchase7.setCustomerId(ids.get(6));
    Pet spot = petRepository.findOneByName("Spot");
    Pet tim = petRepository.findOneByName("Tim");
    spot.setSold(true);
    tim.setSold(true);
    petRepository.save(spot);
    petRepository.save(tim);
    rodents.addInventory(-2);
    purchase7.addItem(new PurchaseItem(spot.getId(), rodents.getPrice()));
    purchase7.addItem(new PurchaseItem(tim.getId(), rodents.getPrice()));




    purchaseRepository.save(purchase1);
    purchaseRepository.save(purchase2);
    purchaseRepository.save(purchase3);
    purchaseRepository.save(purchase4);
    purchaseRepository.save(purchase5);
    purchaseRepository.save(purchase6);
    purchaseRepository.save(purchase7);

    inventoryRepository.save(dogs);
    inventoryRepository.save(cats);
    inventoryRepository.save(birds);
    inventoryRepository.save(reptiles);
    inventoryRepository.save(rodents);


  }

}
