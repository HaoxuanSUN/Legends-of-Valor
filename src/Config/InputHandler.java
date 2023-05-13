package Config;

import Action.ActionType;
import Battle.BattleAction;
import Character.HeroRole;
import Inventory.InventoryActionType;
import Market.MarketActionType;
import Team.MonsterTeam;
import World.LaneType;
import World.Position;

import java.util.Scanner;

// This class handles any input action needed from user during the game process
public class InputHandler {
    private final static Scanner scanner = new Scanner(System.in);

    public static void createPause() {
        scanner.nextLine();
    }

    // ask user to select a hero role
    public static HeroRole getHeroRole(LaneType laneType) {
        String str = "";
        if (laneType == LaneType.TOP) {
            str += "TOP";
        }
        else if (laneType == LaneType.MID) {
            str += "MID";
        }
        else {
            str += "BOT";
        }

        System.out.println("- For " + str + " Hero, which role do you want he/she to be?");
        Printer.printHeroCharacteristic();
        int role = 0;

        do {
            Printer.printInputText("> Please input the valid role number (1/2/3): ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                Printer.printInputText("> Please input the valid role number (1/2/3): ");
            }
            role = scanner.nextInt();
        } while (role < 1 || role > 3);
        scanner.nextLine();

        System.out.println();

        HeroRole hr;
        if (role == 1) {
            hr = HeroRole.WARRIOR;
        }
        else if (role == 2) {
            hr = HeroRole.SORCERER;
        }
        else { // role == 3
            hr = HeroRole.PALADIN;
        }

        return hr;
    }

    // listen to the keyboard input and return the right type
    public static ActionType getAction() {
        Printer.printInputText("> (World) Please enter the desired action (\"help\" for action options): ");
        while (true) {
            String input = scanner.nextLine();

            switch (input) {
                case "w":
                case "W":
                    return ActionType.MOVE_UP;
                case "a":
                case "A":
                    return ActionType.MOVE_LEFT;
                case "s":
                case "S":
                    return ActionType.MOVE_DOWN;
                case "d":
                case "D":
                    return ActionType.MOVE_RIGHT;
                case "t":
                case "T":
                    return ActionType.TELEPORT;
                case "r":
                case "R":
                    return ActionType.RECALL;
                case "weapon":
                    return ActionType.WEAPON;
                case "armor":
                    return ActionType.ARMOR;
                case "potion":
                    return ActionType.POTION;
                case "attack":
                    return ActionType.ATTACK;
                case "i":
                case "I":
                    return ActionType.ALL_INFO;
                case "h":
                case "H":
                    return ActionType.HERO_INFO;
                case "m":
                case "M":
                    return ActionType.MARKET;
                case "map":
                    return ActionType.MAP;
                case "bag":
                    return ActionType.INVENTORY;
                case "skip":
                    return ActionType.SKIP;
                case "help":
                    return ActionType.HELP;
                case "rule":
                    return ActionType.RULE;
                case "q":
                case "Q":
                case "quit":
                    return ActionType.QUIT;
                default:
                    Printer.printInputText("> (World) !INVALID INPUT, please try again (\"help\" for action options): ");
                    break;
            }
        }
    }

    // listen to the keyboard input and return the right type
    public static BattleAction getBattleAction() {
        Printer.printInputText("> (Battle) Please enter the desired action (\"help\" for battle options): ");
        while (true) {
            String input = scanner.nextLine();

            switch (input) {
                case "a":
                case "A":
                    return BattleAction.ATTACK;
                case "s":
                case "S":
                    return BattleAction.SPELL;
                case "p":
                case "P":
                    return BattleAction.POTION;
                case "w":
                case "W":
                    return BattleAction.EQUIP_WEAPON;
                case "e":
                case "E":
                    return BattleAction.EQUIP_ARMOR;
                case "i":
                case "I":
                    return BattleAction.INVENTORY;
                case "d":
                case "D":
                    return BattleAction.STATS;
                case "help":
                    return BattleAction.HELP;
                case "q":
                case "Q":
                case "quit":
                    return BattleAction.QUIT;
                default:
                    Printer.printInputText("> (Battle) !INVALID INPUT, please try again (\"help\" for battle options): ");
                    break;
            }
        }
    }

    // listen to the keyboard input and return the right type
    public static MarketActionType getMarketAction() {
        Printer.printInputText("> (Market) Please enter the desired action (\"help\" for market options): ");
        while (true) {
            String input = scanner.nextLine();

            switch (input) {
                case "b":
                case "B":
                    return MarketActionType.BUY;
                case "s":
                case "S":
                    return MarketActionType.SELL;
                case "v":
                case "V":
                    return MarketActionType.STORE;
                case "bag":
                    return MarketActionType.INVENTORY;
                case "i":
                case "I":
                    return MarketActionType.ALL_INFO;
                case "h":
                case "H":
                    return MarketActionType.HERO_INFO;
                case "map":
                    return MarketActionType.MAP;
                case "help":
                    return MarketActionType.HELP;
                case "close":
                    return MarketActionType.CLOSE;
                case "q":
                case "Q":
                case "quit":
                    return MarketActionType.QUIT;
                default:
                    Printer.printInputText("> (Market) !INVALID INPUT, please try again (\"help\" for market options): ");
                    break;
            }
        }
    }

    // ask user the monster to attack
    public static int getAttackedMonster(MonsterTeam monsters) {
        Printer.printMonstersStatus(monsters);
        System.out.println("- Choose the monster you want this hero to attack.");
        int index = 0;

        while (true) {
            do {
                Printer.printInputText("> Please input valid monster number: ");
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    Printer.printInputText("> Please input valid monster number: ");
                }
                index = scanner.nextInt();
            } while (index < 1 || index > monsters.getSize());
            if (monsters.getMonsterList().get(index - 1).isFaint()) {
                Printer.printInputText("> !Invalid input, selected monster is fainted.");
            }
            else {
                break;
            }
        }
        scanner.nextLine();
        System.out.println();
        return index - 1;
    }

    // ask user to input the index
    public static int getIndexNumber(String action, String keyword, int size) {
        System.out.println("- Choose " + keyword + " to " + action + ".");
        int index = 0;

        do {
            Printer.printInputText("> Please input the valid " + keyword + " number: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                Printer.printInputText("> Please input the valid " + keyword + " number: ");
            }
            index = scanner.nextInt();
        } while (index < 1 || index > size);
        scanner.nextLine();

        System.out.println();

        return index - 1;
    }

    // ask user to input a number
    public static int getNumber(int limit) {
        System.out.println("- How many do you want? (Min 1, Max " + limit + ")");
        int num = 0;

        do {
            Printer.printInputText("> Please input a valid number (1~" + limit + "): ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                Printer.printInputText("> Please input a valid number (1~" + limit + "): ");
            }
            num = scanner.nextInt();
        } while (num < 1 || num > limit);
        scanner.nextLine();

        System.out.println();

        return num;
    }

    public static Position getTeleportPos() {
        int row = getIndexNumber("teleport", "row", 8);
        int col = getIndexNumber("teleport", "col", 8);

        return new Position(row, col);
    }
}
