/*p
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.util.Date;

/**
 *
 * @author hoang
 */
public class nurse extends Person {

    private String department;
    private String shift;
    private double salary;

    public nurse() {
        super();
    }

    public nurse(Person nu, String department, String shift, double salary) {
        super(nu.getId(), nu.getName(), nu.getAge(), nu.getGender(), nu.getAddress(), nu.getPhone());
        this.department = department;
        this.shift = shift;
        this.salary = salary;   
    }

//    public nurse(String id, String name, int age, String gender, String address, String phone,String department, String shift, double salary) {
//        super(id, name, age, gender, address, phone);
//        this.department = department;
//        this.shift = shift;
//        this.salary = salary;
//    }
    public String getDepartment() {
        department = department.substring(0, 1).toUpperCase() + department.substring(1).toLowerCase();
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getShift() {
        shift = shift.substring(0, 1).toUpperCase() + shift.substring(1).toLowerCase();
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

    @Override
    public String toString() {
        return super.toString() + ", " + this.getDepartment()
                + ", " + this.getShift() + ", " + this.getSalary();
    }

}
