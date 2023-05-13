package Item;

// This class extends item class, and represents an armor item in game
public class Armor extends Item {
    private final int damageReduction;

    private boolean equipped;

    public Armor(String name, int price, int level, int damageReduction) {
        super(ItemType.ARMOR, name, price, level);
        this.damageReduction = damageReduction;
    }

    // getter
    public int getDamageReduction() {
        return damageReduction;
    }

    // getter
    public boolean isEquipped() {
        return equipped;
    }

    // setter
    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }
}
