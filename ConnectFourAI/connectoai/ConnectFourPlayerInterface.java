package connectoai;

/**
 * This is the interface which is implemented
 * by HumanPlayer class
 *
 * @author  Ishika Prasad
 * @author  Pavitra Sapaliga
 */
interface ConnectFourPlayerInterface
{
    int takeTurn();
    int takeTurn(ConnectFourGameInterface game);
    String getName();
    int getNumberOfWins();
    void addWin();
    char getGamePiece();
    void setGamePiece(char gamePiece);
    void setPlayerNumber(int num);
    int getPlayerNumber();
}
