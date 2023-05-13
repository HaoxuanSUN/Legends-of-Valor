package Team;

import Config.Stats;
import Character.Monster;
import World.Position;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// This class extends Team and maintains a list of monster objects
public class MonsterTeam extends Team {
    private List<Monster> monsterList;

    private int level;

    public MonsterTeam() {
        super(0);
        monsterList = new ArrayList<>();
    }

    public MonsterTeam(int size, int level) {
        super(size);
        monsterList = new ArrayList<>();
        this.level = level;
    }

    // implement abstract method
    @Override
    public void generateTeam() {

    }

    // clear the monster team
    public void clearTeam() {
        monsterList.clear();
    }

    // getter
    public List<Monster> getMonsterList() {
        return monsterList;
    }

    // set monster team level
    public void setLevel(int level) {
        this.level = level;
    }

    public void addAll(List<Monster> monsters) {
        monsterList.addAll(monsters);
        this.increaseSize(monsters.size());
    }

    public Monster getMonster(int index) {
        return index >= monsterList.size() ? null : monsterList.get(index);
    }

    public void removeMonster(Monster monster) {
        monsterList.remove(monster);
    }

    public boolean hasMonster(Position position) {
        for (Monster monster : monsterList) {
            if (position.equals(monster.getPosition())) return true;
        }

        return false;
    }
}
