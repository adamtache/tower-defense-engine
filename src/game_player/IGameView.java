package game_player;

/**
 * This interface specifies the different methods needed to run and animate the game elements.
 * Throws exception when towers/units are placed in wrong locations.
 * @author ownzandy
 *
 */

public interface IGameView {
    
    //event handler that begins the animation and movement of elements
    void playGame();
    
    //event handler that stops the game
    void pauseGame();
    
    //gets terrain from engine and places/animates 
    void placeTerrain();
    
    //gets path from engine and places/animates 
    void placePath();
    
    //gets units (enemies, towers) from engine and places/animates
    void placeUnit();    
}
