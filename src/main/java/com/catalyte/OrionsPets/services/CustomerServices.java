package com.catalyte.OrionsPets.services;

import com.catalyte.OrionsPets.models.Customer;
import com.catalyte.OrionsPets.repositories.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices {

  private CustomerRepository customerRepository;

  @Autowired
  public CustomerServices(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  /**
   * Finds all customers
   * @return List of Customer
   */
  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  /**
   * finds specific customers
   * @param category category to search
   * @param searchTerm term to search for
   * @return List of Customer
   */
  public List<Customer> searchCustomers(String category, String searchTerm) {
    List<Customer> list;
    switch (category) {
      case "firstname" :
        list = customerRepository.findByFirstname(searchTerm);
        break;
      case  "lastname" :
        list = customerRepository.findByLastname(searchTerm);
        break;
      case "phone" :
        list = customerRepository.findByPhone(searchTerm);
        break;
      case "address" :
        list = customerRepository.findByAddress(searchTerm);
        break;
      default:
        list = new ArrayList<>();
    }
    return list;
  }

  /**
   * Creates a new customer
   * @param customer customer to add
   * @return boolean, tells if was successful
   */
  public boolean createCustomer(Customer customer) {
    if (validateCustomer(customer)) {
      customer.setId(null);
      customerRepository.save(customer);
      return true;
    }
    return false;
  }

  /**
   * Updates a customer
   * @param customer customer to replace old customer
   * @return boolean tells if was successful
   */
  public boolean updateCustomer(Customer customer) {
    if (validateCustomer(customer) && customerRepository.existsById(customer.getId())) {
      customerRepository.save(customer);
      return true;
    }
    return false;
  }

  /**
   * Deletes a customer
   * @param customerId if of customer to delete
   * @return
   */
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
    if (!customer.getEmail().contains(".") || !customer.getEmail().contains("@"))
      return false;
    return customer.getAddress().matches("^[0-9]{2,5} [a-z A-Z]{3,15} [a-z A-Z]{2,8}$");
  }

}
