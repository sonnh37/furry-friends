package com.system.backend.Service.Implement;

import com.system.backend.Entity.Img_Pet;
import com.system.backend.Entity.Pet;
import com.system.backend.Entity.Product;
import com.system.backend.Entity.User;
import com.system.backend.Repository.*;
import com.system.backend.Service.ClearUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClearUserImplement implements ClearUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImgProductRepository imgProductRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private ImgPetRepository imgPetRepository;
    @Override
    public void clearDataFromUser(String account) {
        clearProduct(account);
        clearPet(account);
    }
    private void clearProduct(String account){
        User user = userRepository.findUserByAccount(account);
        if (user != null) {
            //img-product
            List<Product> productList = productRepository.findProductsByUser_idSORT_DESC(user.getUser_id());

            if(productList != null){
                for (Product p : productList) {
                    imgProductRepository.deleteAllByProduct_id(p.getProduct_id());
                    productRepository.delete(p);
                }
            }
        }
    }
    private void clearPet(String account){
        User user = userRepository.findUserByAccount(account);
        if (user != null) {
            //img-pet
            List<Pet> petList = petRepository.findPetsByUser_id(user.getUser_id());

            if(petList != null){
                for (Pet p : petList) {
                    imgPetRepository.deleteAllByPet_id(p.getPet_id());
                    petRepository.delete(p);
                }
            }
        }
    }
}
