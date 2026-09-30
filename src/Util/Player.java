package Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Player Class to have all functions related to the player itself stored
 * Leveling will be stored here
 * Inventory might be stored here as well, don't quote me on that
 */
public class Player {
    private String name;
    private int playerExp = 0;
    private int playerLevel = 1;

    //Stores Player Registerd name provided at the beginning of the game;
    public Player(String playerName) {
        name = playerName;
    }

    //Checks the Player level based off exp points;
    private void levelCheck() {
        System.out.println("You are at level " + playerLevel + "!");
    }

    //Checks exp needed to level;
    public int getExpRequiredForLeveling(int exp, int level) {
        // Key = level, value = related experience to get to that level
        Map<Integer, Integer> levelMap = new HashMap<>();
        levelMap.put(2, 150);
        levelMap.put(3, 275);
        levelMap.put(4, 475);
        levelMap.put(5, 1000);

        int requiredExp = levelMap.get(level);
        return requiredExp;
//        int nextLevel = requiredExp.get(playerLevel - 2);
//       int expNeeded = playerExp - nextLevel;
//       return expNeeded;
    }

    //Quick code to add experience to the player when fighting
    //Code will be removed in future updates, only currently being implemented for testing purposes
    public void Fight() {
        int gobExp = 50;
        System.out.println("You have slain a goblin and gained " + gobExp + " points of experience!" );
        playerExp += gobExp;
        System.out.println("Your current expierence is at " + playerExp + ".");
    }
}
