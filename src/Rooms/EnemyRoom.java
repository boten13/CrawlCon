package Rooms;

import EnemyTypes.Enemy;
import java.util.ArrayList;

public class EnemyRoom extends Room {
    public EnemyRoom(String enemyName) {
        super("\u2622", "Surprise mother fucker!", true);
    }
    private ArrayList<Enemy> roomEncounter = new ArrayList<>();
    private boolean hasEnteredRoom;
}
