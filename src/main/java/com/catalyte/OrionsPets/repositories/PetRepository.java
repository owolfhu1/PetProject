package com.catalyte.OrionsPets.repositories;

import com.catalyte.OrionsPets.models.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetRepository extends MongoRepository<Pet, String> {

}
