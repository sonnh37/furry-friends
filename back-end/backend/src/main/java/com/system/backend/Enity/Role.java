package com.system.backend.Enity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "[role]")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[role_id]")
    private Integer role_id;

    @Column(name = "[role]",nullable = false)
    private String role;

    // getters and setters


    public Role(Integer role_id, String role) {
        this.role_id = role_id;
        this.role = role;
    }

    public Role() {
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}