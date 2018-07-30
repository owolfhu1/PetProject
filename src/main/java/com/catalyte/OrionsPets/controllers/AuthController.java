package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.models.User;
import com.catalyte.OrionsPets.services.AuthServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Orion Wolf_Hubbard on 7/29/2018.
 */
@RestController
@RequestMapping(value = "auth")
public class AuthController {

    private AuthServices authServices;

    @Autowired
    public AuthController(AuthServices authServices) {
        this.authServices = authServices;
    }

    /**
     * Adds a new user
     * @param username login username
     * @param password login password
     * @param newUsername new user username
     * @param newPassword new user password
     * @param role role of new user
     * @return String message telling result of action
     */
    @ApiOperation("Add a user: username + password + newUsername + newPassword + role in header. Access level: ADMIN")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(@RequestHeader String username, @RequestHeader String password
    ,@RequestHeader String newUsername, @RequestHeader String newPassword,
                          @RequestHeader String role) {
        return authServices.authenticate(username,password,"ADMIN") ?
                authServices.addUser(newUsername,newPassword,role) : "Access denied";
    }

  /**
   * Deletes a user
   * @param username login username
   * @param password login password
   * @param usernameToDelete username to be deleted
   * @return String message telling result of action
   */
    @ApiOperation("delete a user: username + password + usernameToDelete in header. Access level: ADMIN")
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String deleteUser(@RequestHeader String username, @RequestHeader String password
            ,@RequestHeader String usernameToDelete) {
        return authServices.authenticate(username,password,"ADMIN") ?
                authServices.deleteUser(usernameToDelete) : "Access denied";
    }

  /**
   * finds all users, hides passwords
   * @return List of User
   */
  @ApiOperation("Navigate here to find all users. Access level: none")
    @RequestMapping(value = "find-all", method = RequestMethod.GET)
    public List<User> findAll() {
        return authServices.findAll();
    }

}
