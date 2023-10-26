package com.system.backend.Dto.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Integer product_id;
    private Integer user_id;
    private String product_name;
    private Float price;
    private List<String> img_stringList;
    private String description;
    private String date;
    private String phone;
    private String type;
    private String address;
    private boolean status;
}
