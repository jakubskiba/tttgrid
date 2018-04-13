package com.jskiba.controller;

import com.jskiba.model.Board;
import com.jskiba.model.Player;
import com.jskiba.service.BoardValidator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HardAIPlayerController extends AbstractAIPlayerController {


    public HardAIPlayerController(Player player, BoardValidator boardValidator) {
        super(player, boardValidator);
    }


    @Override
    public int getCoordinatesOfMove(Board board) {
        float[] fieldsImportance = new float[board.getBoardSize()];
        List<List<Integer>> allLines = getBoardValidator().findAllLinesCoordinates(board);

        Set<Character> opponentsSigns = findOpponentsSigns(board);
        for(Character oopnentSign : opponentsSigns) {
            addImportanceOf(oopnentSign, fieldsImportance, board, allLines);
        }

        addImportanceOf(getPlayer().getSign(), fieldsImportance, board, allLines);
        excludeEmptyFields(fieldsImportance, board);

        return findBestFieldId(fieldsImportance, board);
    }

    private Set<Character> findOpponentsSigns(Board board) {
        Set<Character> opponentsSigns = new HashSet<>();

        char[] fields = board.getFields();
        for(int i = 0; i < fields.length; i++) {
            Character currentField = fields[i];
            if(!opponentsSigns.contains(currentField)) {
                opponentsSigns.add(currentField);
            }
        }

        return opponentsSigns;
    }
}
