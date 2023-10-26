package com.system.backend.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private Float price;

    @Column(name = "[image]")
    private String image;

    @Column(name = "[description]")
    private String description;

    @Column(name = "[date]")
    private String date;

    @Column(name = "[phone]")
    private String phone;

    @Column(name = "[title]")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private boolean status;
}
