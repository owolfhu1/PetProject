package com.catalyte.OrionsPets.services;

import com.catalyte.OrionsPets.DTOs.InventoryDTO;
import com.catalyte.OrionsPets.models.Inventory;
import com.catalyte.OrionsPets.models.Pet;
import com.catalyte.OrionsPets.repositories.InventoryRepository;
import com.catalyte.OrionsPets.repositories.PetRepository;
import com.catalyte.OrionsPets.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class PetServices {

  private PetTypeRepository petTypeRepository;
  private InventoryRepository inventoryRepository;
  private PetRepository petRepository;

  @Autowired
  public PetServices(PetTypeRepository petTypeRepository,
      InventoryRepository inventoryRepository,
      PetRepository petRepository) {
    this.petTypeRepository = petTypeRepository;
    this.petRepository = petRepository;
    this.inventoryRepository = inventoryRepository;
  }

  /**
   * Search for specific pet
   * @param category category to search in
   * @param searchTerm term to search for
   * @return List of Pet
   */
  public List<Pet> searchPets(String category, String searchTerm) {
    List<Pet> list;
    switch (category) {
      case "type":
        list = petRepository.findByPetType(category);
        break;
      case "name":
        list = petRepository.findByName(searchTerm);
        break;
      case "color":
        list = petRepository.findByColor(searchTerm);
        break;
      case "sex":
        list = petRepository.findBySex(searchTerm);
        break;
      case "sold":
        list = (searchTerm.equals("true") || searchTerm.equals("false")) ?
            petRepository.findBySold(searchTerm.equals("true")) : new ArrayList<>();
        break;
      case "age":
        list = searchTerm.matches("^[0-9]+$") ?
            petRepository.findByAge(Integer.parseInt(searchTerm)) : new ArrayList<>();
        break;
      default:
        list = new ArrayList<>();
    }
    return list;
  }

  /**
   * Finds all pets
   * @return List of Pet
   */
  public List<Pet> findAll() {
    return petRepository.findAll();
  }

  /**
   * Creates a pet
   * @param pet Pet to create
   * @return String message telling result of action
   */
  public String createPet(Pet pet) {
    String validation = validatePet(pet);
    if (validation.isEmpty()) {
      pet.setId(null);
      pet.setSold(false);
      Inventory inventory = inventoryRepository.findByPetType(pet.getPetType());
      InventoryDTO invDTO = new InventoryDTO(inventory);
      invDTO.addInventory(1);
      petRepository.save(pet);
      inventoryRepository.save(inventory);
      return "Pet created";
    } else return validation;
  }

  /**
   * Updates a pet
   * @param pet pet to replace old pet
   * @return String message telling result of action
   */
  public String updatePet(Pet pet) {
    if (!petRepository.existsById(pet.getId()))
      return "Could not find pet to update. (bad petId)";
    String validation = validatePet(pet);
    if (validation.isEmpty()) {
      Pet oldPet = petRepository.findOneById(pet.getId());
      petRepository.save(pet);
      if (oldPet.isSold() != pet.isSold()) {
        Inventory inventory = inventoryRepository.findByPetType(pet.getPetType());
        InventoryDTO invDto = new InventoryDTO(inventory);
        invDto.addInventory(pet.isSold() ? -1 : 1);
        inventoryRepository.save(inventory);
      }
      return "Pet updated";
    } else return validation;
  }

  /**
   * Deletes a pet
   * @param petId id of pet to delete
   * @return String message telling result of action
   */
  public String deletePet(String petId) {
    if (petRepository.existsById(petId)){
      Pet pet = petRepository.findOneById(petId);
      if (!pet.isSold()) {
        Inventory inventory = inventoryRepository.findByPetType(pet.getPetType());
        InventoryDTO invDto = new InventoryDTO(inventory);
        invDto.addInventory(-1);
        inventoryRepository.save(inventory);
      }
      petRepository.deleteById(petId);
      return "Pet deleted";
    } else return "Pet to delete not found";
  }

  private String validatePet(Pet pet) {
    if (!petTypeRepository.existsByType(pet.getPetType()))
      return "Bad pet type";
    if (pet.getName().isEmpty() || pet.getName().split(" ").length == 0)
      return "Pet name required";
    if (pet.getName().length() > 15)
      return "Pet name too long, max 15 char.";
    if (pet.getAge() < 0)
      return "Pet must have a positive age!!";
    if (pet.getColor().isEmpty() || pet.getColor().split(" ").length == 0)
      return "Pet color required";
    if (pet.getColor().length() > 15)
      return "Pet color too long, max 15 char.";
    if (!pet.getSex().equals("male") && !pet.getSex().equals("female"))
      return "Pet sex must be male or female";
    return "";
  }

}
