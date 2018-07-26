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
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

public class PetServiceTest {

  private PetService classToTest;

  @Mock
  private PetTypeRepository typeRepoMock;
  @Mock
  private PetRepository petRepoMock;
  @Mock
  private InventoryRepository invRepoMock;

  @Before
  public void before() {
    initMocks(this);
    classToTest = new PetService(typeRepoMock, invRepoMock, petRepoMock);
  }

  @Test
  public void searchPetsHappyPath() {
    ArrayList<Pet> pets = new ArrayList<>();
    pets.add(new Pet("this", "is", 1, "dumb", "pet"));
    String value = "example";
    String type = "name";
    doReturn(pets).when(petRepoMock).findByName(value);
    List<Pet> result = classToTest.searchPets(type, value);
    assertEquals(pets, result);
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
    pet.setId("DUMMY_ID_STRING_4321");
    PetType petType = new PetType("dog");
    petType.setId("DUMMY_ID_STRING_1234");
    doReturn(true).when(typeRepoMock).existsByType(pet.getPetTypeId());
    doReturn(petType).when(typeRepoMock).findByType(pet.getPetTypeId());
    doReturn(new Inventory()).when(invRepoMock).findByPetTypeId(petType.getId());
    doReturn(true).when(petRepoMock).existsById(pet.getId());
    doReturn(pet).when(petRepoMock).findOneById(pet.getId());
    String result = classToTest.updatePet(pet);
    assertEquals("Pet updated", result);
  }

  @Test
  public void deletePetHappyPath() {
    Pet pet = new Pet();
    pet.setId("idString");
    pet.setSold(true);
    doReturn(true).when(petRepoMock).existsById(pet.getId());
    doReturn(pet).when(petRepoMock).findOneById(pet.getId());
    String result = classToTest.deletePet(pet.getId());
    assertEquals("Pet deleted", result);
  }

}




