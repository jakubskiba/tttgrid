package com.jskiba.controller;

import com.jskiba.model.Board;
import com.jskiba.model.Player;

import java.util.Random;

public class RandomPlayerController implements PlayerController {
    private Player player;
    private Random random;

    public RandomPlayerController(Player player) {
        this.player = player;
        this.random = new Random();
    }

    @Override
    public int getCoordinatesOfMove(Board board) {
        return random.nextInt(board.getBoardSize());
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }
}
