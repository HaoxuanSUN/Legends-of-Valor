package Item;

// This class extends item class, and represents a spell item in game
public class Spell extends Item{
    private final int damageValue;

    private final int manaCost;

    private final SpellType type;

    private final int useTimeLimit;

    private int useTime;

    public Spell(String name, int price, int level, int damageValue, int manaCost, SpellType type, int useTimeLimit) {
        super(ItemType.SPELL, name, price, level);
        this.damageValue = damageValue;
        this.manaCost = manaCost;
        this.type = type;
        this.useTimeLimit = useTimeLimit;
        useTime = 0;
    }

    // getter
    public int getDamageValue() {
        return damageValue;
    }

    // getter
    public int getManaCost() {
        return manaCost;
    }

    // getter
    public SpellType getSpellType() {
        return type;
    }

    // getter
    public int getUseTimeLimit() {
        return useTimeLimit;
    }

    // getter
    public int getUseTime() {
        return useTime;
    }

    // getter
    public void setUseTime(int useTime) {
        this.useTime = useTime;
    }

    // make a new spell object
    public Spell makeCopy() {
        return new Spell(this.getName(), this.getPrice(),
                this.getLevel(), this.getDamageValue(),
                this.getManaCost(), this.getSpellType(),
                this.getUseTimeLimit());
    }
}
