package Util;
import Util.Goblin;
import java.util.*;
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
    private int playerDamage;
    Random rand = new Random();

// Stores Player Registerd name provided at the beginning of the game;
    public Player(String playerName) {
        name = playerName;
        playerLevel = 1;
        playerExp = 0;
        playersHealth = 50;
    }

    //Checks the Player level based off exp points;
    private void levelUp() {
        playerLevel++;
        System.out.println("You are now level " + playerLevel + "!");
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

    //Create random amout of damage user provides without weapon
    public int getRandomPlayerDamageWithoutWeapon() {
        playerDamage = rand.nextInt(1, 10);
        return playerDamage;
    }

    //Add exp from kills
    public int addExpGivenFromKill(int expGiven) {
        playerExp += expGiven;
        return playerExp;
    }

    //Damage to player from goblin logic
    public int goblinDoesDamage(int goblinDamage){
        playersHealth -= goblinDamage;
        return playersHealth;
    }

    //Pull up Character Stats
    public void checkCharacterSheet() {
        if (playerExp == 0) {
            System.out.println("You literally just got in here, go fight something you dullard.");
        } else {
            System.out.println("You have " + playersHealth + " health points.");
            System.out.println("You need " + neededExp + " experience points before leveling up.");
            System.out.println("You are currently " + playerLevel + ".");
            System.out.println("What did you expect, we are still developing the program.");
            System.out.println("Go fight something, you plebian.");
        }
    }
}
