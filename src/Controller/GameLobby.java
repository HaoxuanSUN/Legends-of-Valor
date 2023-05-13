package Controller;

import Config.InputHandler;
import Config.Printer;

// This class implements the function for select the game
public class GameLobby {
    public GameLobby() {
        handleGameSelect();
    }

    // handle game selection
    private void handleGameSelect() {
        Printer.printGameSelect();
        Printer.printGameOptions();
        int index = InputHandler.getIndexNumber("play", "game", 1);

        switch (index) {
            case 0:
                MonstersAndHeroes game= new MonstersAndHeroes();
                game.gameStart();
                break;
        }
    }
}
