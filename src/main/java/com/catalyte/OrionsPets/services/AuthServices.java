package com.catalyte.OrionsPets.services;

import com.catalyte.OrionsPets.DTOs.UserDTO;
import com.catalyte.OrionsPets.models.User;
import com.catalyte.OrionsPets.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServices {

  private UserRepository userRepository;

  @Autowired
  public AuthServices(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Checks if username/password combo is correct and if user has role requested
   * @param username username to check
   * @param password password to check
   * @param role role to check for
   * @return boolean tells if user is authenticated or not
   */
  public boolean authenticate(String username, String password, String role) {
    return userRepository.existsByUsername(username) &&
        userRepository.findOneByUsername(username).getPassword().equals(password) &&
        new UserDTO(userRepository.findOneByUsername(username)).hasRole(role) ;
  }

  /**
   * Adds a new user
   * @param username username to add
   * @param password password to put in user
   * @param role role to give user
   * @return String message telling result of action
   */
  public String addUser(String username, String password, String role) {
    if(userRepository.existsByUsername(username))
      return "Username in use";
    if(!role.equals("USER") && !role.equals("ADMIN"))
      return "Invalid role";
    User user = new User(username,password);
    new UserDTO(user).addRole("USER");
    if (role.equals("ADMIN"))
      new UserDTO(user).addRole("ADMIN");
    userRepository.save(user);
    return "User created";
  }

  /**
   * Deletes a user
   * @param username username to delete
   * @return String message telling result of action
   */
  public String deleteUser(String username) {
    if (userRepository.existsByUsername(username)) {
      User user = userRepository.findOneByUsername(username);
      userRepository.delete(user);
      return "User deleted";
    }
    return "Could not find user.";
  }

  /**
   * finds all users and hides passwords
   * @return List of User
   */
  public List<User> findAll() {
    List<User> users = userRepository.findAll();
    users.forEach(user -> user.setPassword("XXXXXXX"));
    return users;
  }

}
