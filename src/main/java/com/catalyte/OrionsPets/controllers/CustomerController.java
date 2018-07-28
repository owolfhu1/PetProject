package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Customer;
import com.catalyte.OrionsPets.services.AuthenticationServices;
import com.catalyte.OrionsPets.services.CustomerServices;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
  private AuthenticationServices authenticationServices;

  @Autowired
  public CustomerController(CustomerServices customerServices,
                            AuthenticationServices authenticationServices) {
    this.customerServices = customerServices;
    this.authenticationServices = authenticationServices;
  }

  @ApiOperation("Navigate here to find all customers")
  @RequestMapping(value = "/findall", method = RequestMethod.GET)
  public List<Customer> findAll() {
    return customerServices.findAll();
  }

  @ApiOperation("Search for a customer by {searchTerm}: ({category}= firstname, lastname, phone, address)")
  @RequestMapping(value = "/search/{category}/{searchTerm}", method = RequestMethod.GET)
  public List<Customer> searchCustomers(@PathVariable String category, @PathVariable String searchTerm) {
    return customerServices.searchCustomers(category,searchTerm);
  }

  @ApiOperation("Create a customer: req username + password in header and customer in body")
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String createCustomer(@RequestHeader String username, @RequestHeader String password,
      @RequestBody Customer customer) {
    return authenticationServices.authenticate(username,password,"ADMIN") ?
            (customerServices.createCustomer(customer) ? "Customer added" : "Invalid customer provided.") :
            "Access denied";
  }

  @ApiOperation("Update a customer: req username + password in header + customerId in uri + customer to save in body.")
  @RequestMapping(value = "/update/{customerId}", method = RequestMethod.PUT)
  public String updateCustomer(@RequestHeader String username, @RequestHeader String password,
      @RequestBody Customer customer, @PathVariable("customerId") String customerId) {
    return authenticationServices.authenticate(username,password,"ADMIN") ?
            (customerServices.updateCustomer(customer,customerId) ? "Customer updated" : "Invalid data provided") :
            "Access denied";
  }

  @ApiOperation("Delete a customer: req username + password in header + customerId in uri")
  @RequestMapping(value = "/delete/{customerId}", method = RequestMethod.DELETE)
  public String deleteCustomer(@RequestHeader String username, @RequestHeader String password,
      @PathVariable("customerId") String customerId) {
    return authenticationServices.authenticate(username,password,"ADMIN") ?
            (customerServices.deleteCustomer(customerId) ? "Customer deleted" : "Invalid data provided") :
            "Access denied";
  }

}
