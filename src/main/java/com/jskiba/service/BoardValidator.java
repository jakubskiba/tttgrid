package com.jskiba.service;

import com.jskiba.model.Board;

public class BoardValidator {
    public boolean isBoardFull(Board board) {
        for(Character field : board.getFields()) {
            if(field.equals(board.EMPTY_FIELD_CHAR)) {
                return false;
            }
        }

        return true;
    }

    public boolean isLineComplete(char[] line) {
        return isLineComplete(' ', line);
    }

    public boolean isLineComplete(char emptyCharSign, char[] line) {
        Character firstChar = line[0];

        if(firstChar.equals(emptyCharSign)) {
            return false;
        }

        for(Character field : line) {
            if(!field.equals(firstChar)) {
                return false;
            }
        }

        return true;
    }
}
