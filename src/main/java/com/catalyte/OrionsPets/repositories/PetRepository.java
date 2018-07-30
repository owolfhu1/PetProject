package com.catalyte.OrionsPets.repositories;

import com.catalyte.OrionsPets.models.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PetRepository extends MongoRepository<Pet, String> {
  Pet findOneById(String id);
  List<Pet> findByName(String name);
  List<Pet> findByColor(String color);
  List<Pet> findBySex(String sex);
  List<Pet> findBySold(boolean sold);
  List<Pet> findByAge(int age);
  void deleteByPetType(String petType);
  List<Pet> findByPetType(String type);
}
