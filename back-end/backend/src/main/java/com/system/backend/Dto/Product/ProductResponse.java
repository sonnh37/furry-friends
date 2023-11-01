package com.system.backend.Dto.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Integer product_id;
    private Integer user_id;
    private String user_name;
    private String product_name;
    private Float price;
    @Lob
    private List<String> img_productList;
    private String description;
    private String date;
    private String phone;
    private String title;
    private String address;
    private boolean status;
    private String account;
}
