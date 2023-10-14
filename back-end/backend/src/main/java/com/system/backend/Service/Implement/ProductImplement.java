package com.system.backend.Service.Implement;

import com.system.backend.Dto.ProductDTO;
import com.system.backend.Dto.UserDTO;
import com.system.backend.Enity.Product;
import com.system.backend.Repository.ProductRepo;
import com.system.backend.Repository.UserRepo;
import com.system.backend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductImplement implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public String insertProduct(Integer user_id, ProductDTO productDTO) {
        Product pExist = productRepo.findProductByProduct_id(user_id);
        if( pExist == null){
            Product p = new Product(null,
                    userRepo.findByUser_id(user_id),
                    productDTO.getProduct_name(),
                    productDTO.getPrice(),
                    productDTO.getImage(),
                    productDTO.getDescription(),
                    productDTO.getDate(),
                    productDTO.getPhone(),
                    productDTO.getType()
            );
            productRepo.save(p);
        } else{
            return "San pham da ton tai";
        }
        return "Them san pham thanh cong";
    }

    @Override
    public String deleteProduct(Integer user_id, Integer product_id) {
        return null;
    }

    @Override
    public String updateProduct(Integer user_id, UserDTO userDTO) {
        return null;
    }

    @Override
    public List<Product> getProducts(Integer user_id) {
        return null;
    }
}
