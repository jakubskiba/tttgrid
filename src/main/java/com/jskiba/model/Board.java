package com.jskiba.model;

public class Board {
    public static final char EMPTY_FIELD_CHAR = ' ';
    private int side;
    private int boardSize;
    private char[] fields;

    public Board(int side) {
        this.side = side;
        this.boardSize = side * side;
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
        return fields.clone();
    }

    public int getSide() {
        return side;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public boolean setField(char sign, int coordinates) {
        if(coordinates >= boardSize || coordinates < 0) {
            throw new IllegalArgumentException("Invalid coordinates!");
        }

        if(this.fields[coordinates] == ' ') {
            this.fields[coordinates] = sign;
            return true;
        } else {
            return false;
        }
    }
}
