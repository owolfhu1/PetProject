package com.catalyte.OrionsPets.controllers;


import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.resorces.DataNotFoundException;
import com.catalyte.OrionsPets.services.PetTypeServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Orion Wolf_Hubbard on 7/28/2018.
 */
@RestController
@RequestMapping(value = "pet-type")
public class PetTypeController {

    private PetTypeServices petTypeServices;

    @Autowired
    public PetTypeController(PetTypeServices petTypeServices) {
        this.petTypeServices = petTypeServices;
    }

    /*                    ** NOTE **

        Only READ functionally found here for convenience.
        Other CRUD operations handled in InventoryController.

     */

    @ApiOperation("Navigate here to find all types")
    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public List<PetType> findAll() {
        return this.petTypeServices.findAll();
    }

    @ApiOperation("find by {category}=('type'/'id') + {searchTerm} in uri")
    @RequestMapping(value = "/find/{category}/{searchTerm}", method = RequestMethod.GET)
    public PetType searchTypes(@PathVariable String category, @PathVariable String searchTerm) throws DataNotFoundException {
        return this.petTypeServices.search(category,searchTerm);
    }

}
