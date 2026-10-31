package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyUtil {
    Random rand = new Random();
    public static ArrayList<String> hobNames = new ArrayList<>(
            List.of("Jerry", "Betsy", "Mitch", "Balthazar", "Gary", "Bob", "Ditsy", "Tom", "Michelle")
    );
    public static ArrayList<String> gobNames = new ArrayList<>(
            List.of("Jerry", "Betsy", "Mitch", "Balthazar", "Gary", "Bob", "Ditsy", "Tom", "Michelle")
    );
public String getHobName(int size) {
    int listSize = hobNames.size();
    int randomIndex = rand.nextInt(0, size);
    return hobNames.get(randomIndex);
    }
}