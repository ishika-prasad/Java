package BattleshipRMI;

/*
 * BattleShipInterface.java
 *
 * Version:
 *     1
 *
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface is provided for the battleship gameboard
 * for remote object.
 *
 * @author  Ishika Prasad
 *
 */

public interface BattleShipInterface extends Remote {
    void resetAndDisplayBoard() throws RemoteException;
    void playerName(String player) throws RemoteException;
    void displayBoard() throws RemoteException;
    void setShip() throws RemoteException;
    void getSetShip(char[][] playerBoard) throws RemoteException;
    void setBomb() throws RemoteException;
    boolean hitOrMiss(int row, int col) throws RemoteException;
    String checkHitOrMiss(String enemyAttack) throws RemoteException;
    boolean checkIfAllDestroyed() throws RemoteException;
    void displayBoard(char[][] board) throws RemoteException;
    void updateMyBoard(String enemyAttack) throws RemoteException;
    void updateEnemyBoard(String rowcol, String enemyResponse) throws RemoteException;
}
