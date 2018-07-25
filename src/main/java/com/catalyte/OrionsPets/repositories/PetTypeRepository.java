package com.catalyte.OrionsPets.repositories;

import com.catalyte.OrionsPets.models.PetType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetTypeRepository extends MongoRepository<PetType, String> {
  PetType findByType(String type);

    PetType findOneByType(String type);
}
