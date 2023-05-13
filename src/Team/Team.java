package Team;

// This is an abstract class includes basic fields and method of a team
public abstract class Team {
    private int size;

    public Team(int size) {
        this.size = size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // getter
    public int getSize() {
        return size;
    }

    public void increaseSize(int num) {
        this.setSize(this.getSize() + num);
    }

    // abstract method to generate a new team
    public abstract void generateTeam();
}
