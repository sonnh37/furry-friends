/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management;

import core.Person;
import core.nurse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MyUntil;

/**
 *
 * @author hoang
 */
public class NurseManagement {

    HashMap<String, nurse> listNurse = new HashMap<>();

    public NurseManagement() {
    }
    static String msg = "Please complete information!";
    static String msgreq = "The wrong format";
    static String fName = "src\\data\\nurse.dat";

    static Scanner sc = new Scanner(System.in);

    public void loadData() throws IOException { // Using FileReader class
        File yourFile = new File(fName);
        if (!yourFile.exists()) {
            System.out.println("File not exists");
        } else {
            FileReader fr = new FileReader(fName);
            BufferedReader br = new BufferedReader(fr);
            String s;
            String[] a;
            String name, gender, address, phone, staffID, deparment, shift;
            double salary;
            int age;

            while (true) {                                                       // while stop if row is null
                s = br.readLine();                                                // row content
                if (s == null || s.trim().length() < 3) {
                    break;
                }
                a = s.split("[,]");                                               // row content like a,b,c,d -> spplit to cup infor
                staffID = a[0].trim();                                              // get infor after cut 
                name = a[1].trim();
                age = Integer.parseInt(a[2].trim());
                gender = a[3].trim();
                address = a[4].trim();
                phone = a[5].trim();
                deparment = a[6].trim();
                shift = a[7].trim();
                salary = Double.parseDouble(a[8].trim());
                Person p = new Person(staffID, name, age, gender, address, phone);
                listNurse.put(staffID, new nurse(p, deparment, shift, salary));
            }

            fr.close();
            br.close();
        }
    }

    public void saveData() throws IOException { // Using FileReader class
        File yourFile = new File(fName);
        if (!yourFile.exists()) {
            yourFile.createNewFile();
        }
        FileWriter fw = new FileWriter(fName);
        PrintWriter pw = new PrintWriter(fw);
        for (nurse c : listNurse.values()) {
            pw.println(c.toString());                                                // print toString of class to file row by row
        }
        pw.close();
        fw.close();
    }

    public void addNurse() {
        String select;
        do {
            nurse nu = getNurse();
            listNurse.put(nu.getId(), nu);
            select = askUser("add more nurse");
        } while (select.equalsIgnoreCase("Y"));
    }

    public String askUser(String welcome) {
        String select = MyUntil.getString("Do u want to " + welcome + "?[Y/N]: ", msg);
        return select;
    }

    public nurse getNurse() {
        //declare
        String id = "";
        String name = "";
        int age = 0;
        String gender = "";
        String address = "";
        String phone = "";

        String department = "";
        String shift = "";
        double salary = 0;

        //input
        boolean check = false;
        do {
            id = MyUntil.getStringreg("Id's nurse(NXXX): ", "N[\\d]{3}",
                    msg, "Ban nhap sai format");
            check = this.isExistByID(id); //true - duplicate;
            if (check) {
                System.out.println("Duplicate!");
            }
        } while (check);
        name = MyUntil.getString("Name's nurse: ", msg);
        age = MyUntil.getInt("Age's nurse ( >= 18 ): ", 18);
        gender = MyUntil.getStringGender("Gender's nurse(male, female): ", msg, msgreq);
        address = MyUntil.getString("Address's nurse: ", msg);
        phone = MyUntil.getString("Phone's nurse: ", msg, 10, 12);
        department = MyUntil.getString("Department's nurse: ", msg);
        shift = MyUntil.getStringShift("Shift's nurse(morning, afternoon, evening: ", msg, msgreq);
        salary = MyUntil.getDouble("Salary's nurse: ", 1);
        //Donate
        Person person = new Person(id, name, age, gender, address, phone);
        return new nurse(person, department, shift, salary);
    }

    public void updateNurse() {
        String ID;
        String input;
        Double newP;
        nurse result;
        if (!this.isEmptyList()) {
            System.out.println("-------------- UPDATE - NURSE -------------");
            ID = MyUntil.getStringreg("(Nxxx) ID which you want to update: ", "N\\d{3}", "VUI LONG NHAP THONG TIN", "NHAP SAI FORMAT!");
            result = this.getNurseById(ID);
            if (result == null) {
                System.out.println("The nurse does not exist");
            } else {
                this.updateNurseDetail(result);
                HashMap<String, nurse> list = new HashMap<>();
                list.put(result.getId(), result);
                display(list);
                System.out.println("UPDATED");
            }
        }
    }

    public void deleteNurse() {
        String id;
        System.out.println("-------------- DELETE - NURSE -------------");
        id = MyUntil.getString("(NXXX) Remove id: ", "VUI LONG DIEN DAY DU!");
        nurse nu = this.getNurseById(id);
        if (nu == null) {
            System.out.println("The nurse does not exist");
        } else {
            listNurse.remove(nu.getId());
            System.out.println("ID: " + id + " has been removed.");
        }
    }

    public void findNurse() {
        HashMap<String, nurse> result;
        if (!this.isEmptyList()) {
            String name = MyUntil.getString("Find by name: ", "VUI LONG DIEN DAY DU!");
            result = this.getNurseByNames(name);
            if (result == null) {
                System.out.println("->This nurse does not exist.");
            } else {
                display(result);
            }
        }
    }

