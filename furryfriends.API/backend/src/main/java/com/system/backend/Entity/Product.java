package com.system.backend.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "[product_name]",  columnDefinition = "nvarchar(255)")
    private String product_name;

    @Column(name = "[price]")
    private Float price;

    @Column(name = "[description]", columnDefinition = "nvarchar(3000)")
    private String description;

    @Column(name = "[date]")
    private String date;

    @Column(name = "[phone]")
    private String phone;

    @Column(name = "[title]", columnDefinition = "nvarchar(255)")
    private String title;

    @Column(name = "address", columnDefinition = "nvarchar(255)")
    private String address;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "product")
    private List<Img_Product> img_productList;
}
