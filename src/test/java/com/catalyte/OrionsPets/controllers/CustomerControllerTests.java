package com.catalyte.OrionsPets.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import com.catalyte.OrionsPets.models.Customer;
import com.catalyte.OrionsPets.services.AuthenticationServices;
import com.catalyte.OrionsPets.services.CustomerServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CustomerControllerTests {

  @InjectMocks
  CustomerController classToTest;

  @Mock
  CustomerServices custServMock;

  @Mock
  AuthenticationServices authServMock;

  private String USER = "user";
  private String PASS = "pass";

  @Before
  public void before() {
    initMocks(this);
    doReturn(true).when(authServMock).authenticate(USER, PASS, "ADMIN");
  }

  @Test
  public void findAllHappyPath() {
    doReturn(new ArrayList()).when(custServMock).findAll();
    TestCase.assertEquals(new ArrayList(), classToTest.findAll());
  }

  @Test
  public void searchHappyPath() {
    List<Customer> expected = Arrays.asList(new Customer(), new Customer());
    doReturn(expected).when(custServMock).searchCustomers("test", "test");
    List<Customer> result = classToTest.searchCustomers("test", "test");
    assertEquals(expected, result);
  }

  @Test
  public void createCustomerHappyPath() {
    Customer customer = new Customer();
    doReturn(true).when(custServMock).createCustomer(customer);
    String result = classToTest.createCustomer(USER, PASS, customer);
    assertEquals("Customer added", result);
  }

  @Test
  public void createCustomerDepressedPath() {
    Customer customer = new Customer();
    doReturn(false).when(custServMock).createCustomer(customer);
    String result = classToTest.createCustomer(USER, PASS, customer);
    assertNotEquals("Customer added", result);
  }

  @Test
  public void updateCustomerHappyPath() {
    Customer customer = new Customer();
    doReturn(true).when(custServMock).updateCustomer(customer);
    String result = classToTest.updateCustomer(USER,PASS,customer);
    assertEquals("Customer updated",result);
  }

  @Test
  public void deleteCustomerHappyPath() {
    doReturn(true).when(custServMock).deleteCustomer("id");
    String result = classToTest.deleteCustomer(USER,PASS,"id");
    assertEquals("Customer deleted",result);
  }

}
