package com.jskiba.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testGetBoard() {
        Board board = new Board(3);
        List<Player> players = new ArrayList<>();

        Game game = new Game(board, players);

        assertEquals(board, game.getBoard());
    }

    @Test
    public void testGetPlayers() {
        Board board = new Board(3);
        List<Player> players = new ArrayList<>();

        players.add(new Player("Andrzej", 'x'));
        players.add(new Player("Janek", 'o'));
        players.add(new Player("Ziutek", 'i'));

        Game game = new Game(board, players);

        assertEquals(players, game.getPlayers());
    }

    @Test
    public void testGetPlayersAmount() {
        Board board = new Board(3);
        List<Player> players = new ArrayList<>();

        players.add(new Player("Andrzej", 'x'));
        players.add(new Player("Janek", 'o'));
        players.add(new Player("Ziutek", 'i'));

        Game game = new Game(board, players);

        assertEquals(players.size(), game.getPlayers().size());
    }
}