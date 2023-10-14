package com.system.backend.Controller;

import com.system.backend.Dto.LoginDTO;
import com.system.backend.Dto.ProductDTO;
import com.system.backend.Enity.Product;
import com.system.backend.Service.ProductService;
import com.system.backend.Service.UserService;
import com.system.backend.payload.response.LoginMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/user/")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("{user_id}/products")
    public String insertProduct(@PathVariable Integer user_id,@RequestBody ProductDTO productDTO)
    {
        String mess = productService.insertProduct(user_id, productDTO);
        return mess;
    }
    @DeleteMapping("{user_id}/products/{product_id}")
    public String deleteProduct(@PathVariable Integer user_id,@PathVariable Integer product_id)
    {
        String mess = productService.deleteProduct(user_id, product_id);
        return mess;
    }
    @PutMapping("{user_id}/products/{product_id}")
    public String updateProduct(@PathVariable Integer user_id,@PathVariable Integer product_id,@RequestBody ProductDTO productDTO)
    {
        String mess = productService.updateProduct(user_id,product_id,productDTO);
        return mess;
    }
    @GetMapping("{user_id}/products")
    public ResponseEntity<List<Product>> getProducts(@PathVariable Integer user_id)
    {
        List<Product> list = productService.getProducts(user_id);
        return ResponseEntity.ok(list);
    }
}
