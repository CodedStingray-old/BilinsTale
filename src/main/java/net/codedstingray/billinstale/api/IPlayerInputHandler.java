package net.codedstingray.billinstale.api;

public interface IPlayerInputHandler {

    PlayerInput getPlayerInput();

    enum PlayerInput {
        ONE("1", 0),
        TWO("2", 1),
        THREE("3", 2),
        FOUR("4", 3),
        FIVE("5", 4),
        SIX("6", 5),

        EXIT("exit", -1);

        public static PlayerInput fromString(String input) {
            input = input.toLowerCase();
            for(PlayerInput inputIterate: PlayerInput.values()) {
                if(input.equals(inputIterate.stringValue))
                    return inputIterate;
            }
            return null;
        }

        public final String stringValue;
        public final int actionCode;

        PlayerInput(String stringValue, int actionCode) {
            this.stringValue = stringValue;
            this.actionCode = actionCode;
        }
    }
}
