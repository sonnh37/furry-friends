package com.system.backend.Controller;

import com.system.backend.Dto.LoginDTO;
import com.system.backend.Dto.ProductDTO;
import com.system.backend.Service.ProductService;
import com.system.backend.Service.UserService;
import com.system.backend.payload.response.LoginMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
