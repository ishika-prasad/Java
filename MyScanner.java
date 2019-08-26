/*
 * MyScanner.java
 *
 * Version:
 *     1
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This program is to implement own Scanner class
 * which is the wrapper class for Java's implementation
 * of the Scanner class.
 *
 * @author  Ishika Prasad
 */
public class MyScanner {

    Scanner fileContent = null;
    static String file = "test_file.txt";

    public MyScanner(String file) {
        try {
            fileContent = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method return true if this scanner
     * has another token in its input.
     */
    public boolean hasNext() {
        return fileContent.hasNext();
    }

    /**
     * This method closes the scanner.
     */
    public void close() {
        fileContent.close();
    }

    /**
     * This method advances this scanner past
     * the current line and returns the input
     * that was skipped.
     * @return input of file which is skipped
     */
    public String nextLine() {
        //lineCount++;
        String nextline = fileContent.nextLine();
        //characterCount += nextline.length();
        return nextline;
    }

    /**
     * This method returns the character
     * count of the input file
     * @return character count
     */
    public int getCharacterCount() {
        int characterCount = 0;
        Scanner fileContent  = null;
        try {
            fileContent  = new Scanner(new File( file) );
        } catch ( Exception e )	{}
        fileContent.reset();
        fileContent.useDelimiter("");
        while ( fileContent.hasNextLine() ) {
            characterCount += fileContent.nextLine().length();
        }
        fileContent.close();
        return characterCount;
    }

    /**
     * This method returns the line count
     * of the input file
     * @return line count
     */
    public int getLineCount() {
        int lineCount = 0;
        Scanner fileContent  = null;
        try {
            fileContent  = new Scanner(new File( file) );
        } catch ( Exception e )	{}
        fileContent.reset();
        fileContent.useDelimiter(" ");
        while(fileContent.hasNext()) {
            lineCount++;
            fileContent.nextLine();
        }
        fileContent.close();
        return lineCount;
    }

    /**
     * The main program.
     *
     *@param args command line arguments (ignored)
     */
    public static void main(String[] args) {
        MyScanner sc = new MyScanner(file);

        //Test case sample uses all methods for wrapper class
        System.out.println("Print first two lines from file:");
        System.out.println("\t" + sc.nextLine());
        System.out.println("\t" + sc.nextLine());
        System.out.println("file line count: " + sc.getLineCount());
        System.out.println("file character count: " + sc.getCharacterCount());
        System.out.println();
        System.out.println("Next line in file: ");
        System.out.println("\t" + sc.nextLine());
        System.out.println("file line count: " + sc.getLineCount());
        System.out.println("file character count: " + sc.getCharacterCount());
        System.out.println();
        System.out.println("Last line in file: ");
        while(sc.hasNext()) {
            System.out.println("\t" + sc.nextLine());
        }
        System.out.println("file line count: " + sc.getLineCount());
        System.out.println("file character count: " + sc.getCharacterCount());
        sc.close();

    }
}
