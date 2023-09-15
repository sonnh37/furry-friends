/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import management.NurseManagement;
import management.PatientManagement;

/**
 *
 * @author hoang
 */
public class Menu extends ArrayList<String> {

    static Scanner sc = new Scanner(System.in);

    public Menu() {
    }

    public void menuOfNurse(NurseManagement nurseFnc) throws Exception {
        Menu menuNurse = new Menu();
        menuNurse.add("Create a nurse");
        menuNurse.add("Find the nurse");
        menuNurse.add("Update the nurse");
        menuNurse.add("Delete the nurse");
        int select;
        int nChoice = menuNurse.size();
        String save;
        boolean check = false;
        do {
            do {
                select = menuNurse.getChoiceWithValue("nurse");
                switch (select) {
                    case 1:
                        nurseFnc.addNurse();
                        check = true;
                        break;
                    case 2:
                        nurseFnc.findNurse();
                        break;
                    case 3:
                        nurseFnc.updateNurse();
                        check = true;
                        break;
                    case 4:
                        nurseFnc.deleteNurse();
                        check = true;
                        break;
                    default:
                        if (menuNurse.askUser()) {
                            check = true;
                        } else {
                            check = false;
                        }
                }
            } while (!check);

        } while (select > 0 && select <= nChoice);
    }

    public void menuOfPatient(PatientManagement patientFnc, NurseManagement nurseFnc) throws IOException, ParseException {
        Menu menuPatient = new Menu();
        menuPatient.add("Add a patient");
        menuPatient.add("Display patients");
        menuPatient.add("Sort the patients list");
        menuPatient.add("Save data");
        menuPatient.add("Load data");
        
        int select;
        int nChoice = menuPatient.size();
        boolean check = false;
        do {
            do {
                select = menuPatient.getChoiceWithValue("patient");
                switch (select) {
                    case 1:
                        patientFnc.addPatient();
                        break;
                    case 2:
                        patientFnc.display();
                        break;
                    case 3:
                        break;
                    case 4:
                        nurseFnc.saveData();
                        patientFnc.saveData();
                        break;
                    case 5:
                        nurseFnc.loadData();
                        patientFnc.loadData();
                        break;
                    default:
                        if (menuPatient.askUser()) {
                            check = true;
                        } else {
                            check = false;
                        } 
                }
            } while (!check);
        } while (select > 0 && select <= nChoice);
    }

    public boolean askUser() {
        String select;
        boolean result = true;
            select = MyUntil.getStringreg("Do you want to exit?(Y/N)", "[a-zA-Z]{1}", "Pls re-enter[Y/N] to exit!", "The wrong format!");
            if (select.equalsIgnoreCase("Y")) {
                result = true;
            }
            if (select.equalsIgnoreCase("N")) {
                result = false;
            }
        return result;

    }

    public int getChoiceWithValue(String value) {
        int choice;
        int n = this.size();
        if (value.equalsIgnoreCase("nurse")) {
            for (int i = 1; i <= n; i++) {
                System.out.println(1 + "." + i + " " + this.get(i - 1));
            }
        } else {
            for (int i = 1; i <= n; i++) {
                System.out.println(2 + "." + i + " " + this.get(i - 1));
            }
        }

        System.out.println("Others" + ": Quit");
        System.out.println("___________________");
        System.out.print("Choose (1.." + (n + 1) + ") :");
        choice = Integer.parseInt(sc.nextLine());
        return choice;
    }

    public int getChoice() {
        int choice;
        int n = this.size();
        for (int i = 1; i <= n; i++) {
            System.out.println(i + "." + this.get(i - 1));
        }
        System.out.println((n + 1) + ": Quit");
        System.out.println("___________________");
        System.out.print("Choose (1.." + (n + 1) + ") :");
        choice = Integer.parseInt(sc.nextLine());
        return choice;
    }

}
