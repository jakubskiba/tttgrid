package com.jskiba.controller;

import com.jskiba.model.Board;
import com.jskiba.model.Player;
import com.jskiba.service.BoardValidator;
import com.jskiba.view.View;

import java.util.*;

public class AIPlayerController implements PlayerController {
    private Player player;
    private BoardValidator boardValidator;

    public AIPlayerController(Player player, BoardValidator boardValidator) {
        this.player = player;
        this.boardValidator = boardValidator;
    }

    @Override
    public int getCoordinatesOfMove(Board board) {
        Map<Integer, Integer> countedCoordinates = countCoordinates(board, findLinesWinningForSign(board, this.player.getSign()));
        int mostFrequentCoordinate = 0;
        int mostFrequentCoordinateFrequency = 0;
        for(Integer coordinate : countedCoordinates.keySet()) {
            if(countedCoordinates.get(coordinate) > mostFrequentCoordinateFrequency) {
                mostFrequentCoordinate = coordinate;
                mostFrequentCoordinateFrequency = countedCoordinates.get(coordinate);
            }
        }
        return mostFrequentCoordinate;

    }

    private Map<Integer, Integer> countCoordinates(Board board, List<List<Integer>> lines) {
        Map<Integer, Integer> coordinatesCount = new HashMap<>();
        for(List<Integer> line : lines) {
            for(Integer coordinate : line) {
                if(!isFieldEmpty(board, coordinate)) {
                    continue;
                }
                if(coordinatesCount.containsKey(coordinate)) {
                    coordinatesCount.put(coordinate, coordinatesCount.get(coordinate) + 1);
                } else {
                    coordinatesCount.put(coordinate, 1);
                }
            }
        }

        return coordinatesCount;
    }

    private boolean isFieldEmpty(Board board, int coordinates) {
        char[] fields = board.getFields();
        Character signOnField = fields[coordinates];
        return signOnField.equals(Board.EMPTY_FIELD_CHAR);
    }

    private List<List<Integer>> findLinesWinningForSign(Board board, char winningSign) {
        List<List<Integer>> winningLines = new ArrayList<>();
        for (List<Integer> lineCoordinates : boardValidator.findAllLinesCoordinates(board)) {
            String line = boardValidator.makeLineFromCoordinates(board, lineCoordinates);
            if(isLinePossibleWinner(Board.EMPTY_FIELD_CHAR, winningSign, line)) {
                winningLines.add(lineCoordinates);
            }
        }
        return winningLines;
    }

    private boolean isLinePossibleWinner(char emptyFieldSign, char winningSign, String line) {
        for(int i = 0; i<line.length(); i++) {
            Character currentChar = line.charAt(i);
            if(!currentChar.equals(emptyFieldSign) && !currentChar.equals(winningSign)) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> findEmptyFieldCoordinates(Board board) {
        List<Integer> emptyFieldCoordinates = new ArrayList<>();

        char[] fields = board.getFields();
        for(int i = 0; i < fields.length; i++) {
            if(fields[i] == board.EMPTY_FIELD_CHAR) {
                emptyFieldCoordinates.add(i);
            }
        }

        return emptyFieldCoordinates;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    class Line {
        private String line;
        private List<Integer> coordinates;
    }
}
