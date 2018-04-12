package com.jskiba.view;

import java.util.Scanner;

public class View {
    private Scanner in;

    public View(Scanner in) {
        this.in = in;
    }

    public int getNumber(String prompt) {
        System.out.println(prompt);

        String line = in.nextLine();
        while (!isNumeric(line)) {
            System.out.println(prompt);
            line = in.nextLine();
        }

        return Integer.parseInt(line);
    }

    public boolean getYesNo(String prompt) {
        System.out.println(prompt);

        String line = in.nextLine();
        while (!line.equalsIgnoreCase("y") && !line.equalsIgnoreCase("n")) {
            System.out.println(prompt);
            line = in.nextLine();
        }

        if(line.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }

    }

    public Character getChar(String prompt) {
        System.out.println(prompt);

        String line = in.nextLine();
        while (line.length() != 1) {
            System.out.println(prompt);
            line = in.nextLine();
        }

        return line.charAt(0);
    }

    public String getText(String prompt) {
        System.out.println(prompt);
        return in.nextLine();
    }

    public void print(String text) {
        System.out.println(text);
    }

    private boolean isNumeric(String line) {
        try {
            Integer.parseInt(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
