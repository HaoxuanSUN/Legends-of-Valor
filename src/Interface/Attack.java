package Interface;

import Battle.AttackType;
import Character.GameCharacter;

// Interface includes a method that handles attack, which makes use of java generics
public interface Attack<T extends GameCharacter, M extends GameCharacter> {

    // method to handle attack
    // T and M are any sub classes or class itself extends GameCharacter
    void attack(T attacker, M target);

}
