/*
 * WordSearch.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program checks if the word entered by the user is present in the
 * given file in horizontally, vertically or diagonally either inn forward or
 * reverse orientation.
 *
 *
 * @author  Ishika Prasad
 *
 */
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSearch  {
    static String filename;
    static String puzzle = "";
    static String wordToFind;
    static String reverseWordToFind;
    static int puzzleLength = 0;
    static String verticalPuzzle = "";
    static int verticalPosition;

    /**
     * This method is called by the main method and performs the search and
     * decides on the orientation.
     *
     * @param   -
     *
     * @return  void
     *
     */
    public static void searchWord() {
        reverseWordToFind = getReverse();
        String orientation;
        Pattern pattern = Pattern.compile(wordToFind);
        Matcher matcher = pattern.matcher(puzzle);
        Pattern pattern2 = Pattern.compile(reverseWordToFind);
        Matcher matcher2 = pattern2.matcher(puzzle);
        String match = "";
        //Checks for presence of word in different orientations
        if (horizontalSearchWord(matcher)) {
            orientation = "on row";
            match = "" + ((matcher.start() / puzzleLength) + 1) + " in";
        }else if(horizontalSearchWord(matcher2)){
            orientation = "on row";
            match = "" + ((matcher2.start() / puzzleLength) + 1) + " in";
        }else if(verticalSearchWord(pattern)){
            orientation = "in column";
            match = ((verticalPosition / puzzleLength) + 1) + " in";
        }else if(verticalSearchWord(pattern2)){
            orientation = "in column";
            match =((verticalPosition / puzzleLength) + 1) + " in";
        }else if(diagonalSearchWord(pattern)){
            orientation = "across";
            match = "";
        }else if(diagonalSearchWord(pattern2)){
            orientation = "across";
            match = "";
        }else {
            orientation = " ";

        }
            display(orientation, match);
    }


    /**
     * This method is called by the searchWord method and displays if a word
     * is present in the given file or not.
     *
     * @param   orientation,matcher     String,String
     *
     * @return  void
     *
     */
    private static void display(String orientation, String matcher){
        if (orientation.equals(" ")){
            System.out.println("The word " + wordToFind + " is not present in" +
                    " the puzzle");
        }else{
            System.out.println("The word " + wordToFind + " is present "
                     + orientation + " " + matcher + " puzzle" );
        }
    }

    /**
     * This method returns true if the word is found or false otherwise.
     *
     * @param   matcher     Matcher
     *
     * @return  boolean     true if match found else false
     *
     */
    public static boolean horizontalSearchWord(Matcher matcher){

        if(matcher.find()){
            return true;
        }
        return false;
    }

    /**
     * This method changes the puzzle orientation to vertical and calls the
     * horizontalSearchWord method to find the match.
     *
     * @param   pattern    Pattern
     *
     * @return  boolean     true if match found else false
     *
     */
    public static boolean verticalSearchWord(Pattern pattern){
        //Changes orientation of puzzle to vertical
        for (int index = 0; index < puzzleLength; index++) {
            for (int counter = index; counter <puzzle.length(); counter = counter+puzzleLength+1) {
                verticalPuzzle =
                        verticalPuzzle + puzzle.charAt(counter);

            }
            verticalPuzzle =
                    verticalPuzzle + " ";
        }

        //create matcher for the new orientation of puzzle
        Matcher matcher = pattern.matcher(verticalPuzzle);

        if(horizontalSearchWord(matcher)){
            verticalPosition = matcher.start();
            return true;
        }
        return false;
    }

    /**
     * This method changes the puzzle orientation to diagonal and calls
     * horizontalSearchWord to return true if the word is found or false
     * otherwise.
     *
     * @param   pattern     Pattern
     *
     * @return  boolean     true if match found else false
     *
     */
    public static boolean diagonalSearchWord(Pattern pattern){
        String diagonalPuzzle = "";
        //Changes orientation of puzzle to diagonal
        for(int index = 0; index<puzzleLength;index++){
            for(int counter = index; counter <puzzle.length(); counter =
                    counter + puzzleLength){
                diagonalPuzzle = diagonalPuzzle + puzzle.charAt(counter);
            }
            System.out.print(" ");
        }
        //Create new matcher for the diagonal orientation of the puzzle
        Matcher matcher = pattern.matcher(diagonalPuzzle);
        if(horizontalSearchWord(matcher)){
            return true;
        }else{
            diagonalPuzzle = "";
            //If match not found in the first diagonal format get the second
            // diagonal format
            for(int index = 0; index<=puzzleLength+1;index++){
                for(int counter = index; counter <puzzle.length(); counter =
                        counter + puzzleLength+2){
                    diagonalPuzzle = diagonalPuzzle + puzzle.charAt(counter);
                }
                System.out.print(" ");
            }
            //matcher for the second diagonal format
            matcher = pattern.matcher(diagonalPuzzle);
            if(horizontalSearchWord(matcher)){
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns reverse of the word to be searched.
     *
     * @param   -
     *
     * @return  String     reverse-returns reverse of the input word.
     *
     */
    public static String getReverse(){
        String reverse = "";
        for(int index = wordToFind.length()-1;index>=0;index--){
            reverse = reverse + wordToFind.charAt(index);

        }
        return reverse;
    }

    /**
     * This method gets the word to be searched
     *
     * @param   -
     *
     * @return  void
     *
     */
    public static void playGame(){
        Scanner sc = new Scanner(System.in);
        System.out.println("What word would you like to find? : ");
        wordToFind = sc.next();

        if(wordToFind.equals("exit")){
            System.exit(2);
        }
        if(wordToFind.length()<=puzzleLength) {
            searchWord();
        }else{
            System.out.println("The word " + wordToFind + " is not present in" +
                    " the puzzle");
        }
    }

    /**
     * The main program.
     * @param   args    -
     *
     *
     */
    public static void main(String[] args) throws IOException {
        int flag, lineLength=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file name: ");
        //Checks if file exists
        while(true) {
            try {
                flag = 0;
                filename = sc.next();
                BufferedReader bufferedReader;
                InputStreamReader in = new InputStreamReader(new FileInputStream(filename));
                bufferedReader = new BufferedReader(in);
                String aLine = bufferedReader.readLine();
                lineLength = aLine.length();
                //reads the file content and stores in a string.
                while (aLine != null) {
                    puzzle = puzzle + aLine + " ";
                    puzzleLength++;
                    //checks if each line has equal number of characters
                    if(aLine.length() == lineLength){
                        System.out.println(aLine);
                    }else {
                        flag = 1;
                        break;
                    }
                    aLine = bufferedReader.readLine();

                }
                //Checks if equal number characters are present horizontally
                // and vertically
                if(puzzleLength != lineLength){
                    System.out.println("Equal number of horizontal and " +
                            "vertical characters required to play the game");
                    flag = 1;
                }
            } catch (FileNotFoundException fe) {
                flag = 1;

            }
            if(flag == 0 || filename.equals("exit")) {
                break;
            }else{
                System.out.println("File not found or error in file content. " +
                        "\n Enter the file name or enter 'exit' to exit " +
                        "puzzle.");
            }

        }
        if(filename.equals("exit")){
            System.exit(1);
        }
        while(true){
            playGame();
        }


    }

}
