package com.catalyte.OrionsPets.repositories;

import com.catalyte.OrionsPets.models.PetType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetTypeRepository extends MongoRepository<String,PetType> {

}