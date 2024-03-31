package com.system.backend.Controller.Dashboard;

import com.system.backend.Dto.Product.ProductResponse;
import com.system.backend.Service.ProductService;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT + Link.USER.STATISTIC)
public class DashboardForProduct {
    @Autowired
    private ProductService productService;
    @GetMapping(Link.USER.STATISTICCRUD.STATISTICPRODUCT)
    public Integer getAllProducts_asc()
    {
        List<ProductResponse> list = productService.getAllProductsSORT_ASC();
        return list.size();
    }
}
