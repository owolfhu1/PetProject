package com.catalyte.OrionsPets.controllers;

import com.catalyte.OrionsPets.services.AuthenticationServices;
import com.catalyte.OrionsPets.services.PurchaseServices;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Orion Wolf_Hubbard on 7/27/2018.
 */
public class PurchaseControllerTest {

    @InjectMocks
    private PurchaseController classToTest;

    @Mock
    private PurchaseServices purchServMock;

    @Mock
    private AuthenticationServices authServMock;

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
