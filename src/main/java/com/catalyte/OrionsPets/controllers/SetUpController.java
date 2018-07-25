package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.services.SetUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "setup")
public class SetUpController {

  @Autowired
  SetUpService setUpService;

  @RequestMapping(value = "clear", method = RequestMethod.GET)
  public String clearDatabase() {
    setUpService.clearDatabase();
    return "Database cleared!";
  }

  @RequestMapping(value = "create", method = RequestMethod.GET)
  public String createDummyPetTypes() {
    return setUpService.createDummyData() ? "Dummy data created" :
            "There is already data in the database, please clear it before proceeding.";
  }

}
