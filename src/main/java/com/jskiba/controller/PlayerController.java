package com.jskiba.controller;

import com.jskiba.model.Board;
import com.jskiba.model.Player;

public interface PlayerController {
    int getCoordinatesOfMove(Board board);
    Player getPlayer();
}
