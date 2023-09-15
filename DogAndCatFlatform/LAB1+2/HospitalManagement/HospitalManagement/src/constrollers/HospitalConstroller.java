/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constrollers;

import java.io.IOException;

/**
 *
 * @author nguye
 */
public class HospitalConstroller {
    public static void saveData() throws IOException{                           // Call save function from NConstroller and PCOnstroller 
        NurseConstroler.saveFileFR("data/nurse.dat");
        PatientConstroller.saveFileFR("data/patient.dat");
    }
    public static void loadData() throws IOException{                           // Call load function from NConstroller and PConstroller 
        NurseConstroler.loadFileFR("data/nurse.dat");
        PatientConstroller.loadFileFR("data/patient.dat");
    }
    public static boolean Quit(){                                               // If true we will save data and out
        Validate.myPrint("DO you want to exit (Y/N) ?");                         
        String choice=Validate.checkInputYN();                                  // return Y or N                   
        if(choice.equals("Y")){
            return true;
        }
        return false;
    }
    public static void DisplayMenu(){                                           // Menu
        Validate.myPrintln("1. Nurse’s management \n" +
"1.1 Create a nurse\n" +
"1.2 Find the nurse\n" +
"1.3 Update the nurse\n" +
"1.4 Delete the nurse\n" +
"2. Patient’s management\n" +
"2.1 Add a patient\n" +
"2.2 Display patients\n" +
"2.3 Sort the patients list\n" +
"2.4 Save data\n" +
"2.5 Load data\n" +
"Others – Quit.");
    }
}
