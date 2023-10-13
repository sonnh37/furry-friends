package com.system.backend.Enity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "[role]")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[role_id]", nullable = false)
    private Integer roleID;

    @Column(name = "[role]",nullable = false)
    private String role;

    // getters and setters

    public Role(Integer roleID, String role) {
        this.roleID = roleID;
        this.role = role;
    }

    public Role() {
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}