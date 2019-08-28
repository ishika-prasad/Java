package connectoai;

/**
 * This class is the logical implementation
 * for the computer play for the connect four game.
 *
 * @author  Ishika Prasad
 * @author  Pavitra Sapaliga
 */

import java.util.Random;

public class ComputerPlayer implements ConnectFourPlayerInterface {

    String playerName ;
    char gamePiece;
    int turn = 2;
    int playerNumber = 0;
    int noOfWins = 0;
    public ComputerPlayer() {
        this.playerName = "Computer Player";
    }

    public ComputerPlayer(String nameOfPlayer){
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
     * This method is to perform temporary turn to check
     * if computer wins or player wins then it will take
     * the turn at that column otherwise random move.
     *
     * @param columnSelected column to take turn
     * @param player current player
     * @param gameBoard connect four board
     * @return row where game piece needs to be set
     */
    public int performTempTurn(int columnSelected, ConnectFourPlayerInterface player, char[][] gameBoard) {
    	int row = -1;
        if(columnSelected >=0 && columnSelected < gameBoard[0].length) {
            for (int i = gameBoard.length - 1; i >= 0; --i) {
                if (gameBoard[i][columnSelected] == '.') {
                    gameBoard[i][columnSelected] = player.getGamePiece();
                    row = i ;
                    break;
                }
            }
        }
        return row;
    }
    
    /**
     * This method will return the column selected by the player
     * Computer player will perform the following analysis
     * 1. Find the column from which computer can win the game
     * 2. If step 1 does not return true then it will find and then occupy the column by which human player might win the game
     * 3. if step 2 does not return true then it will return random value for column
     */
    @Override
    public int takeTurn(ConnectFourGameInterface game1) {
    	ConnectFourGame game = (ConnectFourGame)game1;
    	char[][] gameBoard = game.gameBoard;
    	// run temporary move on each column and try to identify the column for computer player to win
    	for(int i = 0; i < gameBoard[0].length; i++) {
    		if(gameBoard[0][i] == '.') {
    			int row = performTempTurn(i,this,gameBoard);
    			boolean isSelfWin = checkColumnForComputerWin(gameBoard,game.player1);
    			gameBoard[row][i] = '.';
    			if(isSelfWin) {  
    		    	System.out.println(getName()+ " " + getPlayerNumber() +": selected column "+i);
    				return i;
    			}
    		}
    	}
    	// run temporary move on each column for human player and try to identify the column for which other player can win  
    	for(int i = 0; i < gameBoard[0].length; i++) {
    		if(gameBoard[0][i] == '.') {
    			int row = performTempTurn(i,game.player1,gameBoard);
    			boolean isPlayerWin = checkColumnForHumanPlayerWin(gameBoard,game.player1);
    			gameBoard[row][i] = '.';
    			if(isPlayerWin) {
    		    	System.out.println(getName()+ " " + getPlayerNumber() +": selected column "+i);
    				return i;
    			}
    		}
    	}
    	// select random column 
    	int colSelected = new Random().nextInt(gameBoard[0].length);
    	//if column is full then randmly select till you get
    	while (gameBoard[0][colSelected] != '.') {
    		colSelected = new Random().nextInt(gameBoard[0].length);
    	}
    	System.out.println(getName()+ " " + getPlayerNumber() +": selected column "+colSelected);
    	return colSelected;
    }

    /**
     * This method will check all the possible combination
     * of horizontally, vertically, diagonally for the human
     * player to win
     * player t
     * @param gameBoard connect four board
     * @param player1 player1
     * @return true if matches any of combination otherwise false
     */
    private boolean checkColumnForHumanPlayerWin(char[][] gameBoard, ConnectFourPlayerInterface player1) {
    	if(checkHorizontal(player1,gameBoard) || checkVertical(player1,gameBoard) || 
    			checkLeftDiagonal(player1, gameBoard) || checkRightDiagonal(player1, gameBoard)) {
    		return true;
    	}    	
		return false;
	
    }

    /**
     * This method will check all the possible combination
     * of horizontally, vertically, diagonally for the computer
     * to win
     * player t
     * @param gameBoard connect four board
     * @param player1 player1
     * @return true if matches any of combination otherwise false
     */
	private boolean checkColumnForComputerWin(char[][] gameBoard, ConnectFourPlayerInterface player1) {
    	if(checkHorizontal(this,gameBoard) || checkVertical(this,gameBoard) || 
    			checkLeftDiagonal(this, gameBoard) || checkRightDiagonal(this, gameBoard)) {
    		return true;
    	}    	
		return false;
	
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
     * This method will get the player name
     * @return
     */
    public int getPlayerNumber() {
        return this.playerNumber;
    }

    /**
     * This method checks if four consecutive game piece
     * has matched horizontally.
     * @param player computer player
     * @param gameBoard connect four board
     * @return true if horizontal match found. Otherwise, false
     */
    public boolean checkHorizontal(ConnectFourPlayerInterface player, char[][] gameBoard) {

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length-3; j++) {
                if (gameBoard[i][j] == player.getGamePiece()) {
                    if (gameBoard[i][j] == gameBoard[i][j+1] && gameBoard[i][j+1] == gameBoard[i][j+2] &&
                            gameBoard[i][j+2] == gameBoard[i][j+3]) {
                    	return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * This method checks if four consecutive game piece
     * has matched vertically.
     * @param player computer player
     * @param gameBoard connect four board
     * @return true if vertical match found. Otherwise, false
     */
    public boolean checkVertical(ConnectFourPlayerInterface player, char[][] gameBoard) {
        for (int i = 0; i < gameBoard[0].length; i++) {
            for (int j = 0; j < gameBoard.length-3; j++) {
                if (gameBoard[j][i] == player.getGamePiece()) {
                    if (gameBoard[j][i] == gameBoard[j+1][i] && gameBoard[j+1][i] == gameBoard[j+2][i] &&
                            gameBoard[j+2][i] == gameBoard[j+3][i]) {
                    	return true;
                    }
                } 
            }
        }
        return false;

    }

    /**
     * This method checks if four consecutive game piece
     * has matched left diagonally.
     * @param player computer player
     * @param gameBoard connect four board
     * @return true if left diagonal match found. Otherwise, false
     */
    public boolean checkLeftDiagonal(ConnectFourPlayerInterface player, char[][] gameBoard) {
        for(int i = 0; i < gameBoard.length-3; i++) {
            for(int j = 0; j < gameBoard[i].length-3; j++) {
                if(gameBoard[i][j] == player.getGamePiece()) {
                    if(gameBoard[i][j] == gameBoard[i+1][j+1] && gameBoard[i+1][j+1] == gameBoard[i+2][j+2] &&
                        gameBoard[i+2][j+2] == gameBoard[i+3][j+3]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method checks if four consecutive game piece
     * has matched right diagonally.
     * @param player computer player
     * @param gameBoard connect four board
     * @return true if right diagonal match found. Otherwise, false
     */
    public boolean checkRightDiagonal(ConnectFourPlayerInterface player, char[][] gameBoard) {

        for (int i = 0; i < gameBoard.length - 3; i++) {
            for (int j = 3; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == player.getGamePiece()) {
                    if (gameBoard[i][j] == gameBoard[i+1][j-1] && gameBoard[i+1][j-1] == gameBoard[i+2][j-2] &&
                            gameBoard[i+2][j-2] == gameBoard[i+3][j-3]) {
                        return true;

                    }
                }
            }
        }
        return false;

    }
}
