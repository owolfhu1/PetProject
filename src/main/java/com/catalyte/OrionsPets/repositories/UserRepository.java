package com.catalyte.OrionsPets.repositories;

import com.catalyte.OrionsPets.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
