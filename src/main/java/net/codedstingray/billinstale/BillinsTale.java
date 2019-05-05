package net.codedstingray.billinstale;

import net.codedstingray.billinstale.api.IMessenger.MessageType;
import net.codedstingray.billinstale.api.IPlayerInputHandler;
import net.codedstingray.billinstale.ui.ConsoleInputHandler;
import net.codedstingray.billinstale.ui.ConsoleMessenger;

public class BillinsTale {

    public static void main(String[] args) {

        ConsoleMessenger msg = new ConsoleMessenger();
        msg.printMsg(MessageType.COMMUNICATION, "Hi!");
        msg.printMsg(MessageType.WARNING, "Attention! Stuff is happening!");
        msg.printMsg(MessageType.ERROR, "AAAAHAAHHHH!!!");

        msg.displayPlayerOptions("Go to the dark Forest", "Go to the high mountains", "Cry a river");

        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        IPlayerInputHandler.PlayerInput input = inputHandler.getPlayerInput();
        msg.printMsg(MessageType.COMMUNICATION, "Player input was: " + input);
    }
}
