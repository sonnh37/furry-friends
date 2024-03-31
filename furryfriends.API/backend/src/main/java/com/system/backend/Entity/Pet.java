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
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[pet_id]")
    private Integer pet_id;

    @ManyToOne
    @JoinColumn(name = "[user_id]", referencedColumnName = "[user_id]")
    private User user;

    @Column(name = "[pet_name]",  columnDefinition = "nvarchar(255)")
    private String pet_name;

    @Column(name = "[pet_price]")
    private Float pet_price;
    @Column(name = "[development_stage]", columnDefinition = "nvarchar(255)")
    private String development_stage;

    @Column(name = "[description]", columnDefinition = "nvarchar(3000)")
    private String description;

    @Column(name = "[date]")
    private String date;

    @Column(name = "[phone]")
    private String phone;

    @Column(name = "[type]", columnDefinition = "nvarchar(255)")
    private String type;
    @Column(name = "[title]", columnDefinition = "nvarchar(255)")
    private String title;

    @Column(name = "address", columnDefinition = "nvarchar(255)")
    private String address;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "pet")
    private List<Img_Pet> img_petList;
}
