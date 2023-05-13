package Character;

import Config.CalculateHandler;
import Config.InputHandler;
import Config.Printer;
import Inventory.Inventory;
import Item.*;
import World.BuffType;
import World.LaneType;
import World.Position;

import java.util.Random;

// This is a sub class extended from GameCharacter, it is a basic hero unit in game
public class Hero extends GameCharacter {
    private final HeroRole role;

    private int MP;

    private int maxHP;

    private int maxMP;

    private int strength;

    private int dexterity;

    private int agility;

    private boolean strengthFavored;

    private boolean agilityFavored;

    private boolean dexterityFavored;

    private int gold;

    private final Level level;

    private final Inventory inventory;

    private Weapon weaponSlot;

    private Armor armorSlot;

    private LaneType laneType;

    private Position spawnPos;

    private Buff buff;

    private int amountBeforeBuff;

    public Hero(String name, HeroRole role) {
        super(CharacterType.HERO, name);
        this.role = role;
        inventory = new Inventory();
        level = new Level(1, 0);
    }

    public Hero(String name, HeroRole role,
                int mana, int strength, int agility,
                int dexterity, int gold, int exp) {
        this(name, role);
        this.MP = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.strengthFavored = role != HeroRole.SORCERER;
        this.agilityFavored = role != HeroRole.PALADIN;
        this.dexterityFavored = role != HeroRole.WARRIOR;
        this.gold = gold;
        level.setEXP(exp);
        this.setHP(CalculateHandler.calHP(level.getLevel()));
        this.maxHP = this.getHP();
        this.maxMP = mana;
        this.buff = new Buff();
        this.amountBeforeBuff = 0;
    }

    // getter
    public HeroRole getRole() {
        return role;
    }

    public LaneType getLane() {
        return laneType;
    }

    public void setLane(LaneType laneType) {
        this.laneType = laneType;

        switch (laneType) {
            case TOP:
                this.spawnPos = new Position(7, 0);
                break;
            case MID:
                this.spawnPos = new Position(7, 3);
                break;
            case BOT:
                this.spawnPos = new Position(7, 6);
                break;
        }
    }

    public Position getSpawnPos() {
        return this.spawnPos;
    }

    public void recall() {
        this.setPosition(this.spawnPos.getRow(), spawnPos.getCol());
    }

    public boolean isAdjacent(Position position) {
        return this.getPosition().isAdjacent(position);
    }

    // getter
    public int getExp() {
        return level.getEXP();
    }

    // getter
    public int getMP() {
        return MP;
    }

    // setter
    public void setMP(int MP) {
        this.MP = MP;
    }

    // getter
    public int getStrength() {
        return strength;
    }

    // getter
    public int getDexterity() {
        return dexterity;
    }

    // getter
    public int getAgility() {
        return agility;
    }

    // getter
    public int getGold() {
        return gold;
    }

    // setter
    public void setGold(int gold) {
        this.gold = gold;
    }

    // getter
    public int getLevel() {
        return level.getLevel();
    }

    // getter
    public Inventory getInventory() {
        return inventory;
    }

    // getter
    public Weapon getWeaponSlot() {
        return weaponSlot;
    }

    // getter
    public Armor getArmorSlot() {
        return armorSlot;
    }

    // implement abstract method from parent class
    @Override
    public int getAttacked(int damageIncoming) {
        if (hasDodged()) {
            return -1;
        }
        else {
            int damageTaken = CalculateHandler.calHeroDamageTaken(armorSlot, damageIncoming);
            this.takeDamage(damageTaken);
            return damageTaken;
        }
    }

    // calculate if hero can dodge this attack
    private boolean hasDodged() {
        double dodgeChance = CalculateHandler.calHeroDodgeChance(this.getAgility());
        Random r = new Random();
        double randomDouble = r.nextDouble() * 10;

        return randomDouble <= dodgeChance;
    }

    // calculate damage with weapon
    public int calAttackWithWeaponDamage() {
        return CalculateHandler.calHeroAttackDamageWithWeapon(this.strength, this.weaponSlot);
    }

    // calculate damage with spell
    public int calAttackWithSpellDamage(Spell spell) {
        return CalculateHandler.calSpellDamage(spell.getDamageValue(), dexterity);
    }

    // handle regain HP at checkout round stage
    public int regainHP() {
        if (this.getHP() == maxHP) {
            return -1;
        }
        else {
            int curHP = this.getHP();
            int newHP = CalculateHandler.calHpMpRegainAfterBattle(this.getHP());
            this.setHP(Math.min(newHP, maxHP));
            return Math.min(newHP, maxHP) - curHP;
        }
    }

