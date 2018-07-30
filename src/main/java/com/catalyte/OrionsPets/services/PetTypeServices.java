package com.catalyte.OrionsPets.services;
import com.catalyte.OrionsPets.models.PetType;
import com.catalyte.OrionsPets.repositories.PetTypeRepository;
import com.catalyte.OrionsPets.resorces.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Orion Wolf_Hubbard on 7/28/2018.
 */
@Service
public class PetTypeServices {

  private PetTypeRepository petTypeRepository;

  @Autowired
  public PetTypeServices(PetTypeRepository petTypeRepository) {
    this.petTypeRepository = petTypeRepository;
  }

  /**
   * finds all petTypes
   *
   * @return List of PetType
   */
  public List<PetType> findAll() {
    return petTypeRepository.findAll();
  }

  /**
   * Search for specific petType
   *
   * @param category category to search in
   * @param searchTerm term to search for
   * @return a PetType
   * @throws DataNotFoundException if petType isn't found
   */
  public PetType search(String category, String searchTerm) throws DataNotFoundException {
    switch (category) {
      case "type":
        if (petTypeRepository.existsByType(searchTerm))
          return petTypeRepository.findByType(searchTerm);
        throw new DataNotFoundException();
      case "id":
        if (petTypeRepository.existsById(searchTerm))
          return petTypeRepository.findOneById(searchTerm);
        throw new DataNotFoundException();
      default:
        throw new DataNotFoundException();
    }
  }

}
