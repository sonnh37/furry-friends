package com.system.backend.Enity;
import javax.persistence.*;
@Entity
@Table(name = "[product]")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[product_id]")
    private Integer productID;

    @ManyToOne
    @JoinColumn(name = "[user_id]", referencedColumnName = "[user_id]")
    private User user;

    @Column(name = "[product_name]")
    private String productName;

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

    public Product(Integer productID, User user, String productName, Integer price, String image, String description, String date, String phone, String type) {
        this.productID = productID;
        this.user = user;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.description = description;
        this.date = date;
        this.phone = phone;
        this.type = type;
    }
    // getters and setters

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
