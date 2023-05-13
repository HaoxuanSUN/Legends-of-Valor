package Character;

import World.BuffType;

// this is a class tracking the type of buff each hero have.
public class Buff {
    BuffType buffedAttribute;

    public Buff() {
        this.buffedAttribute = null;
    }

    // setter
    public void setBuffedAttribute(BuffType buffedAttribute) {
        this.buffedAttribute = buffedAttribute;
    }

    // getter
    public BuffType getBuffedAttribute() {
        return buffedAttribute;
    }
}
