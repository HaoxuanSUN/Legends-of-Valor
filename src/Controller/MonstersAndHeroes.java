package Controller;

import Action.ActionEventHandler;
import Battle.BattleHandler;
import Battle.MonstersAndHeroesBattle;
import Config.*;
import Character.Hero;
import Character.Monster;
import Interface.Rule;
import Market.Market;
import Team.HeroTeam;
import Team.MonsterTeam;
import World.*;

import java.util.ArrayList;
import java.util.Arrays;

// This class extends GameController class and implements the abstract methods
public class MonstersAndHeroes extends GameController {
    private World world;

    private MonsterSpawner monsterSpawner;

    private final Rule rule;

    private HeroTeam heroTeam;

    private MonsterTeam monsterTeam;

    private Market market;

    private BattleHandler battleHandler;

    private ActionEventHandler actionEventHandler;

    public MonstersAndHeroes() {
        super("Monsters and Heroes");
        rule = new MonstersAndHeroesRule();

        gameInitialize();
    }

    // implement abstract method
    @Override
    public void gameInitialize() {
        Stats.readAllData();
        market = new Market(1);

        Printer.printWelcome();
        rule.printRule();

        Printer.printBreak("Game Initialize");
        generateHeroTeam();

        generateMonsterTeam();

        generateWorld();
        Printer.setWaitTime(1000);

        battleHandler = new MonstersAndHeroesBattle<Hero, Monster>(heroTeam, monsterTeam);
        actionEventHandler = new ActionEventHandler(world, heroTeam, monsterTeam, battleHandler, market);
    }

    // helper function to initialize the world map
    private void generateWorld() {
        world = new World(8);

        Printer.printLoading("Generating Random Map");
        System.out.println("- World has been created!");

        Printer.printWorld(world, heroTeam, monsterTeam, null);
        Printer.printWorldSymbolExplain();
    }

    // helper function to initialize the hero team
    private void generateHeroTeam() {
        heroTeam = new HeroTeam(3);
        Printer.printLoading("Initializing Hero Teams");
        System.out.println("- Team has been created!");
        Printer.printTeamStatus(heroTeam);
    }

    private void generateMonsterTeam() {
        monsterTeam = new MonsterTeam(0, 1);
        monsterSpawner = new MonsterSpawner(new ArrayList<Position>(
                Arrays.asList(new Position(0, 1),
                              new Position(0, 4),
                              new Position(0, 7))
        ));
        monsterTeam.addAll(monsterSpawner.spawnMonsters(1));

        Printer.printLoading("Initializing Monsters");
        System.out.println("- First wave of monster has been created!");
        System.out.println();
    }

    // implement abstract method
    @Override
    public void gameStart() {
        Printer.printPause("start the game");
        Printer.printBreak("Game Start");
        Printer.printAction();
        int round = 1;

        while(true) {
            Printer.printPause("start round " + round);
            // print battle info
            Printer.printBreak("Round " + round);

            prepareRound(round);

            // heroes' turn
            if (dealHeroTurn()) break;

            // monsters' turn
            if (dealMonsterTurn()) break;

            checkoutRound();

            Printer.printBreak("Round " + round + " End");

            round++;
        }

        gameEnd();
    }

    private void prepareRound(int round) {
        // check if market need update
        if (market.checkUpdate(CalculateHandler.calTeamHighestLevel(heroTeam))) {
            Printer.printMarketHasUpdated(market.getMarketLevel());
        }

        // check if next wave monster should spawn
        if (CalculateHandler.calEnemySpawn(round - 1)) {
            monsterTeam.addAll(monsterSpawner.spawnMonsters(CalculateHandler.calTeamHighestLevel(heroTeam)));
            Printer.printMonsterSpawn();
            Printer.printWorld(world, heroTeam, monsterTeam, null);
        }
    }

    private boolean dealHeroTurn() {
        for (Hero hero : heroTeam.getTeam()) {
            Printer.printWorld(world, heroTeam, monsterTeam, hero);
            Printer.printRound(hero.getName());
            Printer.printHeroPosition(hero.getName(), hero.getPosition());

            while (true) {
                printSpaceHint(hero);
                if (actionEventHandler.handleAction(InputHandler.getAction(), hero)) break;
            }

            if (rule.checkWin(hero)) {
                Printer.printGameWin();
                return true;
            }
        }

        return false;
    }

    private void printSpaceHint(Hero hero) {
        SpaceType type = world.getSpaceType(hero.getPosition());
        switch (type) {
            case HERO_NEXUS:
                Printer.printAtMarketHint();
                break;
            case BUSH:
            case CAVE:
            case KOULOU:
                Printer.printAtSpecialSpaceHint(type);
                break;
        }
    }

    private boolean dealMonsterTurn() {
        for (Monster monster : monsterTeam.getMonsterList()) {
            Printer.printMonsterRound(monster.getName());

            monster.performRound(heroTeam, battleHandler);

            if (rule.checkLose(monster)) {
                Printer.printGameLose();
                return true;
            }
        }
        Printer.printWorld(world, heroTeam, monsterTeam, null);

        return false;
    }

    public void checkoutRound() {
        for (Hero hero : heroTeam.getTeam()) {
            if (hero.isFaint()) {
                hero.revive();
                Printer.printHeroRevive(hero.getName());
            }
            else {
                int regainHP = hero.regainHP();
                int regainMP = hero.regainMP();
                Printer.printHeroRegain(hero.getName(), regainHP, regainMP);
            }
        }
    }

    // implement abstract method
    @Override
    public void gameEnd() {
        Printer.printThankYou();
    }
}
