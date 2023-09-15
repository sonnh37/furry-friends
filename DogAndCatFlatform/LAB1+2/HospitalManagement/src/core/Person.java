/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author hoang
 */
public class Person {

    private String id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String phone;

    public Person() {
    }

    public Person(String id, String name, int age, String gender, String address, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public String getId() {
        id = id.substring(0, 1).toUpperCase() + id.substring(1).toLowerCase();
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        String[] arr = name.split(" ");
	//dùng vòng lặp duyệt các từ và thay thế từ đầu tiên!
	String stringfromclient1 = "";
	for (String x : arr) {
		stringfromclient1 = stringfromclient1 + (x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase());
		stringfromclient1 = stringfromclient1 + " ";
	}
        name = stringfromclient1.trim();
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        gender = gender.substring(0, 1).toUpperCase() + gender.substring(1).toLowerCase();
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        address = address.substring(0, 1).toUpperCase() + address.substring(1).toLowerCase();
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return this.getId() + ", " + this.getName() + ", " + this.getAge() + ", "
                + this.getGender() + ", " + this.getAddress() + ", " + this.getPhone();
    }
}
