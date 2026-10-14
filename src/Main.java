//All the imported packages
import Rooms.Floor;
import Util.GameState;
import Rooms.LevelGenerator;
import Rooms.Room;
import EnemyTypes.Goblin;
import GameAssets.*;
import Util.GUI;
// TODO: Star imports are bad
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
//Regular Menu Items
private final String REG_MENU = "MENU";
private final String LOOK_AROUND = "LOOK";
private final String DIRECTION_NORTH = "NORTH";
private final String DIRECTION_SOUTH = "SOUTH";
private final String DIRECTION_EAST = "EAST";
private final String DIRECTION_WEST = "WEST";
private final String MAP_CHECK = "MAP";
private final String FIGHT = "FIGHT";
private final String INVENTORY = "INVENTORY";
private final String CHARACTER_SHEET = "CHARACTER SHEET";
private final String END_GAME = "END GAME";
//Combat menu items
private final String COMBAT_MENU = "MENU";
private final String ATTACK_CREATURE = "ATTACK";
private final String RUN = "RUN";
private final String USE_ITEM = "USE ITEM";
private final String TALK = "TALK";
private final String CHEAT = "CHEAT";

//Generate goblin Object
Goblin goblin = new Goblin();

//Intiates and stores name given for player
Player player;

//Stores user Input from TextField
String userCommand = "";

//Used for the switch:case
private GameState gameState = GameState.WELCOME;

GUI gameDisplayWindow;

private Room[][] currentLevel;
private HashMap<String, Room> currentLevelMap;
private Floor floor;

//private final String CHECK_TIME = "CLOCK";

//List of commands the player is prompted to use.
private ArrayList<String> playerCommands = new ArrayList<>(
        List.of(DIRECTION_EAST, DIRECTION_NORTH, DIRECTION_SOUTH, DIRECTION_WEST, LOOK_AROUND, MAP_CHECK, FIGHT,
                INVENTORY, CHARACTER_SHEET, END_GAME)
);
//Actual Menu items with hidden functions
private ArrayList<String> actualMenu = new ArrayList<>(
        List.of(DIRECTION_EAST, DIRECTION_NORTH, DIRECTION_SOUTH, DIRECTION_WEST, LOOK_AROUND, MAP_CHECK, FIGHT,
                INVENTORY, CHARACTER_SHEET, END_GAME, REG_MENU)
);

//List of commands the player is prompted to use while fighting.
private ArrayList<String> combatCommands = new ArrayList<>(
        List.of(ATTACK_CREATURE, RUN, USE_ITEM, TALK)
);
//List of actual Combat commands can be used
private ArrayList<String> actualCombatOptions = new ArrayList<>(
        List.of(ATTACK_CREATURE, RUN, USE_ITEM, TALK, CHEAT)
);

