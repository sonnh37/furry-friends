package com.system.backend.Enity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

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

    @Column(name = "[first_name]")
    private String first_name;

    @Column(name = "[last_name]")
    private String last_name;

    @Column(name = "[phone]")
    private String phone;

    @Column(name = "[address]")
    private String address;

    @Column(name = "[birth]")
    private String birth;

    @Column(name = "[sex]")
    private String sex;

    // getters and setters

    public User() {
    }


    public User(Integer user_id, Role role, String account, String password, String email, String first_name, String last_name, String phone, String address, String birth, String sex) {
        this.user_id = user_id;
        this.role = role;
        this.account = account;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.address = address;
        this.birth = birth;
        this.sex = sex;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
