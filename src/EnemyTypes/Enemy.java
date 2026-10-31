package EnemyTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy {
    EnemyType type;
    String monsterName;
    String description;
    int damageMin;
    int damageMax;
    String loot;
    int enemyHealth;
    int enemyExpGiven;
    Random rand = new Random();

    public Enemy() {

    }
    public void setEnemyName(String name) {
        monsterName = name;
    }

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
        int damage = rand.nextInt(damageMin, damageMax);
        return damage;
    }

    public int playerDamagesEnemy(int playerDamage) {
        enemyHealth -= playerDamage;
        return enemyHealth;
    }
}
