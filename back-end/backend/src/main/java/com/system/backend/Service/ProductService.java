package com.system.backend.Service;

import com.system.backend.Dto.Product.ProductRequest;
import com.system.backend.Entity.Product;

import java.util.List;

public interface ProductService {
    String insertProduct(Integer user_id, ProductRequest productRequest);
    String deleteProduct(Integer user_id, Integer product_id);
    String updateProduct(Integer user_id,Integer product_id, ProductRequest productRequest);
    List<Product> getProducts(Integer user_id);
}

