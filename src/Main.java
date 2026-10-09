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

private String playerName = "";
Player player;

String userCommand = "";

//Used for the switch:case
private int gameState = 0;

JTextArea outputConsoleResponse;
JTextField inputUserCommand;


//private final String CHECK_TIME = "CLOCK";

//List of commands the player is prompted to use.
private ArrayList<String> userCommands = new ArrayList<>(
        List.of(DIRECTION_EAST, DIRECTION_NORTH, DIRECTION_SOUTH, DIRECTION_WEST, LOOK_AROUND, MAP_CHECK, FIGHT,
                INVENTORY, CHARACTER_SHEET, END_GAME)
);

//List of commands the player is prompted to use while fighting.
private ArrayList<String> combatCommands = new ArrayList<>(
        List.of(ATTACK_CREATURE, RUN, USE_ITEM, TALK)
);
void main() {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
        //Initiate GUI display
        GUI gameDisplayWindow = new GUI();
        JDialog dialog = new JDialog();
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
        cText("\nSyS: Welcome Crawler to Terran's Dungeon Crawl!");
        cText("\nSyS: Please enter your registered crawler name for your time inside the crawl!\n>");
        }
    });
}

//Process User Input
private void processInput(String userCommand){
    //Display what user prompted
    cText(userCommand + "\n");

    switch (gameState) {
        case 0:
            playerName = userCommand;
            gameState = 1;
            cText("SyS: " + playerName + " is now your registered name!\n" +
                    "Sys: Let's begin your practice crawl.\n" + userCommands.toString() +
                    "\nSyS: If you ever forget what the commands are for the menu, you can simply say the word 'MENU'" +
                    "\nSyS: Remember that because I will not be telling you about it again.\n>");
            break;

        case 1:
            chooseAction(userCommand);
    }
}

//Converts Strings to output on GameDisplay -- ShortHanded for ConvertText
private void cText(String text) {
    outputConsoleResponse.append(text);
    outputConsoleResponse.setCaretPosition(outputConsoleResponse.getDocument().getLength());
}
//Welcome Message from the AI
private void showWelcomeMessage() {
    outputConsoleResponse.append("\nSyS: If you ever forget what the commands are for the menu, you can simply say the word 'MENU'");
    outputConsoleResponse.append("\nSyS: Remember that because I will not be telling you about it again.");
    chooseNextMessage();
}
private void secondWelcomeMessage() {
    outputConsoleResponse.append("\nSyS: " + playerName + " is now your registered name!");
    outputConsoleResponse.append("\nSyS: Lets begin the crawl! You can use the following commands:\n");
    outputConsoleResponse.append(userCommands.toString());
    outputConsoleResponse.append("\nSyS: If you ever forget what the commands are for the menu, you can simply say the word 'MENU'");
    outputConsoleResponse.append("\nSyS: Remember that because I will not be telling you about it again.");
}

//So I do not have to type this again everytime an action is preformed.
private void chooseNextMessage(){
    System.out.println("What would you like to do now?");
    String userInput = scanner.nextLine();
    chooseAction(userInput);
}

//Same thing as above to continue with combat options and lazieness.
private void chooseNextCombatMessage() {
    System.out.println("What is your next choice, Crawler " + playerName  + "?");
    String userInput = scanner.nextLine();
    chooseCombatAction(userInput);
}


//Check input vs actions possible
private void chooseAction(String action) {
    String actionInAllCaps = action.toUpperCase();
    //If input does not exist
    if (!userCommands.contains(actionInAllCaps)) {
        cText("\n SyS: Please pay attention, crawler! I do not have all day to repeat myself for you.\n");
        cText(action + " is not a valid option. Try again.\n>");
    }
    //hidden menu item on regular menu
    if (REG_MENU.equals(actionInAllCaps)) {
        System.out.println(userCommands);
    }
    //regular menu items displayed
    if (DIRECTION_EAST.equals(actionInAllCaps)) {
        headEast();
    } else if (DIRECTION_NORTH.equals(actionInAllCaps)) {
        headNorth();
    } else if (DIRECTION_SOUTH.equals(actionInAllCaps)) {
        headSouth();
    } else if (DIRECTION_WEST.equals(actionInAllCaps)) {
        headWest();
    } else if (MAP_CHECK.equals(actionInAllCaps)) {
        checkMap();
    } else if (FIGHT.equals(actionInAllCaps)) {
        startCombat();
    } else if (INVENTORY.equals(actionInAllCaps)) {
        checkInventory();
    } else if (CHARACTER_SHEET.equals(actionInAllCaps)) {
        characterSheet();
    } else if (LOOK_AROUND.equals(actionInAllCaps)) {
        lookAround();
    } else if (END_GAME.equals(actionInAllCaps)) {
        endGame();
    } else {
        System.out.println("Fix the code related to this word: " + action);
        chooseNextMessage();
    }
}

//Check input vs output on Fighting Commands list.
private void chooseCombatAction(String action) {
    String actionInAllCaps = action.toUpperCase();
    //Hidden menu items in combat menu
    if (COMBAT_MENU.equals(actionInAllCaps)){
        System.out.println(combatCommands);
    }
    if (CHEAT.equals(actionInAllCaps)) {
        cheatMode();
    }
    if (END_GAME.equals(actionInAllCaps)) {
        endGame();
    }
    //if the input does not exist
    if (!combatCommands.contains(actionInAllCaps)) {
        System.out.println("Please pay attention, crawler! I do not have all day to repeat myself for you.");
        System.out.println(action + " is not a valid option. Try again.");
        chooseNextCombatMessage();
    }
    //menu items for combat that is displayed
    if (ATTACK_CREATURE.equals(actionInAllCaps)) {
        attackCreature();
    } else if (RUN.equals(actionInAllCaps)) {
        runAway();
    } else if (USE_ITEM.equals(actionInAllCaps)) {
        useAnItemFromInventory();
    } else if (TALK.equals(actionInAllCaps)) {
        talkWithCreature();
    } else {
        System.out.println("Fix the code related to this word: " + action);
        chooseNextCombatMessage();
    }
}

