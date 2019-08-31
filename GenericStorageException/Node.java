/*
 * Node.java
 *
 * Version:
 *     1
 */

/**
 * This class is Node class with prev and next pointer
 *
 * @author      Ishika Prasad
 *
 */
class Node<T> {
    public Node(T value, Node<T> prev, Node<T> next) {
        this.value = value;
        this.next = next;//stores the location of next element
        this.prev =prev;//stores location of previous element
    }
    public T value;
    public Node<T> next;
    public Node<T> prev;
}