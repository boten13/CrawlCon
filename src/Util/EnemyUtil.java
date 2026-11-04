package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyUtil {
    Random rand = new Random();
    public static ArrayList<String> hobNames = new ArrayList<>(
            List.of("Snuggle-Puff", "Betsy", "Noodle-Fuzz", "Balthazar", "Gobbleguts", "Bob", "Grinlock", "Tom",
                    "Sludgemack", "Kressak", "Shifty Guts", "Toodles", "Ragga", "Piggy")
    );
    public static ArrayList<String> gobNames = new ArrayList<>(
            List.of("Borkle", "Marrow", "Sparkmack", "Svish", "Mogglewog", "Bendigo", "Snik", "Nipmonger",
                    "Spip", "Bonegrundle", "Riggity", "Fishspleen", "Piss Jar", "Rattrap", "Zaza", "Grittgrash",
                    "Bwob", "Eyemusher", "Spork", "Barfknees", "McFingers", "Spunk", "Smee", "Sexpants", "Muckmuck",
                    "Crooknose", "Snotmuncher", "Gooplicker", "Slugsnatcher", "Froggrope")
    );
public String getHobName(int size) {
    int listSize = hobNames.size();
    int randomIndex = rand.nextInt(0, size);
    return hobNames.get(randomIndex);
    }
}