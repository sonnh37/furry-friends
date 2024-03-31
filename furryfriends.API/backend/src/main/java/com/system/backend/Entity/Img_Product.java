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
@Table(name = "[img_Product]")
public class Img_Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer image_id;

    @ManyToOne
    @JoinColumn(name = "[product_id]", referencedColumnName = "[product_id]")
    private Product product;


    @Column(name = "src" , columnDefinition = "varchar(max)")
    private String src;


}
