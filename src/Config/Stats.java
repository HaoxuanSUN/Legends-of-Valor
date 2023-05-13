package Config;

import Character.Hero;
import Character.HeroRole;
import Character.Monster;
import Character.MonsterType;
import Item.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// This class read data from Database and parse them to the data for other classes to read
public class Stats {
    private static Scanner scanner;

    private static Database database = new Database();

    private static List<Hero> availableWarriors;

    private static List<Hero> availableSorcerers;

    private static List<Hero> availablePaladins;

    private static List<Monster> monsterList;

    private static List<Weapon> weaponList;

    private static List<Armor> armorList;

    private static List<Potion> potionList;

    private static List<Spell> spellList;

    // main function to read all data from Database
    public static void readAllData() {
        readHeroData();
        readMonsterData();
        readWeaponData();
        readArmorData();
        readPotionData();
        readSpellData();
    }

    // process the raw string to remove unnecessary spaces
    private static String removeSpaces(String input) {
        char[] array = input.toCharArray();
        int end = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ' ' || array[i] == '\t') {
                if (i != 0 && array[i - 1] != ' ' && array[i - 1] != '\t') {
                    array[end++] = array[i];
                }
            }
            else {
                array[end++] = array[i];
            }
        }

        if (end != 0 && array[end - 1] == ' ') end--;
        return new String(array, 0, end);
    }

    // helper function to read hero string data and make it Hero object
    private static void readHeroData(){
        availableWarriors = new ArrayList<>();
        availableSorcerers = new ArrayList<>();
        availablePaladins = new ArrayList<>();

        for (HeroRole role : HeroRole.values()){
            String[] data;
            if (role == HeroRole.WARRIOR) {
                data = database.getWarriorData();
            }
            else if (role == HeroRole.SORCERER) {
                data = database.getSorcererData();
            }
            else {
                data = database.getPaladinData();
            }

            for (String str : data) {
                String[] row = removeSpaces(str).split(" ");
                if (row.length < 7) break;
                String name = row[0];
                int mana = Integer.parseInt(row[1]);
                int strength = Integer.parseInt(row[2]);
                int agility = Integer.parseInt(row[3]);
                int dexterity = Integer.parseInt(row[4]);
                int gold = Integer.parseInt(row[5]);
                int exp = Integer.parseInt(row[6]);
                if (role == HeroRole.WARRIOR) {
                    availableWarriors.add(new Hero(name, role, mana,
                            strength, agility, dexterity, gold, exp));
                }
                else if (role == HeroRole.SORCERER) {
                    availableSorcerers.add(new Hero(name, role, mana,
                            strength, agility, dexterity, gold, exp));
                }
                else {
                    availablePaladins.add(new Hero(name, role, mana,
                            strength, agility, dexterity, gold, exp));
                }
            }
        }
    }

    // helper function to read monster string data and make it Monster object
    private static void readMonsterData() {
        monsterList = new ArrayList<>();

        for (MonsterType type : MonsterType.values()){
            String[] data;
            if (type == MonsterType.DRAGON) {
                data = database.getDragonData();
            }
            else if (type == MonsterType.EXOSKELETON) {
                data = database.getExoskeletonData();
            }
            else {
                data = database.getSpiritData();
            }

            for (String str : data) {
                String[] row = removeSpaces(str).split(" ");
                if (row.length < 5) break;
                String name = row[0];
                int level = Integer.parseInt(row[1]);
                int damage = Integer.parseInt(row[2]);
                int defense = Integer.parseInt(row[3]);
                int dodgeChance = Integer.parseInt(row[4]);

                monsterList.add(new Monster(type, name, level, damage, defense, dodgeChance));
            }
        }
    }

    // helper function to read weapon string data and make it Weapon object
    private static void readWeaponData() {
        weaponList = new ArrayList<>();

        String[] data = database.getWeaponData();

        for (String str : data) {
            String[] row = removeSpaces(str).split(" ");
            if (row.length < 5) break;
            String name = row[0];
            int cost = Integer.parseInt(row[1]);
            int level = Integer.parseInt(row[2]);
            int damage = Integer.parseInt(row[3]);
            int hands = Integer.parseInt(row[4]);

            weaponList.add(new Weapon(name, cost, level, damage, hands));
        }
    }

    // helper function to read armor string data and make it Armor object
    private static void readArmorData() {
        armorList = new ArrayList<>();

        String[] data = database.getArmorData();
        for (String str : data) {
            String[] row = removeSpaces(str).split(" ");
            if (row.length < 4) break;
            String name = row[0];
            int cost = Integer.parseInt(row[1]);
            int level = Integer.parseInt(row[2]);
            int damageReduction = Integer.parseInt(row[3]);

            armorList.add(new Armor(name, cost, level, damageReduction));
        }
    }

    // helper function to read potion string data and make it Potion object
    private static void readPotionData() {
        potionList = new ArrayList<>();

        String[] data = database.getPotionData();
        for (String str : data) {
            String[] row = removeSpaces(str).split(" ");
            if (row.length < 5) break;
            String name = row[0];
            int cost = Integer.parseInt(row[1]);
            int level = Integer.parseInt(row[2]);
            int increaseAmount = Integer.parseInt(row[3]);
            String attributeAffected = row[4];

            potionList.add(new Potion(name, cost, level, increaseAmount, attributeAffected));
        }
    }

    // helper function to read spell string data and make it Spell object
    private static void readSpellData() {
        spellList = new ArrayList<>();

        for (SpellType type : SpellType.values()){
            String[] data;
            if (type == SpellType.FIRE) {
                data = database.getFireSpellData();
            }
            else if (type == SpellType.ICE) {
                data = database.getIceSpellData();
            }
            else {
                data = database.getLightningSpellData();
            }

            for (String str : data) {
                String[] row = removeSpaces(str).split(" ");
                if (row.length < 5) break;
                String name = row[0];
                int cost = Integer.parseInt(row[1]);
                int level = Integer.parseInt(row[2]);
                int damage = Integer.parseInt(row[3]);
                int mana = Integer.parseInt(row[4]);

                spellList.add(new Spell(name, cost, level, damage, mana, type, damage >= 800 ? 2 : 3));
            }
        }
    }

    // randomly select map from map list
    public static int[][] getRandomWorld() {
        Random random = new Random();
        int r = random.nextInt(database.getMapList().size());
        return database.getMapList().get(r);
    }

    // randomly select hero from hero list
    public static Hero getRandomHero(HeroRole role) {
        Random r = new Random();
        List<Hero> cur;

        if (role == HeroRole.WARRIOR) {
            cur = availableWarriors;
        }
        else if (role == HeroRole.SORCERER) {
            cur = availableSorcerers;
        }
        else { // Paladin
            cur = availablePaladins;
        }

        int size = cur.size();
        int random = r.nextInt(size);

        return cur.remove(random);
    }

    // randomly select monster from monster list
    public static List<Monster> getRandomMonsterList(int level, int size) {
        List<Monster> temp = new ArrayList<>();
        List<Monster> result = new ArrayList<>();

        for (Monster monster : monsterList) {
            if (monster.getLevel() == level) {
                temp.add(monster);
            }
        }

        Random r = new Random();

        for (int i = 0; i < size; i++) {
            int index = r.nextInt(temp.size());
            Monster curMonster = temp.get(index);

            MonsterType type = curMonster.getKind();
            String name = curMonster.getName();
            int damage = curMonster.getBaseDamage();
            int defense = curMonster.getDefense();
            int dodgeChance = curMonster.getDodge();
            Monster newMonster = new Monster(type, name, level, damage, defense, dodgeChance);
            result.add(newMonster);
        }

        return result;
    }

    // select weapons that is less or equal to the level given
    public static List<Weapon> getWeaponListByLevel(int level) {
        List<Weapon> result = new ArrayList<>();

        for (Weapon weapon : weaponList) {
            if (weapon.getLevel() <= level) {
                result.add(weapon);
            }
        }

        return result;
    }

    // select armors that is less or equal to the level given
    public static List<Armor> getArmorListByLevel(int level) {
        List<Armor> result = new ArrayList<>();

        for (Armor armor : armorList) {
            if (armor.getLevel() <= level) {
                result.add(armor);
            }
        }

        return result;
    }

    // select potions that is less or equal to the level given
    public static List<Potion> getPotionListByLevel(int level) {
        List<Potion> result = new ArrayList<>();

        for (Potion potion : potionList) {
            if (potion.getLevel() <= level) {
                result.add(potion);
            }
        }

        return result;
    }

    // select spells that is less or equal to the level given
    public static List<Spell> getSpellListByLevel(int level) {
        List<Spell> result = new ArrayList<>();

        for (Spell spell : spellList) {
            if (spell.getLevel() <= level) {
                result.add(spell);
            }
        }

        return result;
    }
}
