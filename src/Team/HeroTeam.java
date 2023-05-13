package Team;

import Config.InputHandler;
import Config.Stats;
import Character.Hero;
import Character.HeroRole;
import World.LaneType;
import World.Position;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

// This class extends Team and maintains a list of hero objects
public class HeroTeam extends Team{
    private final Hero[] team;

    private int row;

    private int col;

    public HeroTeam(int size) {
        super(size);
        team = new Hero[size];
        row = 0;
        col = 0;

        generateTeam();
    }

    // getter
    public Hero[] getTeam() {
        return team;
    }

    // get hero by index
    public Hero getHero(int index) {
        return team[index];
    }

    // generate a new team
    public void generateTeam() {
        System.out.println();

        HeroRole topRole = InputHandler.getHeroRole(LaneType.TOP);
        team[0] = Stats.getRandomHero(topRole);
        team[0].setLane(LaneType.TOP);
        team[0].setPosition(7, 0);

        HeroRole midRole = InputHandler.getHeroRole(LaneType.MID);
        team[1] = Stats.getRandomHero(midRole);
        team[1].setLane(LaneType.MID);
        team[1].setPosition(7, 3);

        HeroRole botRole = InputHandler.getHeroRole(LaneType.BOT);
        team[2] = Stats.getRandomHero(botRole);
        team[2].setLane(LaneType.BOT);
        team[2].setPosition(7, 6);
    }

    // get team position of row
    public int getRow() {
        return row;
    }

    // set team position of row
    public void setRow(int row) {
        this.row = row;
    }

    // get team position of col
    public int getCol() {
        return col;
    }

    // set team position of col
    public void setCol(int col) {
        this.col = col;
    }

    // check whether team got aced
    public boolean teamKilled() {
        for (Hero hero : team) {
            if (!hero.isFaint()) return false;
        }

        return true;
    }

    public List<Position> getHeroPos() {
        List<Position> heroPos = new ArrayList<>();

        for (Hero hero : team) {
            heroPos.add(hero.getPosition());
        }

        return heroPos;
    }

    public boolean hasSamePos(Position position) {
        for (Hero hero : team) {
            if (position.equals(hero.getPosition())) return true;
        }

        return false;
    }
}
