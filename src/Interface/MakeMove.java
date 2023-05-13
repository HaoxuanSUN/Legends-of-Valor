package Interface;

import Action.MoveDirection;
import Character.GameCharacter;

// Interface includes a method that handles move action
public interface MakeMove<T extends GameCharacter> {
    // method to handle move action
    // take direction as input
    // return string to tell caller whether has made a valid move
    // if return null then means a valid move
    // else return error message
    public String makeMove(MoveDirection dir, T character);
}
