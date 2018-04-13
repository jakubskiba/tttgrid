package com.jskiba.controller;

import com.jskiba.model.Board;
import com.jskiba.model.Game;
import com.jskiba.model.Player;
import com.jskiba.service.BoardValidator;
import com.jskiba.view.View;

import java.util.*;
import java.util.stream.Collectors;

public class GameController {
    private View view;
    private Game game;
    private Board board;
    private Queue<PlayerController> playersQueue;
    private BoardValidator boardValidator;
    private PlayerControllerFactory playerControllerFactory;
    private String AIStrategy;
    private Set<Character> takenSigns;

    private final static int BOARD_MIN_SIZE = 3;
    private final static int BOARD_MAX_SIZE = 9;
    private final static int HUMAN_PLAYER_AMOUNT = 2;
    private final static int COMPUTER_PLAYER_AMOUNT = 1;

    public GameController(View view, BoardValidator boardValidator,
                          PlayerControllerFactory playerControllerFactory,
                          String AIStrategy) {
        this.view = view;
        this.boardValidator = boardValidator;
        this.playerControllerFactory = playerControllerFactory;
        this.AIStrategy = AIStrategy;
        this.takenSigns = new HashSet<>();
    }

    public void startController() {
        setupGame();
        while (!isGameOver()) {
            view.cleanScreen();
            view.print(this.game.getBoard());
            PlayerController currentPlayer = playersQueue.peek();
            int coordinates = currentPlayer.getCoordinatesOfMove(board);
            char sign = currentPlayer.getPlayer().getSign();
            boolean isMoveDone = this.board.setField(sign, coordinates);
            if(isMoveDone) {
                playersQueue.offer(playersQueue.poll());
            }
        }
        view.cleanScreen();
        view.print(this.game.getBoard());
        Player winner = determineWinner();
        if(winner == null) {
            view.print("Withdraw!");
        } else {
            view.print(winner.getName() + " wins!");
        }
    }

    private Player determineWinner() {
        char sign = this.boardValidator.findWinningSign(this.board);
        for(Player player : this.game.getPlayers()) {
            if(player.getSign().equals(sign)) {
                return player;
            }
        }
        return null;
    }

    private boolean isGameOver() {
        return this.boardValidator.isBoardComplete(this.game.getBoard()) ||
                this.boardValidator.isBoardFull(this.game.getBoard());
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
        this.board = board;
        List<PlayerController> playerControllers = createPlayerControllers();
        List<Player> players = playerControllers
                                    .stream()
                                    .map(PlayerController::getPlayer)
                                    .collect(Collectors.toList());

        this.game = new Game(board, players);

        Collections.shuffle(playerControllers);
        this.playersQueue = new LinkedList<>(playerControllers);

    }

    private Board createBoard() {

        int size = view.getNumberInRange("Provide size of board", BOARD_MIN_SIZE, BOARD_MAX_SIZE);

        return new Board(size, size);
    }

    private List<PlayerController> createPlayerControllers() {
        List<PlayerController> players = new ArrayList<>();

        for(int i = 0; i < HUMAN_PLAYER_AMOUNT; i++) {
            Player player = createPlayer(true);
            PlayerController playerController = new HumanPlayerController(player, view);
            players.add(playerController);
        }

        for(int i = 0; i < COMPUTER_PLAYER_AMOUNT; i++) {
            Player player = createPlayer(false);
            PlayerController playerController = playerControllerFactory.getPlayerController(AIStrategy, player);
            players.add(playerController);
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
        while (takenSigns.contains(sign)) {
            view.print("Sign taken!");
            sign = view.getChar("Provide another sign for player " + name + ":");
        }
        takenSigns.add(sign);

        return new Player(name, sign);
    }

    private void restartGame() {

    }
}
