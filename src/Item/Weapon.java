package Item;

// This class extends item class, and represents a weapon item in game
public class Weapon extends Item{
    private final int damage;

    private final int handsRequired;

    private boolean equipped;

    public Weapon(String name, int price, int level, int damage, int handsRequired) {
        super(ItemType.WEAPON, name, price, level);
        this.damage = damage;
        this.handsRequired = handsRequired;
    }

    // getter
    public int getDamage() {
        return damage;
    }

    // getter
    public int getHandsRequired() {
        return handsRequired;
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
