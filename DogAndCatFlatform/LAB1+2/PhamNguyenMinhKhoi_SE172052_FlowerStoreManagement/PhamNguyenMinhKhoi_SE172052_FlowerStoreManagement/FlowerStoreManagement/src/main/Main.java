/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;
import management.FlowerManagement;
import management.FlowerStoreManagement;
import management.OrderManagement;
import tools.Menu;
import tools.MyUtils;

/**
 *
 * @author Laptop
 */
public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.add("Flower's management");
        menu.add("Order's management");
        int select;
        int nChoice = menu.size();
        String selects;
        boolean check = false;
        do {
            do {
                System.out.println("------FLOWER------");
                select = menu.getChoice();
                switch (select) {
                    case 1:
                        FlowerStoreManagement.flowerManagement();
                        break;
                    case 2:
                        FlowerStoreManagement.orderManagement();
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
