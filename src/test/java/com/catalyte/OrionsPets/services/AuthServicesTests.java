package com.catalyte.OrionsPets.services;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import com.catalyte.OrionsPets.DTOs.UserDTO;
import com.catalyte.OrionsPets.models.User;
import com.catalyte.OrionsPets.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

public class AuthServicesTests {

  private AuthServices classToTest;

  @Mock
  private
  UserRepository userRepoMock;

  @Before
  public void before() {
    initMocks(this);
    classToTest = new AuthServices(userRepoMock);
  }

  @Test
  public void authenticateHappyPath() {
    User user = new User("username", "password");
    UserDTO userDTO = new UserDTO(user);
    userDTO.addRole("role");
    doReturn(true).when(userRepoMock).existsByUsername(user.getUsername());
    doReturn(user).when(userRepoMock).findOneByUsername(user.getUsername());
    boolean result = classToTest.authenticate(user.getUsername(), user.getPassword(), "role");
    assertTrue(result);
  }

  @Test
  public void addUserHappyPath() {
    assertEquals("User created", classToTest.addUser("","","ADMIN"));
  }

  @Test
  public void addUserSadPaths() {
    assertEquals("Invalid role", classToTest.addUser("","",""));
    doReturn(true).when(userRepoMock).existsByUsername("");
    assertEquals("Username in use", classToTest.addUser("","","ADMIN"));
  }

  @Test
  public void deleteUserHappyPath() {
    doReturn(true).when(userRepoMock).existsByUsername("");
    assertEquals("User deleted", classToTest.deleteUser(""));
  }

  @Test
  public void deleteUserSadPath() {
    assertEquals("Could not find user.", classToTest.deleteUser(""));
  }

  @Test
  public void findAllHappyPath() {
    doReturn(Arrays.asList(new User("orion","password"))).when(userRepoMock).findAll();
    assertEquals("XXXXXXX",classToTest.findAll().get(0).getPassword());
  }


}
