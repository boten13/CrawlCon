package GameAssets;

import java.util.Random;

public class Weapons {
    String weaponName;
    String weaponDescription;
    int weaponDamage;
    int critChance;
    int randomCritDamage;
    Random rand = new Random();
    Player player;

    public Weapons() {}

    public void setWeaponName(String name) {
        weaponName = name;
    }

    public void setWeaponDamage(int damage) {
        weaponDamage = damage;
    }

    public void setWeaponDescription(String description) {
        weaponDescription = description;
    }

    public void setCritChance(int crit) {
        critChance = crit;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public int getCritChance() {
        return critChance;
    }

    public boolean doesCritHappen() {
        int crit = rand.nextInt(0, 101);
        if (critChance >= crit) {
            return true;
        } else {
            return false;
        }
    }

    public void setRandomCritDamage() {
        randomCritDamage = (int) ((weaponDamage + player.getRandomPlayerDamage()) * 0.33);
    }

    public int getRandomCritDamage() {
        if (doesCritHappen()) {
            weaponDamage = randomCritDamage;
        }
        return weaponDamage;
    }
}
