package Item;

// This is an abstract class, and is a general item with basic fields and methods
public abstract class Item {
    private final ItemType type;

    private final String name;

    private final int price;

    private final int level;

    public Item(ItemType type, String name, int price, int level) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.level = level;
    }

    // getter
    public ItemType getType() {
        return type;
    }

    // getter
    public String getName() {
        return name;
    }

    // getter
    public int getPrice() {
        return price;
    }

    // getter
    public int getLevel() {
        return level;
    }
}
