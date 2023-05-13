package Config;

import Character.*;
import Inventory.Inventory;
import Item.*;
import Team.HeroTeam;
import Team.MonsterTeam;
import World.*;

import java.util.List;

// This class handles terminal output of the game
public class Printer {

    private static final String noTab = "";

    private static final String singleTab = "\t";

    private static final String doubleTab = "\t\t";

    private static final String tripleTab = "\t\t\t";

    private static final String blackText = "30";

    private static final String blackBackground = "40";

    private static final String greyText = "37";

    private static final String greyBackground = "47";

    private static final String redText = "91";

    private static final String redBackground = "101";

    private static final String greenText = "92";

    private static final String greenBackground = "102";

    private static final String orangeText = "93";

    private static final String orangeBackground = "103";

    private static final String blueText = "94";

    private static final String blueBackground = "104";

    private static final String magentaText = "95";

    private static final String magentaBackground = "105";

    private static final String cyanText = "96";

    private static final String cyanBackground = "106";

    private static final String whiteText = "97";

    private static final String whiteBackground = "107";

    public static void printGameSelect() {
        System.out.println("WELCOME!");
        System.out.println("- Please select a game to play:");
    }

    public static void printGameOptions() {
        System.out.println("---------- Games ----------");
        System.out.println("|   1. Legends of Valor    |");
        System.out.println("---------------------------");
        System.out.println();
    }

    public static void printColorBoldText(String text, String color) {
        System.out.println("\033[1;" + color + "m" + text + "\033[0m");
    }

    public static void printColorText(String text, String color) {
        System.out.println("\033[" + color + "m" + text + "\033[0m");
    }

    public static void printInputText(String text) {
        System.out.println("\033[1;" + blueText + "m" + text + "\033[0m");
    }

    public static void printBoldText(String text) {
        System.out.println("\033[1m" + text + "\033[0m");
    }

    public static void printWelcome() {
        printColorBoldText("- Welcome to Legends of Valor!", redText);
        System.out.println("- This is a game in a world full of spells, monsters, and heroes!");
        System.out.println("- You will be asked to form a team of heroes to fight against the monsters.");
        System.out.println("- Hope you enjoy the game~");
        System.out.println("- GLHF!");
        System.out.println();
        printPause("show rules");
    }

    public static void printPause(String keyword) {
        printInputText("> Press 'Enter' to " + keyword + ".");
        InputHandler.createPause();
    }

    public static void printThankYou() {
        System.out.println("- Thank you for playing the game!");
        System.out.println("- See you next time!");
    }

    public static void printGameOver() {
        System.out.println();
        printColorBoldText("- Game Over! Monster has reached your nexus!", redText);
        printThankYou();
        printBreak("GAME OVER");
    }

    public static void setWaitTime(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printAction() {
        System.out.println("                                    ============= Input Options ============");
        System.out.println("                                   ｜--- W/w: move up one unit ---          ｜");
        System.out.println("                                   ｜--- A/a: move left one unit ---        ｜");
        System.out.println("                                   ｜--- S/s: move down one unit ---        ｜");
        System.out.println("                                   ｜--- D/d: move right one unit ---       ｜");
        System.out.println("                                   ｜--- T/t: teleport to a space ---       ｜");
        System.out.println("                                   ｜--- R/r: recall to nexus ---           ｜");
        System.out.println("                                   ｜--- weapon: change weapon ---          ｜");
        System.out.println("                                   ｜--- armor: change armor ---            ｜");
        System.out.println("                                   ｜--- potion: use a potion---            ｜");
        System.out.println("                                   ｜--- attack: perform an attack ---      ｜");
        System.out.println("                                   ｜--- * I/i: show hero & monster info ---｜");
        System.out.println("                                   ｜--- * H/h: show current hero info ---  ｜");
        System.out.println("                                   ｜--- * M/m: enter market ---            ｜");
        System.out.println("                                   ｜--- * map: open world map ---          ｜");
        System.out.println("                                   ｜--- * bag: show inventory ---          ｜");
        System.out.println("                                   ｜--- * rule: open rule book ---         ｜");
        System.out.println("                                   ｜--- * help: action list ---            ｜");
        System.out.println("                                   ｜--- Q/q/quit: quit game ---            ｜");
        System.out.println("                                    ========================================");
        System.out.println("                                    action with * does not consume the turn");
        System.out.println();
    }

    public static void printMarketAction() {
        System.out.println("========= Market Options =========");
        System.out.println("｜--- B/b: buy item ---          ｜");
        System.out.println("｜--- S/s: sell item ---         ｜");
        System.out.println("｜--- V/v: view products ---     ｜");
        System.out.println("｜--- I/i: show all info ---     ｜");
        System.out.println("｜--- H/h: show hero info ---    ｜");
        System.out.println("｜--- bag: show inventory ---    ｜");
        System.out.println("｜--- map: open world map ---    ｜");
        System.out.println("｜--- close: close market ---    ｜");
        System.out.println("｜--- help: action list ---      ｜");
        System.out.println("｜--- Q/q/quit: quit game ---    ｜");
        System.out.println("==================================");
        System.out.println();
    }

    public static void printBreak(String title) {
        System.out.println("====================================================== " + title +
                " ======================================================");
    }

    public static void printBanner(String title) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " + title +
                " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void printItemBanner(String itemName) {
        System.out.println("-------------------- " + itemName + " --------------------");
    }


