//package EnemyTypes;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//
//public class HobGoblin extends Enemy {
//    public HobGoblin(String description, String loot) {
//        ArrayList<String> hobNames = new ArrayList<>(
//                List.of("Jerry", "Betsy", "Mitch", "Balthazar", "Gary", "Bob", "Ditsy", "Tom", "Michelle")
//        );
//        Random rand = new Random();
//        int damage = rand.nextInt(4, 10);
//        int health = rand.nextInt(10, 17);
//        int exp = rand.nextInt(34, 49);
//        int listSize = hobNames.size();
//        int randomIndex = rand.nextInt(0, listSize);
//        String name = hobNames.get(randomIndex);
//        super(EnemyType.HobGoblin, name, description, damage, loot, health, exp);
//    }
//}
