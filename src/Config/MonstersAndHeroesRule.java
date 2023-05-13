package Config;

import Interface.Rule;
import Team.*;
import Character.GameCharacter;

//This class implements Rule interface and help game to check win/lose conditions
public class MonstersAndHeroesRule<T extends GameCharacter> implements Rule<T> {
    public MonstersAndHeroesRule() {
    }

    // implement interface method
    @Override
    public boolean checkWin(T character) {
        return character.getRow() == 0;
    }

    // implement interface method
    @Override
    public boolean checkLose(T character) {
        return character.getRow() == 7;
    }

    // implement interface method
    @Override
    public void printRule() {
        Printer.printRule();
    }
}
