/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management;

import core.Person;
import core.nurse;
import core.patient;
import core.patient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static management.NurseManagement.fName;
import tools.MyUntil;

/**
 *
 * @author hoang
 */
public class PatientManagement {

    NurseManagement nurseMangement;
    HashMap<String, patient> listPatient = new HashMap<>();

    public PatientManagement() {
    }

    static String fName = "src\\data\\patient.dat";

    public PatientManagement(NurseManagement nu) {
        this.nurseMangement = nu;
    }
    static String msg = "Please complete information!";
    static String msgreq = "The wrong format";
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
            String id, name, gender, address, phone, diagnosis, admissionDate, dischargeDate, nurseAssigned;
            int age;

            while (true) {
                s = br.readLine();
                if (s == null || s.trim().length() < 3) {
                    break;
                }
                a = s.split("[,]");
                id = a[0].trim();
                name = a[1].trim();
                age = Integer.parseInt(a[2].trim());
                gender = a[3].trim();
                address = a[4].trim();
                phone = a[5].trim();
                diagnosis = a[6].trim();
                admissionDate = a[7].trim();
                dischargeDate = a[8].trim();
                nurseAssigned = a[9].trim();
                Person p = new Person(id, name, age, gender, address, phone);
                listPatient.put(id, new patient(p, diagnosis, admissionDate, dischargeDate, nurseAssigned));
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
        for (patient c : listPatient.values()) {
            pw.println(c.toString());
        }
        pw.close();
        fw.close();

    }

    public void addPatient() {
        String select;
        do {
            patient p = getPatient();
            listPatient.put(p.getId(), p);
            select = askUser("add more patient");
        } while (select.equalsIgnoreCase("Y"));
    }

    public patient getPatient() {
        try {
            //declare
            String id = "";
            String name = "";
            int age = 0;
            String gender = "";
            String address = "";
            String phone = "";

            String diagnosis = "";
            String admissionDate = "";
            String dischargeDate = "";
            String nurseAssigned = "";
            //input
            boolean check = false;

            do {
                id = MyUntil.getStringreg("Id's patient(BXXX): ", "B[\\d]{3}",
                        msg, "Ban nhap sai format");
                check = this.isExistPatientByID(id); //true - duplicate;
                if (check) {
                    System.out.println("Duplicate!");
                }

            } while (check);
            name = MyUntil.getString("Name's patient: ", msg);
            age = MyUntil.getInt("Age's patient: ", 1);
            gender = MyUntil.getStringGender("Gender's patient(male, female): ", msg,msgreq);
            address = MyUntil.getString("Address's patient: ", msg);
            phone = MyUntil.getString("Phone's patient: ", msg, 10, 12);
            diagnosis = MyUntil.getString("Diagnosis's patient: ", msg);
            admissionDate = MyUntil.getDateNow();
            dischargeDate = MyUntil.setDate("Date dischargeDate's patient(dd/MM/YYYY): ", msg);

            String idNurse;
            nurse result;
            int i = 0;
            do {
                i++;
                idNurse = MyUntil.getStringIdNurse("Nurse " + i + "'s id (Nxxx) : ", "N[\\d]{3}", "Patient must be cared for by 2 nurses", msgreq);
                result = nurseMangement.getNurseById(idNurse);
                if (result == null) {
                    System.out.println("The nurse does not exist");
                    i--;
                } else {
                    nurseAssigned += result.getId() + "-";
                }
            } while (i < 2);
            nurseAssigned = nurseAssigned.substring(0, nurseAssigned.length() - 1);

            Person person = new Person(id, name, age, gender, address, phone);
            return new patient(person, diagnosis, admissionDate, dischargeDate, nurseAssigned);
        } catch (ParseException ex) {
            Logger.getLogger(PatientManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String askUser(String welcome) {
        String select = MyUntil.getString("Do u want to " + welcome + "?[Y/N]: ", msg);
        return select;
    }

    public void display() {
        header();
        printListPatient(listPatient);
    }

    public void display(HashMap<String, patient> listPatient) {
        header();
        printListPatient(listPatient);
    }

    private void header() {
        System.out.printf("%11s %11s %11s %11s %11s %11s\n", "No.", "Patient id", "Admission Date", "Full name", "Phone", "Diagnosis");
        System.out.println();
    }

    private void printListPatient(HashMap<String, patient> list) {
        int count = 0;
        for (patient n : list.values()) {
            count++;
            System.out.printf("%11s %11s %11s %11s %11s %11s \n", count, n.getId(), n.getAdmissionDate(), n.getName(), n.getPhone(), n.getDiagnosis());
        }
    }

    public boolean isExistPatientByID(String id) {
        boolean check = false;
        for (patient x : listPatient.values()) {
            if (x.getId().equals(id.toUpperCase())) {
                check = true;
            }
        }
        return check;
    }

}
