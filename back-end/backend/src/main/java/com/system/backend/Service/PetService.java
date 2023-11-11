package com.system.backend.Service;

import com.system.backend.Dto.Pet.PetRequest;
import com.system.backend.Dto.Pet.PetResponse;
import com.system.backend.Dto.Product.ProductRequest;
import com.system.backend.Dto.Product.ProductResponse;

import java.util.List;

public interface PetService {
    String insertPet(String account, PetRequest PetRequest);
    String deletePet(String account, Integer pet_id);
    String updatePet(String account, Integer pet_id, PetRequest PetRequest);
    List<PetResponse> getAllPets_desc();
    List<PetResponse> getAllPets_asc();
    List<PetResponse> getPetsBySearch(String q);
    List<PetResponse> getPets(String account);
    List<PetResponse> getPetsByType_desc(String type);
    List<PetResponse> getPetsByType_asc(String type);
    PetResponse getPetFromUser(String account, Integer pet_id);
    PetResponse getPet(Integer pet_id);
}
