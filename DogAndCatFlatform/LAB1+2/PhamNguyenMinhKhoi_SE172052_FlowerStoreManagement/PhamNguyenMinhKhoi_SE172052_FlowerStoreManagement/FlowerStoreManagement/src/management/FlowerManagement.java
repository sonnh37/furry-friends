/*
 * To change set license header, choose License Headers in Project Properties.
 * To change set template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import core.flower;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MyUtils;

/**
 *
 * @author Laptop
 */
public class FlowerManagement {

    static Set<flower> set = new HashSet<>();
    static String fName = "src\\data\\flower.dat";
    static String msg = "Please fill in this information!";
    static String msgreq = "Fill correct format!";
    static String msgDup = "Duplicate!";

    public static void loadData() {
        File yourFile = new File(fName);
        if (!yourFile.exists()) {
            System.out.println("File not exists");
        } else {

            FileReader fr = null;
            try {
                fr = new FileReader(fName);
                BufferedReader br = new BufferedReader(fr);
                String s;
                String[] a;
                String id, description, importdate, category;
                float unitprice;
                while (true) {
                    s = br.readLine();
                    if (s == null) {
                        break;
                    }
                    a = s.split("[,]");
                    id = a[0].trim();
                    description = a[1].trim();
                    importdate = a[2].trim();
                    unitprice = Float.parseFloat(a[3].trim());
                    category = a[4].trim();
                    set.add(new flower(id, description, importdate, unitprice, category));
                }
                fr.close();
                br.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FlowerManagement.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FlowerManagement.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(FlowerManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void saveData() {
        if (!isEmptyList()) {
            PrintWriter pww = null;
            try {
                pww = new PrintWriter(fName);
                for (flower x : set) {
                    pww.println(x.toString());
                }
                pww.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FlowerManagement.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                pww.close();
            }

        }
    }

    public static void display() {
        for (flower x : set) {
            System.out.println(x.toString());
        }
    }

    public static void addFlower() {
        String id;
        String description = "";
        String importdate = "";
        float unitprice = 0;
        String category = "";
        boolean check;

        do {
            id = MyUtils.getStringreg("Id's flower(FXXX): ", "F[\\d]{3}", msg, msgreq);
            check = isExistFlowerById(id); //true =  dupli
            if (check == true) {
                System.out.println(msgDup);
            }
        } while (check);
        description = MyUtils.getString("Description: ", msg, 3, 50);

        boolean testDate = true;
        do {
            importdate = MyUtils.getInputDate("Import Date (MM/dd/yyyy): ");
            if ((MyUtils.dateToLong(MyUtils.getDateNow()) - MyUtils.dateToLong(importdate)) >= 0) {
                testDate = false;
            } else {
                System.out.println("Date must be less than or equal to current date!");
            }
        } while (testDate);

        unitprice = MyUtils.getFloat("Unit price: ", 10000);
        category = MyUtils.getString("Category: ", msg);

        flower fl = new flower(id, description, importdate, unitprice, category);
        set.add(fl);
    }

    public static void findFlower() {
        Set<flower> result = null;
        flower fl = null;
        if (!isEmptyList()) {
            String nameOrId = MyUtils.getString("Find flower by name or by id: ", msg);
            String pattern = "[a-zA-Z]{1}[\\d]{3}";
            boolean check = isPattern(nameOrId, pattern);
            if (check) {
                //check id unique
                fl = getFlowerById(nameOrId);

                if (fl != null) {
                    System.out.println(fl.toString());
                } else {
                    System.out.println("This flower does not exist.");
                }
            } else {
                //check names
                result = getFlowerByNames(nameOrId);

                if (result == null) {
                    System.out.println("This flower does not exist.");
                } else {
                    for (flower x : result) {
                        System.out.println(x);
                    }
                }
            }

        }
    }

    public static void updateFlower() {
        String name;
        Set<flower> result;
        List<flower> fls;
        int i = 0;
        int select = 0;
        if (!isEmptyList()) {
            System.out.println("-------------- UPDATE - FLOWER -------------");
            name = MyUtils.getString("Update by name: ", msg);
            result = getFlowerByNames(name);
            if (result == null) {
                System.out.println("The flower does not exist");
            } else {
                fls = new ArrayList<>();
                System.out.println("STT");
                for (flower x : result) {
                    i += 1;
                    System.out.println(i + " " + x);
                    if (result.size() == 1) {
                        updateFlowerDetail(x);
                        System.out.println(x.toString());
                        System.out.println("UPDATED");
                    }
                }

                if (result.size() > 1) {
                    select = MyUtils.getInt("Select 1 of them to update: ", 0) - 1;
                    fls.addAll(result);
                    for (int j = 0; j < fls.size(); j++) {
                        if (select == j) {
                            updateFlowerDetail(fls.get(j));
                            System.out.println(fls.get(j).toString());
                            System.out.println("UPDATED");
                            break;
                        }
                    }
                }

            }
        }
    }

    public static void updateFlowerDetail(flower result) {
        float unitprice = 0;
        unitprice = MyUtils.getFloat("New unitprice's flower: ", 10000);
        result.setUnitprice(unitprice);
    }

    public static void deleteFlower() {
        String id;
        System.out.println("-------------- DELETE - FLOWER -------------");
        id = MyUtils.getStringreg("(FXXX) Remove id: ", "F[\\d]{3}", msg, msgreq);
        flower fls = getFlowerById(id);
        boolean removed = false;
        if (fls == null) {
            System.out.println("The flower does not exist");
        } else {
            if (OrderManagement.countFlowerIdOfOrder(id) > 0) {
                //exist order
                System.out.println("Because the flower id you just entered already exists in the order. Are you sure to delete it?[Y/N]: ");
                do {
                    String select = MyUtils.getStringreg("[a-zA-Z]{1}", msg, msgreq);
                    if (select.toUpperCase().equals("Y")) {
                        set.remove(fls);
                        removed = true;
                    } else if (select.toUpperCase().equals("N")) {
                        removed = true;
                    } else {
                        System.out.println("You can only enter 'Y' or 'N'");
                        removed = false;
                    }
                } while (!removed);
            } else {
                set.remove(fls);
                System.out.println("ID: " + id.toUpperCase() + " has been removed.");
            }
        }
    }

    public static flower getFlowerById(String id) {
        flower check = null;
        for (flower x : set) {
            if (x.getId().equals(id)) {
                check = x;
            }
        }
        return check;
    }

    public static boolean isEmptyList() {
        boolean check = false;
        if (set.isEmpty()) {
            System.out.println("Empty List!");
            check = true;
        }
        return check;
    }

    public static Set<flower> getFlowerByNames(String name) {
        Set<flower> check = new HashSet<>();
        for (flower x : set) {
            if (x.getDescription().toLowerCase().contains(name.toLowerCase())) {
                check.add(x);
            }
        }
        if (check.isEmpty()) {
            check = null;
        }
        return check;
    }

    public static boolean isExistFlowerById(String id) {
        boolean check = false;
        for (flower x : set) {
            if (x.getId().equals(id)) {
                check = true;
            }
        }
        return check;
    }

    public static boolean isPattern(String value, String pattern) {
        boolean check = false;
        if (value.matches(pattern)) {
            check = true;
        }
        return check;
    }
}
