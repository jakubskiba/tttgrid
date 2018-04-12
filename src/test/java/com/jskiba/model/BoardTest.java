package com.jskiba.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @Test
    void testSetFieldWithNegativeCoordinates() {
        Board board = new Board(3, 3);
        assertThrows(IllegalArgumentException.class, () -> board.setField('x', -1));
    }

    @Test
    void testSetFieldWithOutOfRangeCoordinates() {
        Board board = new Board(3, 3);
        assertThrows(IllegalArgumentException.class, () -> board.setField('x', 9));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void testSetField(int coordinate) {
        char sign = 'x';
        Board board = new Board(3, 3);

        board.setField(sign, coordinate);

        assertEquals(sign, board.getFields()[coordinate]);
    }

    @Test
    void testSetFieldDontMarkOtherField() {
        char sign = 'x';
        Board board = new Board(3, 3);

        int coordinate = 4;

        board.setField(sign, coordinate);

        assertEquals(board.EMPTY_FIELD_CHAR, board.getFields()[5]);
    }

    @Test
    void testSetFieldAlreadyTakenField() {
        char sign = 'x';
        char sign2 = 'o';
        Board board = new Board(3, 3);

        int coordinate = 4;

        board.setField(sign, coordinate);
        board.setField(sign2, coordinate);

        assertEquals(sign, board.getFields()[coordinate]);
    }
}