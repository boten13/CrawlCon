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
        ArrayList<Integer> requiredExp = new ArrayList<>(
                List.of(150, 275, 475, 1000)
        );

        int nextLevel = requiredExp.get(playerLevel - 2);
       int expNeeded = playerExp - nextLevel;
       return expNeeded;
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
