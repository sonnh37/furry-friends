package com.system.backend.Service;

import com.system.backend.Dto.LoginDTO;
import com.system.backend.Dto.ProductDTO;
import com.system.backend.Dto.UserDTO;
import com.system.backend.Enity.Product;
import com.system.backend.payload.response.LoginMessage;

import java.util.List;

public interface ProductService {
    String insertProduct(Integer user_id, ProductDTO productDTO);

    String deleteProduct(Integer user_id, Integer product_id);
    String updateProduct(Integer user_id, UserDTO userDTO);
    List<Product> getProducts(Integer user_id);
}

