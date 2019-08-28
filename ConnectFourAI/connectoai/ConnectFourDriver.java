package connectoai;
/*
 * ConnectFourDriver.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program is for playing connect four game
 * with two human players, one human player with
 * one computer player and two computer players.
 *
 * @author  Ishika Prasad
 * @author  Pavitra Sapaliga
 */

public class ConnectFourDriver
{
    public static void main(String[] args)
    {
        ConnectFourPlayerInterface player1 = new HumanPlayer("Bob");
        ConnectFourPlayerInterface player2 = new ComputerPlayer();
        //ConnectFourPlayerInterface player2 = new ComputerPlayer("computer2");
       // ConnectFourPlayerInterface player2 = new HumanPlayer();

        ConnectFourGameInterface game = new ConnectFourGame(player1, player2);
        game.playGame();

    }
}
