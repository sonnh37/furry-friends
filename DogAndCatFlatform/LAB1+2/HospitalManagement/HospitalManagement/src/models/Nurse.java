/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author nguye
 */
public class Nurse extends Person{
    private String staffID;
    private String deparment;
    private String shift;
    private double salary;

    public Nurse(String staffID, String deparment, String shift, int salary) {
        this.staffID = staffID;
        this.deparment = deparment;
        this.shift = shift;
        this.salary = salary;
    }

    public Nurse(String staffID, String deparment, String shift, double salary, String id, String name, int age, String gender, String address, String phone) {
        super(id, name, age, gender, address, phone);
        this.staffID = staffID;
        this.deparment = deparment;
        this.shift = shift;
        this.salary = salary;
    }

    public Nurse() {
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getDeparment() {
        return deparment;
    }

    public void setDeparment(String deparment) {
        this.deparment = deparment;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String toString(){
        return getStaffID()+","+getName()+","+getAge()+","+getGender()+","+getAddress()+","+getPhone()+","+getDeparment()+","+getShift()+","+getSalary();
    }
    
    
}
