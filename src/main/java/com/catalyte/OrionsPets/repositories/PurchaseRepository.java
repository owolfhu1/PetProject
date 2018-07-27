package com.catalyte.OrionsPets.repositories;

import com.catalyte.OrionsPets.models.Purchase;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {
  List<Purchase> findByCustomerId(String customerId);
  Purchase findOneById(String purchaseId);
}
