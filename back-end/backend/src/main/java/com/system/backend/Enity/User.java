package com.system.backend.Enity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "[user]")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[user_id]", nullable = false)
    private Integer userID;

    @ManyToOne
    @JoinColumn(name = "[role_id]", referencedColumnName = "[role_id]", nullable = false)
    private Role role;

    @Column(name = "[account]")
    private String account;

    @Column(name = "[password]")
    private String password;

    @Column(name = "[email]", nullable = false )
    private String email;

    @Column(name = "[first_name]")
    private String firstName;

    @Column(name = "[last_name]")
    private String lastName;

    @Column(name = "[phone]", nullable = false)
    private String phone;

    @Column(name = "[address]", nullable = false)
    private String address;

    @Column(name = "[birth]")
    private String birth;

    @Column(name = "[sex]")
    private String sex;

    // getters and setters

    public User() {
    }


    public User(Integer userID, Role role, String account, String password, String email, String firstName, String lastName, String phone, String address, String birth, String sex) {
        this.userID = userID;
        this.role = role;
        this.account = account;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.birth = birth;
        this.sex = sex;
    }

    public Integer getId() {
        return userID;
    }

    public void setId(Integer userID) {
        this.userID = userID;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
