package Controller;// This is an abstract class that integrate the functions to simulate any game

// This class has the abstract method to control the overall game flow
public abstract class GameController {
    // game name
    private final String gameName;

    public GameController(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    // abstract method to implement for initializing the game
    public abstract void gameInitialize();

    // abstract method to implement for starting the game
    public abstract void gameStart();

    // abstract method to implement for end the game (e.g., cleanup, goodbye message)
    public abstract void gameEnd();
}
