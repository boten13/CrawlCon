package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Creating basic class for Goblin Monster for damaging and attacking player
 * Will give damage to player
 * Will provide EXP to player upon its death
 * Considering giving variants to weapon wielding damage and no weapon damage.
 * To begin we will start with no weapon damage, just hand combat.
 */

public class Goblin {
    private int goblinHealth;
    private int goblinExpGiven;
    private int goblinDamage;
    public String goblinName ="";
    Random rand = new Random();
    ArrayList<String> goblinNames = new ArrayList<>(
            List.of("Jerry", "Betsy", "Mitch", "Balthazar", "Gary", "Bob", "Ditsy", "Tom", "Michelle")
    );


    //Create Random Goblin stats and feed it to main for combat.
    public void createGoblinMob() {
        goblinExpGiven = setGoblinExpGiven();
        goblinHealth = setGoblinHealth();
        goblinName = setRandomGoblinName();
    }

    //Test code for displaying stored values
    public void displayGoblin() {
        System.out.println("Name: " + goblinName);
        System.out.println("Damage numbers: 1-6");
        System.out.println("Exp pool: " + goblinExpGiven);
        System.out.println("Health pool: " + goblinHealth);
    }

    //Create random amount of exp given for killing mob.
    private int setGoblinExpGiven() {
        goblinExpGiven = rand.nextInt(37, 55);
        return goblinExpGiven;
    }

    //Create random amount of HP for mob.
    private int setGoblinHealth(){
        goblinHealth = rand.nextInt(3, 8);
        return goblinHealth;
    }

    //Generate damage for Goblin Combat
    public int giveRandomGoblinDamage() {
        goblinDamage = rand.nextInt(1, 6);
        return goblinDamage;
    }

    //Generate random index for the name for Goblin in list
    private String setRandomGoblinName(){
        int listSize = goblinNames.size();
        int randomIndex = rand.nextInt(0, listSize);
        return goblinNames.get(randomIndex);
    }

    //Getter for Goblin Health
    public int getGoblinHealth() {
        return this.goblinHealth;
    }
    //Getter for gobExp
    public int getGoblinExpGiven() {
        return this.goblinExpGiven;
    }

    //Player damage to goblin health logic
    public int playerDamagesGoblin(int playerDamage) {
        goblinHealth -= playerDamage;
        return goblinHealth;
    }
    //Pull random name out of arraylist


}
