package com.system.backend.Dto.Product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String product_name;
    private Float price;
    private List<String> img_productList;
    private String description;
    private String date;
    private String phone;
    private String title;
    private String address;
    private boolean status;
}
