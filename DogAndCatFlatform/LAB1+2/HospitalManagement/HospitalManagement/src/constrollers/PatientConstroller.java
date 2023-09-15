/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constrollers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import models.Nurse;
import models.Patient;

/**
 *
 * @author nguye
 */
public class PatientConstroller {
    private static HashMap<String,Patient> listPatient=new HashMap<>();         // list patient

    public static HashMap<String, Patient> getListPatient() {
        return listPatient;
    }

    public static void setListPatient(HashMap<String, Patient> listPatient) {
        PatientConstroller.listPatient = listPatient;
    }
    
    
    
    
    public static Patient getInput(){                                           // Get patient insert infor 
        Validate.myPrint("Enter ID: ");                                         // You can use ctrl + left click to jumb detail of function
        String ID=Validate.checkInputString();
        while(Validate.checkUniqueID(ID, listPatient)){
            Validate.myPrint("ID is exists,Please enter again: ");
            ID=Validate.checkInputString();
        }
        Validate.myPrint("Enter name: ");
        String name=Validate.checkInputString();
        Validate.myPrint("Enter age: ");
        int age=Validate.checkInputNumber();
        String gender=Validate.checkInputGender();
        Validate.myPrint("Enter address: ");
        String address=Validate.checkInputString();
        Validate.myPrint("Enter phone number: ");
        String phone=Validate.checkInputPhone();
        
        
        
        Validate.myPrint("Enter diagnosis: ");
        String diagnosis=Validate.checkInputString();
        Validate.myPrint("Enter admissionDate: ");
        String admissionDate=Validate.checkInputDate();
        Validate.myPrint("Enter dischargeDate: ");
        String dischargeDate=Validate.checkInputDate();
        Validate.myPrint("Enter nurse 1: ");                                    // nurseAssigned has pattern like nurse1-nurse2 
        String nurse1=Validate.checkInputString();
        boolean status1=false;
        while(!status1){                                                        // while stop if nurser is exists and nurse take care Less than 2 patient
            if(!Validate.checkUniqueStaffID(nurse1, NurseConstroler.getListNurse())||!Validate.checkNurseTakecare(nurse1, listPatient))
            {
                if(!Validate.checkUniqueStaffID(nurse1, NurseConstroler.getListNurse()))            // Input agaign ì nurser not ẽists
                {  
                    Validate.myPrint("Nurse id not exists!Try again: : ");
                nurse1=Validate.checkInputString();  
                }
                if(!Validate.checkNurseTakecare(nurse1, listPatient)){                              // Input a gaign ì nurse take care mỏe than 2 patient
                    Validate.myPrint("Nurse is take caring of two patient!Try again: : ");
                    nurse1=Validate.checkInputString();  
                }
            }else{
                status1=true;
            }
        }
        
        Validate.myPrint("Enter nurse 2: ");                                    // Similar nurse 1
        String nurse2=Validate.checkInputString();
        boolean status2=false;
        while(!status2){
            if(nurse2.trim().equals(nurse1.trim())||!Validate.checkUniqueStaffID(nurse2, NurseConstroler.getListNurse())||!Validate.checkNurseTakecare(nurse2, listPatient)){
                if(nurse2.trim().equals(nurse1.trim())){
                Validate.myPrint("Enter nurse 2 diffrent nurse 1: ");
                nurse2=Validate.checkInputString();
                }
             if(!Validate.checkUniqueStaffID(nurse2, NurseConstroler.getListNurse())){
                Validate.myPrint("Nurse id not exists!Try again: : ");
                nurse2=Validate.checkInputString();  
              }
             if(!Validate.checkNurseTakecare(nurse2, listPatient)){
                    Validate.myPrint("Nurse is take caring of two patient!Try again: : ");
                    nurse2=Validate.checkInputString();  
                }
            }else{
                status2=true;
            }
        }
        
        String nurseAssigned=nurse1+"-"+nurse2;
        System.out.println(nurseAssigned);
        return new Patient(diagnosis,admissionDate,dischargeDate,nurseAssigned,ID,name,age,gender,address,phone);
        
    }
     public static void addNurse(){                                             // insert patient -> it will print exception while have something wrong 
        try{
            String status;
        do{
            Validate.myPrintln("Enter patient information");
            Patient patient=getInput();                                         // Create a patient from the information you just entered
            listPatient.put(patient.getId(), patient);                          // add to list
            Validate.myPrint("Do you want to continue more: ");
            status=Validate.checkInputYN();
        }while(status.equalsIgnoreCase("Y"));                                   // The while loop will stop if the user enters Y and continues to enter the next patient if the user enters N
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
     
     public static void DisplatPatient() throws ParseException{                 // Display patient by getAdmissionDate
         Validate.myPrintln("LIST OF PATIENTS");
         Validate.myPrint("Start date: ");
         String startDate=Validate.checkInputDate();                            // get start date
         Validate.myPrint("End date: ");
         String endDate=Validate.checkInputDate();                              // get end date
         HashMap<String,Patient> listFind=getPatientByDate(startDate,endDate);  // get list patient by stardate and enddate
         Display(listFind);
         
     }
     public static void sortPatient(){
         Validate.myPrintln("LIST OF PATIENTS");
         Validate.myPrint("Sorted by (id,name): ");
         int tern=Validate.checkInputTern();
         Validate.myPrint("Sorted order: ");
         boolean sort=Validate.checkInputType();
         ArrayList<Patient> listSort=sortPatient(tern,sort);
         Display2(listSort);
     }
     public static HashMap<String,Patient> getPatientByDate(String startDate,String endDate) throws ParseException{    // startDate < getAdmissionDate < endDate -> getAdmissionDate-startDate>0 && endDate-getAdmissionDate>0
         HashMap<String,Patient> list=new HashMap<String,Patient>(listPatient);
         for(Patient p:listPatient.values()){
             if(!(dateToInt(p.getAdmissionDate())-dateToInt(startDate)>=0)||!(dateToInt(endDate)-dateToInt(p.getAdmissionDate())>=0)){
                 list.entrySet().removeIf(entry -> entry.getValue().equals(p));                                   // hashmap save data in entry and mapping data so we need to remove data in entry 
             }
         }
         
         return list;
     }
     public static ArrayList<Patient> sortPatient(int tern,boolean sort){        // we can't sort HashMap so we copy value of HashMap to ArrayList and sort
         ArrayList<Patient> listCopy=new ArrayList(listPatient.values());        // create a ArrayList from HashMap value
         HashMap<String,Patient> listFinal=new HashMap();
         if(tern==1){                                                           // Sort by ID 
             if(sort){                                                          // Sort up(asc)
                 Collections.sort(listCopy, new Comparator<Patient>(){
                 @Override
                 public int compare(Patient o1, Patient o2) {
                     return o1.getId().compareTo(o2.getId());
                 }
                 
             });
             }else{                                                             // Sort decreasing (desc)
             Collections.sort(listCopy, new Comparator<Patient>(){
                 @Override
                 public int compare(Patient o1, Patient o2) {
                     return o1.getId().compareTo(o2.getId());
                 }
                 
             }.reversed());                                                     // reversed -> desc
         }
         }else{                                                                 // sort by name
             if(sort){                                                          // similar ID
                 Collections.sort(listCopy, new Comparator<Patient>(){
                 @Override
                 public int compare(Patient o1, Patient o2) {
                     return o1.getName().compareTo(o2.getName());
                 }
                 
             });
             }else{
             Collections.sort(listCopy, new Comparator<Patient>(){
                 @Override
                 public int compare(Patient o1, Patient o2) {
                     return o1.getName().compareTo(o2.getName());
                 }
                 
             }.reversed());
         }
         }
         
         return listCopy;
     }
     public static int dateToInt(String date) throws ParseException{            // Convert date to int ( to use a.date-b.date)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");              
        Date start = sdf.parse(date);                                           // format string to date 
        return Integer.parseInt((start.getTime()/1000)+"");   
    }
    
    
    
    
    
    
    
    
    
    
    
    // Under like PatientConstroller 
    
     public static void loadFileFR(String fname) throws IOException { // Using FileReader class
      File yourFile = new File(fname);
      if(!yourFile.exists()){
          System.out.println("File not exists");
      }else{
            FileReader fr = new FileReader(fname);
            BufferedReader br = new BufferedReader(fr);
            String s; String [] a;
            String id,name,gender,address,phone,diagnosis,admissionDate,dischargeDate,nurseAssigned;
            int age;
            
            
            while(true) {
              s = br.readLine();
              if(s==null || s.trim().length()<3) break;
              a = s.split("[,]");
              id=a[0].trim();
              name=a[1].trim();
              age=Integer.parseInt(a[2].trim());
              gender=a[3].trim();
              address=a[4].trim();
              phone=a[5].trim();
              diagnosis=a[6].trim();
              admissionDate=a[7].trim();
              dischargeDate=a[8].trim();
              nurseAssigned=a[9].trim();
              listPatient.put(id, new Patient(diagnosis,admissionDate,dischargeDate,nurseAssigned,id,name,age,gender,address,phone));
            }
            
            
            fr.close();
            br.close();
               }
          }
     public static void saveFileFR(String fname) throws IOException { // Using FileReader class
      File yourFile = new File(fname);
      if(!yourFile.exists()){
          yourFile.createNewFile();
      }
          FileWriter fw = new FileWriter(fname);
   PrintWriter pw = new PrintWriter(fw);
   for(Patient c:listPatient.values()){
       pw.println(c.toString());
   }
   pw.close();
   fw.close();  
  }
     private static void Display(HashMap<String, Patient> listFind) {
        header();
        printListFlower(listFind);
        printFooter();
    }
     private static void Display2(ArrayList<Patient> listFind){
         header();
         printListFlower(listFind);
            printFooter();
     }
    public static void header(){
          System.out.println("###############################################################################################");
          System.out.printf("%s %11s %11s %11s %11s %11s %11s\n", "#","No.","Patient id#","Admission Date#","Full name#","Phone#","Diagnosis#");
          System.out.println("###############################################################################################");
          
      }
      public static void printListFlower(HashMap<String, Patient> listFind){
          int dem=0;
          for(Patient n:listFind.values()){
              dem++;
              System.out.printf("%s %11d %11s %11s %11s %11s %11s\n", "#",dem,n.getId(),n.getAdmissionDate(),n.getName(),n.getPhone(),n.getDiagnosis());
          }
      }
      public static void printListFlower(ArrayList<Patient> listFind){
          int dem=0;
          for(Patient n:listFind){
              dem++;
              System.out.printf("%s %11d %11s %11s %11s %11s %11s\n", "#",dem,n.getId(),n.getAdmissionDate(),n.getName(),n.getPhone(),n.getDiagnosis());
          }
      }
      public static void printFooter(){
          System.out.println("###############################################################################################");
          
      }
    
}
