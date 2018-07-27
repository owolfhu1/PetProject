package com.catalyte.OrionsPets.services;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */

import com.catalyte.OrionsPets.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServices {

    private PurchaseRepository purchaseRepository;
    private PetRepository petRepository;
    private PetTypeRepository petTypeRepository;
    private InventoryRepository inventoryRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public PurchaseServices(PurchaseRepository purchaseRepository, PetRepository petRepository,
                            PetTypeRepository petTypeRepository, InventoryRepository inventoryRepository,
                            CustomerRepository customerRepository) {
        this.purchaseRepository = purchaseRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
        this.inventoryRepository = inventoryRepository;
        this.customerRepository = customerRepository;
    }





}
