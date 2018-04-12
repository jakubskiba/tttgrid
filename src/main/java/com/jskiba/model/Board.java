package com.jskiba.model;

public class Board {
    public static final char EMPTY_FIELD_CHAR = ' ';
    private int height;
    private int width;
    private int boardSize;
    private char[] fields;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.boardSize = height * width;
        this.initBoard();
        this.populateBoard();
    }

    private void initBoard() {
        this.fields = new char[this.boardSize];
    }

    private void populateBoard() {
        for(int i = 0; i < this.boardSize; i++) {
            this.fields[i] = EMPTY_FIELD_CHAR;
        }
    }

    public char[] getFields() {
        return fields;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getBoardSize() {
        return boardSize;
    }
}
