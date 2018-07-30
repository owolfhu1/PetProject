package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Customer;
import com.catalyte.OrionsPets.services.AuthServices;
import com.catalyte.OrionsPets.services.CustomerServices;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {

  private CustomerServices customerServices;
  private AuthServices authServices;

  @Autowired
  public CustomerController(CustomerServices customerServices,
                            AuthServices authServices) {
    this.customerServices = customerServices;
    this.authServices = authServices;
  }

  /**
   * Finds all customers that exist
   * @return List of all customers
   */
  @ApiOperation("Navigate here to find all customers. Access level: none")
  @RequestMapping(value = "/findall", method = RequestMethod.GET)
  public List<Customer> findAll() {
    return customerServices.findAll();
  }


  /**
   * Search for specific customers
   * @param category category searching in
   * @param searchTerm term searching for
   * @return List of customers that match your search
   */
  @ApiOperation("Search for a customer by {searchTerm}: " +
          "({category}= firstname, lastname, phone, address). Access level: none")
  @RequestMapping(value = "/search/{category}/{searchTerm}", method = RequestMethod.GET)
  public List<Customer> searchCustomers(@PathVariable String category, @PathVariable String searchTerm) {
    return customerServices.searchCustomers(category,searchTerm);
  }

  /**
   * Creates a new customer
   * @param username login username
   * @param password login password
   * @param customer new customer you are creating
   * @return String message telling result of action
   */
  @ApiOperation("Create a customer: req username + password in header and customer in body. Access level: admin")
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String createCustomer(@RequestHeader String username, @RequestHeader String password,
      @RequestBody Customer customer) {
    return authServices.authenticate(username,password,"ADMIN") ?
            (customerServices.createCustomer(customer) ? "Customer added" : "Invalid customer provided.") :
            "Access denied";
  }

  /**
   * Updates a customer
   * @param username login username
   * @param password login password
   * @param customer updated customer to replace old customer
   * @return String message telling result of action
   */
  @ApiOperation("Update a customer: req username + password in header + customer to save in body. Access level: user+")
  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public String updateCustomer(@RequestHeader String username, @RequestHeader String password,
      @RequestBody Customer customer) {
    return authServices.authenticate(username,password,"ADMIN") || authServices.authenticate(username,password,"USER") ?
            (customerServices.updateCustomer(customer) ? "Customer updated" : "Invalid data provided") : "Access denied";
  }

  /**
   * Deletes a customer by customerId
   * @param username login username
   * @param password login password
   * @param customerId customerId to delete
   * @return String message telling result of action
   */
  @ApiOperation("Delete a customer: req username + password in header + customerId in uri. Access level: admin")
  @RequestMapping(value = "/delete/{customerId}", method = RequestMethod.DELETE)
  public String deleteCustomer(@RequestHeader String username, @RequestHeader String password,
      @PathVariable("customerId") String customerId) {
    return authServices.authenticate(username,password,"ADMIN") ?
            (customerServices.deleteCustomer(customerId) ? "Customer deleted" : "Invalid data provided") :
            "Access denied";
  }

}
