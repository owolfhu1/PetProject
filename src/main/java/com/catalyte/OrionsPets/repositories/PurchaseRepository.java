package com.catalyte.OrionsPets.repositories;

import com.catalyte.OrionsPets.models.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {

}
