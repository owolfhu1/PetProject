package com.catalyte.OrionsPets.DTOs;

import com.catalyte.OrionsPets.models.User;

public class UserDTO {

  private User user;

  public UserDTO(User user) {
    this.user = user;
  }

  /**
   * Adds a role to user
   * @param role role to add
   */
  public void addRole(String role) {
    String[] newRoles = new String[user.getRoles().length + 1];
    System.arraycopy(user.getRoles(), 0, newRoles, 0, user.getRoles().length);
    newRoles[user.getRoles().length] = role;
    user.setRoles(newRoles);
  }

  /**
   * checks if role exists
   * @param role role to check for
   * @return
   */
  public boolean hasRole(String role) {
    for(String string : user.getRoles())
      if (string.equals(role))
        return true;
    return false;
  }

}
