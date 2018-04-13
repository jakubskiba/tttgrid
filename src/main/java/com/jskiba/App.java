package com.jskiba;

import com.jskiba.controller.GameController;
import com.jskiba.controller.PlayerControllerFactory;
import com.jskiba.service.BoardValidator;
import com.jskiba.view.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Configuration
@ComponentScan
public class App 
{
    public static void main( String[] args )
    {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        GameController gameController = (GameController) ctx.getBean("gameController");
        gameController.startController();
    }
}
