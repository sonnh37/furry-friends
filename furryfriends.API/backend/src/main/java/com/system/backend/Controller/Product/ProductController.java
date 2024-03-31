package com.system.backend.Controller.Product;

import com.system.backend.Dto.Product.ProductRequest;
import com.system.backend.Dto.Product.ProductResponse;
import com.system.backend.Dto.User.AccountRequest;
import com.system.backend.Entity.Product;
import com.system.backend.Entity.User;
import com.system.backend.Service.ProductService;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT + Link.USER.PRODUCT)
public class ProductController {
    @Autowired
    private ProductService productService;
    //sort desc
    @GetMapping(Link.USER.PRODUCTCRUD.GETBYSEARCH_DESC)
    public ResponseEntity<List<ProductResponse>> getBySearch_desc(@RequestParam(required = false) String q)
    {
        List<ProductResponse> list = productService.getProductsBySearchSORT_DESC(q);
        return ResponseEntity.ok(list);
    }

    @GetMapping(Link.USER.PRODUCTCRUD.GETALLL_DESC)
    public ResponseEntity<List<ProductResponse>> getAllProducts_desc()
    {
        List<ProductResponse> list = productService.getAllProductsSORT_DESC();
        return ResponseEntity.ok(list);
    }
    //sort asc
    @GetMapping(Link.USER.PRODUCTCRUD.GETBYSEARCH_ASC)
    public ResponseEntity<List<ProductResponse>> getBySearch_asc(@RequestParam(required = false) String q)
    {
        List<ProductResponse> list = productService.getProductsBySearchSORT_ASC(q);
        return ResponseEntity.ok(list);
    }

    @GetMapping(Link.USER.PRODUCTCRUD.GETALLL_ASC)
    public ResponseEntity<List<ProductResponse>> getAllProducts_asc()
    {
        List<ProductResponse> list = productService.getAllProductsSORT_ASC();
        return ResponseEntity.ok(list);
    }
    // -------------------------------------------------------------




    // view list product desc
    @GetMapping(Link.USER.PRODUCTCRUD.GETALLFROMUSER)
    public ResponseEntity<List<ProductResponse>> getAllProductFromUser_desc(@PathVariable String account)
    {
        List<ProductResponse> listFromUser = productService.getProducts(account);
        return ResponseEntity.ok(listFromUser);
    }
    // view product detail
    @GetMapping(Link.USER.PRODUCTCRUD.GET)
    public ResponseEntity<ProductResponse> getProductFromUser( @PathVariable Integer product_id )
    {
        ProductResponse productResponse = productService.getProduct(product_id);
        return ResponseEntity.ok(productResponse);
    }








    @PutMapping(Link.USER.PRODUCTCRUD.PUT)
    public String updateProduct(@PathVariable String account,
                                @PathVariable Integer product_id,
                                @RequestBody ProductRequest productRequest)
    {
        String mess = productService.updateProduct(account,product_id, productRequest);
        return mess;
    }

    @DeleteMapping(Link.USER.PRODUCTCRUD.DELETE)
    public String deleteProduct(@PathVariable String account,
                                @PathVariable Integer product_id)
    {
        String mess = productService.deleteProduct(account, product_id);
        return mess;
    }

    @PostMapping(Link.USER.PRODUCTCRUD.POST)
    public String addProduct(
            @PathVariable String account, @RequestBody ProductRequest productRequest){
        String mess = productService.insertProduct(account,productRequest);
        return mess;
    }

}
