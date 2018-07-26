package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Customer;
import com.catalyte.OrionsPets.services.AuthenticationService;
import com.catalyte.OrionsPets.services.CustomerService;
import java.util.List;
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

  private CustomerService customerService;
  private AuthenticationService authenticationService;

  @Autowired
  public CustomerController(CustomerService customerService,
                            AuthenticationService authenticationService) {
    this.customerService = customerService;
    this.authenticationService = authenticationService;
  }

  @RequestMapping(value = "/search/{type}/{value}", method = RequestMethod.GET)
  public List<Customer> searchCustomers(@PathVariable String type, @PathVariable String value) {
    return customerService.searchCustomers(type,value);
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String createCustomer(@RequestHeader String username, @RequestHeader String password,
      @RequestBody Customer customer) {
    if (authenticationService.authenticate(username,password,"ADMIN")){
      return customerService.createCustomer(customer) ? "Customer added." : "Invalid customer provided.";
    }
    return "Access denied";
  }

  @RequestMapping(value = "/update/{customerId}", method = RequestMethod.PUT)
  public String createCustomer(@RequestHeader String username, @RequestHeader String password,
      @RequestBody Customer customer, @PathVariable("customerId") String customerId) {
    if (authenticationService.authenticate(username,password,"ADMIN")){
      return customerService.updateCustomer(customer,customerId) ? "Customer updated." : "Invalid data provided";
    }
    return "Access denied";
  }

  @RequestMapping(value = "/delete/{customerId}", method = RequestMethod.DELETE)
  public String createCustomer(@RequestHeader String username, @RequestHeader String password,
      @PathVariable("customerId") String customerId) {
    if (authenticationService.authenticate(username,password,"ADMIN")){
      return customerService.deleteCustomer(customerId) ? "Customer deleted." : "Invalid data provided";
    }
    return "Access denied";
  }

}
