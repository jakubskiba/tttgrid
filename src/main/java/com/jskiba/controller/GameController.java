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
    private List<String> playerControllersTypes;
    private Set<Character> takenSigns;
    private int boardMinSize;
    private int boardMaxSize;

    public GameController(View view, BoardValidator boardValidator,
                          PlayerControllerFactory playerControllerFactory,
                          List<String> playerControllersTypes, int boardMinSize, int boardMaxSize) {
        this.view = view;
        this.boardValidator = boardValidator;
        this.playerControllerFactory = playerControllerFactory;
        this.playerControllersTypes = playerControllersTypes;
        this.takenSigns = new HashSet<>();
        this.boardMinSize = boardMinSize;
        this.boardMaxSize = boardMaxSize;
    }

    public void startController() {
        boolean isProgramRunning = true;
        while (isProgramRunning) {
            setupGame();
            while (!isGameOver()) {
                proceedGame();
            }
            endGame();

            isProgramRunning = view.getYesNo("Play again?");
        }
    }

    private void setupGame() {
        if(game == null) {
            setupNewGame();
        } else {
            restartGame();
        }
    }

    private void proceedGame() {
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

    private void endGame() {
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

    private void setupNewGame() {
        this.board = createBoard();
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

        int size = view.getNumberInRange("Provide size of board", boardMinSize, boardMaxSize);

        return new Board(size, size);
    }

    private List<PlayerController> createPlayerControllers() {
        List<PlayerController> players = new ArrayList<>();

        for (String playerControllerType : this.playerControllersTypes) {
            Player player = createPlayer(playerControllerType.equals("human"));
            PlayerController playerController = playerControllerFactory.getPlayerController(playerControllerType, player);
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
        Character sign = getPlayerSign(name);

        return new Player(name, sign);
    }

    private Character getPlayerSign(String name) {
        Character sign = view.getChar("Provide sign for player " + name + ":");
        while (takenSigns.contains(sign)) {
            view.print("Sign taken!");
            sign = view.getChar("Provide another sign for player " + name + ":");
        }
        takenSigns.add(sign);

        return sign;
    }

    private void restartGame() {
        this.board = new Board(this.board.getHeight());
        List<PlayerController> playerControllers = new ArrayList<>(this.playersQueue);
        List<Player> players = new ArrayList<>(this.game.getPlayers());

        Collections.shuffle(players);
        this.playersQueue = new LinkedList<>(playerControllers);
        this.game = new Game(board, players);
    }
}
