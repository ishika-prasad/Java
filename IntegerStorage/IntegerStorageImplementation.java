/**
 * This class is the abstract class and have the
 * implementation of common functionality of Arraylist
 * and Linkedlist.
 *
 * @author  Ishika Prasad
 */

public abstract class IntegerStorageImplementation implements IntegerStorage {

    int size = 0;
    abstract public boolean add(int o);
    abstract public void add(int index, int element);
    abstract public void clear();
    abstract public boolean contains(int o);
    abstract public int get(int index);
    abstract public int indexOf(int o);
    abstract public int lastIndexOf(int o);
    abstract public int remove(int index);
    abstract public boolean remove(Integer o);
    abstract public int set(int index, int element);

    /**
     * This method appends all of the elements in the specified collection
     * to the end of this list, in the order that they are returned by the
     * specified collection's Iterator
     * @param os array containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    public boolean addAll(int[] os) {
        for(int i = 0; i < os.length; i++) {
            add(os[i]);
        }
        return true;
    }

    /**
     * This method inserts all of the elements in the specified
     * collection into this list, starting at the specified position
     * @param index index at which to insert the first element from
     *              the specified collection
     * @param os array containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean addAll(int index, int[] os) {
        for(int i = 0; i < os.length; i++) {
            add(index, os[i]);
            index++;
        }
        return true;
    }

    /**
     * This method returns the number of elements in this list.
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This method check if length is 0
     * @return true if, and only if, length() is 0
     */
    @Override
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    /**
     * This method returns true if this collection contains all
     * of the elements in the specified array.
     * @param os array to be checked for containment in this array
     * @return true if this array contains all of the elements in
     * the specified array
     */
    @Override
    public boolean containsAll(int[] os) {
        for(int i = 0; i < os.length; i++)  {
            if((contains(os[i]) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method indicates whether some other object is "equal to" this one.
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * This method returns a hash code value for the object
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        int sum = 0;
        for(int i = 0; i < size; i++) {
            sum += get(i);
        }
        return sum;
    }

    /**
     * This method returns a string representation of the object
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        String toStr = "{";
        for(int i = 0; i < size; i++) {
            toStr += get(i);
            if(i != size-1) {
                toStr +=  ", ";
            }
        }
        toStr += "}";
        return toStr;
    }

    /**
     * This method compares the specified IntegerStorage for content
     * equality both in terms of values and order
     * @param o object to compare
     * @return true if specified IntegerStorage content
     * equality both in terms of values and order. Otherwise, false
     */
    @Override
    public boolean contentEquals(IntegerStorage o) {
        if(size != o.size()) {
            return false;
        }
        for(int i = 0;i < size; i++) {
            if(get(i) != o.get(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method removes from this list all of its elements that
     * are contained in the specified collection
     * @param os object containing elements to be removed from this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean removeAll(int[] os) {
        boolean removeAll = false;
        for(int i = 0; i < os.length; i++)  {
            removeAll = removeAll | remove(new Integer(os[i]));
        }
        return removeAll;
    }

    /**
     *This method sorts this storage into ascending order
     */
    @Override
    public void sort() {
        int tempArr[] = new int[size];
        for(int i = 0; i < size; i++) {
            tempArr[i] = get(i);
        }
        qsort(tempArr, 0, tempArr.length-1);
        clear();
        for(int i = 0; i < tempArr.length; i++) {
            add(tempArr[i]);
        }
    }
    private int partition(int A[], int l, int r) {
        int x = A[l], i = l, j = r;
        while (true) {
            while (i < j) {
                while ((i <= r) && (A[i] <= x)) {
                    i += 1;
                }
                while ((j >= l) && (A[j] > x)) {
                    j -= 1;
                }
                if (i < j) {
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
            int temp = A[l];
            A[l] = A[j];
            A[j] = temp;
            return j;
        }
    }
    private void qsort(int A[], int l, int r) {
        if (l < r) {
            int q = partition(A, l, r);
            qsort(A, l, q - 1);
            qsort(A, q + 1, r);
        }
    }
}
