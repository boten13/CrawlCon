package Rooms;

import EnemyTypes.Enemy;
import EnemyTypes.EnemyFactory;
import Util.EnemyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//TODO: Change room icon to something different if room has been cleared
public class EnemyRoom extends Room {
    private ArrayList<Enemy> roomEncounter = new ArrayList<>();
    private boolean hasEnteredRoom;
    Random rand = new Random();
    public EnemyRoom(String enemyName) {
        super("\u2622", "Surprise mother fucker!", true);
        hasEnteredRoom = false;
    }
    public void enterRoom() {
        if (hasEnteredRoom) {
            return;
        }
        hasEnteredRoom = true;
        //Generates amount of goblins per room
        int gobCount = rand.nextInt(1, 5);
        //Generates amount of Hobgoblins per room
        int hobCount = rand.nextInt(0, 3);
        ArrayList<String> gobNames = EnemyUtil.gobNames;
        ArrayList<String> hobNames = EnemyUtil.hobNames;
        //Gives non-repeating names for Hobs and adds to room
        for (int i = 0; i < hobCount; i++) {
            int y = rand.nextInt(0, hobNames.size());
            String name = hobNames.get(y);
            Enemy enemy = EnemyFactory.createNewHobgoblin();
            enemy.setEnemyName(name);
            roomEncounter.add(enemy);
            hobNames.remove(name);
        }
        //Gives non-repeating names to Gobs and adds to room
        for (int i = 0; i < gobCount; i++) {
            int y = rand.nextInt(0, gobNames.size());
            String name = gobNames.get(y);
            Enemy enemy = EnemyFactory.createNewGoblin();
            enemy.setEnemyName(name);
            roomEncounter.add(enemy);
            gobNames.remove(name);
        }

    }
    public ArrayList<Enemy> getEnemyList() {
        return roomEncounter;
    }
    public boolean hasBeenHere() {
        return hasEnteredRoom;
    }
}