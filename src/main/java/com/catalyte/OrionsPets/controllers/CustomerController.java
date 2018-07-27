package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.Customer;
import com.catalyte.OrionsPets.services.AuthenticationServices;
import com.catalyte.OrionsPets.services.CustomerServices;
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


  private CustomerServices customerServices;
  private AuthenticationServices authenticationServices;

  @Autowired
  public CustomerController(CustomerServices customerServices,
                            AuthenticationServices authenticationServices) {
    this.customerServices = customerServices;
    this.authenticationServices = authenticationServices;
  }

  @RequestMapping(value = "/search/{type}/{value}", method = RequestMethod.GET)
  public List<Customer> searchCustomers(@PathVariable String type, @PathVariable String value) {
    return customerServices.searchCustomers(type,value);
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String createCustomer(@RequestHeader String username, @RequestHeader String password,
      @RequestBody Customer customer) {
    return authenticationServices.authenticate(username,password,"ADMIN") ?
            (customerServices.createCustomer(customer) ? "Customer added" : "Invalid customer provided.") :
            "Access denied";
  }

  @RequestMapping(value = "/update/{customerId}", method = RequestMethod.PUT)
  public String updateCustomer(@RequestHeader String username, @RequestHeader String password,
      @RequestBody Customer customer, @PathVariable("customerId") String customerId) {
    return authenticationServices.authenticate(username,password,"ADMIN") ?
            (customerServices.updateCustomer(customer,customerId) ? "Customer updated" : "Invalid data provided") :
            "Access denied";
  }

  @RequestMapping(value = "/delete/{customerId}", method = RequestMethod.DELETE)
  public String deleteCustomer(@RequestHeader String username, @RequestHeader String password,
      @PathVariable("customerId") String customerId) {
    return authenticationServices.authenticate(username,password,"ADMIN") ?
            (customerServices.deleteCustomer(customerId) ? "Customer deleted" : "Invalid data provided") :
            "Access denied";
  }

}
