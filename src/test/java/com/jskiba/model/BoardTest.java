package com.jskiba.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    @Test
    public void testBoardConstructor() {
        Board board = new Board(3, 3);

        char[] expectedFields = {' ', ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '};

        assertArrayEquals(expectedFields, board.getFields());
    }

    @Test
    public void testBoardGetHeight() {
        int height = 3;

        Board board = new Board(height, 4);

        assertEquals(height, board.getHeight());
    }

    @Test
    public void testBoardGetWidth() {
        int width = 3;

        Board board = new Board(4, width);

        assertEquals(width, board.getWidth());
    }

    @Test
    public void testBoardSize() {
        int boardSize = 12;

        Board board = new Board(3, 4);

        assertEquals(boardSize, board.getBoardSize());
    }
}