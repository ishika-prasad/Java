/**
 * This class implements a double linked list with int values as input
 * and performs operations on them.
 *
 * @author      Pavitra Sapaliga
 * @author      Ishika Prasad
 */
public class IntegerLinkedList extends IntegerStorageImplementation {
    public Node head = null;
    public Node tail = null;
    public Node cursor = null;

    /**
     * Appends the specified element to the end of this list.
     *
     * @param       o    int input
     *
     * @return           true or false
     */
    public boolean add(int o) {
        int oldSize = size();//Gets the original size of set
        Node newNode = new Node(o, tail, null);
        if (head == null) {//for 1st element
            head = newNode;
        } else if (head.next == null) {//For 2nd element
            head.next = newNode;
            newNode.prev = head;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        //Checks if element was added successfully
        size ++;
        if (oldSize + 1 == size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Appends the specified element to the location specified by the user.
     *
     * @param       index,element    int input
     *
     * @return           void
     */
    public void add(int index, int element) {
        index ++;
        int count = 0;
        int oldSize = size();//Gets the original size of set
        if (index > oldSize+1) {
            System.out.println("Index exceeds size. Enter other index.");
        } else {
            Node newNode = new Node(element, null, null);
            size ++;
            if (index == 1) {
                if (head != null) {
                    newNode.next = head;
                    head.prev = newNode;

                } else {
                    tail = newNode;
                }
                head = newNode;
            } else if (index == oldSize+1) {
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
            } else {
                cursor = head;
                while (count < index-1) {
                    cursor = cursor.next;
                    count++;
                }
                cursor.prev.next = newNode;
                newNode.prev = cursor.prev;
                newNode.next = cursor;
                cursor.prev = newNode;

            }


        }

    }

    /**
     * Empties the set.
     *
     * @param       -
     *
     * @return           void
     */
    public void clear() {
        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

    /**
     * Checks if the integer is present in the linked list
     *
     * @param       o    int input
     *
     * @return           true or false
     */
    public boolean contains(int o) {
        Node nodeCounter = head;
        while (nodeCounter != null) {//loop till last element
            if (o == nodeCounter.value) {//checks if elemennt is present
                return true;

            } else {
                nodeCounter = nodeCounter.next;
            }
        }
        return false;
    }

    /**
     * Returns the value at the index passed
     *
     * @param       index    int input
     *
     * @return      cursor.value or -1     int
     */
    public int get(int index) {
        int count = 0;
        index ++;
        int oldSize = size();//Gets the original size of set
        if (head != null) {
            if (index > oldSize) {
                System.out.println("Index exceeds size. Enter other index.");
            } else {
                if (index == 1) {
                    return head.value;

                } else if (index == oldSize) {
                    return tail.value;
                } else {
                    cursor = head;
                    while (count != index - 1) {
                        cursor = cursor.next;
                        count++;
                    }
                    return cursor.value;

                }


            }
        } else {
            System.out.println("No elements in list");
        }
        return -1;
    }

    /**
     * Returns the index of first occurrence of the value passed.
     *
     * @param       o    int input
     *
     * @return      count or -1
     */
    public int indexOf(int o) {
        int count = 1;
        if (head != null) {
            cursor = head;
            while (cursor != null) {
                if (o == cursor.value) {
                    return count-1;
                }
                cursor = cursor.next;
                count++;
            }
        }
        return -1;


    }

    /**
     * Returns the index of last occurrence of the value passed.
     *
     * @param       o    int input
     *
     * @return      count or -1
     */
    public int lastIndexOf(int o) {
        int count = size();//Gets the original size of set
        if (head != null) {
            cursor = tail;
            while (cursor != null) {
                if (o == cursor.value) {
                    return count-1;
                }
                cursor = cursor.prev;
                count--;
            }
        }
        return -1;
    }

    /**
     * Removes the element at the given index
     *
     * @param       index    int input
     *
     * @return           true or false
     */
    public int remove(int index) {
        index ++;
        int count = 0;
        int oldSize = size();//Gets the original size of set
        if (index > oldSize) {
            System.out.println("Index exceeds size. Enter other index.");
        } else {
            if (head != null) {
                if (index == 1) {
                    cursor = head;
                    head = head.next;
                    head.prev = null;
                    if(head == null){
                        tail = null;
                    }

                } else if (index == oldSize) {
                    cursor = tail;
                    tail = tail.prev;
                    tail.next = null;
                    if(tail == null){
                        head = null;
                    }

                } else {
                    cursor = head;
                    while (count < index) {
                        cursor = cursor.next;
                        count++;
                    }
                    cursor.prev.next = cursor.next;
                    cursor.next.prev = cursor.prev;


                }
                size --;
                return cursor.value;
            }else{
                System.out.println("No elements in list to remove");
            }

        }
        return -1;
    }

    /**
     * Removes the passed element from this set if it is present in it.
     *
     * @param       o    Integer input
     *
     * @return           true or false
     */
    public boolean	remove(Integer o){
        //Node nodeCounter = head;
        if(contains(o)){//Checks if set contains element
            if(head.value == o){//checks if value is at 1st position
                head=head.next;
                head.prev = null;
            }else if(tail.value == o){//checks if value is at last position
                tail=tail.prev;
                tail.next = null;
            }else {
                cursor = head;
                while (cursor != null) {//checks if nodeCounter is at the
                    // last element position
                    if (cursor.value == o){
                        cursor.prev.next = cursor.next;
                        cursor.next.prev = cursor.prev;
                        break;
                    }
                    cursor = cursor.next;
                }
            }
            size --;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Replaces the value at the specified index with the passed value.
     *
     * @param       index,element    int input
     *
     * @return      replaced integer
     */
    public int	set(int index, int element){
        index ++;
        int count = 0;
        int oldValue = -1;
        int oldSize = size();//Gets the original size of set
        if (index > oldSize) {
            System.out.println("Index exceeds size. Enter other index.");
        } else {
            if (index == 1) {
                oldValue = head.value;
                head.value = element;

            } else if (index == oldSize) {
                oldValue = tail.value;
                tail.value = element;
            } else {
                cursor = head;
                while (count < index - 1) {
                    cursor = cursor.next;
                    count++;
                }
                oldValue = cursor.value;

            }


        }
        return oldValue;
    }


}