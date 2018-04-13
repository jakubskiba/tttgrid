package com.jskiba.controller;

import com.jskiba.model.Board;
import com.jskiba.model.Player;
import com.jskiba.service.BoardValidator;

import java.util.List;

public abstract class AbstractAIPlayerController implements PlayerController {
    private Player player;
    private BoardValidator boardValidator;

    public AbstractAIPlayerController(Player player, BoardValidator boardValidator) {
        this.player = player;
        this.boardValidator = boardValidator;
    }

    @Override
    public abstract int getCoordinatesOfMove(Board board);

    @Override
    public Player getPlayer() {
        return this.player;
    }

    public BoardValidator getBoardValidator() {
        return boardValidator;
    }

    void addImportanceOf(Character sign, float[] fieldsImportance, Board board, List<List<Integer>> allLines) {
        for(List<Integer> line : allLines) {
            Float lineCompletionProcess = computeLineCompletionProgress(board, Board.EMPTY_FIELD_CHAR, sign, line);
            for(Integer coordinate : line) {
                fieldsImportance[coordinate] += lineCompletionProcess;
            }
        }
    }

    int findBestFieldId(float[] fieldsImportance, Board board) {
        int bestFieldId = 0;
        for(int i = 0; i<board.getBoardSize(); i++) {
            if(fieldsImportance[i] > fieldsImportance[bestFieldId]) {
                bestFieldId = i;
            }
        }

        if(!isFieldEmpty(board, bestFieldId)) {
            bestFieldId = findCoordinateOfAnyEmptyField(board);
        }
        return bestFieldId;
    }

    void excludeEmptyFields(float[] fieldsImportance, Board board) {
        for(int i = 0; i<board.getBoardSize(); i++) {
            if(!isFieldEmpty(board, i)) {
                fieldsImportance[i] = 0;
            }
        }
    }

    private Float computeLineCompletionProgress(Board board, char emptyFieldSign, char winningSign,
                                                List<Integer> lineCoordinates) {
        int lineSize = board.getWidth();
        int completeFieldsAmount = 0;

        for(Integer coordinate : lineCoordinates) {
            Character currentChar = board.getFields()[coordinate];
            if(!currentChar.equals(emptyFieldSign) && !currentChar.equals(winningSign)) {
                return 0F;
            } else {
                completeFieldsAmount++;
            }
        }
        return Float.valueOf(completeFieldsAmount) / lineSize;
    }

    private boolean isFieldEmpty(Board board, int coordinates) {
        char[] fields = board.getFields();
        Character signOnField = fields[coordinates];
        return signOnField.equals(Board.EMPTY_FIELD_CHAR);
    }

    private int findCoordinateOfAnyEmptyField(Board board) {
        for (int i = 0; i < board.getBoardSize(); i++) {
            if(isFieldEmpty(board, i)) {
                return i;
            }
        }

        throw new IllegalStateException("Lack of fields!");
    }
}
