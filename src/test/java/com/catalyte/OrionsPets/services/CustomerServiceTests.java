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

import junit.framework.TestCase;
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
  public void searchCustomersHappyPaths() {
    List<Customer> list = new ArrayList<>();
    list.add(new Customer());
    doReturn(list).when(custRepoMock).findByPhone("value");
    doReturn(list).when(custRepoMock).findByAddress("value");
    doReturn(list).when(custRepoMock).findByLastname("value");
    doReturn(list).when(custRepoMock).findByFirstname("value");
    List<Customer> phones = classToTest.searchCustomers("phone","value");
    List<Customer> addresses = classToTest.searchCustomers("address","value");
    List<Customer> lastnames = classToTest.searchCustomers("lastname","value");
    List<Customer> firstnames = classToTest.searchCustomers("firstname","value");
    assertEquals(list,phones);
    assertEquals(list,addresses);
    assertEquals(list,lastnames);
    assertEquals(list,firstnames);
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
  public void findAllHappyPath() {
    doReturn(new ArrayList()).when(custRepoMock).findAll();
    TestCase.assertEquals(new ArrayList(), classToTest.findAll());
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
    Customer customer = new Customer("Orion","","(111) 111-1111","111 sddr drtg");
    Customer customer2 = new Customer("","Wolf","(111) 111-1111","111 sddr drtg");
    Customer customer3 = new Customer("Orion","Wolf","() 111-1111","111 sddr drtg");
    customer.setId("test");
    doReturn(true).when(custRepoMock).existsById("test");
    boolean result1 = classToTest.updateCustomer(customer,"test");
    boolean result2 = classToTest.updateCustomer(customer2,"test");
    boolean result3 = classToTest.updateCustomer(customer3,"test");
    assertFalse(result1);
    assertFalse(result2);
    assertFalse(result3);
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
