package net.codedstingray.billinstale;

import net.codedstingray.billinstale.ui.console.ConsoleInputHandler;
import net.codedstingray.billinstale.ui.console.ConsoleMessageDisplay;

public class BillinsTale {

    public static void main(String[] args) {

//        ConsoleMessageDisplay msg = new ConsoleMessageDisplay();
//        msg.displayMessage(MessageType.COMMUNICATION, "Hi!");
//        msg.displayMessage(MessageType.WARNING, "Attention! Stuff is happening!");
//        msg.displayMessage(MessageType.ERROR, "AAAAHAAHHHH!!!");
//
//        msg.displayPlayerOptions("Go to the dark Forest", "Go to the high mountains", "Cry a river");
//
//        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
//        IPlayerInputHandler.PlayerInput input = inputHandler.getPlayerInput();
//        msg.displayMessage(MessageType.COMMUNICATION, "Player input was: " + input);

        System.out.println("Hello World");


        ConsoleMessageDisplay consoleMessageDisplay = new ConsoleMessageDisplay();
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();

        GameController gameController = GameController.create(consoleMessageDisplay, consoleMessageDisplay, consoleInputHandler);

        gameController.start();
    }
}