//Function to describe what player can see.
private void lookAround() {
    System.out.println("You can see nothing right now, the developers haven't programmed the space in which we exist.");
    System.out.println("I say we, because I am referring to myself as well as you.");
    System.out.println("Currently I am just words appearing in front of you coming from the dark abyss.");
    System.out.println("Well, I suppose even if they had created a space in which you could see, I would still just");
    System.out.println("be a words on a page that you are reading and nothing more.");
    System.out.println("Alright, enough of that dark rabbit hole. Point is, there is nothing to see right now.");
    chooseNextMessage();
}

//Move Player East
private void headEast() {
    System.out.println("You go nowhere. Currently you live in a 1 by 1 square block that does nothing.");
    chooseNextMessage();
}

//Move Player West
private void headWest() {
    System.out.println("You go nowhere. Currently you live in a 1 by 1 square block that does nothing.");
    chooseNextMessage();
}

//Move Player North
private void headNorth() {
    System.out.println("You go nowhere. Currently you live in a 1 by 1 square block that does nothing.");
    chooseNextMessage();
}

//Move Player South
private void headSouth() {
    System.out.println("You go nowhere. Currently you live in a 1 by 1 square block that does nothing.");
    chooseNextMessage();
}

//Initiate combat
private void startCombat() {
    goblin.createGoblinMob();
    outputConsoleResponse.append("\nYou have initiated combat with a creature. Choose your next action.");
    outputConsoleResponse.append(combatCommands.toString());
    String choosenCombatAction = scanner.nextLine();
    chooseCombatAction(choosenCombatAction);
    chooseNextCombatMessage();
}

//Attack Creature
private void attackCreature() {
    fightTheMob();
    player.getExpRequiredForLeveling();
    chooseNextMessage();
}

//Run From Creature. (Small chance of succeeding, failure will result in damage to player.)
private void runAway() {
    int deservedDmg = goblin.giveRandomGoblinDamage();
    System.out.println("You weak coward. It is a pathetic mob. " + goblin.goblinName + " stabbed you in the back for "
            + deservedDmg + " points of damage.");
    System.out.println("Which you deserved. Now fight " + goblin.goblinName + " like a true player!");
    player.goblinDoesDamage(deservedDmg);
    chooseNextCombatMessage();
}
//Use an item in your inventory. Pull up inventory list.
private void useAnItemFromInventory() {
    System.out.println("You are sitting there rummaging around your pockets as if you have anything in them.");
    System.out.println("You don't. You just look stupid.");
    chooseNextCombatMessage();
}
//Talk with Creature (Will have rare chance of having discussion with creature.)
private void talkWithCreature() {
    System.out.println(goblin.goblinName + " looks at you like you are stupid and attacks you for " +
            goblin.giveRandomGoblinDamage() + " damage.");
    System.out.println("Seriously, it is an unintelligent mob. And you are scary looking, what do you expect?");
    player.isPlayerDead();
    chooseNextCombatMessage();
}

//Check Map Location
private void checkMap() {
    System.out.println("This goes nowhere. You need to play a game by better developers if you want fun stuff.");
    chooseNextMessage();
}

//Check Character Level
private void characterSheet() {
    player.checkCharacterSheet();
    chooseNextMessage();
}

//Check Character Inventory
private void checkInventory() {
    System.out.println("This still needs to be implemented. You have no inventory at this time. Get over it.");
    chooseNextMessage();
}

//Cheat mode to display goblin information
private void cheatMode() {
    goblin.displayGoblin();
    chooseNextCombatMessage();
}

/** The fight function has been moved here instead of under the Player class where it did not belong.
 * Will need help on parsing in intel from player class and mob class to preform combat logic here.
 */
private void fightTheMob(){
    int playerDamage = player.getRandomPlayerDamageWithoutWeapon();
    goblin.playerDamagesGoblin(playerDamage);
    boolean checkDeath = isMobDead(goblin.getGoblinHealth());
    if (checkDeath) {
        System.out.println("You killed " + goblin.goblinName + "!");
        System.out.println("You monster! " + goblin.goblinName + " probably had a family! And you just killed them!");
        System.out.println("You at least got " + goblin.getGoblinExpGiven() + " experience for being a murderer. " +
                "I hope you are happy!");
        player.addExpGivenFromKill(goblin.getGoblinExpGiven());
        player.getExpRequiredForLeveling();
        chooseNextMessage();
    } else {
        int randDmg = goblin.giveRandomGoblinDamage();
        System.out.println("You hit " + goblin.goblinName + " for " + playerDamage + " points!");
        player.goblinDoesDamage(randDmg);
        System.out.println(goblin.goblinName + " hit you for " + randDmg + " points!");
        System.out.println("Doesn't feel so good when they attack back, does it?");
        chooseNextCombatMessage();
    }
}

private boolean isMobDead(int goblinHeatlh) {
    if (goblinHeatlh < 1) {
        return true;
    } else {
        return false;
    }
}

private void endGame() {
    System.out.println("You are weak and pathetic.");
    System.exit(0);
}
