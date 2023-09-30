package com.system.backend.Enity;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="user_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(name="email", length = 45, unique = true)
    private String email;
    @Column(name="password", length = 255)
    private String password;
    @Column(name="first_name", columnDefinition = "nvarchar(255)")
    private String firstName;

    @Column(name="last_name", columnDefinition = "nvarchar(255)")
    private String lastName;
    @Column(name="phone", length = 12)
    private String phone;
    @Column(name="age")
    private int age;
    @Column(name="address", columnDefinition = "nvarchar(255)")
    private String address;
    @Column(name="birth")
    private String birth;
    @Column(name="sex", length = 12)
    private String sex;
    @Column(name="role", length = 255)
    private String role;


    public User() {
    }

    public User(Long userId, String email, String password, String firstName, String lastName, String phone, int age, String address, String birth, String sex, String role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.address = address;
        this.birth = birth;
        this.sex = sex;
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
