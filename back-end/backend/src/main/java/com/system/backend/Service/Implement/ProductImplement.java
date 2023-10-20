package com.system.backend.Service.Implement;

import com.system.backend.Dto.Product.ProductDTO;
import com.system.backend.Entity.Product;
import com.system.backend.Entity.User;
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
        // khong truyen tham so product_id
        String mess = "";
        User u = userRepo.findByUser_id(user_id);
        if (u != null) {
            Product p = new Product(null,
                    u,
                    productDTO.getProduct_name(),
                    productDTO.getPrice(),
                    productDTO.getImage(),
                    productDTO.getDescription(),
                    productDTO.getDate(),
                    productDTO.getPhone(),
                    productDTO.getType()
            );
            productRepo.save(p);
            mess = "Them san pham thanh cong";
        } else{
            mess = "Nguoi dung khong ton tai";
        }


        return mess;
    }
    public Product isExistProduct(Integer product_id){
        // check sản phẩm có tồn tại trong database khong
        Product p;
        Product pExist = productRepo.findProductByProduct_id(product_id);
        if( pExist != null){ // khong
            p = pExist;
        } else{
            p = null;
        }
        return p;
    }
    public User getProductExistFromUser(Integer user_id){
        User u;
        // check sản phẩm có phải của user_id hay không
        User user = userRepo.findByUser_id(user_id);
        if(user != null){
            u = user;
        } else{
            u = null;
        }
        return u;
    }


    @Override
    public String deleteProduct(Integer user_id, Integer product_id) {
        String mess = "";
        Product pExist = this.isExistProduct(product_id);
        if (pExist != null) {
            User uExist = this.getProductExistFromUser(user_id);
            if (uExist != null) {
                productRepo.delete(pExist);

                mess = "deleted";
            } else {
                mess = "user not exist";

            }
        } else{
            mess = "product not exist";
        }
        return mess;
    }
    public void updateProductDetail(Integer user_id,Integer product_id, ProductDTO productDTO){
        Product p = new Product(
                product_id,userRepo.findByUser_id(user_id),
                productDTO.getProduct_name(),
                productDTO.getPrice(),
                productDTO.getImage(),
                productDTO.getDescription(),
                productDTO.getDate(),
                productDTO.getPhone(),
                productDTO.getType()

        );
        productRepo.save(p);
    }

    @Override
    public String updateProduct(Integer user_id,Integer product_id, ProductDTO productDTO) {
        String mess = "";
        Product pExist = this.isExistProduct(product_id);
        if (pExist != null) {
            User uExist = this.getProductExistFromUser(user_id);
            if (uExist != null) {
                // cap nhat
                this.updateProductDetail(user_id,product_id,productDTO);

                mess = "updated";
            } else {
                mess = "user not exist";

            }
        } else{
            mess = "product not exist";
        }
        return mess;
    }

    @Override
    public List<Product> getProducts(Integer user_id) {
        List<Product> list;
        User uExist = this.getProductExistFromUser(user_id);
        if (uExist != null) {
            list = productRepo.findProductsByUser_id(user_id);
        } else {
            list = null;
        }
        return list;
    }
}
