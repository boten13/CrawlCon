package Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Player Class to have all functions related to the player itself stored
 * Leveling will be stored here
 * Inventory might be stored here as well, don't quote me on that
 */
public class Player {
    private String name;
    private int playerExp;

    //Stores Player Registerd name provided at the beginning of the game;
    public Player(String playerName) {
        name = playerName;
    }

    //Checks the Player level based off exp points;
    private void levelCheck() {

    }

    //Checks exp needed to level;
    public int getExpRequiredForLeveling(int currentLevel) {
        ArrayList<Integer> requiredExp = new ArrayList<>(
                List.of(150, 275, 475, 1000)
        );

        int expNeeded = requiredExp.get(currentLevel - 2);
        return expNeeded;
    }
}
