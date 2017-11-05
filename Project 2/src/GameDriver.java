/**
 * The GameDriver class is the main class that acts the driver for the entire game and thereby plays the game.
 */
public class GameDriver {
    public static void main(String[] args) {
        Game gameObject = new Game(5);
        gameObject.playAGame();
    }
}