    public static void printHeroCharacteristic() {
        System.out.println("    1. Warrior:  strength++  agility++");
        System.out.println("    2. Sorcerer: dexterity++ agility++");
        System.out.println("    3. Paladin:  strength++  dexterity++");
    }

    public static void printRound(String name) {
        System.out.println(">>>>>>>>> Hero " + name +"'s Turn <<<<<<<<<");
    }

    public static void printMonsterRound(String name) {
        System.out.println(">>>>>>>>> Monster " + name +"'s Turn <<<<<<<<<");
    }

    public static void printRule() {
        printBreak("Rules");
        System.out.println("                                                  --- Overview ---");
        System.out.println("This is a MOBA (multiplayer online battle arena)-like game named 'Legends of Valor'. ");
        System.out.println("You are in a fictional world full of spells, monsters, and heroes. ");
        System.out.println("The player will control a team of 3 heroes who will attempt to fight their way through to the monsters’ Nexus. ");
        System.out.println("The heroes win if any of them reach the monsters’ Nexus. ");
        System.out.println("The heroes lose if any monster reaches the heroes’ Nexus. ");
        System.out.println("Each hero has their own money and inventory and you will need to wisely use them to power up the hero.");
        System.out.println("Heroes can buy/sell items only at nexuses. ");
        System.out.println("Monsters and heroes both have different types, which lead to distinct skill points.");
        System.out.println("Battle is round-based and you can do one action for each hero in each round. ");
        System.out.println("Heroes will always be the first to do action when round begins.");
        System.out.println("More details are presented below. Have fun!");
        System.out.println();

        System.out.println("I. World");
        System.out.println("    Legends of Valor is played in an 8x8 grid of spaces.");
        System.out.println("    There are three lanes in map: TOP, MID, BOT.");
        System.out.println("    There are six types of space in world. You can see your world map below.");
        System.out.println("    -- Plain space (white): these spaces have no special attributes");
        System.out.println("    -- Inaccessible space (black): heroes and monsters cannot enter these spaces.");
        System.out.println("    -- Hero nexus space (blue): you can enter the market if you step on nexus space by inputting M/m. ");
        System.out.println("    -- Monster nexus space (red): monsters will spawn here every 8 rounds.");
        System.out.println("    -- Bush space (green): bush spaces increase the dexterity of any hero inside them.");
        System.out.println("    -- Cave space (gray): cave spaces increase the agility of any hero inside them.");
        System.out.println("    -- Koulou space (pink): koulou spaces increase the strength of any hero who is inside them.");
        System.out.println("    The bonus is removed when the hero leaves the space.");
        System.out.println();

        System.out.println("II. Team of heroes");
        System.out.println("    You will be asked to lead a team of 3 heroes. ");
        System.out.println("    Each hero has the following attributes: ");
        System.out.println("    -- Name: the unique name of this hero.");
        System.out.println("    -- Type: the type of this hero, which has unique balance of skills.");
        System.out.println("    -- Level: the level of this hero. The hero’s skills increase when the hero levels up.");
        System.out.println("    -- HP: the hit points of this hero.");
        System.out.println("    -- MP: the mana points of this hero, used to cast spells.");
        System.out.println("    -- Skills: ");
        System.out.println("        - Strength: strength increases the amount of damage this hero dealt when using a weapon.");
        System.out.println("        - Dexterity: dexterity increases the amount of damage this hero dealt when casting a spell.");
        System.out.println("        - Agility: hero has chance to dodge a monster's attack, and agility increases the dodge chance.");
        System.out.println("    -- Money: money to purchase items in market.");
        System.out.println("    -- Inventory: a collection of items bought from market.");
        System.out.println("    There are three types of hero, each has unique set of skills. ");
        System.out.println("    '++' means such skill will be increased more than normal at the start of the game and on each level up: ");
        System.out.println("        - Warrior:  strength++  agility++");
        System.out.println("        - Sorcerer: dexterity++ agility++");
        System.out.println("        - Paladin:  strength++  dexterity++");
        System.out.println();

        System.out.println("III. Monsters");
        System.out.println("    Monsters will spawn at nexus every 8 rounds.");
        System.out.println("    Monsters will only move down, and attack when heroes are in range.");
        System.out.println("    Each monster has the following attributes: ");
        System.out.println("    -- Name: the name of this monster.");
        System.out.println("    -- Type: the type of this monster, which with a different favored skill.");
        System.out.println("    -- Level: the level of this monster.");
        System.out.println("    -- HP: the level of this monster.");
        System.out.println("    -- Skills: ");
        System.out.println("        - Base damage value: this value is used to calculate actual attack damage.");
        System.out.println("        - Defense value: this value reduces the amount of damage a monster takes from a hero’s attack or spell.");
        System.out.println("        - Dodge chance: this represents how well a monster can avoid a hero’s attack.");
        System.out.println("    There are three types of monsters, each with a different favored skill");
        System.out.println("    '++' means such skill will be increased more than normal: ");
        System.out.println("        - Dragons:      base damage++");
        System.out.println("        - Exoskeletons: defense++");
        System.out.println("        - Spirits:  dodge chance++");
        System.out.println();

        System.out.println("IV. Market");
        System.out.println("    Market can be accessed when heroes are at nexus, and they can buy and sell items.");
        System.out.println("    Market will produce items which level equal the maximum level among the heroes.");
        System.out.println("    You could enter the market by entering M/m if the party of heroes is on a market space.");
        System.out.println("    A special menu will be showed for the player to see all the items for sale with their details");
        System.out.println("    Each hero would enter the market individually.");
        System.out.println("    Heroes do no share money and inventory with their fellow heroes.");
        System.out.println("    A hero can sell an item in their inventory to the market for less than the price for which it was bought.");
        System.out.println("    Item sold will be added to the market special menu.");
        System.out.println("    A hero can purchase an item in the market, with the following two rules:");
        System.out.println("    -- a hero cannot buy an item that costs more than the money they have.");
        System.out.println("    -- a hero cannot buy an item that is a higher level than they are.");
        System.out.println();

        System.out.println("V. Items");
        System.out.println("    All items have the following attributes: name, price, and level.");
        System.out.println("    There are four types of items:");
        System.out.println("    -- Weapons: ");
        System.out.println("        A weapon is used by a hero to attack a monster. A weapon must be equipped by the hero before it can be used.");
        System.out.println("        A weapon has the following attributes: ");
        System.out.println("        - Damage value: this value is used to calculate the damage dealt during an attack move in a battle");
        System.out.println("        - Number of hands required: A weapon may require one or two hands to be used (i.e. a bow requires two hands)");
        System.out.println("    -- Armor: ");
        System.out.println("        Armor reduces the incoming damage from a monster’s attack.");
        System.out.println("        An armor has the following attributes: ");
        System.out.println("        - Damage reduction value: the amount of damage taken reduced by the hero when equipped");
        System.out.println("    -- Potions: ");
        System.out.println("        Potions can be used by a hero in order to increase one of their statistics by some amount.");
        System.out.println("        Potions are single-use items: once they are used, potions cannot be reused.");
        System.out.println("        A potion has the following attributes: ");
        System.out.println("        - Effect amount: this value is how much the skills will increase if the potion is taken");
        System.out.println("    -- Spells: ");
        System.out.println("        A spell represents a magic attack performed by a hero.");
        System.out.println("        To cast a spell, a hero will require at least the mana cost amount of MP to cast the spell.");
        System.out.println("        A spell has the following attributes: ");
        System.out.println("        - Damage value: the value is used along with the hero’s dexterity value to calculate the damage dealt during a spell cast move in a battle.");
        System.out.println("        Spells also have an additional effect besides their damage.");
        System.out.println("        There are three types of spells: ");
        System.out.println("        - Ice spell: reduces the damage of the target");
        System.out.println("        - Fire spell: reduces the defense of the target");
        System.out.println("        - Lightning spell: reduces the dodge chance of the target");
        System.out.println("    Spells are consumable items, so it has a limitation of how many times one spell can be cast.");
        System.out.println();

        System.out.println("VI. Battle");
        System.out.println("    The battle happens between 1 hero and 1 monster.");
        System.out.println("    In each round: ");
        System.out.println("        During the heroes’ turn, the player chooses for each hero whether they will do one of the following: ");
        System.out.println("        -- Attack, using the hero’s equipped weapon");
        System.out.println("        -- Cast a spell from the hero’s inventory");
        System.out.println("        -- Use a potion from the hero’s inventory");
        System.out.println("        -- Equip a weapon or piece of armor");
        System.out.println("        -- Recall");
        System.out.println("        -- Teleport");
        System.out.println("        The player can also display the statistics of a hero or a monster by entering I/i. This will not consume the turn.");
        System.out.println("        During the monsters' turn, they will only attack the heroes.");
        System.out.println("        At the end of each round (after the monsters’ turn), heroes that have not fainted regain some of their HP and MP.");
        System.out.println("    If a monster is fainted: ");
        System.out.println("        -- all heroes will earn money and experience, potentially leveling up.");
        System.out.println("        -- monster will be removed from the map.");
        System.out.println("    If a hero is fainted: ");
        System.out.println("        -- fainted heroes will revive with full HP and full MP at nexus.");
        System.out.println();
    }

