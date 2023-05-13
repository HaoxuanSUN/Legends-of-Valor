package Character;

import Action.MoveDirection;
import Battle.BattleHandler;
import Config.CalculateHandler;
import Config.Printer;
import Item.SpellType;
import Team.HeroTeam;
import World.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// This is a sub class extended from GameCharacter, it is a basic monster unit in game
public class Monster extends GameCharacter {
    private final MonsterType kind;

    private int baseDamage;

    private int defense;

    private int dodge;

    private final Level level;

    public Monster(MonsterType type, String name, int lv,
                   int damage, int defense, int dodgeChance) {
        super(CharacterType.MONSTER, name);
        this.kind = type;
        this.baseDamage = damage;
        this.defense = defense;
        this.dodge = dodgeChance;
        level = new Level(lv, 0);
        this.setHP(CalculateHandler.calHP(level.getLevel()));
    }

    // Getter
    public MonsterType getKind() {
        return kind;
    }

    // Getter
    public int getBaseDamage() {
        return baseDamage;
    }

    // Getter
    public int getDefense() {
        return defense;
    }

    // Getter
    public int getDodge() {
        return dodge;
    }

    // Getter
    public int getLevel() {
        return level.getLevel();
    }

    // implement abstract method from parent class
    @Override
    public int getAttacked(int damageIncoming) {
        if (hasDodged()) {
            return -1;
        }
        else {
            int damageTaken = CalculateHandler.calMonsterDamageTaken(defense, damageIncoming);
            this.takeDamage(damageTaken);
            return damageTaken;
        }
    }

    // helper function to calculate if monster can dodge this attack
    private boolean hasDodged() {
        Random r = new Random();
        double randomInt = r.nextInt(100);

        return randomInt <= dodge - 1;
    }

    // called when a monster is affected by a spell attack
    public void getEffectedBySpell(SpellType type) {
        switch(type) {
            case ICE:
                int amount = CalculateHandler.calSpellEffectOnMonster(baseDamage);
                baseDamage -= amount;
                Printer.printMonsterIsEffectedBySpell(this.getName(), "ICE", "damage", amount);
                break;
            case FIRE:
                amount = CalculateHandler.calSpellEffectOnMonster(defense);
                defense -= amount;
                Printer.printMonsterIsEffectedBySpell(this.getName(), "FIRE", "defense", amount);
                break;
            case LIGHTNING:
                amount = CalculateHandler.calSpellEffectOnMonster(dodge);
                dodge -= amount;
                Printer.printMonsterIsEffectedBySpell(this.getName(), "LIGHTNING", "dodge", amount);
                break;
        }
    }

    public void performRound(HeroTeam heroTeam, BattleHandler battleHandler) {
        List<Hero> heroAliveInRange = new ArrayList<>();
        for (Hero hero : heroTeam.getTeam()) {
            if (!hero.isFaint() && this.inRange(hero.getPosition())) {
                heroAliveInRange.add(hero);
            }
        }
        if (heroAliveInRange.size() == 0) {
            Printer.printNoEnemyAliveInRange();
            Printer.printMonsterMoveDown(this.getName());
            this.move(MoveDirection.DOWN);
            return;
        }

        Random random = new Random();
        int index = random.nextInt(heroAliveInRange.size());

        battleHandler.attack(this, heroAliveInRange.get(index));
    }
}
