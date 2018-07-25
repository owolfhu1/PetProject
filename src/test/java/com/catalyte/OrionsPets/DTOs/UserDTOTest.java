package com.catalyte.OrionsPets.DTOs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.catalyte.OrionsPets.models.User;
import org.junit.Test;

public class UserDTOTest {

  private User user = new User("name","pass");
  private UserDTO classToTest = new UserDTO(user);

  @Test
  public void happyUserDtoTest() {
    classToTest.addRole("test");
    assertTrue(classToTest.hasRole("test"));
  }

  @Test
  public void sadUserDtoTest() {
    assertFalse(classToTest.hasRole("role that I don't have"));
  }

}
