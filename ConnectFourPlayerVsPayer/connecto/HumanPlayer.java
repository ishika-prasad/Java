package connecto;

/**
 * This class is provide the implementation for
 * ConnectFourInterface.
 *
 * @author  Ishika Prasad
 * @author  Pavitra Sapaliga
 */

public class HumanPlayer implements ConnectFourPlayerInterface {

    String playerName ;
    char gamePiece;
    int turn = 2;
    int playerNumber = 0;
    int noOfWins = 0;
    public HumanPlayer() {
        this.playerName = "Group 9";
    }

    public HumanPlayer(String nameOfPlayer){
        this.playerName = nameOfPlayer;
    }

    /**
     * This method return 1 for player1 and return 2
     * for player2
     * @return 1 for player1 and 2 for player2
     */
    @Override
    public int takeTurn() {
        //return 1 for player1 and return 2 for player 2
        if(turn == 1){
            turn = 2;
            return this.turn;
        }
        else {
            turn = 1;
            return this.turn;
        }
    }

    /**
     * This method will get the name of the current
     * player
     * @return name of the current player
     */
    @Override
    public String getName() {
        return this.playerName;
    }

    /**
     * This method will return the number of
     * wins for each player
     * @return number of wins
     */
    @Override
    public int getNumberOfWins() {
        return noOfWins;
    }

    /**
     * This method will increment the
     * number of wins for each player.
     */
    @Override
    public void addWin() {
        noOfWins++;
    }

    /**
     * This method will get the game piece.
     * @return game piece
     */
    @Override
    public char getGamePiece() {
        return this.gamePiece;
    }

    /**
     * This method will set the game piece
     * @param gamePiece game piece of each player
     */
    @Override
    public void setGamePiece(char gamePiece) {
        this.gamePiece = gamePiece;

    }

    /**
     * This method will set the player number
     * @param num player number
     */
    @Override
    public void setPlayerNumber(int num) {
        this.playerNumber = num;
    }

    /**
     * This method will get the player number
     * @return player number
     */
    public int getPlayerNumber() {
        return this.playerNumber;
    }


}
