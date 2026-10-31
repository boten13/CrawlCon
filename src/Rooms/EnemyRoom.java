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
        if (!hasEnteredRoom) {
            hasEnteredRoom = true;
            int x = rand.nextInt(1, 6);
            ArrayList<String> names = EnemyUtil.hobNames;
            for (int i = 0; i < x; i++) {
                int y = rand.nextInt(0, names.size());
                String name = names.get(y);
                Enemy enemy = EnemyFactory.createNewHobgoblin();
                enemy.setEnemyName(name);
                roomEncounter.add(enemy);
                names.remove(name);
            }
        }
    }
    public ArrayList<Enemy> getEnemyList() {
        return roomEncounter;
    }
}