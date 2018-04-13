package com.jskiba;

import com.jskiba.controller.GameController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
