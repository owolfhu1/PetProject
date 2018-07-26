package com.catalyte.OrionsPets.services;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import com.catalyte.OrionsPets.models.Customer;
import com.catalyte.OrionsPets.repositories.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CustomerServiceTests {

  @InjectMocks
  CustomerServices classToTest;

  @Mock
  CustomerRepository custRepoMock;

  @Before
  public void before() {
    initMocks(this);
  }

  @Test
  public void searchCustomersHappyPath() {
    List<Customer> list = new ArrayList<>();
    list.add(new Customer());
    doReturn(list).when(custRepoMock).findByAddress("value");
    List<Customer> result = classToTest.searchCustomers("address","value");
    assertEquals(result,list);
  }

  @Test
  public void searchCustomersSadPath() {
    List<Customer> result = classToTest.searchCustomers("bogus","value");
    assertEquals(result,new ArrayList<Customer>());
  }

  @Test
  public void createCustomerHappyPath() {
    Customer customer = new Customer("Orion","Wolf","(111) 111-1111","1111 abc drive");
    boolean result = classToTest.createCustomer(customer);
    assertTrue(result);
  }

  @Test
  public void createCustomerSadPath() {
    Customer customer = new Customer("Orion","Wolf","(111) 111-1111","1111 abc");
    boolean result = classToTest.createCustomer(customer);
    assertFalse(result);
  }

  @Test
  public void updateCustomerHappyPath() {
    Customer customer = new Customer("Orion","Wolf","(111) 111-1111","1111 abc drive");
    customer.setId("test");
    doReturn(true).when(custRepoMock).existsById("test");
    boolean result = classToTest.updateCustomer(customer,"test");
    assertTrue(result);
  }

  @Test
  public void updateCustomerSadPath() {
    Customer customer = new Customer("Orion","Wolf","(111) 111-1111","   ");
    customer.setId("test");
    doReturn(true).when(custRepoMock).existsById("test");
    boolean result = classToTest.updateCustomer(customer,"test");
    assertFalse(result);
  }

  @Test
  public void deleteCustomerHappyPath() {
    doReturn(true).when(custRepoMock).existsById("id");
    boolean result = classToTest.deleteCustomer("id");
    assertTrue(result);
  }

  @Test
  public void deleteCustomerSadPath() {
    doReturn(false).when(custRepoMock).existsById("id");
    boolean result = classToTest.deleteCustomer("id");
    assertFalse(result);
  }

}
