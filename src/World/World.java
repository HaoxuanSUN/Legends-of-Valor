package World;

import Config.Stats;
import Character.Monster;

import java.util.ArrayList;
import java.util.List;

// This class extends general Board class and represents a basic world of RPG game
public class World extends Board{
    private final Space[][] world;

    private final Lane[] lanes;

    private final int size;

    public World(int size) {
        super(size);
        this.size = size;
        world = new Space[size][size];
        lanes = new Lane[3];

        generateLanes();
        generateWorld();
    }

    private void generateLanes() {
        lanes[0] = new Lane(LaneType.TOP, 0, 1);
        lanes[1] = new Lane(LaneType.MID, 3, 4);
        lanes[2] = new Lane(LaneType.BOT, 6, 7);
    }

    // generate world based on data from database
    private void generateWorld() {
        int[][] worldPrototype = Stats.getRandomWorld();

        for (int i = 0 ; i < size; i++) {
            for (int j = 0; j < size; j++) {
                switch (worldPrototype[i][j]) {
                    case 0:
                        world[i][j] = new Space(i, j, SpaceType.INACCESSIBLE);
                        break;
                    case 1:
                        world[i][j] = new Space(i, j, SpaceType.PLAIN);
                        break;
                    case 2:
                        world[i][j] = new Space(i, j, SpaceType.BUSH);
                        break;
                    case 3:
                        world[i][j] = new Space(i, j, SpaceType.CAVE);
                        break;
                    case 4:
                        world[i][j] = new Space(i, j, SpaceType.KOULOU);
                        break;
                    case 8:
                        world[i][j] = new Space(i, j, SpaceType.HERO_NEXUS);
                        break;
                    case 9:
                        world[i][j] = new Space(i, j, SpaceType.MONSTER_NEXUS);
                        break;
                }
            }
        }
    }

    // getter
    public Space[][] getWorld() {
        return world;
    }

    // getter
    public int getSize() {
        return size;
    }

    // getter
    public SpaceType getSpaceType(Position position) {
        int row = position.getRow();
        int col = position.getCol();

        if (row < 0 || row >= size || col < 0 || col >= size) return null;
        return world[row][col].getType();
    }

    public Space getSpace(Position position) {
        int row = position.getRow();
        int col = position.getCol();

        if (row < 0 || row >= size || col < 0 || col >= size) return null;
        return world[row][col];
    }

    // implements abstract method
    @Override
    public void clear() {
        for (int i = 0 ; i < size; i++) {
            for (int j = 0; j < size; j++) {
                world[i][j] = null;
            }
        }
    }

    public LaneType atWhichLane(int col) {
        for (Lane lane : lanes) {
            if (lane.isAtLane(col)) return lane.getType();
        }

        return null;
    }
}
