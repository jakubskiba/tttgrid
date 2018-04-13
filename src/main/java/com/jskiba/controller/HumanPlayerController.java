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
        String prompt = player.getName() + " provide coordinate (letterNumber ex. a0)";
        String userInput = view.getText(prompt);
        int coordinate = getCoordinate(userInput, board.getSide());
        while (coordinate < 0) {
            userInput = view.getText(prompt);
            coordinate = getCoordinate(userInput, board.getSide());
        }
        return coordinate;
    }

    private int getCoordinate(String coordinate, int rowSize) {

        if(coordinate.length() >= 2) {
            Character letter = coordinate.charAt(0);
            String digit = coordinate.substring(1, coordinate.length());

            Integer column = Character.toLowerCase(letter) - 'a';
            try {
                Integer row = Integer.parseInt(digit);
                return row*rowSize + column;
            } catch (NumberFormatException e) {
                return -1;
            }
        }

        return -1;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }
}
