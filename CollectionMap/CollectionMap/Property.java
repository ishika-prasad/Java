/*
 * Property.java
 *
 * Version:
 *     1
 *
 */

/**
 * This class is to test the HashMap and
 * TreeMap for given function of java.
 * Calculate the average time for 1 million times
 * performance.
 *
 * @author  Ishika Prasad
 *
 */
package CollectionMap;

import java.util.*;

public class Property {

    /**
     * This method is used for collection or map equality check
     * @param o1 collection/map
     * @param o2 collection/map
     * @return true if given collections or maps are equal. Otherwise, false.
     */
    public boolean equal(Object o1, Object o2) {
        return o1.equals(o2);
    }

    /**
     * This method is used for adding elements in collection data structure
     * @param collection arraylist, linkedlist, treeset, hashset
     * @param value needs to be added
     * @return true if value get added in the list or set. otherwise, false.
     */
    public boolean collectionAdd(Collection<Integer> collection, int value) {
        return collection.add(value);
    }

    /**
     * This method is used for removing an element from list or set
     * @param collection arraylist, linkedlist, treeset, hashset
     * @param value needs to be removed
     * @return rue if value get removed in the list or set. otherwise, false.
     */
    public boolean collectionRemove(Collection<Integer> collection, int value) {
        return collection.remove(value);
    }

    /**
     * This method is used for getting the size of list ot set
     * @param collection arraylist, linkedlist, treeset, hashset
     * @return size of list or set
     */
    public int collectionSize(Collection<Integer> collection) {
        return collection.size();
    }

    /**
     * This method is used to check if the element is contained in the list or set
     * @param collection arraylist, linkedlist, treeset, hashset
     * @param element element to be searched
     * @return true if list or set contains the item. Otherwise, false.
     */
    public boolean collectionContains(Collection<Integer> collection, int element) {
        return collection.contains(element);
    }

    /**
     * This method is used to change an element of collection
     * @param collection arraylist, linkedlist, treeset, hashset
     * @param index index of the collection data structure
     * @param element element to be set for a particular index
     * @return return element previously at specified position
     */
    public int collectionReplace(List<Integer> collection, int index, int element) {
        return collection.set(index, element);
    }

    /**
     * This method is used to add the index to particular index
     * @param collection arraylist, linkedlist
     * @param index index of the collection data structure
     * @param element element to be set for a particular index
     */
    public void collectionAddIndex(List<Integer> collection, int index, int element) {
        collection.add(index, element);
    }


    /**
     * This method is used to sort the list
     * @param collection linkedlist, arraylist
     */
    public void ListSort(List<Integer> collection) {
        Collections.sort(collection);
    }

    /**
     * This method is used to sort the list using custom comparator
     * @param collection
     */
    public void ListSortComparator(List<Integer> collection) {
        Collections.sort(collection, new CustomComaparator());
    }

    /**
     * This method is used to add elements to the map
     * @param map   hashmap,treemap
     * @param key   key value of type Integer
     * @param value value of type Integer
     */

    public void mapAdd(Map<Integer, Integer> map, int key, int value) {
        map.put(key, value);
    }

    /**
     * This method is used to remove element from a particular location in a map
     * @param map   hashmap,treemap
     * @param key   of type Integer which needs to be removed
     */
    public void mapRemove(Map<Integer, Integer> map, int key) {
        map.remove(key);
    }

    /**
     * This method is used to get the size of the map
     * @param map   hashmap,treemap
     * @return   size of type Integer
     */
    public int mapSize(Map<Integer, Integer> map) {
        return map.size();
    }

    /**
     * This method is used to check the element in the map
     * @param map hashmap,treemap
     * @param element element to be searched
     * @return true if element is found. Otherwise, false.
     */
    public boolean mapContains(Map<Integer, Integer> map, int element) {
        return map.containsValue(element);
    }

    /**
     * This method is used to change/modify the map
     * @param map hashmap,treemap
     * @param key key where needs to replace
     * @param value value to be set for particular key
     */
    public void mapReplace(Map<Integer, Integer> map, int key, int value) {
        map.replace(key, value);
    }
}
