package com.jskiba.controller;

import com.jskiba.model.Player;
import com.jskiba.service.BoardValidator;
import com.jskiba.view.View;

public class PlayerControllerFactory {

    private View view;
    private BoardValidator boardValidator;

    public PlayerControllerFactory(View view, BoardValidator boardValidator) {
        this.view = view;
        this.boardValidator = boardValidator;
    }

    public PlayerController getPlayerController(String type, Player player) {
        switch (type) {
            case "human":
                return new HumanPlayerController(player, view);

            case "random":
                return new RandomPlayerController(player);

            case "ai-easy":
                return new EasyAIPlayerController(player, boardValidator);

            case "ai-hard":
                return new HardAIPlayerController(player, boardValidator);


            default:
                throw new IllegalArgumentException("No such type: " + type);
        }
    }
}
