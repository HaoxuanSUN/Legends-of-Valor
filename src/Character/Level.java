package Character;

import Config.CalculateHandler;

// This is a class takes care of level and exp point, Hero has Level
public class Level {
    private int level;

    private int EXP;

    public Level(int level, int EXP) {
        this.level = level;
        this.EXP = EXP;
    }

    // getter
    public int getLevel() {
        return level;
    }

    // setter
    public void setLevel(int level) {
        this.level = level;
    }

    // getter
    public int getEXP() {
        return EXP;
    }

    // setter
    public void setEXP(int EXP) {
        this.EXP = EXP;
    }

    // handle gain exp point, and level up if necessary
    // return boolean to tell caller if this unit has leveled up or not
    public boolean gainEXP(int EXP) {
        this.EXP += EXP;

        int levelUpEXP = CalculateHandler.calLevelUpEXP(this.level);
        if (this.EXP >= levelUpEXP) {
            this.EXP %= levelUpEXP;
            this.level++;
            return true;
        }

        return false;
    }
}
