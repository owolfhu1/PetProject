package com.catalyte.OrionsPets.repositories;

import com.catalyte.OrionsPets.models.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
  Inventory findOneByPetTypeId(String petTypeId);
}
