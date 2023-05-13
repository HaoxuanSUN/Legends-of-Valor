package Interface;

import Character.GameCharacter;
import World.Position;

// Interface includes a method that handles teleport action
public interface Teleport<T extends GameCharacter> {
    // handle teleport function
    public String makeTeleport(Position position, T character);
}
