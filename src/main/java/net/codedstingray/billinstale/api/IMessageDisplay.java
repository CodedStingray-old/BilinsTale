package net.codedstingray.billinstale.api;

import java.io.PrintStream;

public interface IMessageDisplay {

    void displayMessage(MessageType type, String message);

    enum MessageType {
        COMMUNICATION(System.out, ""),
        WARNING(System.out, "[Warning] "),
        ERROR(System.err, "[Error] ");

        public final PrintStream defaultPrintStream;
        public final String prefix;

        MessageType(PrintStream defaultPrintStream, String prefix) {
            this.defaultPrintStream = defaultPrintStream;
            this.prefix = prefix;
        }
    }
}
