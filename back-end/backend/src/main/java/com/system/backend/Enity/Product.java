package com.system.backend.Enity;
import javax.persistence.*;
@Entity
@Table(name = "[product]")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[product_id]",nullable = false)
    private Integer productID;

    @ManyToOne
    @JoinColumn(name = "[user_id]",nullable = false, referencedColumnName = "[user_id]")
    private User user;

    @Column(name = "[product_name]", nullable = false)
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

    // getters and setters
}
