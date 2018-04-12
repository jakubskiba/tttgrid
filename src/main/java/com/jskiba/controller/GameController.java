package com.jskiba.controller;

import com.jskiba.model.Board;
import com.jskiba.model.Game;
import com.jskiba.model.Player;
import com.jskiba.service.BoardValidator;
import com.jskiba.view.View;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private View view;
    private Game game;
    private BoardValidator boardValidator;

    private final static int BOARD_MIN_SIZE = 3;
    private final static int BOARD_MAX_SIZE = 9;
    private final static int PLAYER_AMOUNT = 3;

    public GameController(View view, BoardValidator boardValidator) {
        this.view = view;
        this.boardValidator = boardValidator;
    }

    public void startController() {
        setupGame();
    }

    private void setupGame() {
        if(game == null) {
            setupNewGame();
        } else {
            restartGame();
        }
    }

    private void setupNewGame() {
        Board board = createBoard();
        List<Player> players = createPlayers();

        this.game = new Game(board, players);

    }

    private Board createBoard() {

        int size = view.getNumberInRange("Provide size of board", BOARD_MIN_SIZE, BOARD_MAX_SIZE);

        return new Board(size, size);
        }

        return new Board(width, height);
    }

    private int getLimitedSize(String propmt) {
        int size = view.getNumber(propmt);
        while (size < BOARD_MIN_SIZE || size > BOARD_MAX_SIZE) {
            size = view.getNumber(propmt);
        }

        return size;
    }

    private List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();

        for(int i = 0; i < PLAYER_AMOUNT; i++) {
            players.add(createPlayer());
        }

        return players;
    }

    private Player createPlayer(boolean isHuman) {
        if(isHuman) {
            view.print("Creating human player");
        } else {
            view.print("Creating computer player");
        }
        String name = view.getText("Provide player name: ");
        Character sign = view.getChar("Provide sign for player " + name + ":");
        return new Player(name, sign);
    }

    private void restartGame() {

    }
}
