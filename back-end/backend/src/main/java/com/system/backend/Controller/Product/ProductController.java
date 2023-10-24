package com.system.backend.Controller.Product;

import com.system.backend.Dto.Product.ProductRequest;
import com.system.backend.Dto.Product.ProductResponse;
import com.system.backend.Dto.User.AccountRequest;
import com.system.backend.Entity.Product;
import com.system.backend.Entity.User;
import com.system.backend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/user/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public String insertProduct(@RequestBody ProductRequest productRequest)
    {
        String mess = productService.insertProduct(productRequest.getAccount(),productRequest);
        return mess;
    }
    @DeleteMapping("/{product_id}")
    public String deleteProduct(@PathVariable Integer product_id, @RequestBody ProductRequest productRequest)
    {
        String mess = productService.deleteProduct(productRequest.getAccount(), product_id);
        return mess;
    }
    @PutMapping("/{product_id}")
    public String updateProduct(@PathVariable Integer product_id,@RequestBody ProductRequest productRequest)
    {
        String mess = productService.updateProduct(productRequest.getAccount(),product_id, productRequest);
        return mess;
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(@RequestBody ProductRequest productRequest)
    {
        List<ProductResponse> list = productService.getAllProducts(productRequest.getAccount());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{product_id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Integer product_id)
    {
        ProductResponse product = productService.getProduct(product_id);
        return ResponseEntity.ok(product);
    }
}
