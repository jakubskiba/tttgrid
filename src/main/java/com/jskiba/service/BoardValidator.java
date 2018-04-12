package com.jskiba.service;

import com.jskiba.model.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BoardValidator {
    public boolean isBoardFull(Board board) {
        for(Character field : board.getFields()) {
            if(field.equals(board.EMPTY_FIELD_CHAR)) {
                return false;
            }
        }

        return true;
    }

    public List<String> findAllLines(Board board) {
        List<String> lines = new ArrayList<>();

        lines.addAll(findHorizontalLines(board));

        return lines;
    }

    private List<String> findHorizontalLines(Board board) {
        List<String> horizontalLines = new ArrayList<>();

        board.getBoardSize();
        String fieldsString = String.valueOf(board.getFields());
        int lineLength = board.getWidth();
        for(int i = 0; i < board.getBoardSize(); i+= lineLength) {
            String currentLine = fieldsString.substring(i, i+lineLength);
            horizontalLines.add(currentLine);
        }

        return horizontalLines;
    }

    public boolean isLineComplete(String line) {
        return isLineComplete(' ', line);
    }

    public boolean isLineComplete(Character emptyCharSign, String line) {
        if(line.contains(emptyCharSign.toString())) {
            return false;
        }

        Character firstChar = line.charAt(0);

        for(int i = 0; i < line.length(); i++) {
            Character currentChar = line.charAt(i);
            if(!firstChar.equals(currentChar)) {
                return false;
            }
        }

        return true;
    }
}
