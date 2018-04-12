package com.jskiba.controller;

import com.jskiba.model.Board;
import com.jskiba.model.Player;
import com.jskiba.view.View;

public class AIPlayerController implements PlayerController {
    public AIPlayerController(Player player) {
    }

    @Override
    public int getCoordinatesOfMove(Board board) {
        return 0;
    }

    @Override
    public Player getPlayer() {
        return null;
    }
}
