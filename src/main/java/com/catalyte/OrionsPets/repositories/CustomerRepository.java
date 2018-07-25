package com.catalyte.OrionsPets.repositories;

import com.catalyte.OrionsPets.models.Customer;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
  List<Customer> findByFirstname(String firstname);
  List<Customer> findByLastname(String lastname);
  List<Customer> findByPhone(String phone);
  List<Customer> findByAddress(String address);
}
