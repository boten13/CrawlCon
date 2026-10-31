//All the imported packages
import EnemyTypes.Enemy;
import Rooms.EnemyRoom;
import Rooms.Floor;
import Rooms.Room;
import Rooms.SafehouseRoom;
import Util.GameState;
import Util.Snark;
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
private final String LAZY_N = "N";
private final String DIRECTION_SOUTH = "SOUTH";
private final String LAZY_S = "S";
private final String DIRECTION_EAST = "EAST";
private final String LAZY_E = "E";
private final String DIRECTION_WEST = "WEST";
private final String LAZY_W = "W";
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
Enemy goblin = null;
private ArrayList<Enemy> enemiesInRoom = new ArrayList<>();

//Intiates and stores name given for player
Player player;

//Stores user Input from TextField
String userCommand = "";

//Used for the switch:case
private GameState gameState = GameState.WELCOME;

GUI gameDisplayWindow;

private Floor currentLevel;

//private final String CHECK_TIME = "CLOCK";

//List of commands the player is prompted to use.
private ArrayList<String> playerCommands = new ArrayList<>(
        List.of(DIRECTION_EAST, DIRECTION_NORTH, DIRECTION_SOUTH, DIRECTION_WEST, LOOK_AROUND, MAP_CHECK, FIGHT,
                INVENTORY, CHARACTER_SHEET, END_GAME)
);
//Actual Menu items with hidden functions
private ArrayList<String> actualMenu = new ArrayList<>(
        List.of(DIRECTION_EAST, DIRECTION_NORTH, DIRECTION_SOUTH, DIRECTION_WEST, LOOK_AROUND, MAP_CHECK, FIGHT,
                INVENTORY, CHARACTER_SHEET, END_GAME, REG_MENU, LAZY_E, LAZY_W, LAZY_N, LAZY_S)
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
    currentLevel = new Floor();

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
        gameDisplayWindow.getOutputConsoleResponse().append(Snark.snarkyWelcome());
        }
    });
}
//Process User Input
private void processInput(String userCommand){
    //Display what user prompted
    gameDisplayWindow.getOutputConsoleResponse().append("\n>" + userCommand + "\n");
    //Game state: Bounces between what is accessible at any given moment
    switch (gameState) {
        case GameState.WELCOME:
            //Stores user input as name for the player
            player = new Player(userCommand);
            //Moves case to 1
            gameState = GameState.REG_MENU;
            cText(Snark.snarkyBeginningMenu(player.getPlayerName(), playerCommands));
            break;
        case GameState.REG_MENU: //Reg options case
            //Cheat Word Menu to display the menu again
            if (REG_MENU.equalsIgnoreCase(userCommand)) {
                cText(playerCommands.toString());
                gameState = GameState.REG_MENU;
            }
            //Validates user input against all options available
            if (!actualMenu.contains(userCommand.toUpperCase())) {
                cText(Snark.notAWord(userCommand, player.getPlayerName()));
                gameState = GameState.REG_MENU;
            }
            //regular menu items displayed
            if (DIRECTION_EAST.equalsIgnoreCase(userCommand) || LAZY_E.equalsIgnoreCase(userCommand)) {
                headEast();
            } else if (DIRECTION_NORTH.equalsIgnoreCase(userCommand) || LAZY_N.equalsIgnoreCase(userCommand)) {
                headNorth();
            } else if (DIRECTION_SOUTH.equalsIgnoreCase(userCommand) || LAZY_S.equalsIgnoreCase(userCommand)) {
                headSouth();
            } else if (DIRECTION_WEST.equalsIgnoreCase(userCommand) || LAZY_W.equalsIgnoreCase(userCommand)) {
                headWest();
            } else if (MAP_CHECK.equalsIgnoreCase(userCommand)) {
                gameDisplayWindow.getOutputConsoleResponse().setText("Hope you remembered what I told you.");
                gameDisplayWindow.toggle();
                checkMap();
                //TODO: Fight should only work when in hallway with rats and no where else
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
//                cText("Fix the code related to this word: " + userCommand);
            }
            cText(Snark.snarkyWait(player.getPlayerName()));
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
                cText(Snark.notAWord(userCommand, player.getPlayerName()));
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
//                cText("Fix the code related to this word: " + userCommand);
            }
            break;
    }
}
//Converts Strings to output on GameDisplay -- ShortHanded for ConvertText
private void cText(String text) {
    gameDisplayWindow.getOutputConsoleResponse().append("\nSyS: " + text);
    gameDisplayWindow.getOutputConsoleResponse().setCaretPosition(gameDisplayWindow.getOutputConsoleResponse().getDocument().getLength());
}
// TODO: Move all direction mehthods into different directory
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
    if (checkDirection(player.getYLoc(), player.getXLoc() + 1)) {
        player.setXLoc(1);
        enterRoom(player.getYLoc(), player.getXLoc());
    }
}
//Move Player West
private void headWest() {
    if (checkDirection(player.getYLoc(), player.getXLoc() - 1)) {
        player.setXLoc(-1);
        enterRoom(player.getYLoc(), player.getXLoc());
    }
}
//Move Player North
private void headNorth() {
    if (checkDirection(player.getYLoc() - 1, player.getXLoc())) {
        player.setYLoc(-1);
        enterRoom(player.getYLoc(), player.getXLoc());
    }
}
//Move Player South
private void headSouth() {
    if (checkDirection(player.getYLoc() + 1, player.getXLoc())) {
        player.setYLoc(1);
        enterRoom(player.getYLoc(), player.getXLoc());
    }
}
//Checking player locale vs which room
public boolean checkDirection(int y, int x) {
    if (!isOnMap(y, x)) {
        cText("You can not leave the bounds of the dungeon floor.");
        return false;
    } else if (!currentLevel.getRoom(y, x).canYouGetIn()) {
        cText("You can not go through a wall.");
        return false;
    } else {
        return true;
    }
}
//Check Player location vs room
public void enterRoom(int y, int x) {
    Room room = currentLevel.getRoom(y, x);
    if (room instanceof EnemyRoom){
        cText(room.getDescription());
        ((EnemyRoom) room).enterRoom();
        //TODO: Have more than one enemy populate correctly
        enemiesInRoom = ((EnemyRoom) room).getEnemyList();
        startCombat();
        //TODO: Method to turn enemy bodies into dead body objects and have noncombative states
    } else if (room instanceof SafehouseRoom){
        cText("You are safe here crawler.");
    }
}
//Collect enemy names list
private List<String> enemyNames() {
    List<String> enemyNames = enemiesInRoom.stream()
            .map(e -> e.getMonsterName())
            .collect(Collectors.toList());
    return enemyNames;
}
//Collect enemy damages
//private List<Integer> enemyDamage() {
//    List<Integer> enemyDamage = enemiesInRoom.stream()
//            .map(e -> e.getDamage())
//            .collect(Collectors.toList());
//    return enemyDamage;
//}
//Initiate combat
private void startCombat() {
//    goblin.createGoblinMob();
        cText("You have initiated combat with " + String.join(", ", enemyNames()) + ".");
        cText("Here are your combat options: ");
        cText(combatCommands.toString());
        gameState = GameState.COMBAT;
}