    public void display() {
        header();
        printListNurse(listNurse);
    }

    public void display(HashMap<String, nurse> listNurse) {
        header();
        printListNurse(listNurse);
    }

    private void header() {
        System.out.printf("%s %11s %11s %11s %11s %11s %11s %11s %11s %11s\n", "No.", "staffID", "name", "age", "gender", "address", "phone", "department", "shift", "salary");
        System.out.println();
    }

    private void printListNurse(HashMap<String, nurse> list) {
        int count = 0;
        for (nurse n : list.values()) {
            count++;
            System.out.printf("%s %11s %11s %11d %11s %11s %11s %11s %11s %11s\n", count, n.getId(), n.getName(), n.getAge(), n.getGender(), n.getAddress(),
                    n.getPhone(), n.getDepartment(), n.getShift(), n.getSalary());
        }
    }

    public void updateNurseDetail(nurse result) {
        int L;
        boolean OK;
        String input = "";
        do {
            System.out.print("New Name's nurse: ");
            input = MyUntil.sc.nextLine().toUpperCase().trim();
            L = input.length();

            OK = input.isEmpty() || (L >= 1 && L <= 50); // xét cả 2 trg hợp : blank và có số
        } while (!OK); // false
        if (!input.isEmpty()) {
            result.setName(input);
        }
        int age = 0;
        do {
            System.out.print("New Age's nurse: ");
            input = MyUntil.sc.nextLine().toUpperCase().trim();
            if (!input.isEmpty()) {
                age = Integer.parseInt(input);
            }

            OK = input.isEmpty() || (age >= 18 && L <= 55); // xét cả 2 trg hợp : blank và có số
        } while (!OK); // false
        if (age != 0) {
            result.setAge(age);
        }

        do {
            System.out.print("New Gender's nurse(male, female): ");
            input = MyUntil.sc.nextLine().toUpperCase().trim();
            OK = input.isEmpty();
            if (OK == false) {
                if (input.equalsIgnoreCase("male")) {
                    OK = true;
                } else if (input.equalsIgnoreCase("female")) {
                    OK = true;
                } else{
                    System.out.println(msgreq);
                    OK = false;
                }
            }
        } while (!OK);
        if (!input.isEmpty()) {
            result.setGender(input);
        }
        do {
            System.out.print("New Address's nurse: ");
            input = MyUntil.sc.nextLine().toUpperCase().trim();
            L = input.length();
            OK = input.isEmpty() || (L > 0); // xét cả 2 trg hợp : blank và có số
        } while (!OK);
        if (!input.isEmpty()) {
            result.setAddress(input);
        }

        do {
            System.out.print("New Phone's nurse: ");
            input = MyUntil.sc.nextLine().trim();
            L = input.length();
            OK = input.isEmpty() || (L >= 10 && L <= 12); // xét cả 2 trg hợp : blank và có số
        } while (!OK);
        if (!input.isEmpty()) {
            result.setPhone(input);
        }

        do {
            System.out.print("New Department's nurse: ");
            input = MyUntil.sc.nextLine().toUpperCase().trim();
            L = input.length();
            OK = input.isEmpty() || (L > 0); // xét cả 2 trg hợp : blank và có số
        } while (!OK);
        if (!input.isEmpty()) {
            result.setDepartment(input);
        }

        do {
            System.out.print("New Shift's nurse(morning, afternoon, evening): ");
            input = MyUntil.sc.nextLine().toUpperCase().trim();
            OK = input.isEmpty();
            if (OK == false) {
                if (input.equalsIgnoreCase("morning")) {
                    OK = true;
                } else if (input.equalsIgnoreCase("afternoon")) {
                    OK = true;
                } else if (input.equalsIgnoreCase("evening")) {
                    OK = true;
                } else{
                    System.out.println(msgreq);
                    OK = false;
                }
            }
        } while (!OK);
        if (!input.isEmpty()) {
            result.setShift(input);
        }
        double salary = 0;
        do {
            System.out.print("New Salary's nurse: ");
            input = MyUntil.sc.nextLine().toUpperCase().trim();
            if (!input.isEmpty()) {
                salary = Double.parseDouble(input);
            }

            OK = input.isEmpty() || (salary >= 0); // xét cả 2 trg hợp : blank và có số
        } while (!OK); // false
        if (salary != 0) {
            result.setSalary(salary);
        }
    }

    public boolean isExistByID(String id) {
        boolean check = false;
        for (nurse x : listNurse.values()) {
            if (x.getId().equals(id.toUpperCase())) {
                check = true;
            }
        }
        return check;
    }

    public nurse getNurseById(String id) {
        nurse check = null;
        for (nurse x : listNurse.values()) {
            if (x.getId().equals(id.toUpperCase())) {
                check = x;
            }
        }
        return check;
    }

    public HashMap<String, nurse> getNurseByNames(String name) {
        HashMap<String, nurse> check = new HashMap<>();
        for (nurse x : listNurse.values()) {
            if (x.getName().toLowerCase().contains(name.toLowerCase())) {
                check.put(x.getId(), x);
            }
        }
        if (check.isEmpty()) {
            check = null;
        }
        return check;
    }

    public boolean isEmptyList() {
        boolean check = false;
        if (listNurse.isEmpty()) {
            System.out.println("Empty List!");
            check = true;
        }
        return check;
    }
}
