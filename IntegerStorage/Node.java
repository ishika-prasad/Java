/**
 * This class is Node class with prev and next pointer
 *
 * @author      Pavitra Sapaliga
 * @author      Ishika Prasad
 */
class Node {
    public Node( int value,  Node prev, Node next) {
        this.value = value;
        this.next = next;//stores the location of next element
        this.prev =prev;//stores location of previous element
    }
    public int value;
    public Node next;
    public Node prev;
}