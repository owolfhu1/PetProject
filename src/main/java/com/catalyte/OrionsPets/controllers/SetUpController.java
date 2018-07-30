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

  /**
   * clears database
   * @param password dev password
   * @return String message telling result of action
   */
  @ApiOperation("Navigate here to clear the database + enter password in header. Access: dev password")
  @RequestMapping(value = "/clear", method = RequestMethod.GET)
  public String clearDatabase(@RequestHeader String password) {
    return setUpServices.clearDatabase(password) ? "Database cleared!" : "Access denied";
  }

  /**
   * Creates random database with number of pets/customers/purchases up to user
   * @param password dev password
   * @param pets numb of pets to make, MAX = 200
   * @param customers numb of customers to make, MAX = 100
   * @param purchases num of purchases to make, MAX = half of {pets}
   * @return String message telling result of action
   */
  @ApiOperation("Navigate here to create a random database(database must be cleared first) " +
          "+ enter password + pets(MAX = 200) + customers(MAX = 100) + purchases(MAX = half of {pets})" +
          " in header. Access: dev password")
  @RequestMapping(value = "/create-dummy", method = RequestMethod.GET)
  public String createDummyData(@RequestHeader String password, @RequestHeader int pets,
                                @RequestHeader int customers, @RequestHeader int purchases) {
    return setUpServices.createDummyData(password,pets,customers,purchases) == 1 ? "Dummy data created" :
            (!password.equals(SetUpServices.SUPER_SECRET_PASSWORD) ? "Access denied" :
            "There is already data in the database, please clear it before proceeding.");
  }

  /**
   * Creates empty database with an admin
   * @param password dev password
   * @param adminUsername admin username to create
   * @param adminPassword admin password to create
   * @return String message telling result of action
   */
  @ApiOperation("Navigate here to create a blank database an admin + " +
          "enter password + adminUsername + adminPassword in header. Access: dev password")
  @RequestMapping(value = "/create-empty", method = RequestMethod.GET)
  public String createEmptyData(@RequestHeader String password
          , @RequestHeader String adminUsername,@RequestHeader String adminPassword) {
    return setUpServices.createEmptyData(password,adminUsername,adminPassword) == 1 ? "Database setup complete" :
            (!password.equals(SetUpServices.SUPER_SECRET_PASSWORD) ?
                    "Access denied" : "There is already data in the database, please clear it before proceeding.");
  }



}
