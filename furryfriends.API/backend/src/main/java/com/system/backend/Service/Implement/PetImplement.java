package com.system.backend.Service.Implement;

import com.system.backend.Dto.Pet.PetRequest;
import com.system.backend.Dto.Pet.PetResponse;
import com.system.backend.Dto.Product.ProductResponse;
import com.system.backend.Entity.*;
import com.system.backend.Repository.ImgPetRepository;
import com.system.backend.Repository.PetRepository;
import com.system.backend.Repository.UserRepository;
import com.system.backend.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PetImplement implements PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImgPetRepository imgPetRepository;
    public String getDateNow() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }
    @Override
    public String insertPet(String account, PetRequest petRequest) {
        //xu li date
        petRequest.setDate(getDateNow());

        // khong truyen tham so product_id
        String mess = "";
        User u = userRepository.findUserByAccount(account);
        if (u != null) { // ton tai
            Pet p = Pet.builder()
                    .user(u)
                    .pet_name(petRequest.getPet_name())
                    .pet_price(petRequest.getPet_price())
                    .development_stage(petRequest.getDevelopment_stage())
                    .description(petRequest.getDescription())
                    .date(petRequest.getDate())
                    .phone(petRequest.getPhone())
                    .type(petRequest.getType())
                    .title(petRequest.getTitle())
                    .address(petRequest.getAddress())
                    .status(true)
                    .build();
            if(p!=null){
                petRepository.save(p);
                mess = "Them pet thanh cong";
                if(petRequest.getImg_petList().size() > 0) {
                    for (String image : petRequest.getImg_petList()) {
                        Img_Pet img = Img_Pet.builder()
                                .pet(p)
                                .src(image)
                                .build();
                        imgPetRepository.save(img);
                    }
                }

            } else{
                mess = "Nguoi dung chua nhap du lieu";
            }

        } else{
            mess = "Nguoi dung khong ton tai";
        }


        return mess;
    }
    private Pet getPetFromTableUser(String account, Integer pet_id){
        User user = userRepository.findUserByAccount(account);
        Pet pet = null;
        if(user != null){
            pet = petRepository.findPetsByUser_idAndPet_id(pet_id, user.getUser_id());
        }
        return pet;
    }

    @Override
    public String deletePet(String account, Integer pet_id) {
        String mess = "";

        Pet pet = this.getPetFromTableUser(account, pet_id);
        if (pet != null) {
            imgPetRepository.deleteAllByPet_id(pet.getPet_id());
            petRepository.delete(pet);
            mess = "Xoa thanh cong";
        } else{
            mess = "pet not exist";
        }
        return mess;
    }

    public void updatePetDetail(Pet pet, PetRequest petRequest){
        pet.setPet_name(petRequest.getPet_name());
        pet.setDevelopment_stage(petRequest.getDevelopment_stage());
        pet.setPet_price(petRequest.getPet_price());
        pet.setDescription(petRequest.getDescription());
        pet.setDate(getDateNow());
        pet.setPhone(petRequest.getPhone());
        pet.setType(petRequest.getType());
        pet.setTitle(petRequest.getTitle());
        pet.setAddress(petRequest.getAddress());
        pet.setStatus(true);

        petRepository.save(pet);

        if(petRequest.getImg_petList().size() > 0) { // save vao table Img_Product
            // remove cu
            imgPetRepository.deleteAllByPet_id(pet.getPet_id());
            //add cai moi
            for (String image : petRequest.getImg_petList()) {
                Img_Pet img = Img_Pet.builder()
                        .pet(pet)
                        .src(image)
                        .build();
                imgPetRepository.save(img);
            }
        } else{
            imgPetRepository.deleteAllByPet_id(pet.getPet_id());
        }
    }

    @Override
    public String updatePet(String account, Integer pet_id, PetRequest petRequest) {
        String mess = "";
        Pet pet = this.getPetFromTableUser(account, pet_id);
        if (pet != null) {
            updatePetDetail(pet, petRequest);
            mess = "Cap nhat thanh cong";
        } else{
            mess = "pet not exist";
        }
        return mess;
    }

    @Override
    public List<PetResponse> getAllPets_desc() {
        List<Pet> list = new ArrayList<>();
        List<PetResponse> listConvert = new ArrayList<>();
        list = petRepository.findAllSORT_DESC();
        for (Pet p:
                list) {
            listConvert.add(convertPetToPetResponse(p));
        }
        return listConvert;
    }
    @Override
    public List<PetResponse> getAllPets_asc() {
        List<Pet> list = new ArrayList<>();
        List<PetResponse> listConvert = new ArrayList<>();
        list = petRepository.findAllSORT_ASC();
        for (Pet p:
                list) {
            listConvert.add(convertPetToPetResponse(p));
        }
        return listConvert;
    }

    @Override
    public List<PetResponse> getPetsBySearch(String q) {
        List<Pet> list = new ArrayList<>();
        List<PetResponse> listConvert = new ArrayList<>();
        list = petRepository.findPetsByTitle(q);
        for (Pet p:
                list) {
            listConvert.add(convertPetToPetResponse(p));
        }
        return listConvert;
    }

    @Override
    public List<PetResponse> getPets(String account) {
        List<Pet> list = new ArrayList<>();
        List<PetResponse> listConvert = new ArrayList<>();
        User u = userRepository.findUserByAccount(account);
        if(u!=null){
            list = petRepository.findPetsByUser_id(u.getUser_id());
            for (Pet p:
                    list) {
                listConvert.add(convertPetToPetResponse(p));
            }
        }
        return listConvert;
    }

    @Override
    public List<PetResponse> getPetsByType_desc(String type) {
        List<Pet> list = new ArrayList<>();
        List<PetResponse> listConvert = new ArrayList<>();
        list = petRepository.findPetsByTypeSORT_DESC(type);
        for (Pet p:
                list) {
            listConvert.add(convertPetToPetResponse(p));
            }
        return listConvert;
    }
    @Override
    public List<PetResponse> getPetsByType_asc(String type) {
        List<Pet> list = new ArrayList<>();
        List<PetResponse> listConvert = new ArrayList<>();
        list = petRepository.findPetsByTypeSORT_ASC(type);
        for (Pet p:
                list) {
            listConvert.add(convertPetToPetResponse(p));
        }
        return listConvert;
    }

    @Override
    public PetResponse getPetFromUser(String account, Integer pet_id) {
        PetResponse petResponse = new PetResponse();
        User user = userRepository.findUserByAccount(account);
        if(user != null){
            Pet pet = petRepository.findPetByPet_id(pet_id);
            if(pet != null){
                petResponse = convertPetToPetResponse(pet);
            }
        }
        return petResponse;
    }

    @Override
    public PetResponse getPet(Integer pet_id) {
        Pet pet = petRepository.findPetByPet_id(pet_id);
        PetResponse petResponse = new PetResponse();
        if(petResponse != null){
            petResponse = convertPetToPetResponse(pet);

        }
        return petResponse;
    }
    public PetResponse convertPetToPetResponse(Pet pet){
        if(pet == null){
            return PetResponse.builder().build();
        }
        // convert List<Img_Product> to List<String>
        List<String> img_stringList = new ArrayList<>();
        if(pet.getImg_petList().size() > 0){
            for (Img_Pet img_pet: pet.getImg_petList()){
                img_stringList.add(img_pet.getSrc());
            }
        }
        return PetResponse.builder()
                .pet_id(pet.getPet_id())
                .user_id(pet.getUser().getUser_id())
                .account(pet.getUser().getAccount())
                .user_name(pet.getUser().getFirst_name() + " " + pet.getUser().getLast_name())
                .pet_name(pet.getPet_name())
                .pet_price(pet.getPet_price())
                .development_stage(pet.getDevelopment_stage())
                .type(pet.getType())
                .img_petList(img_stringList)
                .description(pet.getDescription())
                .date(pet.getDate())
                .phone(pet.getPhone())
                .title(pet.getTitle())
                .address(pet.getAddress())
                .status(pet.isStatus())
                .build();
    }
}