    public static void printWorld(World world, HeroTeam heroTeam, MonsterTeam monsterTeam, Hero chosenHero) {
        int row = world.getRows();
        int col = world.getCols();
        Position topPosition = null, midPosition = null, botPosition = null, chosenPosition = null;

        if (chosenHero != null) {
            chosenPosition = chosenHero.getPosition();
        }

        for (Hero hero : heroTeam.getTeam()) {
            if (hero.getLane() == LaneType.TOP) {
                topPosition = hero.getPosition();
            }
            else if (hero.getLane() == LaneType.MID) {
                midPosition = hero.getPosition();
            }
            else {
                botPosition = hero.getPosition();
            }
        }

        System.out.println("  TOP      MID      BOT");
        for (int i = 0; i < row; i++) {
            System.out.print("|");
            for (int j = 0; j < col; j++) {
                Space space = world.getSpace(new Position(i, j));
                Position position = space.getPosition();

                if (space.getType() == SpaceType.INACCESSIBLE) {
                    printInaccessibleSpace();
                    continue;
                }

                String str = "";

                if (position.equals(topPosition) || position.equals(midPosition) || position.equals(botPosition)) {
                    if (position.equals(chosenPosition)) {
                        System.out.print("\033[1;" + redText + "m" + "H" + "\033[0m");
                    }
                    else {
                        str += "H";
                    }
                }
                else {
                    str += " ";
                }

                if (monsterTeam.hasMonster(position)) {
                    str += "M";
                }
                else {
                    str += " ";
                }

                switch (space.getType()) {
                    case PLAIN:
                        printBackgroundColorSpace(str, whiteBackground);
                        break;
                    case HERO_NEXUS:
                        printBackgroundColorSpace(str, blueBackground);
                        break;
                    case MONSTER_NEXUS:
                        printBackgroundColorSpace(str, redBackground);
                        break;
                    case BUSH:
                        printBackgroundColorSpace(str, greenBackground);
                        break;
                    case CAVE:
                        printBackgroundColorSpace(str, greyBackground);
                        break;
                    case KOULOU:
                        printBackgroundColorSpace(str, magentaBackground);
                        break;
                }
            }
            System.out.print(i + 1);
            if (i == 3) {
                System.out.print(" R");
            }
            if (i == 4) {
                System.out.print(" O");
            }
            if (i == 5) {
                System.out.print(" W");
            }

            System.out.println();
        }
        System.out.println(" 1  2  3  4  5  6  7  8");
        System.out.println("         COLUMN");

        System.out.println();
    }

