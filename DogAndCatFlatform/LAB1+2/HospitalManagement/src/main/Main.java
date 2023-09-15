/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.Scanner;
import management.NurseManagement;
import management.PatientManagement;
import tools.Menu;
import tools.MyUntil;

/**
 *
 * @author hoang
 */
public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Menu menu = new Menu();
        menu.add("Nurse's management");
        menu.add("Patient's management");
        int select;
        int nChoice = menu.size();
        String selects;
        boolean check = false;
        NurseManagement nurseFnc = new NurseManagement();
        PatientManagement patientFnc = new PatientManagement(nurseFnc);
        do {
            do {
                System.out.println("------HOSPITAL------");
                select = menu.getChoice();
                switch (select) {
                    case 1:
                        menu.menuOfNurse(nurseFnc);
                        break;
                    case 2:
                        menu.menuOfPatient(patientFnc, nurseFnc);
                        break;
                    default:
                        if (menu.askUser()) {
                            check = true;
                        } else {
                            check = false;
                        }
                }

            } while (!check);
        } while (select > 0 && select <= nChoice);
    }
}
