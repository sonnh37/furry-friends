/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management;

import tools.Menu;
import tools.MyUtils;

/**
 *
 * @author hoang
 */
public class FlowerStoreManagement {
    
    public static void flowerManagement() {
        Menu menu = new Menu();
        menu.add("Add a flower");
        menu.add("Find a flower");
        menu.add("Update a flower");
        menu.add("Delete a flower");
        int select;
        int nChoice = menu.size();
        System.out.flush();
        do {
            select = menu.getChoiceWithValue("flower");
            switch (select) {
                case 1:
                    FlowerManagement.addFlower();
                    break;
                case 2:
                    FlowerManagement.findFlower();
                    break;
                case 3:
                    FlowerManagement.display();
                    FlowerManagement.updateFlower();
                    break;
                case 4:
                    FlowerManagement.deleteFlower();
                    break;
            }
            System.out.flush();
        } while (select > 0 && select <= nChoice);
    }

    public static void orderManagement() {
        Menu menu = new Menu();
        menu.add("Add an order");
        menu.add("Display orders");
        menu.add("Sort orders");
        menu.add("Save data");
        menu.add("Load data");
        int select;
        int nChoice = menu.size();
        do {
            select = menu.getChoiceWithValue("order");
            switch (select) {
                case 1:
                    OrderManagement.addOrder();
                    break;
                case 2:
                    OrderManagement.display();
                    break;
                case 3:
                    break;
                case 4:
                    FlowerManagement.saveData();
                    OrderManagement.saveData();
                    System.out.println("Saved to the binary file that named with flower.dat. and order.dat.");
                    break;
                case 5:
                    FlowerManagement.loadData();
                    OrderManagement.loadData();
                    System.out.println("Loaded from the flowers.dat and order.dat.");
                    break;

            }

        } while (select > 0 && select <= nChoice);
    }
}
