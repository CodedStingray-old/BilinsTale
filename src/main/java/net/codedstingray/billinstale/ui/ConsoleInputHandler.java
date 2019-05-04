package net.codedstingray.billinstale.ui;

import net.codedstingray.billinstale.api.IPlayerInputHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputHandler implements IPlayerInputHandler {
    @Override
    public int getPlayerForkChoice() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int result = 0;
        boolean gotValidInput = false;
        while(!gotValidInput) {
            try {
                String input = reader.readLine();
                result = Integer.parseInt(input);
                gotValidInput = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
