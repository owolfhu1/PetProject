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

    private AuthenticationService authenticationService;
    private PetTypeRepository petTypeRepository;
    private InventoryRepository inventoryRepository;
    private PetRepository petRepository;

    @Autowired
    public PetService(AuthenticationService authenticationService,
                      PetTypeRepository petTypeRepository,
                      InventoryRepository inventoryRepository,
                      PetRepository petRepository) {
        this.authenticationService = authenticationService;
        this.petTypeRepository = petTypeRepository;
        this.petRepository = petRepository;
        this.inventoryRepository = inventoryRepository;
    }


    public List<Pet> searchPets(String type, String value) {
        List<Pet> list;
        switch (type) {
            case "type" :
                if(petTypeRepository.existsByType(value))
                    list = petRepository.findByPetTypeId(petTypeRepository.findByType(value).getId());
                else
                    list = new ArrayList<>();
                break;
            case "name" :
                list = petRepository.findByName(value);
                break;
            case "color" :
                list = petRepository.findByColor(value);
                break;
            case "sex" :
                list = petRepository.findBySex(value);
                break;
            case "sold" :
                list = (value.equals("true") || value.equals("false")) ?
                        petRepository.findBySold(value.equals("true")) : new ArrayList<>();
                break;
            case "age" :
                list = value.matches("^[0-9]+$") ?
                        petRepository.findByAge(Integer.parseInt(value)) : new ArrayList<>();
                break;
            default:
                list = new ArrayList<>();
        }
        return list;
    }

    public String creatPet(Pet pet, String type) {
        String validation = validatePet(pet,type);
        if (validation.isEmpty()) {
            String petTypeId = petTypeRepository.findByType(type).getId();
            pet.setPetTypeId(petTypeId);
            Inventory inventory = inventoryRepository.findByPetTypeId(petTypeId);
            InventoryDTO invDTO = new InventoryDTO(inventory);
            invDTO.addInventory(1);
            petRepository.save(pet);
            inventoryRepository.save(inventory);
            return "Pet created";
        }
        else return validation;
    }

    public String validatePet(Pet pet, String type) {

        if (!petTypeRepository.existsByType(type))
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
