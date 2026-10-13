package Rooms;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Floor {
    private List<RoomWithCoordinates> rooms = new ArrayList<>();
    private int xMax, yMax;

    public Floor() {

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

    public Room getRoom(int x, int y) {
        Optional<RoomWithCoordinates> room = rooms.stream()
                .filter(r -> r.xPos == x && r.yPos == y)
                .findFirst();

        if (room.isEmpty()) {
            return null;
        }

        return room.get().room;
    }
}
