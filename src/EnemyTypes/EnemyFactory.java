package EnemyTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyFactory {
    private EnemyFactory() {
    }
    public static Enemy createNewHobgoblin() {
        Enemy enemy = new Enemy();

        ArrayList<String> hobNames = new ArrayList<>(
                List.of("Jerry", "Betsy", "Mitch", "Balthazar", "Gary", "Bob", "Ditsy", "Tom", "Michelle")
        );
        Random rand = new Random();
        enemy.damage = rand.nextInt(4, 10);
        enemy.enemyHealth = rand.nextInt(10, 17);
        enemy.enemyExpGiven = rand.nextInt(34, 49);
        int listSize = hobNames.size();
        int randomIndex = rand.nextInt(0, listSize);
        enemy.monsterName = hobNames.get(randomIndex);

        return enemy;
    }
}
