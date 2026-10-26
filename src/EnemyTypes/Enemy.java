package EnemyTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy {
    EnemyType type;
    String monsterName;
    String description;
    int damage;
    String loot;
    int enemyHealth;
    int enemyExpGiven;

    public Enemy() {

    }

//    public Enemy(EnemyType type, String name, String description, int damage, String loot, int health, int exp) {
//        if (type == EnemyType.HobGoblin) {
//            ArrayList<String> hobNames = new ArrayList<>(
//                    List.of("Jerry", "Betsy", "Mitch", "Balthazar", "Gary", "Bob", "Ditsy", "Tom", "Michelle")
//            );
//            Random rand = new Random();
//            this.damage = rand.nextInt(4, 10);
//            this.enemyHealth = rand.nextInt(10, 17);
//            this.enemyExpGiven = rand.nextInt(34, 49);
//            int listSize = hobNames.size();
//            int randomIndex = rand.nextInt(0, listSize);
//            this.monsterName = hobNames.get(randomIndex);
//        }
//        this.monsterName = name;
//        this.type = type;
//        this.description = description;
//        this.damage = damage;
//        this.enemyHealth = health;
//        this.enemyExpGiven = exp;
//    }

    public int getEnemyExpGiven() {
        return enemyExpGiven;
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public EnemyType getEnemyType() {
        return type;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public String getDescription() {
        return description;
    }

    public int getDamage() {
        return damage;
    }
}