    public static void printInaccessibleSpace() {
        System.out.print("\033[40m  \033[0m|");
    }

    public static void printBackgroundColorSpace(String str, String color) {
        System.out.print("\033[1;30;" + color + "m" + str + "\033[0m|");
    }

    public static void printWorldSymbolExplain() {
        System.out.println("H: Your heroes location");
        System.out.println("M: Monsters location");

        printInaccessibleSpace();
        System.out.println(" Inaccessible space");

        printBackgroundColorSpace("  ", whiteBackground);
        System.out.println(" Plain space");

        printBackgroundColorSpace("  ", blueBackground);
        System.out.println(" Hero Nexus (Market)");

        printBackgroundColorSpace("  ", redBackground);
        System.out.println(" Monster Nexus");

        printBackgroundColorSpace("  ", greenBackground);
        System.out.println(" Bush space      Dexterity++");

        printBackgroundColorSpace("  ", greyBackground);
        System.out.println(" Cave space      Agility++");

        printBackgroundColorSpace("  ", magentaBackground);
        System.out.println(" Koulou space    Strength++");

        System.out.println();
    }

    public static void printMoveError(String error) {
        printColorText("! INVALID MOVE, " + error + ".", redText);
        System.out.println();
    }

    public static void printRecallError() {
        printColorText("! INVALID RECALL, someone else is on your recall position.", redText);
        System.out.println();
    }

    public static void printTeleportError(String error) {
        printColorText("! INVALID TELEPORT, " + error + ".", redText);
        System.out.println();
    }

    public static void printHeroInventory(Hero hero) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> INVENTORY <<<<<<<<<<<<<<<<<<<<<<<<<");

