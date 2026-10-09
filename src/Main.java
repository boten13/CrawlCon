//All the imported packages
import Util.Goblin;
import Util.Player;
import Util.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 *
 */
//Generate list of actions that player can initialize.
private final Scanner scanner = new Scanner(System.in);
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
private String playerName = "";
Player player = new Player(playerName);

//Stores user Input from TextField
String userCommand = "";

//Used for the switch:case
private int gameState = 0;

JTextArea outputConsoleResponse;
JTextField inputUserCommand;


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
void main() {
    //Initiates EDT for SWING
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
        //Initiate GUI display
        GUI gameDisplayWindow = new GUI();
        //Code for the console output into the GUI display
        outputConsoleResponse = new JTextArea();
        outputConsoleResponse.setBackground(Color.BLACK);
        outputConsoleResponse.setForeground(Color.GREEN);
        outputConsoleResponse.setFont(new Font("Consolas", Font.PLAIN, 17));
        outputConsoleResponse.setEditable(false);
        outputConsoleResponse.setLineWrap(true);
        outputConsoleResponse.setWrapStyleWord(true);
        //Allows for scrolling on previous output
        JScrollPane scrollPane = new JScrollPane(outputConsoleResponse);
        scrollPane.setBorder(null);
        //Code for the user to input commands into the GUI display
        inputUserCommand = new JTextField();
        inputUserCommand.setBackground(Color.BLACK);
        inputUserCommand.setForeground(Color.GREEN);
        inputUserCommand.setCaretColor(Color.WHITE);
        inputUserCommand.setFont(new Font("Consolas", Font.PLAIN, 17));
        inputUserCommand.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));

        //Sets the layout for everything
        gameDisplayWindow.setLayout(new BorderLayout());
        //Sets the scroller to the middle section of the layout
        gameDisplayWindow.add(scrollPane, BorderLayout.CENTER);
        //adds user input box to the bottom of the display
        gameDisplayWindow.add(inputUserCommand, BorderLayout.SOUTH);
        //Activates the new window
        gameDisplayWindow.setVisible(true);
        //Creates listener for user input
        inputUserCommand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userCommand = inputUserCommand.getText();
                if (!userCommand.isEmpty()) {
                    processInput(userCommand);
                inputUserCommand.setText("");
                }
            }
        });
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

    switch (gameState) {
        case 0:
            //Stores user input as name for the player
            playerName = userCommand;
            //Moves case to 1
            gameState = 1;
            cText("SyS: " + playerName + " is now your registered name!\n" +
                    "Sys: Let's begin your practice crawl.\n" + playerCommands.toString() +
                    "\nSyS: If you ever forget what the commands are for the menu, you can simply say the word 'MENU'" +
                    "\nSyS: Remember that because I will not be telling you about it again.\n");
            break;

        case 1: //Reg options case
            //Cheat Word Menu to display the menu again
            if (REG_MENU.equalsIgnoreCase(userCommand)) {
                cText(playerCommands.toString());
                gameState = 1;
            }
            //Validates user input against all options available
            if (!actualMenu.contains(userCommand.toUpperCase())) {
                cText("SyS: Please pay attention, crawler! I do not have all day to repeat myself for you.");
                cText(userCommand + " is not a valid option. Try again.");
                gameState = 1;
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
            cText("SyS: What would you like to do next, " + playerName + "?");
            break;
        case 2://Combat start case
                cText("SyS: You have initiated combat with " + goblin.goblinName + ".");
                cText("SyS: This is your combat menu options.");
                cText(combatCommands.toString());

            //Hidden menu items in combat menu
            if (COMBAT_MENU.equalsIgnoreCase(userCommand)){
                cText(combatCommands.toString());
                gameState = 2;
            }
            if (CHEAT.equalsIgnoreCase(userCommand)) {
                cheatMode();
                gameState = 2;
            }
            if (END_GAME.equalsIgnoreCase(userCommand)) {
                endGame();
            }
            //if the input does not exist
            if (!actualCombatOptions.contains(userCommand.toUpperCase())) {
                cText("Please pay attention, crawler! I do not have all day to repeat myself for you.");
                cText(userCommand + " is not a valid option. Try again.");
                gameState = 3;
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
        case 3: //Combat continue case
            //Hidden menu items in combat menu
            if (COMBAT_MENU.equalsIgnoreCase(userCommand)){
                cText(combatCommands.toString());
                gameState = 3;
            }
            if (CHEAT.equalsIgnoreCase(userCommand)) {
                cheatMode();
                gameState = 3;
            }
            if (END_GAME.equalsIgnoreCase(userCommand)) {
                endGame();
            }
            //if the input does not exist
            if (!actualCombatOptions.contains(userCommand.toUpperCase())) {
                cText("Please pay attention, crawler! I do not have all day to repeat myself for you.");
                cText(userCommand + " is not a valid option. Try again.");
                gameState = 3;
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
    outputConsoleResponse.append("\n" + text);
    outputConsoleResponse.setCaretPosition(outputConsoleResponse.getDocument().getLength());
}

//Function to describe what player can see.
private void lookAround() {
    cText("You can see nothing right now, the developers haven't programmed the space in which we exist.");
    cText("I say we, because I am referring to myself as well as you.");
    cText("Currently I am just words appearing in front of you coming from the dark abyss.");
    cText("Well, I suppose even if they had created a space in which you could see, I would still just");
    cText("be a words on a page that you are reading and nothing more.");
    cText("Alright, enough of that dark rabbit hole. Point is, there is nothing to see right now.");
    gameState = 1;
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
    cText("SyS: You go nowhere. Currently you live in a 1 by 1 square block that does nothing.\n>");
    gameState = 1;
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
    gameState = 3;
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
    gameState = 3;
}
//Use an item in your inventory. Pull up inventory list.
private void useAnItemFromInventory() {
    cText("You are sitting there rummaging around your pockets as if you have anything in them.");
    cText("You don't. You just look stupid.");
    gameState = 3;
}
//Talk with Creature (Will have rare chance of having discussion with creature.)
private void talkWithCreature() {
    cText(goblin.goblinName + " looks at you like you are stupid and attacks you for " +
            goblin.giveRandomGoblinDamage() + " damage.");
    cText("Seriously, it is an unintelligent mob. And you are scary looking, what do you expect?");
    isPlayerDead();
    gameState = 3;
}

//Check Map Location
private void checkMap() {
    cText("This goes nowhere. You need to play a game by better developers if you want fun stuff.");
   gameState = 1;
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
        gameState = 1;
    }
}

//Check Character Inventory
private void checkInventory() {
    cText("This still needs to be implemented. You have no inventory at this time. Get over it.");
    gameState = 1;
}

//Cheat mode to display goblin information
private void cheatMode() {
    cText("Name: " + goblin.goblinName);
    cText("Exp pool: " + goblin.getGoblinExpGiven());
    cText("Health pool: " + goblin.getGoblinHealth());
    gameState = 3;
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
        gameState = 1;
    } else {
        int randDmg = goblin.giveRandomGoblinDamage();
        cText("You hit " + goblin.goblinName + " for " + playerDamage + " points!");
        player.goblinDoesDamage(randDmg);
        isPlayerDead();
        cText(goblin.goblinName + " hit you for " + randDmg + " points!");
        cText("Doesn't feel so good when they attack back, does it?");
       gameState = 3;
    }
}

//Checks if player has died, displays pop up window if yes
public void isPlayerDead() {
    if (player.getPlayersHealth() < 0) {
        JOptionPane.showMessageDialog(null, "You have died");
        System.exit(0);
        cText(playerName + " has died. I am actually shocked because the developers made this game pretty easy.");
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
