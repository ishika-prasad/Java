/*
 * IntegerArrayList.java
 *
 * Version:
 *     1
 */

/**
 * This program is the implementation of arraylist
 * using array.
 *
 * @author  Ishika Prasad
 * @author  Pavitra Sapaliga
 */
public class IntegerArrayList extends IntegerStorageImplementation {
    int initialCapacity = 10;
    int arr[] = new int[initialCapacity];
    int capacity = initialCapacity;
    /**
     * This method appends the specified element
     * to the end of this list.
     * @param o element that will appended
     * @return true if added
     */
    @Override
    public boolean add(int o) {

        int length = size();
        if(length < capacity) {
            arr[length] = o;
        }else {
            capacity = capacity*2;
            int new_arr[] = new int[capacity];
            //copy the elements from previous array
            for(int i = 0; i < length; i++) {
                new_arr[i] = arr[i];
            }
            //append the new element in new array
            new_arr[length] = o;
            arr = new_arr;
        }
        //increase the size of array by one

        size += 1;
        return true;
    }

    /**
     * This method inserts the specified element at the
     * specified position in this list.
     * @param index position where element needs to be inserted
     * @param element element which is being inserted
     */
    @Override
    public void add(int index, int element) {
        if(index >= size) {
            System.out.println("Index exceeds size. Enter other index.");
        }else {
            int length = size;
            //increase the size of array by one
            int new_arr[] = new int[length+1];
            //copy the elements from previous array
            for(int i = 0; i < length; i++) {
                new_arr[i] = arr[i];
            }
            //shift by one until reached the specified index
            for(int i = length-1; i >= index; i--) {
                new_arr[i+1] = new_arr[i];
            }
            new_arr[index] = element;
            //referring array to new array
            arr = new_arr;
            size += 1;
        }

    }

    /**
     * This method removes all of the elements from this list.
     */
    @Override
    public void clear() {
        //arr is pointing to new array with size 0
        arr = new int[initialCapacity];
        capacity = initialCapacity;
        size = 0;
    }

    /**
     * Returns true if this list contains the specified element.
     * @param o element to be searched in given array
     * @return true if element present in the array. Otherwise, false.
     */
    @Override
    public boolean contains(int o) {

        for(int i = 0; i < size; i++) {
            //if element found in array
            if(arr[i] == o) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns the element at the specified position in this list.
     * @param index position where elements need to be searched
     * @return element at the specified position
     */
    @Override
    public int get(int index) {
        if(index < size) {
            return arr[index];
        }
        else {
            //If element not found
            return -1;
        }
    }

    /**
     * This method returns the index of the first occurrence of the
     * specified element in this list, or -1 if this list does not
     * contain the element.
     * @param o element whose first index needs to be found
     * @return first occurrence of the element index
     */
    @Override
    public int indexOf(int o) {

        for(int i = 0; i < size; i++) {
            if(arr[i] == o) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This methos returns the index of the last
     * occurrence of the specified element in this
     * list, or -1 if this list does not contain the element.
     * @param o element whose last index needs to be found
     * @return last occurrence of the element index
     */
    @Override
    public int lastIndexOf(int o) {
        //loop start from last
        for(int i = size-1; i > 0; i--) {
            if(arr[i] == o) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method removes the element at the specified position in this list.
     * @param index index of the element to be removed
     * @return  the element that was removed from the list
     */
    @Override
    public int remove(int index) {
        //print array to confirm the array for now
        int arr_length = size;
        int length = arr_length - 1;
        int new_arr[] = new int[length];
        int temp = arr[index];
        //shift elements from where index was positioned
        for(int i = index; i < length; i++) {
            arr[i] = arr[i + 1];
        }
        //copy elements to new array of size one
        // less than the original array
        for(int i = 0; i < length; i++){
            new_arr[i] = arr[i];
        }
        //referring array to new array
        arr = new_arr;
        size --;
        return temp;
    }

    /**
     * This method removes the first occurrence of the
     * specified element from this list, if it is present.
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    @Override
    public boolean remove(Integer o) {
        for(int i = 0; i < size; i++) {
            if(arr[i] == o) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    /**
     * This method replaces the element at the specified
     * position in this list with the specified element.
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     */
    @Override
    public int set(int index, int element) {
        if(index >= size()) {
            System.out.println("Index exceeds size. Enter other index.");
            return -1;
        }
        int temp = arr[index];
        arr[index] = element;
        return temp;
    }
}
