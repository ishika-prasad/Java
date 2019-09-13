/*
 * SetTest.java
 *
 * Version:
 *     1
 *
 */

/**
 * This class is to test the TreeSet and
 * HashSet for given function of java.
 * Calculate the average time for 1 million times
 * performance.
 *
 * @author  Ishika Prasad
 *
 */
package CollectionMap;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
    long totaltime = 0;
    int iteration = 10000;

    public void setTestMain() {
        Property p = new Property();
        SetTest setTest = new SetTest();

        Set<Integer> ts = new TreeSet<>();
        Set<Integer> hs = new HashSet<>();

        //Adding an element in treeSet
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionAdd(ts, i);
            long endTime = System.nanoTime();
            setTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for adding element in TreeSet: " + setTest.totaltime/iteration + " ns");

        //Adding an element in hashSet
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionAdd(hs, i);
            long endTime = System.nanoTime();
            setTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for adding element in hashSet: " + setTest.totaltime/iteration + " ns");

        //Size of treeSet
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionSize(ts);
            long endTime = System.nanoTime();
            setTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for size of treeset: " + setTest.totaltime/iteration + " ns");

        //Size of hashSet
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionSize(hs);
            long endTime = System.nanoTime();
            setTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for for size of hashset: " + setTest.totaltime/iteration + " ns");

        //equal
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.equal(ts, hs);
            long endTime = System.nanoTime();
            setTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for equal check in set: " + setTest.totaltime/iteration + " ns");

        //contains check for treeSet
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionContains(ts,i);
            long endTime = System.nanoTime();
            setTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for contain check in TreeSet: " + setTest.totaltime/iteration + " ns");

        //contains check for HashSet
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionContains(hs,i);
            long endTime = System.nanoTime();
            setTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for contain check in HashSet: " + setTest.totaltime/iteration + " ns");

        //remove an particular item for treeSet
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionRemove(ts, i+1);
            long endTime = System.nanoTime();
            setTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for removing the particular item in treeSet: " + setTest.totaltime/iteration + " ns");

        //remove an particular item for hashmap
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionRemove(hs, i+1);
            long endTime = System.nanoTime();
            setTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for removing the particular item in Hashmap: " + setTest.totaltime/iteration + " ns");



    }
}
