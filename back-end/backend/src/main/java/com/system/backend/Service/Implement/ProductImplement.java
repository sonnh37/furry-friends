package com.system.backend.Service.Implement;

import com.system.backend.Dto.Product.ProductRequest;
import com.system.backend.Dto.Product.ProductResponse;
import com.system.backend.Dto.User.UserResponse;
import com.system.backend.Entity.Product;
import com.system.backend.Entity.User;
import com.system.backend.Repository.ProductRepository;
import com.system.backend.Repository.UserRepository;
import com.system.backend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductImplement implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public String insertProduct(String account, ProductRequest productRequest) {
        // khong truyen tham so product_id
        String mess = "";
        User u = userRepository.findUserByAccount(account);
        if (u != null) { // ton tai
            Product p = Product.builder()
                    .user(u)
                    .product_name(productRequest.getProduct_name())
                    .price(productRequest.getPrice())
                    .image(productRequest.getImage())
                    .description(productRequest.getDescription())
                    .date(productRequest.getDate())
                    .phone(productRequest.getPhone())
                    .type(productRequest.getType())
                    .build();
            productRepository.save(p);
            mess = "Them san pham thanh cong";
        } else{
            mess = "Nguoi dung khong ton tai";
        }


        return mess;
    }
    public Product isExistProduct(Integer product_id){
        // check sản phẩm có tồn tại trong database khong
        Product p;
        Product pExist = productRepository.findProductByProduct_id(product_id);
        if( pExist != null){ // khong
            p = pExist;
        } else{
            p = null;
        }
        return p;
    }
    public User getProductExistFromUser(String account){
        User u;
        // check sản phẩm có phải của user_id hay không
        User user = userRepository.findUserByAccount(account);
        if(user != null){
            u = user;
        } else{
            u = null;
        }
        return u;
    }


    @Override
    public String deleteProduct(String account, Integer product_id) {
        String mess = "";
        Product pExist = this.isExistProduct(product_id);
        if (pExist != null) {
            User uExist = this.getProductExistFromUser(account);
            if (uExist != null) {
                productRepository.delete(pExist);
                mess = "deleted";
            } else {
                mess = "user not exist";
            }
        } else{
            mess = "product not exist";
        }
        return mess;
    }
    public void updateProductDetail(Product pExist, ProductRequest productRequest){
        pExist.setProduct_name(productRequest.getProduct_name());
        pExist.setPrice(productRequest.getPrice());
        pExist.setImage(productRequest.getImage());
        pExist.setDescription(productRequest.getDescription());
        pExist.setDate(productRequest.getDate());
        pExist.setPhone(productRequest.getPhone());
        pExist.setType(productRequest.getType());
        productRepository.save(pExist);
    }

    @Override
    public String updateProduct(String account, Integer product_id, ProductRequest productRequest) {
        String mess = "";
        Product pExist = this.isExistProduct(product_id);
        if (pExist != null) {
            User uExist = this.getProductExistFromUser(account);
            if (uExist != null) {
                // cap nhat
                this.updateProductDetail(pExist, productRequest);

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
    public List<ProductResponse> getAllProducts(String account) {
        List<Product> list = new ArrayList<>();
        List<ProductResponse> listConvert = new ArrayList<>();
        User uExist = this.getProductExistFromUser(account);
        if (uExist != null) {
            list = productRepository.findProductsByUser_id(uExist.getUser_id());
            for (Product p:
                 list) {
                listConvert.add(convertProdductToProductResponse(p));
            }
        }
        return listConvert;
    }

    @Override
    public ProductResponse getProduct(Integer product_id) {
        Product p = isExistProduct(product_id);
        return convertProdductToProductResponse(p);
    }

    public ProductResponse convertProdductToProductResponse(Product product){
        if(product == null){
            return ProductResponse.builder().build();
        }
        return ProductResponse.builder()
                .product_id(product.getProduct_id())
                .user_id(product.getUser().getUser_id())
                .product_name(product.getProduct_name())
                .price(product.getPrice())
                .image(product.getImage())
                .description(product.getDescription())
                .date(product.getDate())
                .phone(product.getPhone())
                .type(product.getType())
                .build();
    }
}
