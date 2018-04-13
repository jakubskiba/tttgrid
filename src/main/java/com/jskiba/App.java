package com.jskiba;

import com.jskiba.controller.GameController;
import com.jskiba.controller.PlayerControllerFactory;
import com.jskiba.service.BoardValidator;
import com.jskiba.view.View;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        View view = new View(new Scanner(System.in));
        BoardValidator boardValidator = new BoardValidator();
        PlayerControllerFactory playerControllerFactory= new PlayerControllerFactory(view, boardValidator);

        List<String> controllerStrategies = Arrays.asList("human", "human", "ai-hard");
        int boardMinSize = 3;
        int boardMaxSize = 9;

        GameController gameController = new GameController(view, boardValidator, playerControllerFactory,
                controllerStrategies, boardMinSize, boardMaxSize);
        gameController.startController();
    }
}
