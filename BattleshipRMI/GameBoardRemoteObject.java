package BattleshipRMI;

/*
 * GameBoardRemoteObject.java
 *
 * Version:
 *     1
 *
 */

/**
 * This class is act as a remote object between
 * client and server.
 *
 * @author Ishika Prasad
 */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class GameBoardRemoteObject implements BattleShipInterface {

    Scanner in = null;
    static int row = 0;
    static int col = 0;
    static boolean takeTurn = true;
    static boolean isShip = true;
    public char[][] enemyBoard;
    public char[][] myBoard;

    //static GameBoardRemoteObject obj=new GameBoardRemoteObject();

    //default constructor
    public GameBoardRemoteObject() {
        enemyBoard = new char[10][10];
        myBoard = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                enemyBoard[i][j] = '.';
                myBoard[i][j] = '.';
            }
        }
    }

    /**
     * This method of for display the player name
     * connected to the server.
     * @param player
     */
    public void playerName(String player) {
        System.out.println("Player Name is :" + player);
    }

    /**
     * This method is for reset and display the enemyBoard
     */
    @Override
    public void resetAndDisplayBoard() {
        System.out.println("\n New Game: ");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                enemyBoard[i][j] = '.';
                myBoard[i][j] = '.';
                System.out.print(enemyBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * This method is for display the current enemyBoard
     */
    @Override
    public void displayBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(enemyBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * This method is for set the ships in the gameboard
     */
    @Override
    public void setShip() {
        in = new Scanner(System.in);

        for (int i = 0; i < 2; i++) {
            System.out.println("Enter row and column for 1st battleship of length 2");
            System.out.println("Enter row: ");
            int row1 = in.nextInt();
            System.out.println("Enter column: ");
            int col1 = in.nextInt();
            if (row1 > 10 || row1 < 0 || col1 > 10 || col1 < 0) {
                System.out.println("Invalid input!!");
            } else {
                myBoard[row1][col1] = 'B';
            }
        }
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter row and column for 1st battleship of length 4");
            System.out.println("Enter row: ");
            int row1 = in.nextInt();
            System.out.println("Enter column: ");
            int col1 = in.nextInt();
            if (row1 > 10 || row1 < 0 || col1 > 10 || col1 < 0) {
                System.out.println("Invalid input!!");
            } else {
                myBoard[row1][col1] = 'B';
            }
        }
        System.out.println();
        //displayBoard(myBoard);
        //return myBoard;
    }

    public void getSetShip(char[][] playerBoard) {
        displayBoard(playerBoard);
    }

    /**
     * This method is for set the bomb for the opponent
     */
    @Override
    //For Server
    public void setBomb() {
        while (takeTurn) {
            if (!isBattleShip()) {
                takeTurn = false;
                System.out.println("Game is over");
                System.out.println("You won!");
            } else {
                in = new Scanner(System.in);
                System.out.println("Enter the row and column to set bomb");
                String rowcol = in.nextLine();
                String[] data = rowcol.split(",");
                row = Integer.parseInt(data[0]);
                col = Integer.parseInt(data[1]);
                hitOrMiss(row, col);
                displayBoard();
            }
        }
        System.out.println();
    }

    /**
     * This method gives boolean result for hit or miss
     * the target for opponent ship.
     * @param row row for which bomb to be placed
     * @param col column for which bomb to be placed
     * @return true if hit the opponent ship. Otherwise, false.
     */
    @Override
    public boolean hitOrMiss(int row, int col) {
        if (enemyBoard[row][col] == 'B') {
            enemyBoard[row][col] = '1';
            takeTurn = true;
            System.out.println();
            return takeTurn;
        } else {
            enemyBoard[row][col] = '0';
            takeTurn = false;
            System.out.println();
            return takeTurn;
        }

    }

    /**
     * This method is to check if the opponent battleship is
     * all destroyed or remaining.
     * @return true if ships are sunk. Otherwise, false.
     */
    public boolean isBattleShip() {
        isShip = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (enemyBoard[i][j] == 'B') {
                    isShip = true;
                    break;
                }
            }
        }
        System.out.println();
        return isShip;
    }

    /**
     * This method is for checking if the enemy board got
     * hit or missed
     * @param enemyAttack
     * @return 1 if hit. Otherwise, 0
     */
    public String checkHitOrMiss(String enemyAttack) {
        if (enemyAttack.equals("-1,-1")) {
            System.out.println("Congrats! You won ");
            System.exit(0);
        }
        int x = Integer.parseInt(enemyAttack.charAt(0) + "");
        int y = Integer.parseInt(enemyAttack.charAt(2) + "");

        if (myBoard[x][y] == 'B') {
            System.out.println();
            return "1";
        }
        else {
            System.out.println();
            return "0";
        }
    }

    /**
     * This method is to check if all the ships are destroyed
     * @return true if destroyed all. Otherwise, false
     */
    public boolean checkIfAllDestroyed() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (myBoard[i][j] == 'B') {
                    System.out.println();
                    return false;
                }
        System.out.println();
        return true;
    }

    /**
     * This method is to display the current board
     * @param board
     */
    public void displayBoard(char[][] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * This method is to update myboard with the enemy attack
     * @param enemyAttack
     */
    public void updateMyBoard(String enemyAttack) {
        int x = Integer.parseInt(enemyAttack.charAt(0) + "");
        int y = Integer.parseInt(enemyAttack.charAt(2) + "");

        if (myBoard[x][y] == 'B') {
            myBoard[x][y] = '1';
            System.out.println();
        }

        else {
            System.out.println();
            myBoard[x][y] = '0';
            System.out.println();
        }

    }

    /**
     * This method is to update the enemy board with the rowcol
     * @param rowcol
     * @param enemyResponse
     */
    public void updateEnemyBoard(String rowcol, String enemyResponse) {
        int x = Integer.parseInt(rowcol.charAt(0) + "");
        int y = Integer.parseInt(rowcol.charAt(2) + "");

        enemyBoard[x][y] = enemyResponse.charAt(0);
        System.out.println();
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                System.out.print(enemyBoard[i][j]+" ");
            }
            System.out.println();
        }

    }
}
