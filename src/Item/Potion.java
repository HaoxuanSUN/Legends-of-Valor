package Item;

// This class extends item class, and represents a potion item in game
public class Potion extends Item{
    private final int increaseAmount;

    private final String attributeAffected;

    public Potion(String name, int price, int level, int value, String attributeAffected) {
        super(ItemType.POTION, name, price, level);
        this.increaseAmount = value;
        this.attributeAffected = attributeAffected;
    }

    // getter
    public int getIncreaseAmount() {
        return increaseAmount;
    }

    // getter
    public String getAttributeAffected() {
        return attributeAffected;
    }

    // make a new potion object
    public Potion makeCopy() {
        return new Potion(this.getName(), this.getPrice(),
                this.getLevel(), this.getIncreaseAmount(),
                this.getAttributeAffected());
    }
}
