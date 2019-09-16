/*
 * BattleShipInterface.java
 *
 * Version:
 *     1
 *
 */

/**
 * This interface is provided for the battleship gameboard
 * for client and server.
 *
 * @author  Ishika Prasad
 *
 */

public interface BattleShipInterface {
    void resetAndDisplayBoard();
    void displayBoard();
    void setShip();
    void setBomb();
    boolean hitOrMiss(int row, int col);

}
