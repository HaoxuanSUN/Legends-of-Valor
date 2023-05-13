package World;

// This class contains the basic fields and methods for a lane
public class Lane {
    private final LaneType type;

    private final int colLowerBound;

    private final int colUpperBound;

    public Lane(LaneType type, int colLowerBound, int colUpperBound) {
        this.type = type;
        this.colLowerBound = colLowerBound;
        this.colUpperBound = colUpperBound;
    }

    public LaneType getType() {
        return type;
    }

    public int getColLowerBound() {
        return colLowerBound;
    }

    public int getColUpperBound() {
        return colUpperBound;
    }

    public boolean isAtLane(int col) {
        return col >= colLowerBound && col <= colUpperBound;
    }
}
