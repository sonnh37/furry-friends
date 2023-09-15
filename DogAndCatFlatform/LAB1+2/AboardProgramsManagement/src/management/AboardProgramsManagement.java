/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management;

import tool.Menu;

/**
 *
 * @author hoang
 */
public class AboardProgramsManagement {
    public static void ManageAboardPrograms() {
        Menu menu = new Menu();
        menu.add("Displays all aboard programs");
        menu.add("Add a new aboard program");
        menu.add("Edit information a program by id");
        menu.add("Search and display a program by id");
        menu.add("Back to main menu");
        int select;
        int nChoice = menu.size();
        do {
            select = menu.getChoiceSub(0);
            switch (select) {
                case 1:
                    ManageAboardPrograms.displayAllAboardPrograms();
                    break;
                case 2:
                    ManageAboardPrograms.addNewAboardProgram();
                    break;
                case 3:
                    ManageAboardPrograms.editInformationAboardProgram();
                    break;
                case 4:
                    ManageAboardPrograms.searchAndDisplayAboardProgram();
                    break;
                case 5:
                    select = select + 1;
                    break;
            }
        } while (select > 0 && select <= nChoice);
    }

    public static void ManageStudents() {
        Menu menu = new Menu();
        menu.add("Displays all students");
        menu.add("Add a new student");
        menu.add("Edit information a student by id");
        menu.add("Back to main menu");
        int select;
        int nChoice = menu.size();
        do {
            select = menu.getChoiceSub(1);
            switch (select) {
                case 1:
                    ManageStudents.displayAllStudents();
                    break;
                case 2:
                    ManageStudents.addNewStudent();
                    break;
                case 3:
                    ManageStudents.editInformationStudents();
                    break;
                case 4:
                    select = select + 1;
            }
        } while (select > 0 && select <= nChoice);
    }
}
