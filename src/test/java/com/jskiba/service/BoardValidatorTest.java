package com.jskiba.service;

import com.jskiba.model.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardValidatorTest {
    @Test
    public void testBoardFullFalse() {
        Board board = new Board(3, 3);
        BoardValidator boardValidator = new BoardValidator();

        assertFalse(boardValidator.isBoardFull(board));
    }

    @Test
    public void testBoardFullFalseWithOneMarked() {
        Board board = new Board(3, 3);
        BoardValidator boardValidator = new BoardValidator();

        board.setField('x', 0);

        assertFalse(boardValidator.isBoardFull(board));
    }

    @Test
    public void testBoardFullFalseWithAlmostAllMarked() {
        Board board = new Board(3, 3);
        BoardValidator boardValidator = new BoardValidator();

        for(int i = 0; i < board.getBoardSize() - 1; i++) {
            board.setField('x', i);
        }

        assertFalse(boardValidator.isBoardFull(board));
    }

    @Test
    public void testBoardFullTrueWithAllMarked() {
        Board board = new Board(3, 3);
        BoardValidator boardValidator = new BoardValidator();

        for(int i = 0; i < board.getBoardSize(); i++) {
            board.setField('x', i);
        }

        assertTrue(boardValidator.isBoardFull(board));
    }
}