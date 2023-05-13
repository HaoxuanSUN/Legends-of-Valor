package Interface;// this is an interface of game rule

import Character.GameCharacter;

// Interface helps to check win/lose conditions
public interface Rule<T extends GameCharacter> {

    // Check if player wins the game
    public boolean checkWin(T character);

    // Check if player loses the game
    public boolean checkLose(T character);

    public void printRule();
}