        Inventory inventory = hero.getInventory();
        List<Weapon> weapons = inventory.getWeapons();
        List<Spell> spells = inventory.getSpells();
        List<Potion> potions = inventory.getPotions();
        List<Armor> armors = inventory.getArmors();

        int weaponNum = inventory.getWeaponNum();
        int spellNum = inventory.getSpellNum();
        int potionNum = inventory.getPotionNum();
        int armorNum = inventory.getArmorNum();

        if (weaponNum != 0) printWeaponList(weapons);
        if (spellNum != 0) printSpellList(spells);
        if (potionNum != 0) printPotionList(potions);
        if (armorNum != 0) printArmorList(armors);

        System.out.println("- Hero " + hero.getName() + " has ");
        System.out.println("        " + weaponNum + " weapons");
        System.out.println("        " + spellNum + " spells");
        System.out.println("        " + potionNum + " potions");
        System.out.println("        " + armorNum + " armors");
        System.out.println("- in inventory.");
        System.out.println();
        System.out.println("- Hero " + hero.getName() + " current equip:");
        System.out.println("-------- Weapon --------");
        if (hero.getWeaponSlot() == null) {
            System.out.println(" None");
        }
        else {
            System.out.println("* Name: " + hero.getWeaponSlot().getName());
            System.out.println("* Damage: " + hero.getWeaponSlot().getDamage());
            System.out.println("* Lv: " + hero.getWeaponSlot().getLevel());
        }
        System.out.println("------------------------");
        System.out.println("-------- Armor --------");
        if (hero.getArmorSlot() == null) {
            System.out.println(" None");
        }
        else {
            System.out.println("* Name: " + hero.getArmorSlot().getName());
            System.out.println("* Damage Reduction: " + hero.getArmorSlot().getDamageReduction());
            System.out.println("* Lv: " + hero.getArmorSlot().getLevel());
        }
        System.out.println("-----------------------");
        System.out.println();
    }

    public static void printWeaponList(List<Weapon> weapons) {
        printItemBanner("Weapon");
        System.out.println("No." + singleTab + "Lv" + singleTab +
                "Damage" + singleTab + "Price" + singleTab +
                "Hands" + singleTab + "Name");

        for (int i = 0; i < weapons.size(); i++) {
            Weapon weapon = weapons.get(i);
            int num = i + 1;
            int level = weapon.getLevel();
            int damage = weapon.getDamage();
            int cost = weapon.getPrice();
            int hands = weapon.getHandsRequired();
            String name = weapon.getName();

            System.out.println(num + singleTab
                    + level + singleTab
                    + damage + singleTab
                    + cost + singleTab
                    + hands + singleTab
                    + name);
        }
        System.out.println("------------------------------------------------");
    }

    public static void printArmorList(List<Armor> armors) {
        System.out.println("----------------------------- Armor ------------------------------");
        System.out.println("No." + singleTab + "Lv" + singleTab +
                "Reduction" + singleTab + "Price" + singleTab + "Name");

        for (int i = 0; i < armors.size(); i++) {
            Armor armor = armors.get(i);
            int num = i + 1;
            int level = armor.getLevel();
            int reduction = armor.getDamageReduction();
            int cost = armor.getPrice();
            String name = armor.getName();

            System.out.println(num + singleTab
                    + level + singleTab
                    + reduction + doubleTab
                    + cost + singleTab
                    + name);
        }
        System.out.println("------------------------------------------------------------------");
    }

    public static void printPotionList(List<Potion> potions) {
        System.out.println("-------------------------------- Potion ----------------------------------");
        System.out.println("No." + singleTab + "Lv" + singleTab +
                "Increase" + singleTab + "Price" + singleTab +
                "Name" + doubleTab + "Attribute_Affected");

        for (int i = 0; i < potions.size(); i++) {
            Potion potion = potions.get(i);
            int num = i + 1;
            int level = potion.getLevel();
            int increase = potion.getIncreaseAmount();
            int cost = potion.getPrice();
            String name = potion.getName();
            String attributeAffected = potion.getAttributeAffected();

            System.out.println(num + singleTab
                    + level + singleTab
                    + increase + (increase > 999 ? singleTab : doubleTab)
                    + cost + singleTab
                    + name + singleTab
                    + attributeAffected);
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    public static void printSpellList(List<Spell> spells) {
        System.out.println("--------------------------------------- Spell ---------------------------------------");
        System.out.println("No." + singleTab + "Lv" + singleTab +
                "Damage" + singleTab + "Mana" + singleTab +
                "Price" + singleTab + "Used" + singleTab +
                "Max_Use" + singleTab + "Type" + doubleTab + "Name");

        for (int i = 0; i < spells.size(); i++) {
            Spell spell = spells.get(i);
            int num = i + 1;
            int level = spell.getLevel();
            int damage = spell.getDamageValue();
            int cost = spell.getPrice();
            int mana = spell.getManaCost();
            int useTime = spell.getUseTime();
            int useTimeLimit = spell.getUseTimeLimit();
            String name = spell.getName();
            String spellType = "";
            String typeIndent = singleTab;
            SpellType type = spell.getSpellType();
            if (type == SpellType.FIRE) {
                spellType = "FIRE";
                typeIndent = doubleTab;
            }
            else if (type == SpellType.ICE) {
                spellType = "ICE";
                typeIndent = doubleTab;
            }
            else {
                spellType = "LIGHTNING";
                typeIndent = singleTab;
            }

            System.out.println(num + singleTab
                    + level + singleTab
                    + damage + singleTab
                    + mana + singleTab
                    + cost + singleTab
                    + useTime + singleTab
                    + useTimeLimit + singleTab
                    + spellType + typeIndent
                    + name);
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static void printTeamStatus(HeroTeam heroTeam) {
        printBanner("Team Status");
        printBoldText("No." + singleTab + "HP" + singleTab +
                "Mana" + singleTab + "Strength" + singleTab +
                "Agility" + singleTab + "Dexterity" + singleTab +
                "Gold" + singleTab + "Lv" + singleTab +
                "EXP" + singleTab + "Role" + singleTab + "Alive" + singleTab + "Name");

        for (int i = 0; i < heroTeam.getSize(); i++) {
            Hero hero = heroTeam.getHero(i);
            int level = hero.getLevel();
            String name = hero.getName();
            HeroRole role = hero.getRole();
            int HP = hero.getHP();
            int mana = hero.getMP();
            int strength = hero.getStrength();
            int agility = hero.getAgility();
            int dexterity = hero.getDexterity();
            int gold = hero.getGold();
            int exp = hero.getExp();
            String faint = hero.isFaint() ? "-" : "+";


            System.out.println((i + 1) + singleTab
                    + HP + singleTab
                    + mana + singleTab
                    + strength + doubleTab
                    + agility + singleTab
                    + dexterity + doubleTab
                    + gold + singleTab
                    + level + singleTab
                    + exp + singleTab
                    + role + (role == HeroRole.SORCERER ? noTab : singleTab)
                    + faint + singleTab + name);
        }
        System.out.println("('+' : not faint; '-' : faint)");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
    }

    public static void printMonstersStatus(MonsterTeam monsterList) {
        List<Monster> monsters = monsterList.getMonsterList();

        printBanner("Monster Status");
        printBoldText("No." + singleTab + "HP" + singleTab + "Damage" +
                singleTab + "Defense" + singleTab +
                "Dodge" + singleTab + "Lv" +
                singleTab + "Row" + singleTab +
                "Col" + singleTab + "Type" + doubleTab + "Name");

        for (int i = 0; i < monsters.size(); i++) {
            Monster monster = monsters.get(i);
            int level = monster.getLevel();
            String name = monster.getName();
            MonsterType kind = monster.getKind();
            int HP = monster.getHP();
            int damage = monster.getBaseDamage();
            int dodge = monster.getDodge();
            int defense = monster.getDefense();
            Position position = monster.getPosition();

            System.out.println((i + 1) + singleTab
                    + HP + (HP > 999 ? noTab : singleTab)
                    + damage + (damage > 999 ? noTab : singleTab)
                    + defense + (defense > 999 ? noTab : singleTab)
                    + dodge + "%" + singleTab
                    + level + singleTab
                    + (position.getRow() + 1) + singleTab
                    + (position.getCol() + 1) + singleTab
                    + kind + (kind == MonsterType.EXOSKELETON ? singleTab : doubleTab)
                    + name);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
    }

    public static void printAllInfo(HeroTeam team, MonsterTeam monsters) {
        printTeamStatus(team);
        printMonstersStatus(monsters);
    }

    public static void printHeroInfo(Hero hero) {
        System.out.println("----------------------------------------");
        System.out.println(" HP" + singleTab + "Mana" + singleTab +
                "Lv" + singleTab + "EXP" + singleTab + "Role");

        int level = hero.getLevel();
        String name = hero.getName();
        HeroRole role = hero.getRole();
        int HP = hero.getHP();
        int mana = hero.getMP();
        int exp = hero.getExp();

        System.out.println(" " + HP + singleTab
                + mana + singleTab
                + level + singleTab
                + exp + singleTab
                + role + (role == HeroRole.SORCERER ? noTab : singleTab));
        System.out.println("----------------------------------------");
    }

    public static void printHeroPosition(String name, Position position) {
        int col = position.getCol();
        String lane = "";
        if (col <= 1) {
            lane += "Top";
        }
        else if (col <= 4) {
            lane += "Mid";
        }
        else {
            lane += "Top";
        }

        System.out.println("- Hero " + name + " is currently at " + lane + " lane. ");
        System.out.println("- Row " + (position.getRow() + 1));
        System.out.println("- Col " + (position.getCol() + 1));
    }

    public static void printHeroFaint(String name) {
        System.out.println("- Hero " + name + " has fainted, therefore cannot do any action!");
        System.out.println();
    }

    public static void printMonsterFaint(String name) {
        System.out.println("- Monster " + name + " has fainted, therefore removed from game!");
        System.out.println();
    }

    public static void printHeroAttackMonsterByWeapon(String heroName, String monsterName, int damage) {
        printColorText("LOG: Hero " + heroName + " attacked monster " + monsterName
                + " for " + damage + " damage by weapon!", greenText);
        System.out.println();
    }

    public static void printHeroAttackMonsterBySpell(String heroName, String monsterName, String spellName, int damage) {
        printColorText("LOG: Hero " + heroName + " attacked monster " + monsterName
                + " for " + damage + " damage by spell " + spellName + "!", greenText);
        System.out.println();
    }

    public static void printMonsterIsEffectedBySpell(String monsterName, String type, String attribute, int amount) {
        printColorText("LOG: Monster " + monsterName + " is affected by " + type + " spell!", greenText);
        printColorText("LOG: Monster " + monsterName + "'s " + attribute + " is lower by " + amount + "!", greenText);
        System.out.println();
    }

    public static void printMonsterDodgeAttack(String name) {
        printColorText("LOG: Monster " + name + " has dodged attack!", greenText);
        System.out.println();
    }

    public static void printMonsterAttackHero(String monsterName, String heroName, int damage) {
        printColorText("LOG: Monster " + monsterName + " attacked hero " + heroName
                + " for " + damage + " damage!", greenText);
        System.out.println();
    }

    public static void printHeroDodgeAttack(String name) {
        printColorText("LOG: Hero " + name + "has dodged attack!", greenText);
        System.out.println();
    }

    public static void printHeroRegain(String name, int HP, int MP) {
        if (HP != -1) {
            System.out.println("- Hero " + name + " has regained " + HP + "HP!");
            System.out.println();
        }
        if (MP != -1) {
            System.out.println("- Hero " + name + " has regained " + MP + "MP!");
            System.out.println();
        }
    }

    public static void printGameWin() {
        printColorBoldText("- Congrats! You have reached monster nexus!!!", greenText);
    }

    public static void printGameLose() {
        printColorBoldText("- Monster has reached your nexus, you lose!!!", redText);
    }

    public static void printHeroRevive(String name) {
        System.out.println("- Hero " + name + " has revived at nexus, and restores full MP & HP!");
        System.out.println();
    }

    public static void printHeroGainEXP(String name, int EXP) {
        System.out.println("- Hero " + name + " has gained " + EXP + " EXP!");
        System.out.println();
    }

    public static void printHeroLevelUp(String name, int level) {
        System.out.println(">>> Hero " + name + " LEVELS UP!!!");
    }

    public static void printHeroAttributeGrow(String attribute, int amount, boolean favored) {
        System.out.println("    > " + attribute + (favored ? " ++" : " +") + amount);
    }

    public static void printHeroGainGold(String name, int gold) {
        System.out.println("- Hero " + name + " has gained " + gold + " gold!");
    }

    public static void printNoSuchItemError(String itemName) {
        printColorText("! INVALID, hero does not have any " + itemName + " in inventory. Please try again!", redText);
        System.out.println();
    }

    public static void printNoEnoughMana() {
        System.out.println("! INVALID, hero does not have enough mana. Please try again!");
        System.out.println();
    }

    public static void printWeaponSuccessEquip(String name) {
        System.out.println("- Weapon " + name + " is successfully equipped!");
        System.out.println();
    }

    public static void printArmorSuccessEquip(String name) {
        System.out.println("- Armor " + name + " is successfully equipped!");
        System.out.println();
    }

    public static void printPotionSuccessUse(String name, int amount, String[] attributes) {
        System.out.println("- Potion " + name + " has been consumed!");
        if (attributes[0].equals("All")) {
            System.out.println("    * Attribute Health is increased by " + amount);
            System.out.println("    * Attribute Mana is increased by " + amount);
            System.out.println("    * Attribute Strength is increased by " + amount);
            System.out.println("    * Attribute Agility is increased by " + amount);
            System.out.println("    * Attribute Dexterity is increased by " + amount);
        }
        else {
            for (String attribute : attributes) {
                System.out.println("    * Attribute " + attribute + " is increased by " + amount);
            }
        }
        System.out.println();
    }

    public static void printLoading(String action) {
        System.out.println("- " + action + "...");
        setWaitTime(1000);
        System.out.println();
    }

    public static void printUseMP(String heroName, String spellName,  int amount) {
        printColorText("LOG: Hero " + heroName + " uses " + amount + " mana point to cast spell " + spellName + "!",
                greenText);
        System.out.println();
    }

    public static void printSpellReachUseLimit(String name) {
        printColorText("LOG: Spell " + name + " has reached its use time limit, and therefore is destroyed!", greenText);
        System.out.println();
    }

    public static void printWelcomeMarket() {
        System.out.println("- Welcome to market!");
        System.out.println();
    }

    public static void printNotAtMarketError() {
        printColorText("! INVALID, you cannot enter market, because you are not at market space.", redText);
        System.out.println();
    }

    public static void printAtMarketHint() {
        System.out.println("- You are currently at a market space, you can enter the market by M/m.");
    }

    public static void printAtSpecialSpaceHint(SpaceType type) {
        switch (type) {
            case BUSH:
                System.out.println("- You are currently at a bush space, dexterity has increased by 10%.");
                break;
            case CAVE:
                System.out.println("- You are currently at a cave space, agility has increased by 10%.");
                break;
            case KOULOU:
                System.out.println("- You are currently at a koulou space, strength has increased by 10%.");
                break;
        }
    }

    public static void printItemType() {
        System.out.println("--- Item Types ---");
        System.out.println("|   1. Weapon    |");
        System.out.println("|   2. Armor     |");
        System.out.println("|   3. Spell     |");
        System.out.println("|   4. Potion    |");
        System.out.println("------------------");
        System.out.println();
    }

    public static void printPurchaseItem(String heroName, String type, String itemName, int amount) {
        printColorText("LOG: Hero " + heroName + " purchased " + type + " " + itemName + " using " + amount + " golds!",
                greenText);
        System.out.println();
    }

    public static void printPurchaseItemWithNum(String heroName, String type, String itemName, int amount, int num) {
        printColorText("LOG: Hero " + heroName + " purchased " + num + " " + type + " "
                + itemName + " using " + amount * num + " golds!", greenText);
        System.out.println();
    }

    public static void printSellItem(String heroName, String type, int amount) {
        printColorText("LOG: Hero " + heroName + " sold " + type + " by " + amount + " golds!", greenText);
        System.out.println();
    }

    public static void printNoEnoughGold(String heroName) {
        printColorText("! INVALID: purchase failed, because hero " + heroName + " does not have enough gold!", redText);
        System.out.println();
    }

    public static void printNoEnoughLevel(String heroName) {
        printColorText("! INVALID: purchase failed, because hero " + heroName
                + " does not have enough level to purchase this item!", redText);
        System.out.println();
    }

    public static void printNoItemInMarketError(String keyword) {
        printColorText("! INVALID: market has no " + keyword + "!", redText);
        System.out.println();
    }

    public static void printNothingToSell(String heroName, String keyword) {
        printColorText("! INVALID: hero " + heroName + " has nothing in " + keyword + " to sell!", redText);
        System.out.println();
    }

    public static void printUsedSpellError() {
        printColorText("! INVALID: sell failed, because spell is already used!", redText);
        System.out.println();
    }

    public static void printSellPriceHint() {
        System.out.println("* Sell price is the half of the price shown.");
        System.out.println();
    }

    public static void printGoldLeft(String name, int amount) {
        System.out.println("- Hero " + name + " has " + amount + " golds.");
        System.out.println();
    }

    public static void printHeroLevel(String name, int level) {
        System.out.println("- Hero " + name + " is level " + level + ".");
    }

    public static void printNoMonsterInRangeError() {
        printColorText("! INVALID, no monster in range to attack.", redText);
        System.out.println();
    }

    public static void printNoEnemyAliveInRange() {
        System.out.println("- No hero in range to attack or hero in range is fainted.");
        System.out.println();
    }

    public static void printMonsterMoveDown(String name) {
        printColorText("LOG: Monster " + name + " moves down by one unit.", greenText);
        System.out.println();
    }

    public static void printAttackOptions() {
        System.out.println("1. Weapon");
        System.out.println("2. Spell");
    }

    public static void printMonsterSpawn() {
        printColorBoldText("!!! New monsters spawn !!!", redText);
        System.out.println();
    }

    public static void printMarketLevel(int level) {
        System.out.println("- Current market level: " + level);
        System.out.println();
    }

    public static void printMarketHasUpdated(int level) {
        System.out.println("- Market level has increased to " + level + ", and products are updated!");
        System.out.println();
    }

    public static void printHeroGold(String name, int gold) {
        System.out.println("- Hero " + name + " has " + gold + " golds.");
    }

}