    // handle regain MP at checkout round stage
    public int regainMP() {
        if (this.getMP() == maxMP) {
            return -1;
        }
        else {
            int curMP = this.getMP();
            int newMP = CalculateHandler.calHpMpRegainAfterBattle(this.getMP());
            this.setMP(Math.min(newMP, maxMP));
            return Math.min(newMP, maxMP) - curMP;
        }
    }

    // handle revive
    public void revive() {
        this.setHP(maxHP);
        this.setMP(maxMP);
        recall();
    }

    // handle gain gold
    public void gainGold(int gold) {
        this.setGold(this.getGold() + gold);
    }

    // handle gain exp point
    public void gainEXP(int EXP) {
        boolean levelUp = this.level.gainEXP(EXP);
        if (levelUp) handleLevelUp();
    }

    // handle attribute grow when level up
    private void handleLevelUp() {
        Printer.printHeroLevelUp(this.getName(), this.level.getLevel());

        int changeAmount = 0;

        changeAmount = CalculateHandler.calLevelUpSkillAmount(this.getHP(), false);
        this.setHP(this.getHP() + changeAmount);
        maxHP += changeAmount;
        Printer.printHeroAttributeGrow("HP", changeAmount, false);

        changeAmount = CalculateHandler.calLevelUpSkillAmount(this.getHP(), false);
        MP += changeAmount;
        maxMP += changeAmount;
        Printer.printHeroAttributeGrow("MP", changeAmount, false);

        changeAmount = CalculateHandler.calLevelUpSkillAmount(strength, strengthFavored);
        strength += changeAmount;
        Printer.printHeroAttributeGrow("Strength", changeAmount, strengthFavored);

        changeAmount = CalculateHandler.calLevelUpSkillAmount(agility, agilityFavored);
        agility += changeAmount;
        Printer.printHeroAttributeGrow("Agility", changeAmount, agilityFavored);

        changeAmount = CalculateHandler.calLevelUpSkillAmount(dexterity, dexterityFavored);
        dexterity += changeAmount;
        Printer.printHeroAttributeGrow("Dexterity", changeAmount, dexterityFavored);
    }

    // handle weapon equip
    public boolean handleWeaponEquip() {
        if (inventory.getWeaponNum() == 0) {
            Printer.printNoSuchItemError("weapon");
            return false;
        }

        Printer.printWeaponList(inventory.getWeapons());
        int chosenWeaponIndex = InputHandler.getIndexNumber("equip", "a weapon", inventory.getWeaponNum());
        Weapon chosenWeapon = inventory.removeWeaponByIndex(chosenWeaponIndex);

        if (weaponSlot != null) {
            weaponSlot.setEquipped(false);
            inventory.addItem(weaponSlot);
        }

        chosenWeapon.setEquipped(true);
        weaponSlot = chosenWeapon;

        Printer.printWeaponSuccessEquip(weaponSlot.getName());
        return true;
    }

    // handle armor equip
    public boolean handleArmorEquip() {
        if (inventory.getArmorNum() == 0) {
            Printer.printNoSuchItemError("armor");
            return false;
        }

        Printer.printArmorList(inventory.getArmors());
        int chosenArmorIndex = InputHandler.getIndexNumber("equip", "an armor", inventory.getArmorNum());
        Armor chosenArmor = inventory.removeArmorByIndex(chosenArmorIndex);

        if (armorSlot != null) {
            armorSlot.setEquipped(false);
            inventory.addItem(armorSlot);
        }

        chosenArmor.setEquipped(true);
        armorSlot = chosenArmor;

        Printer.printArmorSuccessEquip(armorSlot.getName());

        return true;
    }

