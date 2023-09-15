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
import java.util.HashMap;
import java.util.Map;
import models.Nurse;
import models.Patient;

/**
 *
 * @author nguye
 */
public class NurseConstroler {
    private static HashMap<String,Nurse> listNurse=new HashMap<>();             // list nurse

    public static HashMap<String, Nurse> getListNurse() {
        return listNurse;
    }

    public static void setListNurse(HashMap<String, Nurse> listNurse) {
        NurseConstroler.listNurse = listNurse;
    }
    
    
    public static Nurse getInput(){                                             // Get nurse insert infor 
        Validate.myPrint("Enter staffID: ");                                    // You can use ctrl + left click to jumb detail of function
        String staffID=Validate.checkInputString();                             
        while(Validate.checkUniqueStaffID(staffID, listNurse)){
            Validate.myPrint("StaffID is exists,Please enter again: ");
            staffID=Validate.checkInputString();
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
        Validate.myPrint("Enter department: ");
        String deparment=Validate.checkInputDepartment();
        
        String shift=Validate.checkInputShift();
        Validate.myPrint("Enter salary: ");
        double salary=Validate.checkInputSalary();
        return new Nurse(staffID,deparment,shift,salary,"P001",name,age,gender,address,phone);
        
    }
    public static Nurse getInputUpdate(String staffID){                         // Get nurse update infor -> don't need staffID
        Validate.myPrint("Enter name: ");
        String name=Validate.checkInputString();
        Validate.myPrint("Enter age: ");
        int age=Validate.checkInputNumber();
        String gender=Validate.checkInputGender();
        Validate.myPrint("Enter address: ");
        String address=Validate.checkInputString();
        Validate.myPrint("Enter phone number: ");
        String phone=Validate.checkInputPhone();
        Validate.myPrint("Enter department: ");
        String deparment=Validate.checkInputDepartment();
        
        String shift=Validate.checkInputShift();
        Validate.myPrint("Enter salary: ");
        double salary=Validate.checkInputSalary();
        return new Nurse(staffID,deparment,shift,salary,"P001",name,age,gender,address,phone);
    }
    public static void addNurse(){                                              // insert nurse -> it will print exception while have something wrong 
        try{
            String status;
        do{
            Validate.myPrintln("Enter nurse information");
            Nurse nurse=getInput();                                             // Create a nurse from the information you just entered
            listNurse.put(nurse.getStaffID(), nurse);                           // add to list
            Validate.myPrint("Do you want to continue more: ");
            status=Validate.checkInputYN();                                     // The while loop will stop if the user enters Y and continues to enter the next nurse if the user enters N
        }while(status.equalsIgnoreCase("Y"));
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static HashMap<String,Nurse> findNurseByName(String name){           // Return a list of nurse find by name
        HashMap<String,Nurse> listFind=new HashMap();
        for(Nurse n:listNurse.values()){                                        // for the hashmap values to find the nurse with the corresponding name
            if(n.getName().contains(name)){                                     // add if name  contains 
                listFind.put(n.getStaffID(), n);
            }
        }
        
        return listFind;
    }
    public static void updateNurse(){                                           // Update a nurse from the information you just entered
        try{
            Validate.myPrint("Enter staffID: ");
            String staffID=Validate.checkInputString();
            if(Validate.checkUniqueStaffID(staffID, listNurse)){                // update nurse if staffID exists in list -> if not throw and print exception
                Nurse nurse=getInputUpdate(staffID);
                listNurse.compute(staffID, (k,v)->v=nurse);
                System.out.println("Update success");
            }else{
                throw new Exception("The nurse does not exist");
            }
                    
        }catch(Exception ex){
            Validate.myPrintln(ex.getMessage());
        }
    }
    public static void deleteNurse(){                                           // Delete a nurse from the staffID you just entered
        try{
            Validate.myPrint("Enter staffID: ");
            String staffID=Validate.checkInputString();
            if(Validate.checkUniqueStaffID(staffID, listNurse)){                // Delete nurse if staffID exists in list -> if not throw and print exception
                Validate.myPrint("Delete nurse "+staffID+" (Y/N): ");
                String choice=Validate.checkInputYN();
                if(choice.equalsIgnoreCase("Y")){                               // Delete nurse if user enter Y
                    if(Validate.getNurseTakecare(staffID, PatientConstroller.getListPatient())>0){          // Throw and prin exception if nurrse take care patient or delete if not
                    throw new Exception("Nurse is take caring a patient");
                    
                }else{
                    listNurse.remove(staffID);
                    Validate.myPrintln("Remove nurse "+staffID+" success");
                }
                }
            }else{
                throw new Exception("The nurse does not exist");
            }
                    
        }catch(Exception ex){
            Validate.myPrintln(ex.getMessage());                                // Print exception that throw below 
        }
    }
    public static void findAndDisplayNurse(){                                   // Get name and display list nurse 
        try{
            Validate.myPrint("Enter nurse name: ");
        String name=Validate.checkInputString();
        HashMap<String,Nurse> listFind=findNurseByName(name);                   // use ctrl + left click to jump
        if(listFind.size()<=0){                                                 // throw and print exception if list find is empty
            throw new Exception("The nurse does not exist");
        }else{
            Display(listFind);                                                  // display list find
        }
        }catch(Exception ex){
            Validate.myPrintln(ex.getMessage());
        }
    }
    
    
    
    
    
    public static void loadFileFR(String fname) throws IOException { // Using FileReader class
      File yourFile = new File(fname);
      if(!yourFile.exists()){
          System.out.println("File not exists");
      }else{
            FileReader fr = new FileReader(fname);
            BufferedReader br = new BufferedReader(fr);
            String s; String [] a;
            String name,gender,address,phone,staffID,deparment,shift;
            double salary;
            int age;
            
            while(true) {                                                       // while stop if row is null
              s = br.readLine();                                                // row content
              if(s==null || s.trim().length()<3) break;             
              a = s.split("[,]");                                               // row content like a,b,c,d -> spplit to cup infor
              staffID=a[0].trim();                                              // get infor after cut 
              name=a[1].trim();
              age=Integer.parseInt(a[2].trim());
              gender=a[3].trim();
              address=a[4].trim();
              phone=a[5].trim();
              deparment=a[6].trim();
              shift=a[7].trim();
              salary=Double.parseDouble(a[8].trim());
              listNurse.put(staffID, new Nurse(staffID,deparment,shift,salary,"P001",name,age,gender,address,phone));
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
   for(Nurse c:listNurse.values()){
       pw.println(c.toString());                                                // print toString of class to file row by row
   }
   pw.close();
   fw.close();  
  }

    private static void Display(HashMap<String, Nurse> listFind) {              // s->String, d->decimal(number),f->Double or float
        header();                                                               // %11s -> 11 String boxes and print in right 
        printListFlower(listFind);
        printFooter();
    }
    public static void header(){
          System.out.println("###############################################################################################");
          System.out.printf("%s %11s %11s %11s %11s %11s %11s %11s %11s %11s\n", "#","staffID#","name#","age#","gender#","address#","phone#","department#","shift#","salary#");
          System.out.println("###############################################################################################");
          
      }
      public static void printListFlower(HashMap<String, Nurse> listFind){
          for(Nurse n:listFind.values()){
              System.out.printf("%s %11s %11s %11d %11s %11s %11s %11s %11s %11.2f\n", "#",n.getStaffID(),n.getName(),n.getAge(),n.getGender(),n.getAddress()
                      ,n.getPhone(),n.getDeparment(),n.getShift(),n.getSalary());
          }
      }
      public static void printFooter(){
          System.out.println("###############################################################################################");
          
      }
    
    
}
