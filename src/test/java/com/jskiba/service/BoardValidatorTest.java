package com.jskiba.service;

import com.jskiba.model.Board;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardValidatorTest {
    @Test
    public void testBoardFullFalse() {
        Board board = new Board(3);
        BoardValidator boardValidator = new BoardValidator();

        assertFalse(boardValidator.isBoardFull(board));
    }

    @Test
    public void testBoardFullFalseWithOneMarked() {
        Board board = new Board(3);
        BoardValidator boardValidator = new BoardValidator();

        board.setField('x', 0);

        assertFalse(boardValidator.isBoardFull(board));
    }

    @Test
    public void testBoardFullFalseWithAlmostAllMarked() {
        Board board = new Board(3);
        BoardValidator boardValidator = new BoardValidator();

        for(int i = 0; i < board.getBoardSize() - 1; i++) {
            board.setField('x', i);
        }

        assertFalse(boardValidator.isBoardFull(board));
    }

    @Test
    public void testBoardFullTrueWithAllMarked() {
        Board board = new Board(3);
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
        Board board = new Board(3);
        for(int i = 0; i<9; i++) {
            char sign = (char) ('a'+i);
            board.setField(sign, i);
        }

        List<String> expectedLines = new ArrayList<>();
        expectedLines.add("abc");
        expectedLines.add("def");
        expectedLines.add("ghi");
        BoardValidator boardValidator = new BoardValidator();

        List<String> realLines = boardValidator.findHorizontalLines(board);

        assertEquals(expectedLines, realLines);
    }

    @Test
    public void testFindVerticalLines() {
        Board board = new Board(3);
        for(int i = 0; i<9; i++) {
            char sign = (char) ('a'+i);
            board.setField(sign, i);
        }

        List<String> expectedLines = new ArrayList<>();
        expectedLines.add("adg");
        expectedLines.add("beh");
        expectedLines.add("cfi");
        BoardValidator boardValidator = new BoardValidator();

        List<String> realLines = boardValidator.findVerticalLines(board);

        assertEquals(expectedLines, realLines);
    }

    @Test
    public void testFindDiagonalLines() {
        Board board = new Board(3);
        for(int i = 0; i<9; i++) {
            char sign = (char) ('a'+i);
            board.setField(sign, i);
        }

        List<String> expectedLines = new ArrayList<>();
        expectedLines.add("aei");
        expectedLines.add("ceg");
        BoardValidator boardValidator = new BoardValidator();

        List<String> realLines = boardValidator.findDiagonalLines(board);

        assertEquals(expectedLines, realLines);
    }

    @Test
    public void testBoardNotComplete() {
        Board board = new Board(3);
        BoardValidator boardValidator = new BoardValidator();
        assertFalse(boardValidator.isBoardComplete(board));
    }

    @Test
    public void testBoardAlmostComplete() {
        Board board = new Board(3);
        BoardValidator boardValidator = new BoardValidator();
        board.setField('x', 0);
        board.setField('x', 4);
        board.setField('o', 8);
        assertFalse(boardValidator.isBoardComplete(board));
    }

    @Test
    public void testBoardComplete() {
        Board board = new Board(3);
        BoardValidator boardValidator = new BoardValidator();
        board.setField('x', 0);
        board.setField('x', 4);
        board.setField('x', 8);
        assertTrue(boardValidator.isBoardComplete(board));
    }

    @Test
    public void testFindWinningSign() {
        Board board = new Board(3);
        BoardValidator boardValidator = new BoardValidator();
        board.setField('x', 0);
        board.setField('x', 4);
        board.setField('x', 8);
        assertEquals('x', boardValidator.findWinningSign(board));
    }


}