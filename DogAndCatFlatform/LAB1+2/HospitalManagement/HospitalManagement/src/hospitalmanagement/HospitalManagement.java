/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagement;

import constrollers.HospitalConstroller;
import constrollers.NurseConstroler;
import constrollers.PatientConstroller;
import constrollers.Validate;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author nguye
 */
public class HospitalManagement {                                               // Main class use to navigate to function from Controller
                                                                                // The NurseConstroler and PatientConstroller contains insert, update, delete and display functions
                                                                                // The validate class contains input and output classes and checks for input patterns 
                                                                                // Class HospitalConstroller contains functions that combine both the NurseConstroller and PatientConstroller classes
                                                                                // You can use ctrl and click lefr mouse  to jump source code of function
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException, ParseException {
        while(true){
            HospitalConstroller.DisplayMenu();
            Validate.myPrint("Enter your choice: ");
            String choice=Validate.checkInputString();
            if(choice.trim().equals("1.1")){
                NurseConstroler.addNurse();
            }else if(choice.trim().equals("1.2")){
                NurseConstroler.findAndDisplayNurse();
            }else if(choice.trim().equals("1.3")){
                NurseConstroler.updateNurse();
            }else if(choice.trim().equals("1.4")){
                NurseConstroler.deleteNurse();
            }else if(choice.trim().equals("2.1")){
                PatientConstroller.addNurse();
            }else if(choice.equals("2.2")){
                PatientConstroller.DisplatPatient();
            }else if(choice.equals("2.3")){
                PatientConstroller.sortPatient();
            }else if(choice.equals("2.4")){
                HospitalConstroller.saveData();
            }else if(choice.equals("2.5")){
                HospitalConstroller.loadData();
            }else {
                if(HospitalConstroller.Quit()){
                    HospitalConstroller.saveData();
                    System.exit(0);
                }
            }
        }
    }
    
    
}
