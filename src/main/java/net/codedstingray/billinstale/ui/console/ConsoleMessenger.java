package net.codedstingray.billinstale.ui.console;

import net.codedstingray.billinstale.api.IMessenger;
import net.codedstingray.billinstale.api.IPlayerOptionsDisplay;

public class ConsoleMessenger implements IMessenger, IPlayerOptionsDisplay {
    @Override
    public void printMsg(MessageType type, String message) {
        type.defaultPrintStream.println(type.prefix + message);
    }

    @Override
    public void displayPlayerOptions(String... options) {
        StringBuilder optionsMsgBuilder = new StringBuilder();
        for(int i = 0; i < options.length; i++) {
            optionsMsgBuilder.append(i+1).append(": ").append(options[i]).append("\n");
        }

        System.out.print(optionsMsgBuilder); //toString implied in PrintStream#print
    }
}
