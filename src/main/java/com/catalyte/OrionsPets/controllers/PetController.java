package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Pet;
import com.catalyte.OrionsPets.services.AuthenticationServices;
import com.catalyte.OrionsPets.services.PetServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Orion Wolf_Hubbard on 7/25/2018.
 */
@RestController
@RequestMapping(value = "pets")
public class PetController {

    private PetServices petServices;
    private AuthenticationServices authenticationServices;

    @Autowired
    public PetController(PetServices petServices, AuthenticationServices authenticationServices) {
        this.petServices = petServices;
        this.authenticationServices = authenticationServices;
    }

    @RequestMapping(value = "/search/{type}/{value}", method = RequestMethod.GET)
    public List<Pet> searchPets(@PathVariable String type, @PathVariable String value) {
        return petServices.searchPets(type,value);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPet(@RequestHeader String username, @RequestHeader String password,
                            @RequestBody Pet pet) {
        if (authenticationServices.authenticate(username,password,"ADMIN"))
            return petServices.createPet(pet);
        return "Not authorized";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updatePet(@RequestHeader String username, @RequestHeader String password,
                            @RequestBody Pet pet) {
        if (authenticationServices.authenticate(username,password,"ADMIN"))
            return petServices.updatePet(pet);
        return "Not authorized";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public String deletePet(@RequestHeader String username, @RequestHeader String password,
                            @PathVariable String id) {
        if (authenticationServices.authenticate(username,password,"ADMIN"))
            return petServices.deletePet(id);
        return "Not authorized";
    }


}
