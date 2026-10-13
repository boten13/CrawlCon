package Rooms;

public abstract class Room {
    private String name;
    private String description;
    private boolean hasEnemy;

    public Room(String name, String description, boolean hasEnemy) {
        this.name = name;
        this.description = description;
        this.hasEnemy = hasEnemy;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
