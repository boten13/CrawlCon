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
    private int playerExp;
    private int playerLevel;
    private int neededExp;
    private int playersHealth;

    //Stores Player Registerd name provided at the beginning of the game;
    public Player(String playerName) {
        name = playerName;
        playerLevel = 1;
        playerExp = 0;
        playersHealth = 50;
    }

    //Checks the Player level based off exp points;
    private void levelUp() {
        playerLevel++;
        System.out.println("You are now level" + playerLevel + "!");
    }

    //Checks exp needed to level;
    public void getExpRequiredForLeveling() {
        // Key = level, value = related experience to get to that level
        Map<Integer, Integer> levelMap = new HashMap<>();
        levelMap.put(1, 150);
        levelMap.put(2, 275);
        levelMap.put(3, 475);
        levelMap.put(4, 1000);

        int requiredExp = levelMap.get(playerLevel);
        //Maths for figuring out if need to level or not
        if (requiredExp > playerExp) {
            neededExp = (requiredExp - playerExp);
        } else {
            levelUp();
        }
    }

    //Quick code to add experience to the player when fighting
    //Code will be removed in future updates, only currently being implemented for testing purposes
    public void Fight() {
        int gobExp = 50;
        System.out.println("You have slain a goblin and gained " + gobExp + " points of experience!" );
        playerExp += gobExp;
        System.out.println("Your current expierence is at " + playerExp + ".");
    }

    public void checkCharacterSheet() {
        if (playerExp == 0) {
            System.out.println("You literally just got in here, go fight something you dullard.");
        } else {
            System.out.println("You have " + playersHealth + " health points.");
            System.out.println("You need " + neededExp + " before leveling up.");
            System.out.println("You are currently " + playerLevel + ".");
            System.out.println("What did you expect, we are still developing the program.");
            System.out.println("Go fight something, you plebian.");
        }
    }
}
