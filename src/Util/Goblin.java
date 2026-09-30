package Util;

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
    Random rand = new Random();


    //Create Random Goblin stats and feed it to main for combat.
    public void createGoblinMob() {
        goblinDamage = setGoblinDamage();
        goblinExpGiven = setGoblinExpGiven();
        goblinHealth = setGoblinHealth();
    }

    //Test code for displaying stored values
    public void displayGoblin() {
        System.out.println("Will hit you with " + goblinDamage + " damage.");
        System.out.println("Will give you " + goblinExpGiven + " exp when you kill it.");
        System.out.println("Has " + goblinHealth + " health points.");
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
    private int setGoblinDamage() {
        goblinDamage = rand.nextInt(1, 6);
        return goblinDamage;
    }

}
