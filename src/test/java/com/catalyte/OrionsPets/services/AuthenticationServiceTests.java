package com.catalyte.OrionsPets.services;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import com.catalyte.OrionsPets.DTOs.UserDTO;
import com.catalyte.OrionsPets.models.User;
import com.catalyte.OrionsPets.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AuthenticationServiceTests {

  private AuthenticationService classToTest;

  @Mock
  private
  UserRepository userRepoMock;

  @Before
  public void before() {
    initMocks(this);
    classToTest = new AuthenticationService(userRepoMock);
  }

  @Test
  public void authenticateHappyPath() {
    User user = new User("username","password");
    UserDTO userDTO = new UserDTO(user);
    userDTO.addRole("role");
    doReturn(true).when(userRepoMock).existsByUsername(user.getUsername());
    doReturn(user).when(userRepoMock).findOneByUsername(user.getUsername());
    boolean result = classToTest.authenticate(user.getUsername(),user.getPassword(),"role");
    assertTrue(result);
  }

}
