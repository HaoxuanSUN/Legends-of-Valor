package Interface;

import Character.GameCharacter;

// Interface includes a method that handles recall action
public interface Recall<T extends GameCharacter> {

    // function that handles recall action
    public boolean makeRecall(T character);
}
