package com.jskiba.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void testBoardConstructor() {
        Board board = new Board(3, 3);

        char[] expectedFields = {' ', ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '};

        assertArrayEquals(expectedFields, board.getFields());
    }
}