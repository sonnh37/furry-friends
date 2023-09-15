/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tool;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hoang
 */
public class Menu extends ArrayList<String> {

    static Scanner sc = new Scanner(System.in);

    public Menu() {
    }

    public int getChoiceSub(int value) {
        int choice;
        int n = this.size();
        if (value == 0) {
            for (int i = 1; i <= n; i++) {
                System.out.println(1 + "." + i + " " + this.get(i - 1));
            }
        } else if (value == 1) {
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

    public int getChoiceMain() {
        int choice;
        int n = this.size();
        for (int i = 1; i <= n; i++) {
            System.out.println(i + "." + this.get(i - 1));
        }
        System.out.println(("Others") + ": Quit");
        System.out.println("___________________");
        System.out.print("Choose (1.." + (n + 1) + ") :");
        choice = Integer.parseInt(sc.nextLine());
        return choice;
    }

}
