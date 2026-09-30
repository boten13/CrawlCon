package Util;

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


    //Create Random Goblin stats and feed it to main for combat.
    public void createGoblinMob() {
        goblinDamage = 1;
        goblinExpGiven = 5;
        goblinHealth = 5;
    }

}
