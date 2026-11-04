package EnemyTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyFactory {
    private EnemyFactory() {
    }
    public static Enemy createNewHobgoblin() {
        Enemy enemy = new Enemy();
        enemy.damageMin = 2;
        enemy.damageMax = 8;
        Random rand = new Random();
        enemy.enemyHealth = rand.nextInt(7, 13);
        enemy.enemyExpGiven = rand.nextInt(34, 49);
        enemy.type = EnemyType.HobGoblin;

        return enemy;
    }
    public static Enemy createRat() {
        Enemy enemy = new Enemy();
        Random rand = new Random();
        enemy.enemyHealth = rand.nextInt(1, 7);
        enemy.enemyExpGiven = rand.nextInt(10, 18);
        enemy.type = EnemyType.Rat;

        return enemy;
    }
    public static Enemy createNewGoblin() {
        Enemy enemy = new Enemy();
        enemy.damageMin = 1;
        enemy.damageMax = 3;
        Random rand = new Random();
        enemy.enemyHealth = rand.nextInt(3, 9);
        enemy.enemyExpGiven = rand.nextInt(22, 37);
        enemy.type = EnemyType.Goblin;

        return enemy;
    }
}
