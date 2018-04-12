package com.jskiba.controller;

import com.jskiba.model.Player;
import com.jskiba.view.View;

public class PlayerControllerFactory {

    private View view;

    public PlayerControllerFactory(View view) {
        this.view = view;
    }

    public PlayerController getPlayerController(String type, Player player) {
        switch (type) {
            case "human":
                return new HumanPlayerController(player, view);

            case "random":
                return new RandomPlayerController(player);

            case "ai":
                return new AIPlayerController(player);

            default:
                throw new IllegalArgumentException("No such type: " + type);
        }
    }
}
