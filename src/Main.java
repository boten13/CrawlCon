import Util.Goblin;
import Util.Player;

/**
 *
 */
//Generate list of actions that player can initialize.

private final Scanner scanner = new Scanner(System.in);
//Regular Menu Items
private final String REG_MENU = "MENU";
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
Goblin goblin = new Goblin();
private String playerName = "";
Player player;
//private final String CHECK_TIME = "CLOCK";

//List of commands the player is prompted to use.
private ArrayList<String> playerCommands = new ArrayList<>(
        List.of(DIRECTION_EAST, DIRECTION_NORTH, DIRECTION_SOUTH, DIRECTION_WEST, MAP_CHECK, FIGHT, INVENTORY,
                CHARACTER_SHEET, END_GAME)
);

//List of commands the player is prompted to use while fighting.
private ArrayList<String> combatCommands = new ArrayList<>(
        List.of(ATTACK_CREATURE, RUN, USE_ITEM, TALK)
);
void main() {
    showWelcomeMessage();
}

//Welcome Message from the AI
private void showWelcomeMessage() {
    System.out.println("Welcome Crawler to Terran's Dungeon Crawl!");
    System.out.println("Please enter your registered crawler name for your time inside the crawl!");
    nameYourCharacter();
    System.out.println(playerName + " is now your registered name!");
    System.out.println("Lets begin the crawl! You can use the following commands:");
    System.out.println(playerCommands);
    System.out.println("If you ever forget what the commands are for the menu, you can simply say the word 'MENU'");
    System.out.println("Remember that because I will not be telling you about it again.");
    chooseNextMessage();
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

//Register Player name
private void nameYourCharacter() {
    playerName = scanner.nextLine();
    player = new Player(playerName);
}

//Check input vs actions possible
private void chooseAction(String action) {
    String actionInAllCaps = action.toUpperCase();
    if (!playerCommands.contains(actionInAllCaps)) {
        System.out.println("Please pay attention, crawler! I do not have all day to repeat myself for you.");
        System.out.println(action + " is not a valid option. Try again.");
        chooseNextMessage();
    }
    if (REG_MENU.equals(actionInAllCaps)) {
        System.out.println(playerCommands);
    }

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
    if (COMBAT_MENU.equals(actionInAllCaps)){
        System.out.println(combatCommands);
    }
    if (CHEAT.equals(actionInAllCaps)) {
        cheatMode();
    }

    if (!combatCommands.contains(actionInAllCaps)) {
        System.out.println("Please pay attention, crawler! I do not have all day to repeat myself for you.");
        System.out.println(action + " is not a valid option. Try again.");
        chooseNextCombatMessage();
    }

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
    System.out.println("You have initiated combat with a creature. Choose your next action.");
    System.out.println(combatCommands);
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
    System.out.println("You weak coward. It is a pathetic mob. Take the damage you deserve.");
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
    String name = goblin.goblinName;
    System.out.println(name);
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
        System.out.println("You at least got " + goblin.getGoblinExpGiven() + " for being a murderer. I hope you are happy!");
        player.addExpGivenFromKill(goblin.getGoblinExpGiven());
        player.getExpRequiredForLeveling();
        chooseNextMessage();
    } else {
        System.out.println("You hit " + goblin.goblinName + " for " + playerDamage + " points!");
        player.goblinDoesDamage(goblin.getGoblinDamage());
        System.out.println(goblin.goblinName + " hit you for " + goblin.getGoblinDamage() + " points!");
        System.out.println("Doesn't feel so good when they attack back, does it?");
        chooseNextCombatMessage();
    }
}

private boolean isMobDead(int goblinHeatlh) {
    if (goblinHeatlh < 0) {
        return true;
    } else {
        return false;
    }
}

private void endGame() {
    System.out.println("You are weak and pathetic.");
}
