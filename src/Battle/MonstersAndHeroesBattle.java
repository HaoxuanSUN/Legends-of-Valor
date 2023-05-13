package Battle;

import Config.CalculateHandler;
import Config.InputHandler;
import Config.MonstersAndHeroesRule;
import Config.Printer;
import Character.GameCharacter;
import Character.Monster;
import Character.Hero;
import Item.Spell;
import Team.HeroTeam;
import Team.MonsterTeam;

import java.util.ArrayList;
import java.util.List;

// This class extends BattleHandler class and implements
// abstract methods, it is used to control a battle flow
// of Monsters and Heroes.
public class MonstersAndHeroesBattle<T extends GameCharacter, M extends GameCharacter> extends BattleHandler<T, M> {

    private final HeroTeam heroTeam;

    private MonsterTeam monsterTeam;

    private Hero attacker;

    private MonsterTeam monstersInRange;

    private Monster chosenMonster;

    public MonstersAndHeroesBattle(HeroTeam heroTeam, MonsterTeam monsterTeam) {
        this.heroTeam = heroTeam;
        this.monsterTeam = monsterTeam;
    }

    // used to detect a battle and init if necessary
    @Override
    public boolean detectBattle(T character) {
        Hero hero = (Hero) character;
        List<Monster> temp = new ArrayList<Monster>();

        for (Monster monster : monsterTeam.getMonsterList()) {
            if (hero.inRange(monster.getPosition())) {
                temp.add(monster);
            }
        }

        if (temp.size() == 0) {
            Printer.printNoMonsterInRangeError();
            return false;
        }

        // if detect monster in range, start battle
        attacker = hero;
        monstersInRange = new MonsterTeam();
        monstersInRange.addAll(temp);

        battleStart();
        return true;
    }

    // do init work of a battle
    @Override
    public void battleInit() {
    }

    // start the battle
    @Override
    public void battleStart() {
        // if hero has spell, give option for attack, otherwise use weapon
        int option = 0;
        if (attacker.getInventory().getSpellNum() > 0) {
            Printer.printAttackOptions();
            option = InputHandler.getIndexNumber("attack the monster", "type of attack", 2);
        }

        if (option == 0) { // weapon attack
            handleHeroWeaponAttack(attacker);
        }
        else { // spell attack
            Spell chosenSpell = attacker.handleSpellCast();
            if (chosenSpell != null) {
                handleHeroSpellAttack(attacker, chosenSpell);
            }
        }

        battleEnd();
    }

    // helper function to handle hero attack monster using weapon
    private void handleHeroWeaponAttack(Hero hero) {
        chosenMonster = monstersInRange.getMonster(0);

        if (monstersInRange.getSize() > 1) {
            int monsterIdx = InputHandler.getAttackedMonster(monstersInRange);
            chosenMonster = monstersInRange.getMonster(monsterIdx);
        }

        attack((T)hero, (M)chosenMonster);
    }

    // helper function to handle hero attack monster using spell
    private void handleHeroSpellAttack(Hero hero, Spell spell) {
        chosenMonster = monstersInRange.getMonster(0);

        if (monstersInRange.getSize() > 1) {
            int monsterIdx = InputHandler.getAttackedMonster(monstersInRange);
            chosenMonster = monstersInRange.getMonster(monsterIdx);
        }

        heroAttackMonsterBySpell(hero, chosenMonster, spell);
    }

    // end a battle, do some cleanup work
    @Override
    public void battleEnd() {
        if (chosenMonster.isFaint()) {
            Printer.printMonsterFaint(chosenMonster.getName());

            for (Hero hero : heroTeam.getTeam()) {
                int gainedGold = CalculateHandler.calGoldGain(chosenMonster.getLevel());
                Printer.printHeroGainGold(hero.getName(), gainedGold);
                hero.gainGold(gainedGold);

                int gainedEXP = CalculateHandler.calEXPGain(chosenMonster.getLevel());
                Printer.printHeroGainEXP(hero.getName(), gainedEXP);
                hero.gainEXP(gainedEXP);
            }

            monsterTeam.removeMonster(chosenMonster);
        }
    }

    // implement interface method
    @Override
    public void attack(T attacker, M target) {
        if (attacker instanceof Hero) {
            heroAttackMonsterByWeapon((Hero)attacker, (Monster)target);
        }
        else {
            monsterAttackHero((Monster) attacker, (Hero) target);
        }
    }

    // concrete function to handle hero attack by weapon
    private void heroAttackMonsterByWeapon(Hero hero, Monster monster) {
        int damage = hero.calAttackWithWeaponDamage();
        int monsterDamageTaken = monster.getAttacked(damage);

        if (monsterDamageTaken == -1) {
            Printer.printMonsterDodgeAttack(monster.getName());
        }
        else {
            Printer.printHeroAttackMonsterByWeapon(hero.getName(), monster.getName(), monsterDamageTaken);
        }
    }

    // concrete function to handle hero attack by spell
    private void heroAttackMonsterBySpell(Hero hero, Monster monster, Spell spell) {
        Printer.printUseMP(hero.getName(), spell.getName(), spell.getManaCost());
        if (spell.getUseTime() >= spell.getUseTimeLimit()) Printer.printSpellReachUseLimit(spell.getName());

        int damage = hero.calAttackWithSpellDamage(spell);
        int monsterDamageTaken = monster.getAttacked(damage);

        if (monsterDamageTaken == -1) {
            Printer.printMonsterDodgeAttack(monster.getName());
        }
        else {
            Printer.printHeroAttackMonsterBySpell(hero.getName(), monster.getName(), spell.getName(), monsterDamageTaken);
            monster.getEffectedBySpell(spell.getSpellType());
        }
    }

    // helper function to handle monster attack hero
    private void monsterAttackHero(Monster monster, Hero hero) {
        int damage = monster.getBaseDamage();
        int heroDamageTaken = hero.getAttacked(damage);

        if (heroDamageTaken == -1) {
            Printer.printHeroDodgeAttack(hero.getName());
        }
        else {
            Printer.printMonsterAttackHero(monster.getName(), hero.getName(), heroDamageTaken);
        }
    }
}
