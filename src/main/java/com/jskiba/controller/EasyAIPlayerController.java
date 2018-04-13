package com.jskiba.controller;

import com.jskiba.model.Board;
import com.jskiba.model.Player;
import com.jskiba.service.BoardValidator;

import java.util.List;

public class EasyAIPlayerController extends AbstractAIPlayerController {

    public EasyAIPlayerController(Player player, BoardValidator boardValidator) {
        super(player, boardValidator);
    }


    @Override
    public int getCoordinatesOfMove(Board board) {
        {
            float[] fieldsImportance = new float[board.getBoardSize()];
            List<List<Integer>> allLines = getBoardValidator().findAllLinesCoordinates(board);

            addImportanceOf(getPlayer().getSign(), fieldsImportance, board, allLines);
            excludeEmptyFields(fieldsImportance, board);

            return findBestFieldId(fieldsImportance, board);
        }
    }
}
