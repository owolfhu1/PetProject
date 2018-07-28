package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Pet;
import com.catalyte.OrionsPets.services.AuthenticationServices;
import com.catalyte.OrionsPets.services.PetServices;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("Navigate here to find all pets")
    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public List<Pet> findAll() {
        return petServices.findAll();
    }

    @ApiOperation("Search for a pet by {searchTerm}: ({category}= type, name, color, sex, age, sold)")
    @RequestMapping(value = "/search/{category}/{searchTerm}", method = RequestMethod.GET)
    public List<Pet> searchPets(@PathVariable String category, @PathVariable String searchTerm) {
        return petServices.searchPets(category,searchTerm);
    }

    @ApiOperation("Create a pet: req username + password in header and pet in body")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPet(@RequestHeader String username, @RequestHeader String password,
                            @RequestBody Pet pet) {
        return authenticationServices.authenticate(username,password,"ADMIN") ?
                petServices.createPet(pet) : "Not authorized";
    }

    @ApiOperation("Update a pet: req username + password in header + pet in body")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updatePet(@RequestHeader String username, @RequestHeader String password,
                            @RequestBody Pet pet) {
        return authenticationServices.authenticate(username,password,"ADMIN") ?
                petServices.updatePet(pet) : "Not authorized";
    }


    @ApiOperation("Delete a pet: req username + password in header + petId in uri")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deletePet(@RequestHeader String username, @RequestHeader String password,
                            @PathVariable String id) {
        return authenticationServices.authenticate(username,password,"ADMIN") ?
                petServices.deletePet(id) : "Not authorized";
    }


}
