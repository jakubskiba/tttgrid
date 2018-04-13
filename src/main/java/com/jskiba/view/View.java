package com.jskiba.view;

import com.jskiba.model.Board;

import java.util.Scanner;

public class View {
    private Scanner in;

    public View() {
        this.in = new Scanner(System.in);
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

    public int getNumberInRange(String prompt, int from, int to) {
        String promptWithRange = prompt + "(" + from + "-" + to + ")";

        int number = getNumber(promptWithRange);
        while (number < from || number > to ) {
            number = getNumber(promptWithRange);
        }
        return number;
    }

    public boolean getYesNo(String prompt) {
        System.out.println(prompt + "(y/n)");

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

    public void print(Board board) {
        char[] fields = board.getFields();
        printLetterCoordinates(board.getSide());
        for(int row = 0; row < board.getSide(); row++) {
            printRow(board.getSide(), row, fields);

            if(row < board.getSide() - 1) {
                printSeparator(board.getSide());
            }
        }
    }

    private void printSeparator(int width) {
        for(int i = 0; i < width * 4 - 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private void printRow(int width, int row, char[] fields) {
        for(int column = 0; column < width; column++) {
            int index = row * width + column;
            String ending = column < width - 1 ? "|" : String.valueOf(row);
            System.out.printf(" %s %s", fields[index], ending);
        }
        System.out.println();
    }

    private void printLetterCoordinates(int width) {
        for(int i = 0; i < width; i++) {
            char letter = (char) ('a' + i);
            System.out.printf(" %s  ", letter);
        }
        System.out.println();

    }

    public void cleanScreen() {
        for(int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
