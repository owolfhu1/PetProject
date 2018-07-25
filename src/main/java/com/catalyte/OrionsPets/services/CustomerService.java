package com.catalyte.OrionsPets.services;

import com.catalyte.OrionsPets.models.Customer;
import com.catalyte.OrionsPets.repositories.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  CustomerRepository customerRepository;
  Authenticate authenticate;

  @Autowired
  public CustomerService(CustomerRepository customerRepository,
      Authenticate authenticate) {
    this.customerRepository = customerRepository;
    this.authenticate = authenticate;
  }

  public List<Customer> searchCustomers(String type, String value) {
    List<Customer> list;
    switch (type) {
      case "firstname" :
        list = customerRepository.findByFirstname(value);
        break;
      case  "lastname" :
        list = customerRepository.findByLastname(value);
        break;
      case "phone" :
        list = customerRepository.findByPhone(value);
        break;
      case "address" :
        list = customerRepository.findByAddress(value);
        break;
      default:
        list = new ArrayList<>();
    }
    return list;
  }

  public boolean createCustomer(Customer customer) {
    if (validateCustomer(customer)) {
      customerRepository.save(customer);
      return true;
    }
    return false;
  }

  public boolean updateCustomer(Customer customer, String customerId) {
    if (validateCustomer(customer) && customerRepository.existsById(customerId)) {
      customer.setId(customerId);
      customerRepository.save(customer);
      return true;
    }
    return false;
  }

  public boolean deleteCustomer(String customerId) {
    if (customerRepository.existsById(customerId)) {
      customerRepository.deleteById(customerId);
      return true;
    }
    return false;
  }

  private boolean validateCustomer(Customer customer) {
    if (customer.getFirstname().isEmpty())
      return false;
    if (customer.getLastname().isEmpty())
      return false;
    if (!customer.getPhone().matches("^[(][0-9]{3}[)] [0-9]{3}[-][0-9]{4}$"))
      return false;
    return customer.getAddress().matches("^[0-9]{2,5} [a-z A-Z]{3,15} [a-z A-Z]{2,8}$");
  }

}
