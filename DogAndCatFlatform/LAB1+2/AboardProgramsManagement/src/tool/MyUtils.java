/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoang
 */
public class MyUtils {

    public static Scanner sc = new Scanner(System.in);
    public static String msg = "Not empty!";
    public static String msgreq = "Wrong format!";
    public static String duplicate = "Duplicate!";

    public static String getString(String welcome, String msg, int min, int max) {
        int temp;
        if (min > max) {
            temp = min;
            min = max;
            max = temp;
        }
        boolean check = true;
        String result = "";
        int L;
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
            } else {
                check = false;
            }
            L = result.length();
            if (L > max || L < min) {
                check = true;
            }

        } while (check);
        return result;
    }

    public static String getInputDate(String welcome) {                                   // Return a String with format dd/MM/YYYY or dd-MM-YYYY -> I copy this regex in the internet
//        String regex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String dateRe = "";
        boolean result = false;
        do {
            result = false;
            try {
                String dates = getInput(welcome);
                if (!dates.isEmpty()) {
                    Date date = sdf.parse(dates);
                    dateRe = sdf.format(date);
                    result = checkMMdd(dates);
                }
            } catch (ParseException e) {
                System.out.println(msgreq);
                result = true;
            }
        } while (result);

        return dateRe;
    }

    public static boolean checkMMdd(String date) {
        String[] arr = date.split("/");
        int MM = Integer.parseInt(arr[0]);
        int dd = Integer.parseInt(arr[1]);
        boolean result = false;
        if (MM > 12 && dd > 31) {
            System.out.println("You have entered both 12 months and 31 days. Please re-enter a valid [MM] month and [dd] day");
            System.out.print("Enter again: ");
            result = true;
        } else {
            if (MM > 12 || dd > 31) {
                if (MM > 12) {
                    System.out.println("You have entered more than 12 months. Please re-enter date");
                    System.out.print("Enter again: ");
                    result = true;
                }
                if (dd > 31) {
                    System.out.println("You have entered more than 31 days. Please re-enter date");
                    System.out.print("Enter again: ");
                    result = true;
                }
            }
        }
        return result;
    }

    public static String getString(String msg) {

        boolean check = true;
        String result = "";
        int L;
        do {
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
            } else {
                check = false;
            }

        } while (check);
        return result;
    }

    public static String getInputTime() {

        String result = "";

        boolean check = false;
        do {
            check = false;
            System.out.print("Time(January, March, May, July, September, November): ");
            result = sc.nextLine();
            if (result.isEmpty()) {
                result = "";
            } else {
                if (result.equalsIgnoreCase("January")) {
                    result = "January";
                } else if (result.equalsIgnoreCase("March")) {
                    result = "March";
                } else if (result.equalsIgnoreCase("May")) {
                    result = "May";
                } else if (result.equalsIgnoreCase("July")) {
                    result = "July";
                } else if (result.equalsIgnoreCase("September")) {
                    result = "September";
                } else if (result.equalsIgnoreCase("November")) {
                    result = "November";
                } else {
                    System.out.println("Only accept as: January, March, May, July, September, November!");
                    System.out.println("Enter again: ");
                    check = true;
                }
            }
        } while (check);

        return result;
    }

    public static String getInput(String welcome) {

        String result = "";
        System.out.print(welcome);
        result = sc.nextLine();
        return result;
    }

    public static String getInputDay(String welcome) {

        int result;
        boolean check = false;
        String re = "";
        do {
            System.out.print(welcome);
            re = sc.nextLine();
            if (!re.isEmpty()) {
                result = Integer.parseInt(re);
                if (result > 40) {
                    System.out.println("The maximum number of days to enter is 40!");
                    check = true;
                } else if (result < 30) {
                    System.out.println("The minimum number of days to enter is 30!");
                    check = true;
                } else {
                    re = "" + result;
                    check = false;
                }
            }

        } while (check);
        return re;
    }

    public static String getString(String welcome, String msg) {

        boolean check = true;
        String result = "";
        int L;
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
            } else {
                check = false;
            }

        } while (check);
        return result;
    }

    public static String getInputContent(String welcome, String msg) {

        boolean check = true;
        String result = "";
        do {
            check = true;
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
            } else {
                if (result.endsWith(".doc") || result.endsWith(".pdf")) {
                    check = false;
                } else {
                    System.out.println("Your path is not a path because '.doc' or '.pdf' is missing at the end of the line");
                }

            }
        } while (check);
        return result;
    }

    public static String getStringPass(String welcome) {

        String result = "";
        System.out.print(welcome);
        result = sc.nextLine();

        return result;
    }

    public static String getStringreg(String welcome, String pattern, String msg, String msgreg) {
        boolean check = true;
        String result = "";
        Scanner sc = new Scanner(System.in);
        do {

            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
            } else if (!result.matches(pattern)) {
                System.out.println(msgreg);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getStringreg(String pattern, String msg, String msgreg) {
        boolean check = true;
        String result = "";
        Scanner sc = new Scanner(System.in);
        do {
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
            } else if (!result.matches(pattern)) {
                System.out.println(msgreg);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static int getIntInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must be large than " + min);
                } else if (number > max) {
                    System.out.println("Number must be lower than " + max);
                } else {
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min && number > max);
        return number;
    }

    public static int getInt(String welcome, int min) {
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {

                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must be large than " + min);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static float getFloat(String welcome, float min) {
        boolean check = true;
        float number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {

                System.out.print(welcome);
                number = Float.parseFloat(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must be large than " + min);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static double getDouble(String welcome, int min) {
        boolean check = true;
        double number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {

                System.out.print(welcome);
                number = Double.parseDouble(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must be large than " + min);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static String getDateNow() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }

    public static boolean Quit(String welcome) {
        String select;
        boolean result = true;
        select = MyUtils.getStringreg("Do you want to exit " + welcome + "?(Y/N)", "[a-zA-Z]{1}", "Pls re-enter[Y/N] to exit!", "The wrong format!");
        if (select.equalsIgnoreCase("Y")) {
            result = true;
        }
        if (select.equalsIgnoreCase("N")) {
            result = false;
        }
        return result;
    }

    public static boolean Quit() {
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
//    public static int dateToInt(String date) {
//        try {
//            // Convert date to int
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Date start = sdf.parse(date);                                           // format string to date
//            return Integer.parseInt((start.getTime() / 1000) + "");
//        } catch (ParseException ex) {
//            Logger.getLogger(FlowerManagement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return 0;
//    }
}