    // handle point use
    public boolean handlePotionUse() {
        if (inventory.getPotionNum() == 0) {
            Printer.printNoSuchItemError("potion");
            return false;
        }

        Printer.printPotionList(inventory.getPotions());
        int chosenPotionIndex = InputHandler.getIndexNumber("use", "a potion", inventory.getPotionNum());
        Potion chosenPotion = inventory.removePotionByIndex(chosenPotionIndex);

        int increaseAmount = chosenPotion.getIncreaseAmount();
        String attributeAffected = chosenPotion.getAttributeAffected();
        String[] attributes = attributeAffected.split("/");

        outerloop:
        for (String attribute : attributes) {
            switch (attribute) {
                case "All":
                    this.setHP(this.getHP() + increaseAmount);
                    maxHP += increaseAmount;
                    MP += increaseAmount;
                    maxMP += increaseAmount;
                    strength += increaseAmount;
                    dexterity += increaseAmount;
                    agility += increaseAmount;
                    break outerloop;
                case "Health":
                    this.setHP(this.getHP() + increaseAmount);
                    maxHP += increaseAmount;
                    break;
                case "Strength":
                    strength += increaseAmount;
                    break;
                case "Mana":
                    MP += increaseAmount;
                    maxMP += increaseAmount;
                    break;
                case "Agility":
                    agility += increaseAmount;
                    break;
                case "Dexterity":
                    dexterity += increaseAmount;
                    break;
            }
        }

        Printer.printPotionSuccessUse(chosenPotion.getName(), increaseAmount, attributes);

        return true;
    }

    // handle spell cast
    public Spell handleSpellCast() {
        if (inventory.getSpellNum() == 0) {
            Printer.printNoSuchItemError("spell");
            return null;
        }

        Printer.printSpellList(inventory.getSpells());
        int chosenSpellIndex = InputHandler.getIndexNumber("cast", "a spell", inventory.getSpellNum());
        Spell chosenSpell = inventory.getSpellByIndex(chosenSpellIndex);

        int manaCost = chosenSpell.getManaCost();
        if (manaCost > this.MP) {
            Printer.printNoEnoughMana();
            return null;
        }

        String name = chosenSpell.getName();
        int useTime = chosenSpell.getUseTime() + 1;
        int useTimeLimit = chosenSpell.getUseTimeLimit();

        this.MP -= manaCost;

        chosenSpell.setUseTime(useTime);
        if (useTime >= useTimeLimit) {
            inventory.removeSpellByIndex(chosenSpellIndex);
        }

        return chosenSpell;
    }

    // handle purchase item
    // 1. reduce gold
    // 2. add item to inventory
    // return boolean to tell caller if hero can successfully buy the item
    public boolean buyItem(Item item) {
        int price = item.getPrice();
        if (price > gold) {
            return false;
        }

        inventory.addItem(item);
        gold -= price;
        return true;
    }

    // handle sell item
    // 1. gain gold
    // 2. remove item from inventory
    // return removed item
    public Item sellItem(ItemType type, int index) {
        switch(type) {
            case WEAPON:
                Item soldItem = inventory.removeWeaponByIndex(index);
                gold += CalculateHandler.calItemSellPrice(soldItem.getPrice());
                return soldItem;
            case ARMOR:
                soldItem = inventory.removeArmorByIndex(index);
                gold += CalculateHandler.calItemSellPrice(soldItem.getPrice());
                return soldItem;
            case SPELL:
                Spell soldSpell = inventory.removeSpellByIndex(index);
                if (soldSpell.getUseTime() > 0) {
                    inventory.addItem(soldSpell);
                    return null;
                }
                return soldSpell;
            case POTION:
                soldItem = inventory.removePotionByIndex(index);
                gold += CalculateHandler.calItemSellPrice(soldItem.getPrice());
                return soldItem;
        }

        return null;
    }

    // handle buff change
    public void handleBuff(BuffType type) {
        removeBuff();
        applyBuff(type);
    }

    // helper function to remove buff when hero leaves the space
    private void removeBuff() {
        BuffType buffedAttribute = buff.getBuffedAttribute();
        if (buffedAttribute == null) return;

        switch (buffedAttribute) {
            case AGILITY:
                agility = amountBeforeBuff;
                break;
            case STRENGTH:
                strength = amountBeforeBuff;
                break;
            case DEXTERITY:
                dexterity = amountBeforeBuff;
                break;
        }

        amountBeforeBuff = 0;
    }

    // helper function to apply current space buff to hero
    private void applyBuff(BuffType type) {
        if (type == null) return;

        buff.setBuffedAttribute(type);
        switch (buff.getBuffedAttribute()) {
            case AGILITY:
                amountBeforeBuff = agility;
                agility = CalculateHandler.calSpaceBuffAmount(agility);
                break;
            case STRENGTH:
                amountBeforeBuff = strength;
                strength = CalculateHandler.calSpaceBuffAmount(strength);
                break;
            case DEXTERITY:
                amountBeforeBuff = dexterity;
                dexterity = CalculateHandler.calSpaceBuffAmount(dexterity);
                break;
        }
    }
}
