/*
 * CustomComparator.java
 *
 * Version:
 *     1
 *
 */

/**
 * This class have the custom comparator
 *
 * @author  Ishika Prasad
 *
 */
package CollectionMap;

import java.util.Comparator;

public class CustomComaparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
}