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
@Table(name = "[user]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[user_id]")
    private Integer user_id;

    @ManyToOne
    @JoinColumn(name = "[role_id]", referencedColumnName = "[role_id]")
    private Role role;

    @Column(name = "[account]")
    private String account;

    @Column(name = "[password]")
    private String password;

    @Column(name = "[email]" )
    private String email;

    @Column(name = "[first_name]", columnDefinition = "nvarchar(255)")
    private String first_name;

    @Column(name = "[last_name]", columnDefinition = "nvarchar(255)")
    private String last_name;

    @Column(name = "[phone]")
    private String phone;

    @Column(name = "[address]", columnDefinition = "nvarchar(255)")
    private String address;

    @Column(name = "[birth]")
    private String birth;

    @Column(name = "[sex]", columnDefinition = "nvarchar(255)")
    private String sex;
}