// TODO: Cleanup project structure--Everything is currently in Util dir
void main() {
    // Initiates map
    currentLevel = LevelGenerator.generateLevel();
    currentLevelMap = LevelGenerator.generateLevelAsStringMap();
    floor = LevelGenerator.generateFloor();

    //Initiates EDT for SWING
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
        //Initiate GUI display
        gameDisplayWindow = new GUI();
        //Creates listener for user input
        gameDisplayWindow.getInputUserCommand().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userCommand = gameDisplayWindow.getInputUserCommand().getText();
                if (!userCommand.isEmpty()) {
                    processInput(userCommand);
                    gameDisplayWindow.getInputUserCommand().setText("");
                }
            }
        });
            gameDisplayWindow.getInputUserCommand().grabFocus();
        //Start game by prompting for name
        cText("SyS: Welcome Crawler to Terran's Dungeon Crawl!");
        cText("SyS: Please enter your registered crawler name for your time inside the crawl!");
        }
    });
}
//Process User Input
private void processInput(String userCommand){
    //Display what user prompted
    cText("\n>" + userCommand + "\n");
    //Game state: Bounces between what is accessible at any given moment
    switch (gameState) {
        case GameState.WELCOME:
            //Stores user input as name for the player
            player = new Player(userCommand);
            //Moves case to 1
            gameState = GameState.REG_MENU;
            cText("SyS: " + player.getPlayerName() + " is now your registered name!\n" +
                    "Sys: Let's begin your practice crawl.\n" + playerCommands.toString() +
                    "\nSyS: If you ever forget what the commands are for the menu, you can simply say the word 'MENU'" +
                    "\nSyS: Remember that because I will not be telling you about it again.\n");
            break;
        case GameState.REG_MENU: //Reg options case
            //Cheat Word Menu to display the menu again
            if (REG_MENU.equalsIgnoreCase(userCommand)) {
                cText(playerCommands.toString());
                gameState = GameState.REG_MENU;
            }
            //Validates user input against all options available
            if (!actualMenu.contains(userCommand.toUpperCase())) {
                cText("SyS: Please pay attention, crawler! I do not have all day to repeat myself for you.");
                cText(userCommand + " is not a valid option. Try again.");
                gameState = GameState.REG_MENU;
            }
            //regular menu items displayed
            if (DIRECTION_EAST.equalsIgnoreCase(userCommand)) {
                headEast();
            } else if (DIRECTION_NORTH.equalsIgnoreCase(userCommand)) {
                headNorth();
            } else if (DIRECTION_SOUTH.equalsIgnoreCase(userCommand)) {
                headSouth();
            } else if (DIRECTION_WEST.equalsIgnoreCase(userCommand)) {
                headWest();
            } else if (MAP_CHECK.equalsIgnoreCase(userCommand)) {
                gameDisplayWindow.toggle();
                checkMap();
            } else if (FIGHT.equalsIgnoreCase(userCommand)) {
                startCombat();
            } else if (INVENTORY.equalsIgnoreCase(userCommand)) {
                checkInventory();
            } else if (CHARACTER_SHEET.equalsIgnoreCase(userCommand)) {
                characterSheet();
            } else if (LOOK_AROUND.equalsIgnoreCase(userCommand)) {
                lookAround();
            } else if (END_GAME.equalsIgnoreCase(userCommand)) {
                endGame();
            } else {
                cText("Fix the code related to this word: " + userCommand);
            }
            cText("SyS: What would you like to do next, " + player.getPlayerName() + "?");
            break;
        case GameState.COMBAT://Combat start case
            //Hidden menu items in combat menu
            if (COMBAT_MENU.equalsIgnoreCase(userCommand)){
                cText(combatCommands.toString());
                gameState = GameState.COMBAT;
            }
            if (CHEAT.equalsIgnoreCase(userCommand)) {
                cheatMode();
                gameState = GameState.COMBAT;
            }
            if (END_GAME.equalsIgnoreCase(userCommand)) {
                endGame();
            }
            //if the input does not exist
            if (!actualCombatOptions.contains(userCommand.toUpperCase())) {
                cText("Please pay attention, crawler! I do not have all day to repeat myself for you.");
                cText(userCommand + " is not a valid option. Try again.");
            }
            //menu items for combat that is displayed
            if (ATTACK_CREATURE.equalsIgnoreCase(userCommand)) {
                attackCreature();
            } else if (RUN.equalsIgnoreCase(userCommand)) {
                runAway();
            } else if (USE_ITEM.equalsIgnoreCase(userCommand)) {
                useAnItemFromInventory();
            } else if (TALK.equalsIgnoreCase(userCommand)) {
                talkWithCreature();
            } else {
                cText("Fix the code related to this word: " + userCommand);
            }
            break;
    }
}

//Converts Strings to output on GameDisplay -- ShortHanded for ConvertText
private void cText(String text) {
    gameDisplayWindow.getOutputConsoleResponse().append("\n" + text);
    gameDisplayWindow.getOutputConsoleResponse().setCaretPosition(gameDisplayWindow.getOutputConsoleResponse().getDocument().getLength());
}

//Function to describe what player can see.
private void lookAround() {
    cText("You can see nothing right now, the developers haven't programmed the space in which we exist. " +
            "I say we, because I am referring to myself as well as you. " +
            "Currently I am just words appearing in front of you coming from the dark abyss. " +
            "Well, I suppose even if they had created a space in which you could see, I would still just " +
            "be a words on a page that you are reading and nothing more. " +
            "Alright, enough of that dark rabbit hole. Point is, there is nothing to see right now.");
}

//Move Player East
private void headEast() {
    cText("You go nowhere. Currently you live in a 1 by 1 square block that does nothing.");
}

//Move Player West
private void headWest() {
    cText("You go nowhere. Currently you live in a 1 by 1 square block that does nothing.");
}

//Move Player North
private void headNorth() {
    cText("SyS: You go nowhere. Currently you live in a 1 by 1 square block that does nothing.");
}

//Move Player South
private void headSouth() {
    cText("You go nowhere. Currently you live in a 1 by 1 square block that does nothing.");
}

//Initiate combat
private void startCombat() {
    goblin.createGoblinMob();
    cText("You have initiated combat with " + goblin.goblinName+ ".");
    cText("Here are your combat options: ");
    cText(combatCommands.toString());
    gameState = GameState.COMBAT;
}

//Attack Creature
private void attackCreature() {
    fightTheMob();
    player.getExpRequiredForLeveling();
}

