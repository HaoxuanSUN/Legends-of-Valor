package Battle;

import Character.GameCharacter;
import Interface.Attack;

// This is an abstract class that make use of java generics,
// it contains abstract methods of a battle.
// and it implements attack interface.
public abstract class BattleHandler<T extends GameCharacter, M extends GameCharacter> implements Attack<T, M> {

    /**
     * This method is triggered
     * when the party move to a new tile and the triggerBattle flag is true
     * This method will init the battle (e.g. generate monster)
     */
    public abstract void battleInit();

    /**
     * This method represents the battle flow
     */
    public abstract void battleStart();

    /**
     * This method is triggered when the battle ends
     */
    public abstract void battleEnd();

    /**
     * This method is used to detect whether a battle will happen
     */
    public abstract boolean detectBattle(T character);
}
