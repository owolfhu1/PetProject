package com.catalyte.OrionsPets.services;


import com.catalyte.OrionsPets.DTOs.InventoryDTO;
import com.catalyte.OrionsPets.DTOs.PurchaseDTO;
import com.catalyte.OrionsPets.DTOs.UserDTO;
import com.catalyte.OrionsPets.models.*;
import com.catalyte.OrionsPets.repositories.CustomerRepository;
import com.catalyte.OrionsPets.repositories.InventoryRepository;
import com.catalyte.OrionsPets.repositories.PetRepository;
import com.catalyte.OrionsPets.repositories.PetTypeRepository;
import com.catalyte.OrionsPets.repositories.PurchaseRepository;

import com.catalyte.OrionsPets.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetUpServices {
  private static final int MAX_PETS_PER_PURCHASE = 2;
  private static final int MAX_PET_AGE = 15;
  private static final int NUMB_OF_PETS = 20;
  private static final int NUMB_OF_CUSTOMERS = 10;
  private static final int NUMB_OF_PURCHASES = 10;
  private static String[] colors = {"red", "yellow", "blue", "green", "brown",
          "white", "black", "gray", "striped", "orange", "purple", "pink"};
  private static String[] begin = {"Kr", "Ca", "Ra", "Mrok", "Cru",
          "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
          "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
          "Mar", "Luk"};
  private static String[] middle = {"air", "ir", "mi", "sor", "mee", "clo",
          "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
          "marac", "zoir", "slamar", "salmar", "urak"};
  private static String[] end = {"d", "ed", "ark", "arc", "es", "er", "der",
          "tron", "med", "ure", "zur", "cred", "mur"};
  private static String[] petTypes = {"dog", "cat", "bird", "reptile",
          "rodent", "fish", "monkey", "insect", "plant"};
  private static Random rand = new Random();

  private CustomerRepository customerRepository;
  private InventoryRepository inventoryRepository;
  private PetRepository petRepository;
  private PetTypeRepository petTypeRepository;
  private PurchaseRepository purchaseRepository;
  private UserRepository userRepository;

  @Autowired
  public SetUpServices(CustomerRepository customerRepository, InventoryRepository inventoryRepository,
                       PetRepository petRepository, PetTypeRepository petTypeRepository,
                       PurchaseRepository purchaseRepository, UserRepository userRepository) {
    this.customerRepository = customerRepository;
    this.inventoryRepository = inventoryRepository;
    this.petRepository = petRepository;
    this.petTypeRepository = petTypeRepository;
    this.purchaseRepository = purchaseRepository;
    this.userRepository = userRepository;
  }

  public boolean clearDatabase() {
    try {
      customerRepository.deleteAll();
      inventoryRepository.deleteAll();
      petRepository.deleteAll();
      petTypeRepository.deleteAll();
      purchaseRepository.deleteAll();
      userRepository.deleteAll();
      return true;}  catch (Exception e) {return false;}//cheat...
  }

  public boolean createDummyData() {
    if (petTypeRepository.findAll().size() == 0 && inventoryRepository.findAll().size() == 0 &&
            petRepository.findAll().size() == 0 && customerRepository.findAll().size() == 0 &&
            purchaseRepository.findAll().size() == 0 && userRepository.findAll().size() == 0) {
      createUsers();
      createDummyPetTypesAndInventories();
      createDummyPets();
      createDummyCustomers();
      createDummyPurchases();
      return true;
    }
    return false;
  }

  private void createUsers() {
    User user = new User("user", "password");
    User admin = new User("admin", "password");
    UserDTO userDTO = new UserDTO(user);
    UserDTO adminDTO = new UserDTO(admin);
    userDTO.addRole("USER");
    adminDTO.addRole("USER");
    adminDTO.addRole("ADMIN");
    userRepository.save(user);
    userRepository.save(admin);
  }

  private void createDummyPetTypesAndInventories() {
    for (String type : petTypes) {
      petTypeRepository.save(new PetType(type));
      String petTypeId = petTypeRepository.findByType(type).getId();
      Inventory inventory = new Inventory();
      inventory.setPetTypeId(petTypeId);
      inventory.setPrice((rand.nextInt(400)+1)*.25);
      inventoryRepository.save(inventory);
    }
  }

  private void createDummyPets() {
    String[] petTypeIds = new String[petTypes.length];
    for (int i = 0; i < petTypes.length; i++)
      petTypeIds[i] = petTypeRepository.findByType(petTypes[i]).getId();
    for (int i = 0; i < NUMB_OF_PETS; i++) {
      Inventory inventory = inventoryRepository.findByPetTypeId(petTypeIds[rand.nextInt(petTypeIds.length)]);
      InventoryDTO invDTO = new InventoryDTO(inventory);
      invDTO.addInventory(1);
      inventoryRepository.save(inventory);
      Pet pet = new Pet(inventory.getPetTypeId(), randomName(),rand.nextInt(MAX_PET_AGE)+1, randomColor(), rand.nextBoolean() ? "male":"female");
      petRepository.save(pet);
    }
  }

  private void createDummyCustomers() {
    for (int i = 0; i < NUMB_OF_CUSTOMERS; i++)
      customerRepository.save(new Customer(randomName(),randomName(),randomPhone(),randomAddress()));
  }

  private void createDummyPurchases() {
    ArrayList<String> customerIds = new ArrayList<>();
    customerRepository.findAll().forEach(customer -> customerIds.add(customer.getId()));
    ArrayList<String> petIds = new ArrayList<>();
    petRepository.findAll().forEach(pet -> petIds.add(pet.getId()));

    /*
        FOR TESTING
        because all repositories .findAll() must return empty to start these methods
    */
    if (customerIds.isEmpty()) {
      Customer dummyCustomer = new Customer();
      dummyCustomer.setId("abc");
      Pet dummyPet = new Pet();
      dummyPet.setId("abc");
      List<Pet> dummyPetList = new ArrayList<>();
      List<Customer> dummyCustomerList = new ArrayList<>();
      for (int i = 0; i < 100; i++) {
        dummyPetList.add(dummyPet);
        dummyCustomerList.add(dummyCustomer);
      }
      dummyCustomerList.forEach(customer -> customerIds.add(customer.getId()));
      dummyPetList.forEach(pet -> petIds.add(pet.getId()));
    }
    //end testing addition

    for (int i = 0; i < NUMB_OF_PURCHASES; i++){
      Purchase purchase = new Purchase();
      purchase.setCustomerId(customerIds.get(rand.nextInt(customerIds.size())));
      int numbOfPets = rand.nextInt(MAX_PETS_PER_PURCHASE)+1;
      for (int x = 0; x < numbOfPets; x++){
        if (petIds.size() > 0) {
          String petId = petIds.remove(rand.nextInt(petIds.size()));
          Pet pet = petRepository.findOneById(petId);
          pet.setSold(true);
          Inventory inventory = inventoryRepository.findByPetTypeId(pet.getPetTypeId());
          InventoryDTO invDTO = new InventoryDTO(inventory);
          invDTO.addInventory(-1);
          PurchaseDTO purchaseDTO = new PurchaseDTO(purchase);
          purchaseDTO.addItem(new PurchaseItem(petId, inventory.getPrice()));
          petRepository.save(pet);
          inventoryRepository.save(inventory);
        }
      }
      purchaseRepository.save(purchase);
    }
  }

  private String randomAddress() {
    String string = "";
    for (int i = 0; i < 4; i++)
      string += rand.nextInt(10);
    string += " " + randomName() + " ";
    string += rand.nextBoolean() ? "Street" : rand.nextBoolean() ? "Road" : rand.nextBoolean() ? "Drive" : "Boulevard";
    return string;
  }

  private String randomPhone() {
    String string = "(";
    for (int i = 0; i < 3; i++)
      string += rand.nextInt(10);
    string += ") ";
    for (int i = 0; i < 3; i++)
      string += rand.nextInt(10);
    string += "-";
    for (int i = 0; i < 4; i++)
      string += rand.nextInt(10);
    return string;
  }

  private String randomColor() {
    return colors[rand.nextInt(colors.length)];
  }

  private String randomName() {
    return begin[rand.nextInt(begin.length)] +
            middle[rand.nextInt(middle.length)] +
            end[rand.nextInt(end.length)];
  }

}
