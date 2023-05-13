package Market;

import Config.Printer;
import Config.Stats;
import Item.*;
import World.World;

import java.util.ArrayList;
import java.util.List;

// This class handles market actions in game
public class Market {
    private List<Weapon> weaponsForSale;

    private List<Armor> armorsForSale;

    private List<Potion> potionsForSale;

    private List<Spell> spellsForSale;

    private int highestLevel;

    public Market(int level) {
        weaponsForSale = new ArrayList<>();
        armorsForSale = new ArrayList<>();
        potionsForSale = new ArrayList<>();
        spellsForSale = new ArrayList<>();
        highestLevel = level;
        generateProducts();
    }

    // getter
    public List<Weapon> getWeaponsForSale() {
        return weaponsForSale;
    }

    // getter
    public List<Armor> getArmorsForSale() {
        return armorsForSale;
    }

    // getter
    public List<Potion> getPotionsForSale() {
        return potionsForSale;
    }

    // getter
    public List<Spell> getSpellsForSale() {
        return spellsForSale;
    }

    // generate products that are less or equal to the highest level hero in team
    public void generateProducts() {
        weaponsForSale = Stats.getWeaponListByLevel(highestLevel);
        armorsForSale = Stats.getArmorListByLevel(highestLevel);
        potionsForSale = Stats.getPotionListByLevel(highestLevel);
        spellsForSale = Stats.getSpellListByLevel(highestLevel);
    }

    // print store products
    public void printStore() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> MARKET <<<<<<<<<<<<<<<<<<<<<<<<<");
        Printer.printWeaponList(weaponsForSale);
        Printer.printArmorList(armorsForSale);
        Printer.printPotionList(potionsForSale);
        Printer.printSpellList(spellsForSale);
    }

    // handle purchase action, return the item
    public Item handlePurchase(ItemType type, int index) {
        switch(type) {
            case ARMOR:
                return armorsForSale.remove(index);
            case WEAPON:
                return weaponsForSale.remove(index);
            case SPELL:
                Spell selectedSpell = spellsForSale.remove(index);
                return selectedSpell.makeCopy();
            case POTION:
                Potion selectedPotion = potionsForSale.get(index);
                return selectedPotion.makeCopy();
        }

        return null;
    }

    // handle sell action, add item to product list
    public void addItem(Item item) {
        if (item instanceof Weapon) {
            if (!weaponsForSale.contains(item)) weaponsForSale.add((Weapon) item);
        }
        else if (item instanceof Armor) {
            if (!armorsForSale.contains(item)) armorsForSale.add((Armor) item);
        }
        else if (item instanceof Spell) {
            if (!spellsForSale.contains(item)) spellsForSale.add((Spell) item);
        }
    }

    // check whether need update based on highest level
    public boolean checkUpdate(int level) {
        if (level > highestLevel) {
            highestLevel = level;
            generateProducts();
            return true;
        }

        return false;
    }

    public int getMarketLevel() {
        return highestLevel;
    }
}
