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
public class Patient extends Person {
    private String diagnosis;
    private String admissionDate;
    private String dischargeDate;
    private String nurseAssigned;

    public Patient(String diagnosis, String admissionDate, String dischargeDate, String nurseAssigned) {
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nurseAssigned = nurseAssigned;
    }

    public Patient(String diagnosis, String admissionDate, String dischargeDate, String nurseAssigned, String id, String name, int age, String gender, String address, String phone) {
        super(id, name, age, gender, address, phone);
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nurseAssigned = nurseAssigned;
    }

    

    public Patient() {
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getNurseAssigned() {
        return nurseAssigned;
    }

    public void setNurseAssigned(String nurseAssigned) {
        this.nurseAssigned = nurseAssigned;
    }
    public String toString(){
        return getId()+","+getName()+","+getAge()+","+getGender()+","+getAddress()+","+getPhone()+","+getDiagnosis()+","+getAdmissionDate()+","+getDischargeDate()+","+getNurseAssigned();
    }

    
    
    
}
