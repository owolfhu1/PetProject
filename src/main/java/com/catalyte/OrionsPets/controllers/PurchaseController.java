package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.services.AuthenticationServices;
import com.catalyte.OrionsPets.services.PurchaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */
@RestController
@RequestMapping(value = "purchases")
public class PurchaseController {

    private PurchaseServices purchaseServices;
    private AuthenticationServices authenticationServices;

    @Autowired
    public PurchaseController(PurchaseServices purchaseServices,
                              AuthenticationServices authenticationServices) {
        this.purchaseServices = purchaseServices;
        this.authenticationServices = authenticationServices;
    }


}
