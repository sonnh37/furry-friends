/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constrollers;

import java.util.HashMap;
import java.util.Scanner;
import models.Nurse;
import models.Patient;

/**
 *
 * @author nguye
 */
public class Validate {
    private static final Scanner SCANNER=new Scanner(System.in);                // Scanner class use get line input ò console
        public static String checkInputString(){                                // Return String that is's empty
            while (true) {                                                      // while stop if user enter something if not user must enter agaign
                String result = SCANNER.nextLine().trim();
                if (result.isEmpty()) {
                    System.err.println("Not empty");
                    System.out.print("Enter again: ");
                } else {
                    return result;
                }
            }
        }
        public static int checkInputNumber(){                                   // Return a positive number
        
        do{                                                                     // while stop if user enter a number that greater than 0 if not user must enter agaign
            try{
               int result = Integer.parseInt(SCANNER.nextLine());
               
                    if (result<=0) {
                        System.err.println("It can be smaller than 0");
                        System.out.print("Enter again: ");
                    } else {
                        return result;
                    }
            }catch(NumberFormatException e){
                System.out.println("Input a number");
                System.out.println("Enter agaign: ");
               
            }
        }
        while (true);
    }
        public static double checkInputSalary(){                                // Return a positive number
        
        do{                                                                     // while stop if user enter a number that greater than 0 if not user must enter agaign
            try{
               float result = Float.parseFloat(SCANNER.nextLine());
               
                    if (result<=0) {
                        System.err.println("It can be smaller than 0");
                        System.out.print("Enter again: ");
                    } else {
                        return result;
                    }
            }catch(NumberFormatException e){
                System.out.println("Input a number");
                System.out.println("Enter agaign: ");
               
            }
        }
        while (true);
    }
        public static boolean checkUniqueStaffID(String staffID,HashMap<String,Nurse> listNurse){        // return true if staffID exists in list And false in the opposite case 
            return listNurse.containsKey(staffID);
        }
        public static boolean checkUniqueID(String id,HashMap<String,Patient> listPatient){             // return true if id exists in list And false in the opposite case
            return listPatient.containsKey(id);
        }
        public static boolean checkNurseTakecare(String staffID,HashMap<String,Patient> listPatient){     // Check if the nurse is taking care of 2 or more patients -> Return false ì nurse take care greater than 2 And false in the opposite case 
            int dem=0;
            
            for(Patient patient : listPatient.values()){
                String twoNurse[]=patient.getNurseAssigned().split("-");                                   // getNurseAssigned has the form nurse1-nurse2 so we will cut the string to get each nurseID
                for(String nurse:twoNurse){
                    if(nurse.trim().equals(staffID)){
                        dem++;
                    }
                }
            }
            if(dem>=2){
                return false;
            }
            return true;
        }
        public static int getNurseTakecare(String staffID,HashMap<String,Patient> listPatient){     // Check if the nurse is taking care of 2 or more patients -> Return false ì nurse take care greater than 2 And false in the opposite case 
            int dem=0;
            
            for(Patient patient : listPatient.values()){
                String twoNurse[]=patient.getNurseAssigned().split("-");                                   // getNurseAssigned has the form nurse1-nurse2 so we will cut the string to get each nurseID
                for(String nurse:twoNurse){
                    if(nurse.trim().equals(staffID)){
                        dem++;
                    }
                }
            }
            return dem;
        }
        public static String checkInputGender(){                                // Return a string male or female
        System.out.print("Enter gender(male,female): ");
        while (true) {                                                          // while stop if user enter male or female if not user must enter agaign
            String result = checkInputString();
            //return true if user input y/Y
            if (result.trim().equals("male")) {
                return result;
            }
            if(result.trim().equals("female")){
                return result;
            }
           
            System.err.println("Please gender (male,female).");
            System.out.print("Enter again: ");
        }
    }
        public static int checkInputTern(){                                     // Return 1 to sort with id and return 2 to sort with name
        
        while (true) {                                                          // while stop if user enter id or name if not user must enter agaign
            String result = checkInputString();
            
            if (result.trim().equals("id")) {
                return 1;
            }
            if(result.trim().equals("name")){
                return 2;
            }
            System.err.println("Please enter (id,name).");
            System.out.print("Enter again: ");
        }
    }
       public static String checkInputDate(){                                   // Return a String with format dd/MM/YYYY or dd-MM-YYYY -> I copy this regex in the internet
           String regex="^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
           String date=checkInputString();
           while(!date.matches(regex)){                                         // while stop if user enter match pattern if not user must enter agaign
               myPrint("Enter correct date format: ");
               date=checkInputString();
           }
           return date;
       } 
        public static String checkInputDepartment(){                            // return a string tha have from 3 to 50 characters [a-zA-Z1-9] Allows input of characters from a-z, A-Z and 1-9
            String regex="^[a-zA-Z1-9 ]{3,50}";                                 // while stop if user enter match pattern if not user must enter agaign
            while(true){
                String result=checkInputString();
                if(result.matches(regex)){
                    return result;
                }else{
                    System.out.println("Enter department from 3 to 50 chracter");
                    System.out.print("ENter again: ");
            }
        }}
        public static String checkInputPhone(){                                 // return a phone the have from 9 to 11 positive number
        String regex="^\\d{9,11}$";                                             // \d -> input a number, {a,b} Require lenght from a to b, \ check not null, $ check in a line
        String phone=checkInputString();
        while(!phone.matches(regex)){                                           // while stop if user enter match phone pattern if not user must enter agaign
            Validate.myPrint("Not valid phone format!Try agaign: ");
            phone=checkInputString();
        }
        return phone;
        
    }
        public static String checkInputShift(){                                 // Return morning, afternoon or night
        System.out.print("Enter shift(morning,afternoon or night): ");          // While stop if user enter morning, afternoon or night if not user must enter agaign
        while (true) {
            String result = checkInputString();
            //return true if user input y/Y
            if (result.trim().equals("morning")) {
                return result;
            }
            if(result.trim().equals("afternoon")){
                return result;
            }
            if(result.trim().equals("night")){
                return result;
            }
            
            System.err.println("Please enterr shift(morning,afternoon or night).");
            System.out.print("Enter again: ");
        }
    }
        public static String checkInputYN(){                                    // Return Y or N 
            while(true){                                                        // while will stop if function run to return if not it will be loop and don't stop
                String result=checkInputString();
                if(result.trim().equalsIgnoreCase("Y")){
                    return result;
                }else if(result.trim().equalsIgnoreCase("N")){
                    return result;
                }else{
                    System.out.print("Enter Y/N: ");                            // If input don't be Y or N. require user input agaign
                }
            }
        }
        public static boolean checkInputType(){                                 // Return true -> Sort up   return false -> Sort decreasing
        System.out.print("Enter type(asc,desc): ");
        while (true) {                                                          // While stop if user enter asc or desc if not user must enter agaign
            String result = checkInputString();
            //return true if user input y/Y
            if (result.trim().equalsIgnoreCase("asc")) {
                return true;
            }
            if(result.trim().equalsIgnoreCase("desc")){
                return false;
            }
           
            System.err.println("Please input (asc,desc).");
            System.out.print("Enter again: ");
        }
    }
        public static void myPrint(String content){
            System.out.print(content);
        }
        public static void myPrintln(String content){
            System.out.println(content);
        }
}
