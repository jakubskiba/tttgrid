package com.jskiba.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @RepeatedTest(2)
    public void testGetPlayerName() {
        String name = UUID.randomUUID().toString();

        Player player = new Player(name, 'X');

        assertEquals(name, player.getName());
    }

    @Test
    public void testGetPlayerSign() {
        Character sing = (char)(new Random().nextInt(26) + 'a');

        Player player = new Player("", sing);

        assertEquals(sing, player.getSign());
    }
}