package com.jskiba.service;

import com.jskiba.model.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoardValidator {
    public boolean isBoardFull(Board board) {
        for(Character field : board.getFields()) {
            if(field.equals(board.EMPTY_FIELD_CHAR)) {
                return false;
            }
        }

        return true;
    }

    private String makeLineFromCoordinates(Board board, List<Integer> coordinateList) {
        StringBuilder line = new StringBuilder();

        char[] fields = board.getFields();

        for(Integer coordinate : coordinateList) {
            if(coordinate > board.getBoardSize() || coordinate < 0) {
                throw new IllegalStateException("There is no " + coordinate + " coordinate");
            }
            line.append(fields[coordinate]);
        }

        return line.toString();
    }

    public List<String> findAllLines(Board board) {
        List<String> lines = new ArrayList<>();

        lines.addAll(findHorizontalLines(board));
        lines.addAll(findVerticalLines(board));
        lines.addAll(findDiagonalLines(board));
        return lines;
    }

    public boolean isBoardComplete(Board board) {
        List<String> lines = findAllLines(board);
        for(String line : lines) {
            if(isLineComplete(line)) {
                return true;
            }
        }
        return false;
    }

    public char findWinningSign(Board board) {
        List<String> lines = findAllLines(board);
        for (String line : lines) {
            if(isLineComplete(line)) {
                return line.charAt(0);
            }
        }
        return board.EMPTY_FIELD_CHAR;
    }

    public List<List<Integer>> findHorizontalLinesCoordinates(Board board) {
        List<List<Integer>> verticalLinesCoordinates = new ArrayList<>();

        int lineSize = board.getHeight();
        for(int i = 0; i<board.getWidth(); i++) {
            List<Integer> coordinates = new ArrayList<>();
            for(int j = 0; j<board.getHeight(); j++) {
                int index = i*lineSize +j;
                coordinates.add(index);

            }
            verticalLinesCoordinates.add(coordinates);
        }

        return verticalLinesCoordinates;
    }

    public List<String> findHorizontalLines(Board board) {
        List<String> horizontalLines = findHorizontalLinesCoordinates(board)
                .stream()
                .map(coordinatedList -> makeLineFromCoordinates(board, coordinatedList))
                .collect(Collectors.toList());


        return horizontalLines;
    }

    public List<List<Integer>> findVerticalLinesCoordinates(Board board) {
        List<List<Integer>> verticalLinesCoordinates = new ArrayList<>();

        int lineSize = board.getWidth();

        for(int i = 0; i<board.getWidth(); i++) {
            List<Integer> lineCoordinates = new ArrayList<>();
            for(int j = 0; j<board.getHeight(); j++) {
                int index = j * lineSize + i;
                lineCoordinates.add(index);
            }
            verticalLinesCoordinates.add(lineCoordinates);
        }
        return verticalLinesCoordinates;
    }

    public List<String> findVerticalLines(Board board) {
        List<String> verticalLines = findVerticalLinesCoordinates(board)
                .stream()
                .map(coordinatedList -> makeLineFromCoordinates(board, coordinatedList))
                .collect(Collectors.toList());
        return verticalLines;
    }

    public List<String> findDiagonalLines(Board board) {
        List<String> diagonalLines = new ArrayList<>();

        diagonalLines.add(findFirstDiagonal(board));
        diagonalLines.add(findSecondDiagonal(board));

        return diagonalLines;
    }

    public List<Integer> findFirstDiagonalCoordinates(Board board) {
        List<Integer> firstDiagonalCoordinates = new ArrayList<>();

        for(int row = 0; row < board.getWidth(); row++) {
            int index = board.getWidth() * row + row;
            firstDiagonalCoordinates.add(index);
        }

        return firstDiagonalCoordinates;
    }

    public String findFirstDiagonal(Board board) {
        return makeLineFromCoordinates(board, findFirstDiagonalCoordinates(board));
    }

    public List<Integer> findSecondDiagonalCoordinates(Board board) {
        List<Integer> secondDiagonalCoordinates = new ArrayList<>();
        for(int row = 0; row < board.getWidth(); row++) {
            int index = board.getWidth() * row + board.getWidth() - row - 1;
            secondDiagonalCoordinates.add(index);
        }
        return secondDiagonalCoordinates;
    }

    public String findSecondDiagonal(Board board) {
        return makeLineFromCoordinates(board, findSecondDiagonalCoordinates(board));
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
