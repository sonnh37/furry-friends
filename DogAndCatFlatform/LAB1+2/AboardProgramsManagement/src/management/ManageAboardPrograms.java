/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management;

import core.AboardProgram;
import java.util.ArrayList;
import tool.MyUtils;

/**
 *
 * @author hoang
 */
public class ManageAboardPrograms {

    static ArrayList<AboardProgram> listProgram = new ArrayList<>();

    public static void addNewAboardProgram() {
        do {
            AboardProgram program = getProgram();
            listProgram.add(program);
        } while (MyUtils.Quit("add more Aboard Program"));
    }

    public static void displayAllAboardPrograms() {
        header();
        printListProgram(listProgram);
    }

    public static void editInformationAboardProgram() {
        String id;
        int i = 0;
        int select = 0;
        if (!isEmptyList()) {
            id = MyUtils.getString("Update by id: ", MyUtils.msg);
            AboardProgram result = getProgramById(id);
            if (result == null) {
                System.out.println("The flower does not exist");
            } else {
                this.updateInfProgramDetail(res );
                System.out.println(x.toString());
                System.out.println("UPDATED");

            }
        }
    }

    public String getInputOfUpdate(String welcome, int min, int max) {
        String input = "";
        int L;
        boolean OK;
        int temp;
        if (min > max) {
            temp = min;
            min = max;
            max = temp;
        }
        do {
            System.out.print(welcome);
            input = MyUtils.sc.nextLine().trim();
            OK = input.isEmpty(); // true - pass, false - retake length
            if (OK == false) {
                L = input.length();
                if (L > max || L < min) {
                    OK = false;
                } else {
                    OK = true;
                }
            }
        } while (OK == false); // empty thi pass, !empty 
        return input;
    }

    public float getInputOfUpdate(String welcome, float value) {
        String input = "";
        boolean OK;
        do {
            System.out.print(welcome);
            input = MyUtils.sc.nextLine().trim();
            OK = input.isEmpty();
            if (OK == false) {
                value = Float.parseFloat(input);
                OK = true;
            }

        } while (OK == false || value < 1);
        return value;
    }

    public void updateInfProgram(AboardProgram result) {
        String input = "";
        String Id;
        String Name;
        String time;
        String FromRegistrationDate = "";
        String EndRegistrationDate = "";
        String days = "";
        String location = "";
        String content = "";
        int cost = 0;
        //description
        input = this.getInputOfUpdate("New Name: ", 1, 100);
        if (!input.isEmpty()) {
            result.setName(input);
        } 
        //unitprice
        float unitprice = 1;
        unitprice = this.getInputOfUpdate("New unitprice's flower: ", unitprice);
        if (unitprice != 1) {
            result.setUnitprice(unitprice);
        }
        // category
        input = this.getInputOfUpdate("New category's flower: ", 1, 500);
        if (!input.isEmpty()) {
            result.setCategory(input);
        }
    }

    public static void searchAndDisplayAboardProgram() {

    }

    // func check, get
    public static void header() {
        System.out.printf("%11s %11s %25s %11s %20s %20s %11s %60s %11s %11s\n",
                "No.", "Id",
                "Name",
                "Time",
                "From Registration Date",
                "End Registration Date ",
                "Days",
                "Location",
                "Cost",
                "Content"
        );
        System.out.println();
    }

    public static void printListProgram(ArrayList<AboardProgram> list) {
        int count = 0;
        for (AboardProgram x : list) {
            count++;
            System.out.printf("%11s %11s %25s %11s %20s %20s %11s %60s %11s %11s\n", count,
                    x.getId(),
                    x.getName(),
                    x.getTime(),
                    x.getFromRegistrationDate(),
                    x.getEndRegistrationDate(),
                    x.getDays(),
                    x.getLocation(),
                    x.getCost(),
                    x.getContent()
            );
        }
    }

    private static AboardProgram getProgram() {
        boolean ap;
        String Id;
        String Name;
        String time;
        String FromRegistrationDate = "";
        String EndRegistrationDate = "";
        String days = "";
        String location = "";
        String content = "";
        int cost = 0;
        System.out.println("Program:");
        do {
            Id = MyUtils.getStringreg("Id(Pxxx): ", "P[\\d]{3}", MyUtils.msg, MyUtils.msgreq); // not
            ap = isHaveProgramById(Id); //true =  dupli
            if (ap) {
                System.out.println(MyUtils.duplicate);
            }
        } while (ap);

        Name = MyUtils.getString("Name: ", MyUtils.msg); // not
        time = MyUtils.getInputTime();// can 
        FromRegistrationDate = MyUtils.getInputDate("From registration date: "); // can
        EndRegistrationDate = MyUtils.getInputDate("End registration date: "); // can
        days = MyUtils.getInputDay("Days (30 to 40): "); // can
        location = MyUtils.getString("Location: ", MyUtils.msg); // not
        cost = MyUtils.getInt("Cost: ", 1);
        content = MyUtils.getInputContent("Content: ", MyUtils.msg);

        return new AboardProgram(Id, Name, time, FromRegistrationDate, EndRegistrationDate, days + "days", location, cost, content);
    }

    private static AboardProgram getProgramById(String Id) {
        AboardProgram check = null;
        for (AboardProgram x : listProgram) {
            if (x.getId().equals(Id)) {
                check = x;
            }
        }
        return check;
    }

    private static boolean isHaveProgramById(String Id) {
        boolean check = false;
        for (AboardProgram x : listProgram) {
            if (x.getId().equals(Id)) {
                check = true;
            }
        }
        return check;
    }

    public static boolean isEmptyList() {
        boolean check = false;
        if (listProgram.isEmpty()) {
            System.out.println("Empty List!");
            check = true;
        }
        return check;
    }
}
