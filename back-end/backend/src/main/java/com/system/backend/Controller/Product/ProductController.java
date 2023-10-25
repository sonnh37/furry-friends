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
    @PostMapping("/{account}")
    public String insertProduct(@PathVariable String account,
                                @RequestBody ProductRequest productRequest)
    {
        String mess = productService.insertProduct(account,productRequest);
        return mess;
    }
    @DeleteMapping("/{account}-{product_id}")
    public String deleteProduct(@PathVariable String account,
                                @PathVariable Integer product_id,
                                @RequestBody ProductRequest productRequest)
    {
        String mess = productService.deleteProduct(account, product_id);
        return mess;
    }
    @PutMapping("/{account}-{product_id}")
    public String updateProduct(@PathVariable String account,
                                @PathVariable Integer product_id,
                                @RequestBody ProductRequest productRequest)
    {
        String mess = productService.updateProduct(account,product_id, productRequest);
        return mess;
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponse>> getAllProducts()
    {
        List<ProductResponse> list = productService.getAllProducts();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{account}")
    public ResponseEntity<List<ProductResponse>> getProduct(@PathVariable String account)
    {
        List<ProductResponse> listFromUser = productService.getProduct(account);
        return ResponseEntity.ok(listFromUser);
    }
}
