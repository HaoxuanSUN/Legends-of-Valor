package Action;

import Character.*;
import Interface.MakeMove;
import Interface.Recall;
import Interface.Teleport;
import Team.HeroTeam;
import Team.MonsterTeam;
import World.SpaceType;
import World.*;

// This class handles the movement action for team to move in world.
// it implements MakeMove/Teleport/Recall interface
public class PositionChangeHandler<T extends GameCharacter> implements MakeMove<T>, Teleport<T>, Recall<T> {
    private final World world;

    private final MonsterTeam monsterTeam;

    private final HeroTeam heroTeam;

    public PositionChangeHandler(World world, HeroTeam heroTeam, MonsterTeam monsterTeam) {
        this.world = world;
        this.monsterTeam = monsterTeam;
        this.heroTeam = heroTeam;
    }

    // implement interface method
    @Override
    public String makeMove(MoveDirection dir,  T character) {
        String errorMsg = checkValidMove(dir, character);
        if (errorMsg != null) return errorMsg;

        character.move(dir);
        return null;
    }

    // Check if team can move to (row, col)
    private String checkValidMove(MoveDirection dir, T character) {
        Position newPos = new Position(character.getRow(), character.getCol());

        switch (dir) {
            case UP:
                newPos.setRow(newPos.getRow() - 1);
                break;
            case DOWN:
                newPos.setRow(newPos.getRow() + 1);
                break;
            case LEFT:
                newPos.setCol(newPos.getCol() - 1);
                break;
            case RIGHT:
                newPos.setCol(newPos.getCol() + 1);
                break;
        }

        // error case 1: move to inaccessible space
        SpaceType type = world.getSpaceType(newPos);
        if (type == null || type == SpaceType.INACCESSIBLE) return "you cannot move to inaccessible space";

        if (character instanceof Hero) {
            // error case 2: move to same position as other hero
            if (heroTeam.hasSamePos(newPos)) return "someone else is at that position";

            // error case 3: move to point behind monster
            Position curPos = character.getPosition();
            Position leftPos = new Position(curPos.getRow(), curPos.getCol() - 1);

            if (monsterTeam.hasMonster(curPos) || monsterTeam.hasMonster(leftPos)) {
                if (dir == MoveDirection.UP) return "you cannot move behind monster";
            }
        }

        return null;
    }

    @Override
    public String makeTeleport(Position position, T character) {
        String errorMsg = checkValidTeleport(position, character);
        if (errorMsg != null) return errorMsg;

        character.setPosition(position.getRow(), position.getCol());
        return null;
    }

    // check if position is a valid teleport position
    private String checkValidTeleport(Position position, T character) {
        // error case 1: teleport to inaccessible space
        if (world.getSpaceType(position) == SpaceType.INACCESSIBLE) {
            return "you cannot teleport to inaccessible space";
        }

        // error case 2: teleport to the same lane
        if (world.atWhichLane(position.getCol()) == world.atWhichLane(character.getCol())) {
            return "you cannot teleport to the same lane";
        }

        // error case 3: teleport to a point where other hero is at
        if (heroTeam.hasSamePos(position)) {
            return "someone else is at the teleport point";
        }

        // error case 4: teleport to a point not adjacent to another hero
        boolean adjacent = false;
        int furthest = 7;
        for (Hero hero : heroTeam.getTeam()) {
            if (hero.isAdjacent(position)) {
                adjacent = true;
                furthest = Math.min(furthest, hero.getRow());
            }
        }
        if (!adjacent) return "you must choose a teleport space adjacent to another hero";

        // error case 5: teleport to a point ahead of that lane hero
        if (position.getRow() < furthest) return "you cannot teleport ahead of hero in this lane";

        return null;
    }

    @Override
    public boolean makeRecall(T character) {
        Hero hero = (Hero) character;
        Position recallPos = hero.getSpawnPos();

        for (Hero h : heroTeam.getTeam()) {
            if (h != hero) {
                if (recallPos.equals(h.getPosition())) return false;
            }
        }

        hero.recall();
        return true;
    }
}
