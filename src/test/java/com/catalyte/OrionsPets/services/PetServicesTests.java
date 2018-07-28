package com.catalyte.OrionsPets.services;

import com.catalyte.OrionsPets.models.Inventory;
import com.catalyte.OrionsPets.models.Pet;
import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.repositories.InventoryRepository;
import com.catalyte.OrionsPets.repositories.PetRepository;
import com.catalyte.OrionsPets.repositories.PetTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

public class PetServicesTests {

  private PetServices classToTest;

  @Mock
  private PetTypeRepository typeRepoMock;
  @Mock
  private PetRepository petRepoMock;
  @Mock
  private InventoryRepository invRepoMock;

  @Before
  public void before() {
    initMocks(this);
    classToTest = new PetServices(typeRepoMock, invRepoMock, petRepoMock);
  }

  @Test
  public void searchPetsHappyPath() {
    ArrayList<Pet> pets = new ArrayList<>();
    doReturn(pets).when(petRepoMock).findByName("");
    doReturn(pets).when(petRepoMock).findByColor("");
    doReturn(pets).when(petRepoMock).findBySex("");
    doReturn(pets).when(petRepoMock).findByPetTypeId("");
    doReturn(pets).when(petRepoMock).findByAge(1);
    doReturn(pets).when(petRepoMock).findBySold(true);
    List<Pet> names = classToTest.searchPets("name", "");
    assertEquals(pets, names);
    List<Pet> colors = classToTest.searchPets("color", "");
    assertEquals(pets, colors);
    List<Pet> ids = classToTest.searchPets("type", "");
    assertEquals(ids, names);
    List<Pet> ages = classToTest.searchPets("age", "1");
    assertEquals(pets, ages);
    List<Pet> sales = classToTest.searchPets("sold", "true");
    assertEquals(pets, sales);
    List<Pet> sexes = classToTest.searchPets("sex", "");
    assertEquals(pets, sexes);
  }

  @Test
  public void searchPetsSadPath() {
    assertEquals(new ArrayList<Pet>(), classToTest.searchPets("badType", "value"));
  }

  @Test
  public void createPetHappyPath() {
    Pet pet = new Pet("dog", "name", 1, "color", "male");
    PetType petType = new PetType("dog");
    petType.setId("DUMMY_ID_STRING_1234");
    doReturn(true).when(typeRepoMock).existsByType(pet.getPetTypeId());
    doReturn(petType).when(typeRepoMock).findByType(pet.getPetTypeId());
    doReturn(new Inventory()).when(invRepoMock).findByPetTypeId(petType.getId());
    String result = classToTest.createPet(pet);
    assertEquals("Pet created", result);
  }

  @Test
  public void createPetSadPath() {
    Pet pet = new Pet("", "dd", -1, "blue", "male");
    PetType petType = new PetType("");
    petType.setId("");
    doReturn(true).when(typeRepoMock).existsByType(pet.getPetTypeId());
    doReturn(petType).when(typeRepoMock).findByType(pet.getPetTypeId());
    doReturn(new Inventory()).when(invRepoMock).findByPetTypeId(petType.getId());
    String result = classToTest.createPet(pet);
    assertNotEquals("Pet created", result);
  }

  @Test
  public void updatePetHappyPath() {
    Pet pet = new Pet("dog", "name", 1, "color", "male");
    Pet oldPet = new Pet("dog", "name", 1, "color", "male");
    pet.setId("abc");
    oldPet.setSold(true);
    PetType petType = new PetType("dog");
    petType.setId("abc");
    doReturn(true).when(typeRepoMock).existsByType(pet.getPetTypeId());
    doReturn(petType).when(typeRepoMock).findByType(pet.getPetTypeId());
    doReturn(new Inventory()).when(invRepoMock).findByPetTypeId(petType.getId());
    doReturn(true).when(petRepoMock).existsById(pet.getId());
    doReturn(oldPet).when(petRepoMock).findOneById(pet.getId());
    String result = classToTest.updatePet(pet);
    assertEquals("Pet updated", result);
  }

  @Test
  public void updatePetSadPaths() {
    Pet pet = new Pet("dd", "", -1, "", "d");
    pet.setId("");
    doReturn(false).when(petRepoMock).existsById(pet.getId());
    assertEquals("Could not find pet to update. (bad petId)", classToTest.updatePet(pet));
    doReturn(true).when(petRepoMock).existsById(pet.getId());
    assertEquals("Bad pet type", classToTest.updatePet(pet));
    doReturn(true).when(typeRepoMock).existsByType(pet.getPetTypeId());
    assertEquals("Pet name required", classToTest.updatePet(pet));
    pet.setName("thisIsALongStringForTestingAStringThatIsTooLong");
    assertEquals("Pet name too long, max 15 char.", classToTest.updatePet(pet));
    pet.setName("fdg");
    assertEquals("Pet must have a positive age!!", classToTest.updatePet(pet));
    pet.setAge(1);
    assertEquals("Pet color required", classToTest.updatePet(pet));
    pet.setColor("1234567890987654321");
    assertEquals("Pet color too long, max 15 char.", classToTest.updatePet(pet));
    pet.setColor("fair");
    assertEquals("Pet sex must be male or female", classToTest.updatePet(pet));
  }

  @Test
  public void deletePetHappyPath() {
    Pet pet = new Pet();
    pet.setId("idString");
    pet.setSold(false);
    doReturn(true).when(petRepoMock).existsById(pet.getId());
    doReturn(pet).when(petRepoMock).findOneById(pet.getId());
    doReturn(new Inventory()).when(invRepoMock).findByPetTypeId(null);
    String result = classToTest.deletePet(pet.getId());
    assertEquals("Pet deleted", result);
  }

  @Test
  public void deletePetSadPath() {
    doReturn(false).when(petRepoMock).existsById("");
    assertEquals("Pet to delete not found",classToTest.deletePet(""));
  }

}




