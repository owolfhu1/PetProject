package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Pet;
import com.catalyte.OrionsPets.resorces.DataNotFoundException;
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


        return null;
    }


}
