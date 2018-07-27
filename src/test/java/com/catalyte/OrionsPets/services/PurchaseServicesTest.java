package com.catalyte.OrionsPets.services;

import com.catalyte.OrionsPets.repositories.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */
public class PurchaseServicesTest {

    @InjectMocks
    private PurchaseServices classToTest;

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private CustomerRepository customerRepository;


    @Before
    public void before() {
        initMocks(this);
    }

    @Test public void test(){}
    @Test public void test1(){}
    @Test public void test2(){}
    @Test public void test3(){}
    @Test public void test4(){}
    @Test public void test5(){}
    @Test public void test6(){}

}
