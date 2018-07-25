package com.catalyte.OrionsPets.services;

import com.catalyte.OrionsPets.DTOs.UserDTO;
import com.catalyte.OrionsPets.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Authenticate {

  UserRepository userRepository;

  @Autowired
  public Authenticate(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean authenticate(String username, String password, String role) {
    return userRepository.existsByUsername(username) &&
        userRepository.findOneByUsername(username).getPassword().equals(password) &&
        new UserDTO(userRepository.findOneByUsername(username)).hasRole(role) ;
  }

}