//Attack Creature
private void attackCreature() {
//    fightTheMob();

    player.getExpRequiredForLeveling();
}
//TODO ADD SNARK TO THIS
//Run From Creature. (Small chance of succeeding, failure will result in damage to player.)
private void runAway() {
    for (int i = 0; i < enemiesInRoom.size(); i++) {
        cText(enemiesInRoom.get(i).getMonsterName() +" does " + enemiesInRoom.get(i).getDamage() + " damage.");
        player.enemyDoesDamage(enemiesInRoom.get(i).getDamage());
        isPlayerDead();
    }
}
//Use an item in your inventory. Pull up inventory list.
private void useAnItemFromInventory() {
    cText("You are sitting there rummaging around your pockets as if you have anything in them.");
    cText("You don't. You just look stupid.");
}
//TODO ADD SNARK TO THIS
//Talk with Creature (Will have rare chance of having discussion with creature.)... MAYBE
private void talkWithCreature() {
    for (int i = 0; i < enemiesInRoom.size(); i++) {
        cText(enemiesInRoom.get(i).getMonsterName() + " does " + enemiesInRoom.get(i).getDamage() + " damage.");
        player.enemyDoesDamage(enemiesInRoom.get(i).getDamage());
        isPlayerDead();
    }
}
//Checks the bounds of the map size
private boolean isOnMap(int y, int x) {
    return (y >= 0 && x >= 0 && y <= (currentLevel.getYMax() - 1) && x <= (currentLevel.getXMax()) - 1);
}
//Check Map Location
private void checkMap() {
    gameDisplayWindow.getMapDisplay().append("Sys: Wow... You're lost already? \n" +
            "That truly takes talent to be that bad at this game. \n" +
            "Take a quick look and get your bearings before something eats you.\n" +
            "or whatever it is you flesh bags do.\n");

    for (int y = 0; y < currentLevel.getMap().length; y++) {
        String rowOfRoomsToOutput = "";
        for (int x = 0; x < currentLevel.getMap()[y].length; x++) {
            if (player.getXLoc() == x && player.getYLoc() == y) {
                rowOfRoomsToOutput = rowOfRoomsToOutput.concat(player.getPlayerIcon() + " ");
            } else if (Math.abs(y - player.getYLoc()) <= player.getVisibility()
                    && Math.abs(x - player.getXLoc()) <= player.getVisibility()){
                rowOfRoomsToOutput = rowOfRoomsToOutput.concat(currentLevel.getRoom(y, x).getName() + " ");
            }
        }
        if (!rowOfRoomsToOutput.isEmpty())  {
            gameDisplayWindow.getMapDisplay().append("\n" + rowOfRoomsToOutput);
        }
    }
}
//Check Character Level
private void characterSheet() {
    if (player.getPlayerExp() == 0) {
        cText("You literally just got in here, go fight something you dullard.");
    } else {
        cText("You have " + player.healthCheck() + " health points.");
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
    cText("Name: " + goblin.getMonsterName());
    cText("Exp pool: " + goblin.getEnemyExpGiven());
    cText("Health pool: " + goblin.getEnemyHealth());
}
/** The fight function has been moved here instead of under the Player class where it did not belong.
 * Will need help on parsing in intel from player class and mob class to preform combat logic here.
 */
//TODO: Needs to be refactored to display entire list of enemies and choose which one to fight
private void fightTheMob(){
    int playerDamage = player.getRandomPlayerDamageWithoutWeapon();
    goblin.playerDamagesEnemy(playerDamage);
    boolean checkDeath = isMobDead(goblin.getEnemyHealth());
    if (checkDeath) {
        Snark.snarkyKill(goblin.getMonsterName(), player.getPlayerName());
        player.addExpGivenFromKill(goblin.getEnemyExpGiven());
        player.getExpRequiredForLeveling();
        gameState = GameState.REG_MENU;
    } else {
        int randDmg = goblin.getDamage();
        cText("You hit " + goblin.getMonsterName() + " for " + playerDamage + " points!");
        player.enemyDoesDamage(randDmg);
        isPlayerDead();
        cText(goblin.getMonsterName() + " hit you for " + randDmg + " points!");
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