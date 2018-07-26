package com.catalyte.OrionsPets.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import com.catalyte.OrionsPets.models.Customer;
import com.catalyte.OrionsPets.services.AuthenticationServices;
import com.catalyte.OrionsPets.services.CustomerServices;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CustomerControllertests {

  @InjectMocks
  CustomerController classToTest;

  @Mock
  CustomerServices custServMock;

  @Mock
  AuthenticationServices authServMock;

  @Before
  public void before() {
    initMocks(this);
    doReturn(true).when(authServMock).authenticate("user","pass","ADMIN");
  }

  @Test
  public void searchHappyPath() {
    List<Customer> expected = Arrays.asList(new Customer(),new Customer());
    doReturn(expected).when(custServMock).searchCustomers("test","test");
    List<Customer> result = classToTest.searchCustomers("test","test");
    assertEquals(expected,result);
  }

  @Test
  public void createCustomerHappyPath() {
    Customer customer = new Customer();
    doReturn(true).when(custServMock).createCustomer(customer);
    String result = classToTest.createCustomer("user","pass",customer);
    assertEquals("Customer added", result);
  }

  @Test
  public void createCustomerDepressedPath() {
    Customer customer = new Customer();
    doReturn(false).when(custServMock).createCustomer(customer);
    String result = classToTest.createCustomer("user","pass",customer);
    assertNotEquals("Customer added", result);
  }

  @Test
  public void updateCustomerHappyPath() {

  }

  @Test
  public void deleteCustomerHappyPath() {

  }

}
