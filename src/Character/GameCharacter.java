package Character;

import Action.MoveDirection;
import World.Position;

// This is an abstract class which has basic fields and methods of a game character.
public abstract class GameCharacter {
    private final CharacterType type;

    private final String name;

    private int HP;

    private final Position position;

    public GameCharacter(CharacterType type, String name) {
        this.type = type;
        this.name = name;
        position = new Position(-1, -1);
    }

    public CharacterType getType() {
        return type;
    }

    public String getName() {
        String[] name = this.name.split("_");
        return name.length == 1 ? this.name : name[0] + " " + name[1];
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = Math.max(HP, 0);
    }

    public boolean isFaint() {
        return HP <= 0;
    }

    // called when this character is attacked, take int as incoming damage
    // return int to tell how much damage is actually dealt to this character
    // if return -1, means this character has dodged the attack
    public abstract int getAttacked(int damageIncoming);

    public void takeDamage(int damage) {
        this.setHP(this.getHP() - damage);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int row, int col) {
        this.setRow(row);
        this.setCol(col);
    }

    public int getRow() {
        return position.getRow();
    }

    public int getCol() {
        return position.getCol();
    }

    public void setRow(int row) {
        position.setRow(row);
    }

    public void setCol(int col) {
        position.setCol(col);
    }

    public void move(MoveDirection dir) {
        switch (dir) {
            case UP:
                setRow(getRow() - 1);
                break;
            case DOWN:
                setRow(getRow() + 1);
                break;
            case LEFT:
                setCol(getCol() - 1);
                break;
            case RIGHT:
                setCol(getCol() + 1);
                break;
        }
    }

    public boolean inRange(Position position) {
        return this.getPosition().inRange(position);
    }
}
