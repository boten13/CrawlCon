package Rooms;

import EnemyTypes.Enemy;
import EnemyTypes.EnemyFactory;

import java.util.ArrayList;
import java.util.Random;

public class EnemyRoom extends Room {
    private ArrayList<Enemy> roomEncounter = new ArrayList<>();
    private boolean hasEnteredRoom;
    Random rand = new Random();
    public EnemyRoom(String enemyName) {
        super("\u2622", "Surprise mother fucker!", true);
        hasEnteredRoom = false;
    }
    public void enterRoom() {
        if (!hasEnteredRoom) {
            hasEnteredRoom = true;
            int x = rand.nextInt(1, 6);
            for (int i = 0; i < x; i++) {
                roomEncounter.add(EnemyFactory.createNewHobgoblin());
            }
        }
    }
    public ArrayList<Enemy> getEnemyList() {
        return roomEncounter;
    }
}
