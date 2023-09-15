/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aboardprograms;

import management.AboardProgramsManagement;
import tool.Menu;
import tool.MyUtils;

/**
 *
 * @author hoang
 */
public class AboardPrograms {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.add("Manage aboard programs");
        menu.add("Manage students");
        menu.add("Register a program for a student");
        menu.add("Report");
        int select;
        int nChoice = menu.size();
        boolean check = false;
        do {
            do {
                System.out.println("####################Aboard Programs Management System#########################");
                select = menu.getChoiceMain();
                switch (select) {
                    case 1:
                        AboardProgramsManagement.ManageAboardPrograms();
                        break;
                    case 2:
                        AboardProgramsManagement.ManageStudents();
                        break;
                    default:
                        if (MyUtils.Quit()) {
                            check = true;
                        } else {
                            check = false;
                        }

                }
            } while (!check);
        } while (select > 0 && select <= nChoice);
    }
}
