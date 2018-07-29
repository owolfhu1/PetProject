package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.services.SetUpServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "setup")
public class SetUpController {

  private SetUpServices setUpServices;

  @Autowired
  public SetUpController(SetUpServices setUpServices) {
    this.setUpServices = setUpServices;
  }

  @ApiOperation("Navigate here to clear the database + enter password in header. Access: dev password")
  @RequestMapping(value = "clear", method = RequestMethod.GET)
  public String clearDatabase(@RequestHeader String password) {
    return setUpServices.clearDatabase(password) ? "Database cleared!" : "Access denied";
  }

  @ApiOperation("Navigate here to create a random database(database must be cleared first) " +
          "+ enter password in header. Access: dev password")
  @RequestMapping(value = "create-dummy", method = RequestMethod.GET)
  public String createDummyData(@RequestHeader String password) {
    return setUpServices.createDummyData(password) == 1 ? "Dummy data created" :
            (!password.equals(SetUpServices.SUPER_SECRET_PASSWORD) ? "Access denied" :
            "There is already data in the database, please clear it before proceeding.");
  }

  @ApiOperation("Navigate here to create a blank database an admin + " +
          "enter password + adminUsername + adminPassword in header. Access: dev password")
  @RequestMapping(value = "create-empty", method = RequestMethod.GET)
  public String createEmptyData(@RequestHeader String password
          , @RequestHeader String adminUsername,@RequestHeader String adminPassword) {
    return setUpServices.createEmptyData(password,adminUsername,adminPassword) == 1 ? "Database setup complete" :
            (!password.equals(SetUpServices.SUPER_SECRET_PASSWORD) ?
                    "Access denied" : "There is already data in the database, please clear it before proceeding.");
  }



}
