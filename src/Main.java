import Util.Player;

/**
 *
 */
//Generate list of actions that player can initialize.

private final Scanner scanner = new Scanner(System.in);

private final String DIRECTION_NORTH = "NORTH";
private final String DIRECTION_SOUTH = "SOUTH";
private final String DIRECTION_EAST = "EAST";
private final String DIRECTION_WEST = "WEST";
private final String MAP_CHECK = "MAP";
private final String FIGHT = "FIGHT";
private final String INVENTORY = "INVENTORY";
private final String CHARACTER_SHEET = "CHARACTER SHEET";
private final String END_GAME = "END GAME";
private String playerName = "";
Player player;
//private final String CHECK_TIME = "CLOCK";

private ArrayList<String> playerCommands = new ArrayList<>(
        List.of(DIRECTION_EAST, DIRECTION_NORTH, DIRECTION_SOUTH, DIRECTION_WEST, MAP_CHECK, FIGHT, INVENTORY,
                CHARACTER_SHEET, END_GAME)
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
    chooseNextMessage();
}

//So I do not have to type this again everytime an action is preformed.
private void chooseNextMessage(){
    System.out.println("What would you like to do now?");
    String userInput = scanner.nextLine();
    chooseAction(userInput);
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
        return;
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
        fuckEmUP();
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

//Move Player East
private void headEast() {

}

//Move Player West
private void headWest() {

}

//Move Player North
private void headNorth() {

}

//Move Player South
private void headSouth() {

}

//Initiate combat
private void fuckEmUP() {
    player.Fight();
    player.getExpRequiredForLeveling();
    chooseNextMessage();
}

//Check Map Location
private void checkMap() {

}

//Check Character Level
private void characterSheet() {
    player.checkCharacterSheet();
    chooseNextMessage();
}

//Check Character Inventory
private void checkInventory() {

}

private void endGame() {
    System.out.println("You are weak and pathetic.");
}
