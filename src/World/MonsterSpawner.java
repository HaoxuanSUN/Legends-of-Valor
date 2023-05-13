package World;

import Character.Monster;
import Config.Stats;

import java.util.List;

// This class handles monster spawner
public class MonsterSpawner {
    private final int num;

    private List<Position> positions;

    public MonsterSpawner(List<Position> positions) {
        num = positions.size();
        this.positions = positions;
    }

    // spawn monster at nexus
    public List<Monster> spawnMonsters(int level) {
        List<Monster> monsters = Stats.getRandomMonsterList(level, num);

        for (int i = 0; i < num; i++) {
            monsters.get(i).setPosition(positions.get(i).getRow(), positions.get(i).getCol());
        }

        return monsters;
    }
}
