package connectoai;

/**
 * This class is for the logical implementation of
 * playing connect four game and checking vertically,
 * horizontally and diagonally.
 *
 * @author  Ishika Prasad
 */

import java.util.Scanner;

public class ConnectFourGame implements connectoai.ConnectFourGameInterface {

    int columnSelectedByPlayer = 0;
    int currentTurnCount = 0;
    char[][] gameBoard = new char[6][7];
    Scanner scanner = new Scanner(System.in);
    boolean player1Turn = true;

    ConnectFourPlayerInterface player1;
    ConnectFourPlayerInterface player2;
    ConnectFourGame(ConnectFourPlayerInterface player1, ConnectFourPlayerInterface player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * This method returns the final status of game
     * if player exits the game
     */
    @Override
    public void getStats() {
    	System.out.println(player1.getName() +" (Player "+player1.getPlayerNumber()+") has "+player1.getNumberOfWins() +" wins "
    			+ "and "+player2.getName()+" (Player "+player2.getPlayerNumber()+") has "+player2.getNumberOfWins()+" wins");
    }

    /**
     * This method takes the char piece as a input
     * from user for both players and take turns
     * with the check of vertically, horizontally
     * and diagonally
     */
    @Override
    public void playGame() {

        player1.setPlayerNumber(1);
        player2.setPlayerNumber(2);

        System.out.println(player1.getName() + " select a single char game piece");
        char charPiecePlayer1 = scanner.next().charAt(0);
        //set the game char piece of player1
        player1.setGamePiece(charPiecePlayer1);
        System.out.println(player2.getName() + " select a single char game piece");
        char charPiecePlayer2 = scanner.next().charAt(0);
        //set the game char piece of player2
        player2.setGamePiece(charPiecePlayer2);

        //check if both player1 and player2 doesn't have same char piece
        while(charPiecePlayer1 == charPiecePlayer2) {
            System.out.println(charPiecePlayer2 + " has already taken. select another single char game piece");
            charPiecePlayer2 = scanner.next().charAt(0);
            //set the game char piece of player2
            player2.setGamePiece(charPiecePlayer2);
        }

        //System.out.println(p2);
        System.out.println("Welcome to Connect Four!");
        resetAndDisplayGameBoard();
        
        while(true) {
        	//Check if game board has empty slots
        	if(currentTurnCount < gameBoard[0].length*gameBoard.length) {
            	// If game board is not full
        		currentTurnCount ++;
                if (player1.takeTurn() == 1) {
                    //For player1                
                	columnSelectedByPlayer = player1.takeTurn(this);
                    setGamePieceOnBoard(columnSelectedByPlayer, player1);
                } else {
                    //For player2
                	columnSelectedByPlayer = player2.takeTurn(this);
                    setGamePieceOnBoard(columnSelectedByPlayer, player2);
                }
                display();
                checkHorizontal();
                checkVertical();
                checkLeftDiagonal();
                checkRightDiagonal();	
        	}else {
        		// Game board is full so reset the board on player's request
        		resetOrQuit();
        	}       	
        }

    }

    /**
     * This method set the game piece on the board with the
     * given column and player
     * @param columnByPlayer column in which want to sent the game piece
     * @param player player who wants to set the game piece
     */
    public void setGamePieceOnBoard(int columnByPlayer, ConnectFourPlayerInterface player) {

        if(columnByPlayer >=0 && columnByPlayer < gameBoard[0].length) {
            for (int i = gameBoard.length - 1; i >= 0; --i) {
                if (gameBoard[i][columnByPlayer] == '.') {
                    gameBoard[i][columnByPlayer] = player.getGamePiece();
                    break;
                }
            }
        }
    }

    /**
     * This method display the game board
     */
    public void display()  {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * This method checks if four consecutive game piece
     * has matched horizontally.
     */
    public void checkHorizontal() {

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length-3; j++) {
                if (gameBoard[i][j] == player1.getGamePiece()) {
                    if (gameBoard[i][j] == gameBoard[i][j+1] && gameBoard[i][j+1] == gameBoard[i][j+2] &&
                            gameBoard[i][j+2] == gameBoard[i][j+3]) {
                        System.out.println(player1.getName() + " has won the game");
                        player1.addWin();
                        resetOrQuit();
                        break;
                    }
                } else if (gameBoard[i][j] == player2.getGamePiece()) {
                    if (gameBoard[i][j] == gameBoard[i][j+1] && gameBoard[i][j+1] == gameBoard[i][j+2] &&
                            gameBoard[i][j+2] == gameBoard[i][j+3]) {
                        System.out.println(player2.getName() + " has won the game");
                        player2.addWin();
                        resetOrQuit();
                        break;
                    }
                }
            }
        }
    }
    /**
     * This method take input from user for play again or quit
     */
    private void resetOrQuit() {
		System.out.println("Would you like to play again? y/n");
		String choice = scanner.next();
		if("y".equalsIgnoreCase(choice)) {
			currentTurnCount = 0;
			resetAndDisplayGameBoard();
		}else {
			getStats();
			System.exit(0);
		}
	}
    /**
     * This method reset the game board
     */
	private void resetAndDisplayGameBoard() {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				gameBoard[i][j] = '.';
				System.out.print(gameBoard[i][j]);
			}
			System.out.println();
		}
	}

    /**
     * This method checks if four consecutive game piece
     * has matched vertically.
     */
	public void checkVertical() {
        for (int i = 0; i < gameBoard[0].length; i++) {
            for (int j = 0; j < gameBoard.length-3; j++) {
                if (gameBoard[j][i] == player1.getGamePiece()) {
                    if (gameBoard[j][i] == gameBoard[j+1][i] && gameBoard[j+1][i] == gameBoard[j+2][i] &&
                            gameBoard[j+2][i] == gameBoard[j+3][i]) {
                        System.out.println(player1.getName() + " has won the game");
                        player1.addWin();
                        resetOrQuit();
                        break;
                    }
                } else if (gameBoard[j][i] == player2.getGamePiece()) {
                    if (gameBoard[j][i] == gameBoard[j+1][i] && gameBoard[j+1][i] == gameBoard[j+2][i] &&
                            gameBoard[j+2][i] == gameBoard[j+3][i]) {
                        System.out.println(player2.getName() + " has won the game");   
                        player2.addWin();
                        resetOrQuit();
                        break;
                    }
                }
            }
        }

    }

    /**
     * This method checks if four consecutive game piece
     * has matched left diagonal.
     */
    public void checkLeftDiagonal() {
        for(int i = 0; i < gameBoard.length-3; i++) {
            for(int j = 0; j < gameBoard[i].length-3; j++) {
                if(gameBoard[i][j] == player1.getGamePiece()) {
                    if(gameBoard[i][j] == gameBoard[i+1][j+1] && gameBoard[i+1][j+1] == gameBoard[i+2][j+2] &&
                            gameBoard[i+2][j+2] == gameBoard[i+3][j+3]) {
                        System.out.println(player1.getName() + " has won the game");
                        player1.addWin();
                        resetOrQuit();
                        break;
                    }
                    else  if(gameBoard[i][j] == player1.getGamePiece()) {
                        if(gameBoard[i][j] == gameBoard[i+1][j+1] && gameBoard[i+1][j+1] == gameBoard[i+2][j+2] &&
                                gameBoard[i+2][j+2] == gameBoard[i+3][j+3]) {
                            System.out.println(player2.getName() + " has won the game");
                            player2.addWin();
                            resetOrQuit();
                            break;
                        }
                    }
                }
            }
        }

    }

    /**
     * This method checks if four consecutive game piece
     * has matched right diagonal.
     */
    public void checkRightDiagonal() {

        for (int i = 0; i < gameBoard.length - 3; i++) {
            for (int j = 3; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == player1.getGamePiece()) {
                    if (gameBoard[i][j] == gameBoard[i+1][j-1] && gameBoard[i+1][j-1] == gameBoard[i+2][j-2] &&
                            gameBoard[i+2][j-2] == gameBoard[i+3][j-3]) {
                        System.out.println(player1.getName() + " has won the game");
                        player1.addWin();
                        resetOrQuit();
                        break;
                    } else if (gameBoard[i][j] == player2.getGamePiece()) {
                        if (gameBoard[i][j] == gameBoard[i+1][j-1] && gameBoard[i+1][j-1] == gameBoard[i+2][j-2] &&
                                gameBoard[i+2][j-2] == gameBoard[i+3][j-3]) {
                            System.out.println(player2.getName() + " has won the game");
                            player2.addWin();
                            resetOrQuit();
                            break;
                        }
                    }
                }
            }
        }

    }
}