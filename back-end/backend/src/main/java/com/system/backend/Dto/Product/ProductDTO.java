package com.system.backend.Dto.Product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer product_id;
    private Integer user_id;
    private String product_name;
    private Integer price;
    private String image;
    private String description;
    private String date;
    private String phone;
    private String type;
}
