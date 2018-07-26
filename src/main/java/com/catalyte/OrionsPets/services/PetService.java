package com.catalyte.OrionsPets.services;

import com.catalyte.OrionsPets.models.Pet;
import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.repositories.PetRepository;
import com.catalyte.OrionsPets.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.TextScore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;


@Service
public class PetService {

    private AuthenticationService authenticationService;
    private PetTypeRepository petTypeRepository;
    private PetRepository petRepository;

    @Autowired
    public PetService(AuthenticationService authenticationService,
                      PetTypeRepository petTypeRepository,
                      PetRepository petRepository) {
        this.authenticationService = authenticationService;
        this.petTypeRepository = petTypeRepository;
        this.petRepository = petRepository;
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

    public boolean creatPet(Pet pet, String type) {




        return false;
    }


    public String validatePet(Pet pet, String type) {

        if (petTypeRepository.existsByType(type))
            pet.setPetTypeId(petTypeRepository.findByType(type).getId());
        else return "Bad pet type";

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
