package com.system.backend.Service;

import com.system.backend.Dto.ProductDTO;
import com.system.backend.Entity.Product;

import java.util.List;

public interface ProductService {
    String insertProduct(Integer user_id, ProductDTO productDTO);

    String deleteProduct(Integer user_id, Integer product_id);
    String updateProduct(Integer user_id,Integer product_id, ProductDTO productDTO);
    List<Product> getProducts(Integer user_id);
}

