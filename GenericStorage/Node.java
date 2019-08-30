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
 * @author      Pavitra Sapaliga
 *
 */
class Node<T> {
    public Node(T value, GenericsStorage.Node<T> prev, GenericsStorage.Node<T> next) {
        this.value = value;
        this.next = next;//stores the location of next element
        this.prev =prev;//stores location of previous element
    }
    public T value;
    public GenericsStorage.Node<T> next;
    public GenericsStorage.Node<T> prev;
}