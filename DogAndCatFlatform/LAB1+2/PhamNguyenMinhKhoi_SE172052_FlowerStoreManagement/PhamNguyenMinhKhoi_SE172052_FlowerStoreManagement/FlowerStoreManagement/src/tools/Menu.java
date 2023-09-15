/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.ArrayList;
import java.util.Scanner;
import management.FlowerManagement;
import management.OrderManagement;

/**
 *
 * @author Laptop
 */
public class Menu extends ArrayList<String> {

    static Scanner sc = new Scanner(System.in);

    public Menu() {
    }

    public boolean askUser() {
        String select;
        boolean result = true;
            select = MyUtils.getStringreg("Do you want to exit?(Y/N)", "[a-zA-Z]{1}", "Pls re-enter[Y/N] to exit!", "The wrong format!");
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
        if (value.equalsIgnoreCase("flower")) {
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
