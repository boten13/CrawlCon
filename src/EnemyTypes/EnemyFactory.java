package EnemyTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyFactory {
    private EnemyFactory() {
    }
    public static Enemy createNewHobgoblin() {
        Enemy enemy = new Enemy();
        enemy.damageMin = 4;
        enemy.damageMax = 10;
        Random rand = new Random();
        enemy.enemyHealth = rand.nextInt(10, 17);
        enemy.enemyExpGiven = rand.nextInt(34, 49);

        return enemy;
    }
}
