/*
 * Huffman.java
 *
 * Version:
 *     1
 */

/**
 * This program is the implementation of
 * Huffman coding
 *
 * @author  Ishika Prasad
 */
import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
class HuffmanNode{
    int data;
    char characters;

    HuffmanNode left;
    HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y)
    {

        return x.data - y.data;
    }
}

class Huffman {
    private static String fileName;
    private static char fileContents[] = new char[1000];
    private static int priorityLength = 0, count = 0;
    private static int frequency[] = new int[1000];
    /**
     * Gets the file contents in String format if the file exists.
     *
     * @param       -
     *
     * @return      char[]  charArray of the string
     */
    private static char[] getFileContents() throws IOException {
        String fileString = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file name: ");
        //Checks if file exists
        try {
            fileName = sc.next();
            BufferedReader bufferedReader;
            InputStreamReader in = new InputStreamReader(new FileInputStream(fileName));
            bufferedReader = new BufferedReader(in);
            String aLine = bufferedReader.readLine();
            //reads the file content and stores in a string.    +
            while (aLine != null) {
                fileString += aLine;
                aLine = bufferedReader.readLine();

            }
        } catch (FileNotFoundException fe) {
            System.out.println("File not found." + fe);

        }
        char fileChars[] = fileString.toCharArray();
        return fileChars;
    }

    /**
     * checks if character is already present in the fileContents array.
     *
     * @param       word    char input
     *
     * @return      true or false
     */
    private static boolean wordPresent(char word){
        count = 0;
        for(int index = 0; index < priorityLength; index++) {
            if (fileContents[index] == word){
                return true;
            }
            count++;
        }
        return false;
    }

    /**
     * Creates an array without repetations and increments the frequency
     * incase of repetation of a character.
     *
     * @param       fileChars       char[] input
     *
     * @return      void
     */
    private static void getPriorityQueue(char[] fileChars){

        for(int index = 0; index < 1000; index++){
            frequency[index] = 0;
        }
        for(int index = 0; index < fileChars.length; index++){
            if(wordPresent(fileChars[index])){
                frequency[count-1]++;
            }else {
                fileContents[priorityLength] = fileChars[index];
                frequency[priorityLength]++;
                priorityLength++;
            }
        }

    }

    /**
     * Creates nodes without linking them to each other.
     *
     * @param       q       Priority Queue input.
     *
     * @return      void
     */
    public static void createTree(PriorityQueue<HuffmanNode> q){

        for (int i = 0; i < priorityLength; i++) {

            // creating a huffman node object
            // and adding it to the priority-queue.
            HuffmanNode huffman = new HuffmanNode();

            huffman.characters = fileContents[i];
            huffman.data = frequency[i];

            huffman.left = null;
            huffman.right = null;

            // add functions adds
            // the huffman node to the queue.
            q.add(huffman);
        }
    }

    /**
     * Gets the file contents in String format if the file exists.
     *
     * @param       -
     *
     * @return      HuffmanNode  Returns the root node
     */
    public static HuffmanNode createPriorityTree(){
        PriorityQueue<HuffmanNode> q
                = new PriorityQueue<HuffmanNode>(priorityLength, new MyComparator());
        createTree(q);
        HuffmanNode root = null;
        while (q.size() > 1) {

            // first min extract.
            HuffmanNode x = q.peek();
            q.poll();

            // second min extarct.
            HuffmanNode y = q.peek();
            q.poll();

            // new node prioritySort which is equal
            HuffmanNode prioritySort = new HuffmanNode();

            // to the sum of the frequency of the two nodes
            // assigning values to the prioritySort node.
            prioritySort.data = x.data + y.data;
            prioritySort.characters = '+';

            // first extracted node as left child.
            prioritySort.left = x;

            // second extracted node as the right child.
            prioritySort.right = y;

            // marking the f node as the root node.
            root = prioritySort;

            // add this node to the priority-queue.
            q.add(prioritySort);
        }
        return root;
    }

    /**
     * Gets the file contents in String format if the file exists.
     *
     * @param       root,code,currentChar   Huuffman node, String, char input
     *
     * @return      char[]  charArray of the string
     */
    public static void huffmanEncode(HuffmanNode root, String code,
                                     char currentChar) throws FileNotFoundException,
            UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("encoded_war_and_peace.txt",
                "UTF-8");



        if (root.characters == currentChar) {

            // c is the character in the node
            writer.println(code);

        }else {
            if(root.left != null) {
                //add 0 if we go to left
                huffmanEncode(root.left, code + "0", currentChar);
            }
            if(root.right != null) {
                //add 1 if we go to right
                huffmanEncode(root.right, code + "1", currentChar);
            }
        }
        writer.close();
    }

    /**
     * The main method
     *
     * @param       args        String[] input
     *
     * @return      void
     */
    public static void main(String[] args) throws IOException {
        char[] fileContent = getFileContents();
        String code;
        getPriorityQueue(fileContent);
        HuffmanNode root = createPriorityTree();

        for(int i = 0; i < fileContents.length; i++) {
            code = "";
            huffmanEncode(root, code, fileContents[i]);

        }

    }

}
