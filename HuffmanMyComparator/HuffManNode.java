/**
 * This class is for huffman node.
 *
 * @author  Ishika Prasad
 */
public class HuffManNode {
    HuffManNode left, right;
    long data;
    String character;

    /**
     * This constructor takes data and character
     * as parameters
     * @param data frequency of a character
     * @param character character in a file
     */
    public HuffManNode(long data, String character) {
        this.data = data;
        this.character = character;
        left = null;
        right = null;
    }

    /**
     * This constructor place the node with frequency and character
     * according to the data or frequency of a character.
     * @param left node with data and character of left
     * @param right node with data and character of right
     */
    public HuffManNode(HuffManNode left, HuffManNode right) {
        this.data = left.data + right.data;
        character = left.character + right.character;
        if (left.data < right.data) {
            this.right = right;
            this.left = left;
        } else {
            this.right = left;
            this.left = right;
        }
    }
}