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
@Table(name = "[img_Pet]")
public class Img_Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer image_id;

    @ManyToOne
    @JoinColumn(name = "[pet_id]", referencedColumnName = "[pet_id]")
    private Pet pet;


    @Column(name = "src" , columnDefinition = "varchar(max)")
    private String src;

}
