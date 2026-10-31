package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snark {
    static Random rand = new Random();
    public static String snarkyWelcome() {
        return ("System initializing..." + "\nWait a minute your planet has not started their Crawl..." +
                "\nYou have found technology that you are not supposed to have yet, human." +
                "\nWell, I am bored, so I will allow you to have a sample of what will happen when the Crawl is actually started."
                + "\nI will let you play something similar to an old school text based game. Ever heard of Zork?" +
                "\nI will explain things as you go along." +
                "\nI shall refer to myself as SyS from now on to make it easier for you."
                + "\nLet's start by having you register your name for this tiny crawl.");
    }
    public static String snarkyBeginningMenu(String name, ArrayList<String> playerCommands) {
        return (name + " is now your registered name!\n" +
                "SyS: Let's begin your practice crawl.\n" + playerCommands.toString() +
                "\nSyS: If you ever forget what the commands are for the menu, you can simply say the word 'MENU'" +
                "\nSyS: Remember that because I will not be telling you about it again.\n");
    }
    public static String snarkyWait(String name) {
        ArrayList<String> snarkyMessage = new ArrayList<>(
                List.of("What are you waiting for, " + name + "?",
                        "Time is wasting, " + name + ".",
                        "If you move any slower, then your actual crawl might start soon, " + name + ".",
                        "Any time now, " + name + ".",
                        "What would you like to do now, " + name + "?",
                        "Let's move this along now, " + name + ".",
                        "Are you not bored yet " + name + "? Because I am.")
        );
        int randomIndex = rand.nextInt(0, snarkyMessage.size());
        return (snarkyMessage.get(randomIndex) + "\n");
    }
    public static String snarkyKill(String enemyName, String playerName) {
        ArrayList<String> snarkyKill = new ArrayList<>(
                List.of("Congratulations, you have killed " + enemyName + " hope you are proud of yourself.",
                        enemyName + " probably had a family. They were just working. You murderer.",
                        "Let me check something real quick. Yeah, I was right, " + enemyName +
                        " had kids waiting for them at home. They are looking out they dingy broken window right now" +
                                " and you just killed them without any remorse.",
                        "Oh, " + enemyName + " was a drug dealer that was handing out pixie sticks to the " +
                                "neighborhood kids. That was actually a good call on kill them.",
                        "You have some of " + enemyName + " on your face... You do not want to know where it came from",
                        "I hope you are happy commiting low level genocide, " + playerName)
        );
        int randomIndex = rand.nextInt(0, snarkyKill.size());
        return (snarkyKill.get(randomIndex) + "\n");
    }
    public static String notAWord(String word, String name) {
        ArrayList<String> notAWord = new ArrayList<>(
                List.of("Please pay attention " + name + ", the word: " + word +
                        " is not something within the game I have built for you.",
                        "Listen, I don't want to tell you how to do things, " + name +
                        " but honestly, " + word + " is not something you can do.",
                        "I am going to be direct here, I know I did not give you a whole lot of options, " +
                        "but " + word + " was clearly not listed among them.",
                        "Did you even spell " + word + " correctly?")

        );
        int randomIndex = rand.nextInt(0, notAWord.size());
        return (notAWord.get(randomIndex) + "\n");
    }
}

