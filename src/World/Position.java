package World;

// This class contains the basic fields and methods for position
public class Position {
    private int row;

    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setPos(int row, int col) {
        setRow(row);
        setCol(col);
    }

    // returns if input position is adjacent to this position
    public boolean isAdjacent(Position position) {
        return (position.getCol() == col && position.getRow() == row - 1) ||
                (position.getCol() == col && position.getRow() == row + 1) ||
                (position.getCol() == col - 1 && position.getRow() == row) ||
                (position.getCol() == col + 1 && position.getRow() == row);
    }

    // returns if input position is in attack range of current position
    public boolean inRange(Position position) {
        int row = position.getRow();
        int col = position.getCol();

        return row >= this.row - 1 && row <= this.row + 1
                && col >= this.col - 1 && col <= this.col + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Position)) {
            return false;
        }

        Position p = (Position) o;

        return this.col == p.col && this.row == p.row;
    }
}
