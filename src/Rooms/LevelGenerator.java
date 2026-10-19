package Rooms;

import java.util.Arrays;
import java.util.HashMap;

public class LevelGenerator {
    private LevelGenerator() {
        // Empty constructor to force Static usage of methods
    }

    public static Room[][] generateLevel() {
        Room[][] map = new Room[5][10];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], new EmptyRoom());
        }
        map[0][0] = new SafehouseRoom();
        map[1][0] = new Wall();
        map[2][0] = new Wall();
        map[3][0] = new Wall();
        map[4][0] = new Wall();
        map[0][2] = new Wall();
        map[1][2] = new Wall();
        map[3][2] = new Wall();
        map[4][2] = new Wall();
        map[2][2] = new Wall();
        map[2][5] = new EnemyRoom("Goblin");
        map[3][9] = new EnemyRoom("Goblin");
        map[3][8] = new EnemyRoom("Hobgoblin");
        map[4][8] = new EnemyRoom("Goblin");
        map[4][9] = new EnemyRoom("Boss");

        return map;
    }

    /**
     * Creates a HashMap of Rooms. The key is a comma-separated coordinate for x and y.
     */
    public static HashMap<String, Room> generateLevelAsStringMap() {
        HashMap<String, Room> map = new HashMap<>();
        map.put("0,0", new SafehouseRoom());
        map.put("2,5", new EnemyRoom("Goblin"));
        map.put("3,9", new EnemyRoom("Goblin"));
        map.put("3,8", new EnemyRoom("Hobgoblin"));
        map.put("4,8", new EnemyRoom("Goblin"));
        map.put("4,9", new EnemyRoom("Boss"));

        return map;
    }

    public static Floor generateFloor() {
        Floor floor = new Floor();
        floor.addRoom(new SafehouseRoom(), 0, 0);
        floor.addRoom(new EnemyRoom("Goblin"), 2, 5);
        floor.addRoom(new EnemyRoom("Goblin"), 3, 9);
        floor.addRoom(new EnemyRoom("Hobgoblin"), 3, 8);
        floor.addRoom(new EnemyRoom("Goblin"), 4, 8);
        floor.addRoom(new EnemyRoom("Boss"), 4, 9);

        return floor;
    }
}
