package net.codedstingray.billinstale.ui;

import net.codedstingray.billinstale.api.IPlayerInputHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputHandler implements IPlayerInputHandler {
    @Override
    public PlayerInput getPlayerInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PlayerInput result = null;
        boolean gotInput = false;
        //this entire loop structure is only here to handle IOExceptions; making sure we got valid input
        // is the problem of the caller
        while(!gotInput) {
            try {
                String input = reader.readLine();
                result = PlayerInput.fromString(input);
                gotInput = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
