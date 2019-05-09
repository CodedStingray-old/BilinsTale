package net.codedstingray.billinstale;

import net.codedstingray.billinstale.api.IMessageDisplay;
import net.codedstingray.billinstale.api.IPlayerInputHandler;
import net.codedstingray.billinstale.api.IPlayerOptionsDisplay;

public class GameController {

    public final IMessageDisplay messageDisplay;
    public final IPlayerOptionsDisplay playerOptionsDisplay;
    public final IPlayerInputHandler playerInputHandler;

    private GameController(IMessageDisplay messageDisplay
            , IPlayerOptionsDisplay playerOptionsDisplay
            , IPlayerInputHandler playerInputHandler) {

        this.messageDisplay = messageDisplay;
        this.playerOptionsDisplay = playerOptionsDisplay;
        this.playerInputHandler = playerInputHandler;
    }

    void start() {
        init();
        gameLoop();
    }

    private void init() {
        //TODO: GameController#init()
    }

    private void gameLoop() {
        //TODO: GameController#gameLoop()
    }

    //Singleton
    private static GameController instance;

    public static GameController create(IMessageDisplay messageDisplay
            , IPlayerOptionsDisplay playerOptionsDisplay
            , IPlayerInputHandler playerInputHandler) {

        if(instance != null)
            throw new IllegalStateException("Instance of GameController already exists");

        return instance = new GameController(messageDisplay, playerOptionsDisplay, playerInputHandler);
    }

    public static GameController get() {
        return instance;
    }
}
