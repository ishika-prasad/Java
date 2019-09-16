/*
 * PlayerA.java
 *
 * Version:
 *     1
 *
 */

/**
 * This class acts as a server class using TCP.
 *
 * @author  Ishika Prasad
 *
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//Server
public class PlayerA extends Thread{
    ServerSocket clientSocket;
    int port = 4242;
    GameBoardServer gameBoardServer = new GameBoardServer();
    Scanner in = new Scanner(System.in);
    static String player1 = null;

    //default constructor
    public PlayerA() {
    }

    /**
     * Parametrized constructor with argument as port number
     * @param port port number
     */
    public PlayerA(int port) {
        try {
            clientSocket = new ServerSocket(port);
            System.out.println("Listening on port: " + clientSocket.getLocalPort());
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Parse the commandlind arguments and set port.
     */
    public void parseArgs(String args[]) {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-port")) {
                port = new Integer(args[++i]).intValue();
                new PlayerA(port).start();
            }
        }
    }

    /**
     * This is run method which creates a connection with client
     * and play battleship through connection using TCP.
     */
    public void run() {
        PrintWriter out = null;
        BufferedReader inputStream = null;
        System.out.println("Enter the Player name: ");
        player1 = in.nextLine();
        gameBoardServer.resetAndDisplayBoard();
        gameBoardServer.setShip();

        try {
            while(true) {

                Socket clientConnection = clientSocket.accept();
                out = new PrintWriter(clientConnection.getOutputStream(), true);
                inputStream = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
                out.println(player1);

                String opponent_name = inputStream.readLine();
                System.out.println("\n Opponent name is " + opponent_name);
                gameBoardServer.setBomb();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * The main program
     * @param argv -port
     */
    public static void main(String[] argv) {
        if(argv.length == 0) {
            new PlayerA(0).start();
        }
        else {
            new PlayerA().parseArgs(argv);
        }
    }
}
