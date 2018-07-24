package com.catalyte.OrionsPets.repositories;

import com.catalyte.OrionsPets.models.InventoryItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<String, InventoryItem> {

}
