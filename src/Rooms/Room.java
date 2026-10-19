package Rooms;

public abstract class Room {
    private String name;
    private String description;
    private boolean hasEnemy;
    private boolean isTraversable;

    public Room(String name, String description, boolean hasEnemy) {
        this.name = name;
        this.description = description;
        this.hasEnemy = hasEnemy;
        this.isTraversable = true;
    }

    public Room(String name, String description, boolean hasEnemy, boolean isTraversable) {
        this.name = name;
        this.description = description;
        this.hasEnemy = hasEnemy;
        this.isTraversable = isTraversable;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public boolean canYouGetIn() {
        return isTraversable;
    }
}
