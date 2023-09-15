/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hoang
 */
public class patient extends Person{
    private String diagnosis;
    private String admissionDate;
    private String dischargeDate;
    private String nurseAssigned;

    public patient() {
        super();
    }   


    public patient(Person nu,String diagnosis, String admissionDate, String dischargeDate, String nurseAssigned) {
        super(nu.getId(), nu.getName(), nu.getAge(), nu.getGender(), nu.getAddress(), nu.getPhone());
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nurseAssigned = nurseAssigned;
    }
    

    public String getDiagnosis() {
        diagnosis = diagnosis.substring(0, 1).toUpperCase() + diagnosis.substring(1).toLowerCase();
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
    @Override
    public String toString(){
        return super.toString()+", "+getDiagnosis()+", "+getAdmissionDate()+", "+getDischargeDate()+", "+getNurseAssigned();
    }
    
}
