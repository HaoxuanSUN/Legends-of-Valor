package World;

// Enum class show what the symbol of each space type in world looks like
public enum Symbol {

    Team("*"),
    Market("M"),
    Obstacle("X");

    private String value;

    Symbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
