package com.system.backend.Entity;
import javax.persistence.*;
@Entity
@Table(name = "[product]")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[product_id]")
    private Integer product_id;

    @ManyToOne
    @JoinColumn(name = "[user_id]", referencedColumnName = "[user_id]")
    private User user;

    @Column(name = "[product_name]")
    private String product_name;

    @Column(name = "[price]")
    private Integer price;

    @Column(name = "[image]")
    private String image;

    @Column(name = "[description]")
    private String description;

    @Column(name = "[date]")
    private String date;

    @Column(name = "[phone]")
    private String phone;

    @Column(name = "[type]")
    private String type;

    public Product() {
    }


    // getters and setters


    public Product(Integer product_id, User user, String product_name, Integer price, String image, String description, String date, String phone, String type) {
        this.product_id = product_id;
        this.user = user;
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

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
