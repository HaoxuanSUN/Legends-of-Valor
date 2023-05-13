package Config;

import Character.Hero;
import Item.Armor;
import Item.Weapon;
import Team.HeroTeam;

// This class handles any calculation needed during the game process
public class CalculateHandler {
    // calculate the spell damage based on dexterity
    public static int calSpellDamage(int spellBaseDamage, int dexterity) {
        return spellBaseDamage + (int)((dexterity / 10000) * spellBaseDamage);
    }

    // calculate hp based on damage
    public static int calHP(int level) {
        return level * 100;
    }

    // calculate damage of hero using weapon based on strength and weapon damage
    public static int calHeroAttackDamageWithWeapon(int strength, Weapon weapon) {
        return weapon == null ? (int)(strength * 0.05) : (int)((strength + weapon.getDamage()) * 0.05);
    }

    // calculate hero dodge chance based on agility
    public static double calHeroDodgeChance(int agility) {
        return agility * 0.002;
    }

    // calculate monster damage taken based on monster defense
    public static int calMonsterDamageTaken(int defense, int damage) {
        return Math.max(0, damage - defense / 10);
    }

    // calculate hero damage taken based on armor equipped
    public static int calHeroDamageTaken(Armor armor, int damage) {
        return armor == null ? damage : Math.max(0, damage - armor.getDamageReduction());
    }

    // calculate attribute grow amount when level up
    public static int calLevelUpSkillAmount(int skill, boolean favored) {
        return favored ? (int)(skill * 0.1) : (int)(skill * 0.05);
    }

    // calculate exp point needed for level up based on current level
    public static int calLevelUpEXP(int level) {
        return level * 10;
    }

    // calculate HP and MP regain after battle round
    public static int calHpMpRegainAfterBattle(int HPorMP) {
        return (int) (HPorMP * 1.1);
    }

    // calculate gold gain after battle based on level
    public static int calGoldGain(int level) {
        return level * 500;
    }

    // calculate exp point gain after combat based on monster number
    public static int calEXPGain(int level) {
        return level * 2;
    }

    // calculate amount of monster's attribute affected by spell
    public static int calSpellEffectOnMonster(int affectedSkillAmount) {
        return (int)(affectedSkillAmount * 0.1);
    }

    // calculate item sell price
    public static int calItemSellPrice(int itemPrice) {
        return (int)(itemPrice * 0.5);
    }

    // calculate current highest level in hero team
    public static int calTeamHighestLevel(HeroTeam heroTeam) {
        int max = 0;

        for (Hero hero : heroTeam.getTeam()) {
            max = Math.max(max, hero.getLevel());
        }

        return max;
    }

    public static int calSpaceBuffAmount(int base) {
        return (int) (base * 1.1);
    }

    public static boolean calEnemySpawn(int round) {
        return round != 0 && round % 8 == 0;
    }
}
