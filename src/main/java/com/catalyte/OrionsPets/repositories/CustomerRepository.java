package com.catalyte.OrionsPets.repositories;

import com.catalyte.OrionsPets.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
