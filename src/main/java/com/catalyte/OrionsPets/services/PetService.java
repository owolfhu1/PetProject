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
public class PetService {

  private PetTypeRepository petTypeRepository;
  private InventoryRepository inventoryRepository;
  private PetRepository petRepository;

  @Autowired
  public PetService(PetTypeRepository petTypeRepository,
      InventoryRepository inventoryRepository,
      PetRepository petRepository) {
    this.petTypeRepository = petTypeRepository;
    this.petRepository = petRepository;
    this.inventoryRepository = inventoryRepository;
  }


  public List<Pet> searchPets(String type, String value) {
    List<Pet> list;
    switch (type) {
      case "type":
        if (petTypeRepository.existsByType(value))
          list = petRepository.findByPetTypeId(petTypeRepository.findByType(value).getId());
        else
          list = new ArrayList<>();
        break;
      case "name":
        list = petRepository.findByName(value);
        break;
      case "color":
        list = petRepository.findByColor(value);
        break;
      case "sex":
        list = petRepository.findBySex(value);
        break;
      case "sold":
        list = (value.equals("true") || value.equals("false")) ?
            petRepository.findBySold(value.equals("true")) : new ArrayList<>();
        break;
      case "age":
        list = value.matches("^[0-9]+$") ?
            petRepository.findByAge(Integer.parseInt(value)) : new ArrayList<>();
        break;
      default:
        list = new ArrayList<>();
    }
    return list;
  }

  public String createPet(Pet pet) {
    String validation = validatePet(pet);
    if (validation.isEmpty()) {
      String petTypeId = petTypeRepository.findByType(pet.getPetTypeId()).getId();
      pet.setPetTypeId(petTypeId);
      Inventory inventory = inventoryRepository.findByPetTypeId(petTypeId);
      InventoryDTO invDTO = new InventoryDTO(inventory);
      invDTO.addInventory(1);
      petRepository.save(pet);
      inventoryRepository.save(inventory);
      return "Pet created";
    } else return validation;
  }

  public String updatePet(Pet pet) {
    if (!petRepository.existsById(pet.getId()))
      return "Could not find pet to update. (bad petId)";
    String validation = validatePet(pet);
    if (validation.isEmpty()) {
      Pet oldPet = petRepository.findOneById(pet.getId());
      String petTypeId = petTypeRepository.findByType(pet.getPetTypeId()).getId();
      pet.setPetTypeId(petTypeId);
      petRepository.save(pet);
      if (oldPet.isSold() != pet.isSold()) {
        Inventory inventory = inventoryRepository.findByPetTypeId(petTypeId);
        InventoryDTO invDto = new InventoryDTO(inventory);
        invDto.addInventory(pet.isSold() ? -1 : 1);
        inventoryRepository.save(inventory);
      }
      return "Pet updated";
    } else return validation;
  }

  public String deletePet(String petId) {
    if (petRepository.existsById(petId)){
      Pet pet = petRepository.findOneById(petId);
      if (!pet.isSold()) {
        Inventory inventory = inventoryRepository
            .findByPetTypeId(pet.getPetTypeId());
        InventoryDTO invDto = new InventoryDTO(inventory);
        invDto.addInventory(-1);
        inventoryRepository.save(inventory);
      }
      petRepository.deleteById(petId);
      return "Pet deleted";
    } else return "Pet to delete not found";
  }


  private String validatePet(Pet pet) {

    if (!petTypeRepository.existsByType(pet.getPetTypeId()))
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
