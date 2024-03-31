package com.system.backend.Controller.Pet;

import com.system.backend.Dto.Pet.PetRequest;
import com.system.backend.Dto.Pet.PetResponse;
import com.system.backend.Dto.Product.ProductRequest;
import com.system.backend.Dto.Product.ProductResponse;
import com.system.backend.Service.PetService;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT + Link.USER.PET)
public class PetController {
    // view all pets
    // view 1 pet detail by id
    // view all pets by account
    // delete pet by account, id
    // edit pet by account, id
    // get by type ( chó và mèo)
    // search by q = value
    @Autowired
    private PetService petService;
    @GetMapping(Link.USER.PET_CRUD.GETBYSEARCH)
    public ResponseEntity<List<PetResponse>> getBySearch(@RequestParam(required = false) String q)
    {
        List<PetResponse> list = petService.getPetsBySearch(q);
        return ResponseEntity.ok(list);
    }

    // moi nhat cua get type va get all
    @GetMapping(Link.USER.PET_CRUD.GETTYPE_DESC)
    public ResponseEntity<List<PetResponse>> getByType_desc(@PathVariable String type)
    {
        List<PetResponse> list = petService.getPetsByType_desc(type);
        return ResponseEntity.ok(list);
    }

    @GetMapping(Link.USER.PET_CRUD.GETALLL_DESC)
    public ResponseEntity<List<PetResponse>> getAllPets_desc()
    {
        List<PetResponse> list = petService.getAllPets_desc();
        return ResponseEntity.ok(list);
    }
     // cu nhat cua get type va get all
    @GetMapping(Link.USER.PET_CRUD.GETTYPE_ASC)
    public ResponseEntity<List<PetResponse>> getByType_asc(@PathVariable String type)
    {
        List<PetResponse> list = petService.getPetsByType_asc(type);
        return ResponseEntity.ok(list);
    }

    @GetMapping(Link.USER.PET_CRUD.GETALLL_ASC)
    public ResponseEntity<List<PetResponse>> getAllPets_asc()
    {
        List<PetResponse> list = petService.getAllPets_asc();
        return ResponseEntity.ok(list);
    }



    @GetMapping(Link.USER.PET_CRUD.GETALLFROMUSER)
    public ResponseEntity<List<PetResponse>> getAllPetFromUser(@PathVariable String account)
    {
        List<PetResponse> listFromUser = petService.getPets (account);
        return ResponseEntity.ok(listFromUser);
    }
    @GetMapping(Link.USER.PET_CRUD.GET)
    public ResponseEntity<PetResponse> getPetFromUser( @PathVariable Integer pet_id )
    {
        PetResponse petResponse = petService.getPet(pet_id);
        return ResponseEntity.ok(petResponse);
    }

    @PutMapping(Link.USER.PET_CRUD.PUT)
    public String updatePet(@PathVariable String account,
                                @PathVariable Integer pet_id,
                                @RequestBody PetRequest petRequest)
    {
        String mess = petService.updatePet(account,pet_id, petRequest);
        return mess;
    }

    @DeleteMapping(Link.USER.PET_CRUD.DELETE)
    public String deletePet(@PathVariable String account,
                                @PathVariable Integer pet_id)
    {
        String mess = petService.deletePet(account, pet_id);
        return mess;
    }

    @PostMapping(Link.USER.PET_CRUD.POST)
    public String addPet(
            @PathVariable String account, @RequestBody PetRequest petRequest){
        String mess = petService.insertPet(account,petRequest);
        return mess;
    }
}
