package World;

// This class represents a single space in board/world
public class Space {
    private final Position position;

    private final SpaceType type;

    private BuffType buff;

    public Space(int row, int col, SpaceType type) {
        position = new Position(row, col);
        this.type = type;

        setBuff();
    }

    private void setBuff() {
        switch (type) {
            case BUSH:
                buff = BuffType.DEXTERITY;
                break;
            case CAVE:
                buff = BuffType.AGILITY;
                break;
            case KOULOU:
                buff = BuffType.STRENGTH;
        }
    }

    public BuffType getBuff() {
        return buff;
    }

    // getter
    public Position getPosition() {
        return position;
    }

    // getter
    public SpaceType getType() {
        return type;
    }
}
