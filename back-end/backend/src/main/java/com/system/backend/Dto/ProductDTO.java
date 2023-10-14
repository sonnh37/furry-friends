package com.system.backend.Dto;



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

    public ProductDTO() {
    }

    public ProductDTO(Integer product_id, Integer user_id, String product_name, Integer price, String image, String description, String date, String phone, String type) {
        this.product_id = product_id;
        this.user_id = user_id;
        this.product_name = product_name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.date = date;
        this.phone = phone;
        this.type = type;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
