package com.entry;

import com.controller.Controller;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Runner {
    public static void main(String args[]) {

                        Scanner sc1 = new Scanner(System.in);
                        System.out.println("Input command (or copy/paste from the list)\nGET_ALL_BOOKS" +
                                "\nGET_BOOK\nADD_BOOK\nDELETE_BOOK or 'q' for exit");
                        if (sc1.hasNextLine()) {
                            String message = sc1.nextLine();
                            if (message.equals("q")) {
                                System.out.println("Bye!!");
                                return;
                            }

                                new Controller(8085, message);

                        }



                   // showCollection(result);




    }


    public static int getInt()
    {
        Scanner in = new Scanner(System.in);
        String s = null;
        Pattern p = Pattern.compile("^\\d+$");
        Matcher m = null;

        do
        {
            System.out.println("Введите целое число");
            s = in.nextLine();
            m = p.matcher(s);
        } while(!m.matches());

        return Integer.parseInt(s);
    }
}
