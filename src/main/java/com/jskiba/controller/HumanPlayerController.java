package com.jskiba.controller;

import com.jskiba.model.Board;
import com.jskiba.model.Player;
import com.jskiba.view.View;

public class HumanPlayerController implements PlayerController {
    private Player player;
    private View view;

    public HumanPlayerController(Player player, View view) {
        this.player = player;
        this.view = view;
    }

    @Override
    public int getCoordinatesOfMove(Board board) {
        String prompt = player.getName() + " provide coodinates";
        return view.getNumberInRange(prompt, 0, board.getBoardSize()-1);
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }
}
