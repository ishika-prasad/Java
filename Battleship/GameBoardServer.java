/*
 * GameBoardServer.java
 *
 * Version:
 *     1
 *
 */

/**
 * This class is for the set up of gameboard for
 * server.
 *
 * @author  Ishika Prasad
 *
 */

import java.util.Scanner;

public class GameBoardServer implements BattleShipInterface{

    Scanner in = null;
    static boolean takeTurn = true;
    static boolean isShip = true;
    public char[][] board;

    //default constructor
    public GameBoardServer() {
        board = new char[10][10];
    }

    /**
     * This method is for reset and display the board
     */
    @Override
    public void resetAndDisplayBoard() {
        System.out.println("\n New Game: ");
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                board[i][j] = '.';
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * This method is for display the current board
     */
    @Override
    public void displayBoard() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * This method is for set the ships in the gameboard
     */
    @Override
    public void setShip() {
        in = new Scanner(System.in);

        for(int i = 0; i < 2; i++) {
            System.out.println("Enter row and column for 1st battleship of length 2");
            System.out.println("Enter row: ");
            int row1 = in.nextInt();
            System.out.println("Enter column: ");
            int col1 = in.nextInt();
            if(row1 > 10 || row1 < 0 || col1 > 10 || col1 < 0) {
                System.out.println("Invalid input!!");
            }
            else {
                board[row1][col1] = 'B';
            }
        }

        for(int i = 0; i < 4; i++) {
            System.out.println("Enter row and column for 1st battleship of length 4");
            System.out.println("Enter row: ");
            int row1 = in.nextInt();
            System.out.println("Enter column: ");
            int col1 = in.nextInt();
            if(row1 > 10 || row1 < 0 || col1 > 10 || col1 < 0) {
                System.out.println("Invalid input!!");
            }
            else {
                board[row1][col1] = 'B';
            }
        }
        displayBoard();
    }

    /**
     * This method is for set the bomb for the opponent
     */
    @Override
    //For Server
    public void setBomb() {
        while(takeTurn) {
            if(!isBattleShip()) {
                takeTurn = false;
                System.out.println("Game is over");
                System.out.println("You won!");
            }
            else {
                in = new Scanner(System.in);
                System.out.println("Enter the row and column to set bomb");
                String rowcol = in.nextLine();
                String[] data = rowcol.split(",");
                int row = Integer.parseInt(data[0]);
                int col = Integer.parseInt(data[1]);
                hitOrMiss(row, col);
                displayBoard();
            }
        }
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
        if(board[row][col] == 'B') {
            board[row][col] = '1';
            takeTurn = true;
            return takeTurn;
        }
        else {
            board[row][col] = '0';
            takeTurn = false;
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
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if (board[i][j] == 'B') {
                    isShip = true;
                    break;
                }
            }
        }
        return isShip;
    }


    public static void main(String[] args) {
        GameBoardClient gameBoard = new GameBoardClient();
        gameBoard.resetAndDisplayBoard();
        gameBoard.setShip();
        gameBoard.setBomb();
        gameBoard.resetAndDisplayBoard();
    }
}
