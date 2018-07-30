package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.services.AuthServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("Add a user: username + password + newUsername + newPassword + role in header. Access level: ADMIN")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(@RequestHeader String username, @RequestHeader String password
    ,@RequestHeader String newUsername, @RequestHeader String newPassword,
                          @RequestHeader String role) {
        return authServices.authenticate(username,password,"ADMIN") ?
                authServices.addUser(newUsername,newPassword,role) : "Access denied";
    }

    @ApiOperation("delete a user: username + password + usernameToDelete in header. Access level: ADMIN")
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String deleteUser(@RequestHeader String username, @RequestHeader String password
            ,@RequestHeader String usernameToDelete) {
        return authServices.authenticate(username,password,"ADMIN") ?
                authServices.deleteUser(usernameToDelete) : "Access denied";
    }

}
