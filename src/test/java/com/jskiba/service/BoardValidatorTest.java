package com.jskiba.service;

import com.jskiba.model.Board;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testLineCompleteTrue() {
        String line = "xxx";
        BoardValidator boardValidator = new BoardValidator();

        assertTrue(boardValidator.isLineComplete(line));
    }

    @Test
    public void testLineCompleteFalse() {
        String line = "xx ";
        BoardValidator boardValidator = new BoardValidator();

        assertFalse(boardValidator.isLineComplete(line));
    }

    @Test
    public void testLineCompleteFalseDiffSigns() {
        String line = "xxo";
        BoardValidator boardValidator = new BoardValidator();

        assertFalse(boardValidator.isLineComplete(line));
    }

    @Test
    public void testFindHorizontalLines() {
        Board board = new Board(3, 4);
        for(int i = 0; i<12; i++) {
            char sign = (char) ('a'+i);
            board.setField(sign, i);
        }

        List<String> expectedLines = new ArrayList<>();
        expectedLines.add("abcd");
        expectedLines.add("efgh");
        expectedLines.add("ijkl");
        BoardValidator boardValidator = new BoardValidator();

        List<String> realLines = boardValidator.findAllLines(board);

        assertEquals(expectedLines, realLines);
    }


}