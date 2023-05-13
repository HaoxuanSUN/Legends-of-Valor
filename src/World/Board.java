package World;
// This is an abstract class that creates a game board

// This class is an abstract class contains the basic fields and methods that
// consist of a game board
public abstract class Board {
    private final int rows;

    private final int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public Board(int side) {
        this(side, side);
    }

    // reset the board
    public abstract void clear();

    // getter
    public int getRows() {
        return rows;
    }

    // getter
    public int getCols() {
        return cols;
    }

}
