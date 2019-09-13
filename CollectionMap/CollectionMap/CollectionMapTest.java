/*
 * CollectionMapTest.java
 *
 * Version:
 *     1
 *
 */

/**
 * This class is for testing all list, set and map tests
 * and calculate average time in ns.
 *
 * @author  Ishika Prasad
 *
 */
package CollectionMap;

public class CollectionMapTest {

    public static void main(String[] args) {

        ListTest listTest = new ListTest();
        SetTest setTest = new SetTest();
        MapTest mapTest = new MapTest();

        listTest.listTestMain();
        setTest.setTestMain();
        mapTest.mapTestmain();

    }
}