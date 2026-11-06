package Rooms;

import EnemyTypes.Enemy;
import EnemyTypes.EnemyFactory;

import java.util.ArrayList;
import java.util.Random;

public class Hallway extends Room {
    private ArrayList<Enemy> ratsInTheRoom = new ArrayList<>();
    Random rand = new Random();
    int ratChance = 95;
    boolean hasRats;
    public Hallway() {
        super("   ", "Nothing to see here.", false);
    }
    public void chanceOfRats() {
        int chanceEncounter = rand.nextInt(0, 101);
        if (ratChance >= chanceEncounter) {
            hasRats = true;
            int ratCount = rand.nextInt(1, 4);
            for (int i = 0; i < ratCount; i++) {
                Enemy enemy = EnemyFactory.createRat();
                ratsInTheRoom.add(enemy);
            }
        }
    }
    public ArrayList<Enemy> getRatsInTheRoom() {
        return ratsInTheRoom;
    }
    public boolean doesItHaveRats() {
        return hasRats;
    }
    public void setHasRats(boolean doesIt) {
        hasRats = doesIt;
    }
}
//TODO: Have chance of gen rats to smosh with toesys