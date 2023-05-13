package Inventory;

import Item.*;

import java.util.ArrayList;
import java.util.List;

// This class take cares of everything related to inventory, Hero has Inventory
public class Inventory {
    private final List<Weapon> weapons;

    private final List<Armor> armors;

    private final List<Potion> potions;

    private final List<Spell> spells;

    private int size;

    public Inventory() {
        weapons = new ArrayList<>();
        armors = new ArrayList<>();
        potions = new ArrayList<>();
        spells = new ArrayList<>();
        size = 0;
    }

    // getter
    public List<Weapon> getWeapons() {
        return weapons;
    }

    // getter
    public List<Armor> getArmors() {
        return armors;
    }

    // getter
    public List<Potion> getPotions() {
        return potions;
    }

    // getter
    public List<Spell> getSpells() {
        return spells;
    }

    // getter
    public int getWeaponNum() {
        return weapons.size();
    }

    // getter
    public int getArmorNum() {
        return armors.size();
    }

    // getter
    public int getPotionNum() {
        return potions.size();
    }

    // getter
    public int getSpellNum() {
        return spells.size();
    }

    // get weapon by index
    public Weapon getWeaponByIndex(int index) {
        return weapons.get(index);
    }

    // get armor by index
    public Armor getArmorByIndex(int index) {
        return armors.get(index);
    }

    // get potion by index
    public Potion getPotionByIndex(int index) {
        return potions.get(index);
    }

    // get spell by index
    public Spell getSpellByIndex(int index) {
        return spells.get(index);
    }

    // remove weapon by index
    public Weapon removeWeaponByIndex(int index) {
        return weapons.remove(index);
    }

    // remove armor by index
    public Armor removeArmorByIndex(int index) {
        return armors.remove(index);
    }

    // remove potion by index
    public Potion removePotionByIndex(int index) {
        return potions.remove(index);
    }

    // remove spell by index
    public Spell removeSpellByIndex(int index) {
        return spells.remove(index);
    }

    // getter
    public int getSize() {
        return size;
    }

    // handle add item
    public void addItem(Item item) {
        if (item instanceof Weapon) weapons.add((Weapon) item);
        else if (item instanceof Armor) armors.add((Armor) item);
        else if (item instanceof Potion) potions.add((Potion) item);
        else if (item instanceof Spell) spells.add((Spell) item);

        size++;
    }
}
