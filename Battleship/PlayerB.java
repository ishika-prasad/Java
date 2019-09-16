/*
 * PlayerB.java
 *
 * Version:
 *     1
 *
 */

/**
 * This class acts as a client class using TCP.
 *
 * @author  Ishika Prasad
 *
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//client
public class PlayerB {
    String host = "localhost";
    int port = 6737;

    GameBoardServer gameBoardServer = new GameBoardServer();
    Scanner in = new Scanner(System.in);

    /**
     * Parse the commandlind arguments and sets variables.
     */
    public void parseArgs(String args[]) {
        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-host")) {
                host = args[++i];
            }
            else if(args[i].equals("-port")) {
                port = new Integer(args[++i]).intValue();
            }
        }
    }

    /**
     * This method which creates a connection with server
     * and play battleship through connection using TCP.
     */
    public void playGame() {
        System.out.println("Enter Player2 name: ");
        String player2 = in.nextLine();
        gameBoardServer.resetAndDisplayBoard();
        gameBoardServer.setShip();
        try ( Socket socket = new Socket(host, port);
            BufferedReader dataStreamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

            String opponent_name = dataStreamIn.readLine();
            System.out.println("\n Opponent name is: " + opponent_name);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(player2);

            //gameBoardServer.resetAndDisplayBoard();
            gameBoardServer.setBomb();

        }catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * The main program
     * @param argv -port
     */
    public static void main(String[] argv) {
        PlayerB playerB = new PlayerB();
        playerB.parseArgs(argv);
        playerB.playGame();
    }
}