//Run From Creature. (Small chance of succeeding, failure will result in damage to player.)
private void runAway() {
    int deservedDmg = goblin.giveRandomGoblinDamage();
    cText("You weak coward. It is a pathetic mob. " + goblin.goblinName + " stabbed you in the back for "
            + deservedDmg + " points of damage.");
    cText("Which you deserved. Now fight " + goblin.goblinName + " like a true player!");
    player.goblinDoesDamage(deservedDmg);
    isPlayerDead();
}
//Use an item in your inventory. Pull up inventory list.
private void useAnItemFromInventory() {
    cText("You are sitting there rummaging around your pockets as if you have anything in them.");
    cText("You don't. You just look stupid.");
}
//Talk with Creature (Will have rare chance of having discussion with creature.)
private void talkWithCreature() {
    int deservedDmg = goblin.giveRandomGoblinDamage();
    cText(goblin.goblinName + " looks at you like you are stupid and attacks you for " +
            deservedDmg + " damage.");
    cText("Seriously, it is an unintelligent mob. And you are scary looking, what do you expect?");
    player.goblinDoesDamage(deservedDmg);
    isPlayerDead();
}
//Check Map Location
private void checkMap() {
    gameDisplayWindow.getMapDisplay().append("Sys: Wow... You're lost already? \n" +
            "That truly takes talent to be that bad at this game. \n" +
            "Take a quick look and get your bearings before something eats you.\n" +
            "or whatever it is you flesh bags do.\n");

    for (int i = 0; i < currentLevel.length; i++) {
        String rowOfRoomsToOutput = "";
        for (int j = 0; j < currentLevel[i].length; j++) {
            String roomName = currentLevel[i][j].getName();
            int spacesNeeded = 9 - roomName.length();
            rowOfRoomsToOutput = rowOfRoomsToOutput.concat( currentLevel[i][j].getName() + " ".repeat(Math.max(spacesNeeded, 0)));
        }
        gameDisplayWindow.getMapDisplay().append("\n" + rowOfRoomsToOutput);
//        cText(rowOfRoomsToOutput);
    }

//    cText("");
//    cText("Map generation approach 2:");
//    for (int x = 0; x <= floor.getXMax(); x++) {
//        String rowOfRoomsToOutput = "";
//        for (int y = 0; y <= floor.getYMax(); y++) {
//            String roomName;
//            Room foundRoom = floor.getRoom(x, y);
//
//            if (foundRoom == null) {
//                roomName = "EMPTY";
//            } else {
//                roomName = foundRoom.getName();
//            }
//
//            int spacesNeeded = 9 - roomName.length();
//            rowOfRoomsToOutput = rowOfRoomsToOutput.concat("[" + roomName + " ".repeat(Math.max(spacesNeeded, 0)) + "]");
//        }
//        cText(rowOfRoomsToOutput);
//    }
}

//Check Character Level
private void characterSheet() {
    if (player.getPlayerExp() == 0) {
        cText("You literally just got in here, go fight something you dullard.");
    } else {
        cText("You have " + player.getPlayersHealth() + " health points.");
        cText("You need " + player.getNeededExp() + " experience points before leveling up.");
        cText("You are currently level " + player.getPlayerLevel() + ".");
        cText("What did you expect, we are still developing the program.");
        cText("Go fight something, you plebian.");
    }
}

//Check Character Inventory
private void checkInventory() {
    cText("This still needs to be implemented. You have no inventory at this time. Get over it.");
}

//Cheat mode to display goblin information
private void cheatMode() {
    cText("Name: " + goblin.goblinName);
    cText("Exp pool: " + goblin.getGoblinExpGiven());
    cText("Health pool: " + goblin.getGoblinHealth());
}

/** The fight function has been moved here instead of under the Player class where it did not belong.
 * Will need help on parsing in intel from player class and mob class to preform combat logic here.
 */
private void fightTheMob(){
    int playerDamage = player.getRandomPlayerDamageWithoutWeapon();
    goblin.playerDamagesGoblin(playerDamage);
    boolean checkDeath = isMobDead(goblin.getGoblinHealth());
    if (checkDeath) {
        cText("You killed " + goblin.goblinName + "!");
        cText("You monster! " + goblin.goblinName + " probably had a family! And you just killed them!");
        cText("You at least got " + goblin.getGoblinExpGiven() + " experience for being a murderer. " +
                "I hope you are happy!");
        player.addExpGivenFromKill(goblin.getGoblinExpGiven());
        player.getExpRequiredForLeveling();
        gameState = GameState.REG_MENU;
    } else {
        int randDmg = goblin.giveRandomGoblinDamage();
        cText("You hit " + goblin.goblinName + " for " + playerDamage + " points!");
        player.goblinDoesDamage(randDmg);
        isPlayerDead();
        cText(goblin.goblinName + " hit you for " + randDmg + " points!");
        cText("Doesn't feel so good when they attack back, does it?");
    }
}

//Checks if player has died, displays pop up window if yes
public void isPlayerDead() {
    if (player.getPlayersHealth() < 0) {
        JOptionPane.showMessageDialog(null, "You have died");
        System.exit(0);
        cText(player.getPlayerName() + " has died. I am actually shocked because the developers made this game pretty easy.");
    }
}

//Checks if the mob has died
private boolean isMobDead(int goblinHeatlh) {
    if (goblinHeatlh < 1) {
        return true;
    } else {
        return false;
    }
}

//Ends the game
private void endGame() {
    cText("You are weak and pathetic.");
    System.exit(0);
}