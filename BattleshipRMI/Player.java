package BattleshipRMI;

/*
 * Player.java
 *
 * Version:
 *     1
 *
 */

/**
 * This class acts as a client using RMI.
 *
 * @author  Ishika Prasad
 *
 */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

//Client1
public class Player {
    static GameBoardRemoteObject gameBoardRemoteObject = new GameBoardRemoteObject();
    static String player = null;

    public char[][] enemyBoard;
    public char[][] myBoard;

    //default constructor
    public Player() {
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
     * The main method
     * @param args
     */
    public static void main(String args[])
    {
        try{
            String hostname=null;
            int port=1099;
            Scanner in=new Scanner(System.in);
            for (int i = 0; i < args.length; i ++) {
                if (args[i].equals("-host"))
                    hostname=args[++i];
                else if (args[i].equals("-port")) {
                    port = Integer.valueOf(args[++i]);
                }
            }
            Registry registry = LocateRegistry.getRegistry(hostname,port);
            BattleShipInterface stub=(BattleShipInterface)registry.lookup("Battleship");
            System.out.println("Connection Successful");
            System.out.println("Enter the Player name: ");
            player = in.nextLine();
            stub.playerName(player);
            stub.resetAndDisplayBoard();
            System.out.println();
            gameBoardRemoteObject.setShip();
            //stub.getSetShip(playerBoard);
            //System.out.println();

            System.out.println("My board -> ");
            gameBoardRemoteObject.displayBoard(gameBoardRemoteObject.myBoard);
            stub.displayBoard(gameBoardRemoteObject.myBoard);
            System.out.println();

            while(true) {

                //Scanner in = new Scanner(System.in);
                System.out.println("Enter the row and column to set bomb");
                String rowcol = in.nextLine();

                String enemyResponse = gameBoardRemoteObject.checkHitOrMiss(rowcol);
                gameBoardRemoteObject.updateEnemyBoard(rowcol,enemyResponse);
                //stub.updateEnemyBoard(rowcol, enemyResponse);
                if(gameBoardRemoteObject.checkIfAllDestroyed()) {
                    if(enemyResponse.equals("-1,-1")) {
                        System.out.println("Congrats! You won! ");
                        System.exit(0);
                    }
                }

                System.out.println("Enemy Board -> ");
                gameBoardRemoteObject.displayBoard(gameBoardRemoteObject.enemyBoard);
                stub.displayBoard(gameBoardRemoteObject.enemyBoard);

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
