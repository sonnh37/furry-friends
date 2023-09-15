package management;

import core.flower;
import core.order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MyUtils;

public class OrderManagement {

    static ArrayList<order> list = new ArrayList<>();

    static String fName = "src\\data\\order.dat";
    static String msg = "Please fill in this information!";
    static String msgreq = "Fill correct format!";
    static String msgDup = "Duplicate!";

    public static void addOrder() {
        String select;
        do {
            order o = getOrder();
            list.add(o);
            select = MyUtils.askUser("add more order");
        } while (select.equalsIgnoreCase("Y"));
    }

    private static order getOrder() {
        String orderId;
        String orderDate;
        String cusName;
        int orddeId;
        String flowerId, flowersId = "";
        int quantity = 0;
        float unitPrice = 0;

        float flowersCost = 0;
        float flowerCost = 0;
        boolean check;

        do {
            orderId = MyUtils.getStringreg("Id's order(OXXX): ", "O[\\d]{3}", msg, msgreq);
            check = isExistOrderById(orderId); //true =  dupli
            if (check == true) {
                System.out.println(msgDup);
            }
        } while (check);
        boolean testDate = true;
        do {
            orderDate = MyUtils.getInputDate("Order Date (MM/dd/YYY): ");
            if ((MyUtils.dateToLong(MyUtils.getDateNow()) - MyUtils.dateToLong(orderDate)) >= 0) {
                testDate = false;
            } else {
                System.out.println("Date must be less than or equal to current date!");
            }
        } while (testDate);
        cusName = MyUtils.getString("Customer Name: ", msg);

        orddeId = list.size() + 1;
        String ask = "";

        int quantityFlower = 1;
        do {
            flowerId = MyUtils.getString("Flower id: ", msg);
            flower f = FlowerManagement.getFlowerById(flowerId);
            if (f != null) {
                quantityFlower = MyUtils.getInt("Flower quantity: ", 1);
                unitPrice = f.getUnitprice();
                flowerCost = quantityFlower * unitPrice;

                flowersId += flowerId.toUpperCase() + "-";
                quantity += quantityFlower;
                flowersCost += flowerCost;
            } else {
                System.out.println("Flower doesnot exit at store!");
            }
            ask = MyUtils.askUser("buy more flower");

        } while (ask.equalsIgnoreCase("Y"));
        String flowersIds = flowersId.substring(0, flowersId.length() - 1);
        return new order(orderId, orderDate, cusName, orddeId, flowersIds, quantity, flowersCost);
    }

    public static void display() {
        System.out.println("LIST ORDER FROMS [Start date] TO [End date]");
        System.out.print("From date [MM/dd/yyyy]: ");
        String start = MyUtils.getInputDate();
        System.out.print("End date [MM/dd/yyyy]: ");
        String end;
        long checkDateToLong;
        do {
            end = MyUtils.getInputDate();
            checkDateToLong = MyUtils.dateToLong(end) - MyUtils.dateToLong(start);
            if (checkDateToLong < 0) {
                System.out.println("Your end date must be greater than or equal from date!");
                System.out.print("Enter again: ");
            }
        } while (checkDateToLong < 0);
        System.out.println();
        System.out.println("LIST ORDER FROMS [" + start + "]" + " TO [" + end + "]");
        DisplayWithValue(getListWithDATE(start, end));
    }

    public static ArrayList<order> getListWithDATE(String start, String end) {
        ArrayList<order> newList = new ArrayList<>();
        for (order x : list) {
            if ((MyUtils.dateToLong(x.getOrderDate()) - MyUtils.dateToLong(start) >= 0)
                    && (MyUtils.dateToLong(end) - MyUtils.dateToLong(x.getOrderDate()) >= 0)) {
                newList.add(x);
            }
        }
        return newList;
    }

    public static void DisplayWithValue(ArrayList<order> list) {
        header();
        printListOrder(list);
    }

    public static void header() {
        System.out.printf("%11s %11s %11s %25s %60s %11s %11s\n", "No.", "OrderId", "OrderDate", "Customer", "FlowerNames", "Quantity", "OrderTotal");
        System.out.println();
    }

    public static void printListOrder(ArrayList<order> list) {
        int dem = 0;

        //flower id -> flower name
        //in
        for (order x : list) {
            dem++;
            System.out.printf("%11s %11s %11s %25s %60s %11s %11s\n", dem, x.getOrderId(), x.getOrderDate(), x.getCusName(), getFlowerNameOfOrder(x.getFlowerId()), x.getQuantity(), x.getFlowerCost());
        }
    }

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
                String orderId, orderDate, cusName, flowerId;
                int orddeId;
                int quantity;
                float flowerCost;
                while (true) {
                    s = br.readLine();
                    if (s == null) {
                        break;
                    }
                    a = s.split("[,]");
                    orderId = a[0].trim();
                    orderDate = a[1].trim();
                    cusName = a[2].trim();
                    orddeId = Integer.parseInt(a[3].trim());
                    flowerId = a[4].trim();
                    quantity = Integer.parseInt(a[5].trim());
                    flowerCost = Float.parseFloat(a[6].trim());
                    list.add(new order(orderId, orderDate, cusName, orddeId, flowerId, quantity, flowerCost));
                }
                fr.close();
                br.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(OrderManagement.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(OrderManagement.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(OrderManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void saveData() {
        if (!isEmptyList()) {
            PrintWriter pww = null;
            try {
                pww = new PrintWriter(fName);
                for (order x : list) {
                    pww.println(x.toString());
                }
                pww.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(OrderManagement.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                pww.close();
            }
        }
    }

    public static boolean isEmptyList() {
        boolean check = false;
        if (list.isEmpty()) {
            System.out.println("Empty List!");
            check = true;
        }
        return check;
    }

    public static boolean isExistOrderById(String id) {
        boolean check = false;
        for (order x : list) {
            if (x.getOrderId().equals(id)) {
                check = true;
            }
        }
        return check;
    }

    public static int countFlowerIdOfOrder(String id) {
        int count = 0;
        for (order x : list) {
            String[] idFlowers = x.getFlowerId().split("-");
            for (String idFlower : idFlowers) {
                if (idFlower.trim().equals(id)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static String getFlowerNameOfOrder(String idNotSplit) { // F000-F0001
        String result = "";
        String[] idSplit = idNotSplit.split("-");
        for (String idFlower : idSplit) {
            flower flowerChecked = FlowerManagement.getFlowerById(idFlower);
            if (flowerChecked != null) {
                result += flowerChecked.getDescription() + ",";
            }
        }
        if (!result.isEmpty()) {
            result = result.substring(0, result.length() - 1);
        }
        if (result.length() > 57) {
            result = result.substring(0, 57) + "...";
        }
        return result;
    }
}
