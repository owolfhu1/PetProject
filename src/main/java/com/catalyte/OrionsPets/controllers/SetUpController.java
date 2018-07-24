package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.services.SetUpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "setup")
public class SetUpController {

  @Autowired
  SetUpServices setUpServices;

  @RequestMapping(value = "clear", method = RequestMethod.GET)
  public String clearDatabase() {
    setUpServices.clearDatabase();
    return "database cleared!";
  }

  @RequestMapping(value = "create", method = RequestMethod.GET)
  public String createDummyPetTypes() {
    setUpServices.createDummyData();
    return "dummy data created";
  }

}
