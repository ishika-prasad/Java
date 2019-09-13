/*
 * MapTest.java
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

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapTest {
    long totaltime = 0;
    int iteration = 10000;

    public void mapTestmain() {
        Property p = new Property();
        MapTest mapTest = new MapTest();

        Map<Integer, Integer> hm = new HashMap<>();
        Map<Integer, Integer> tm = new TreeMap<>();

        //Adding an element in hashmap
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.mapAdd(hm, i, i);
            long endTime = System.nanoTime();
            mapTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for adding an element using hashmap: " + mapTest.totaltime/iteration);

        //Adding an element in treemap
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.mapAdd(tm, i, i);
            long endTime = System.nanoTime();
            mapTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for adding an element using treemap: " + mapTest.totaltime/iteration);

        //Size of hashmap
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.mapSize(hm);
            long endTime = System.nanoTime();
            mapTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for size of hashmap: " + mapTest.totaltime/iteration);

        //Size of treemap
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.mapSize(tm);
            long endTime = System.nanoTime();
            mapTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for size of treemap: " + mapTest.totaltime/iteration);

        //equal

        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.equal(hm,tm);
            long endTime = System.nanoTime();
            mapTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for equal check in map: " + mapTest.totaltime/iteration);


        //contains check for hashmap

        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.mapContains(hm, 300);
            long endTime = System.nanoTime();
            mapTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time contains check for hashmap: " + mapTest.totaltime/iteration);

        //contains check for treemap
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.mapContains(tm, 300);
            long endTime = System.nanoTime();
            mapTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time contains check for treemap: " + mapTest.totaltime/iteration);

        //change an element for hashmap
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.mapReplace(hm, i, i+1);
            long endTime = System.nanoTime();
            mapTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for changing element in hashmap: " + mapTest.totaltime/iteration);

        //change an element for treemap
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.mapReplace(tm, i, i+1);
            long endTime = System.nanoTime();
            mapTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for changing element in treemap: " + mapTest.totaltime/iteration);

        //remove an element from hashmap
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.mapRemove(hm, i+1);
            long endTime = System.nanoTime();
            mapTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for removing element in hashmap: " + mapTest.totaltime/iteration);

        //remove an element from treemap
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.mapRemove(tm, i+1);
            long endTime = System.nanoTime();
            mapTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for removing element in treemap: " + mapTest.totaltime/iteration);



    }
}
