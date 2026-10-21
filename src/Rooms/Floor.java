package Rooms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Floor {
    private List<RoomWithCoordinates> rooms = new ArrayList<>();
    private int xMax;
    private int yMax;
    private Room[][] map;

    public Floor() {
       map = generateMap();
    }
    private Room[][] generateMap() {
        yMax = 5;
        xMax = 10;
        Room[][] map = new Room[yMax][xMax];
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

    public void addRoom(Room room, int xPos, int yPos) {
        boolean doesRoomPositionAlreadyExist = rooms.stream()
                .anyMatch((r) -> r.xPos == xPos && r.yPos == yPos);

        if (doesRoomPositionAlreadyExist) {
            return;
        }

        rooms.add(new RoomWithCoordinates(room, xPos, yPos));
        xMax = Math.max(xMax, xPos);
        yMax = Math.max(yMax, yPos);
    }

    public int getXMax() {
        return xMax;
    }

    public int getYMax() {
        return yMax;
    }

    public Room[][] getMap() {
        return map;
    }
    public Room getRoom(int y, int x) {
       return map[y][x];
    }
}
