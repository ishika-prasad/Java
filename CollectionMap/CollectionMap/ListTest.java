/*
 * ListTest.java
 *
 * Version:
 *     1
 *
 */

/**
 * This class is to test the Arraylist and
 * LinkedList for given function of java.
 * Calculate the average time for 1 million times
 * performance.
 *
 * @author  Ishika Prasad
 *
 */
package CollectionMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {
    long totaltime = 0;
    int iteration = 10000;

    public void listTestMain() {
        Property p = new Property();
        ListTest listTest = new ListTest();

        List<Integer> al = new ArrayList<>();
        List<Integer> ll = new LinkedList<>();

        //Adding an element to arraylist
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionAdd(al, i);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for adding an element to arraylist: " + listTest.totaltime/iteration + " ns");

        //Adding an element to linkedlist
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionAdd(ll, i);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for adding an element to linkedlist: " + listTest.totaltime/iteration + " ns");

        //Size of arraylist
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionSize(al);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for size of arraylist: " + listTest.totaltime/iteration + " ns");

        //Size of linkedlist
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionSize(ll);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for size of linkedlist: " + listTest.totaltime/iteration + " ns");

        //equal
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.equal(al,ll);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for list euality check: " + listTest.totaltime/iteration + " ns");

        //contains check for arraylist

        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionContains(al,300);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for contains check in Arraylist : " + listTest.totaltime/iteration);

        //contains check for linkedlist

        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionContains(ll,300);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for contains check in Linkedlist: " + listTest.totaltime/iteration);

        //change an element in arraylist
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionReplace(al,i, i+1);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for changing element in arraylist: " + listTest.totaltime/iteration + " ns");

        //change an element in linkedlist
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionReplace(ll,i, i+1);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for changing element in linkedlist: " + listTest.totaltime/iteration + " ns");

        //modified for sort
        p.collectionReplace(al, 0, 1000008);
        p.collectionReplace(ll, 0, 1000008);

        //sort for arraylist
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.ListSort(al);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for sorting an arraylist: " + listTest.totaltime/iteration + " ns");

        //sort for linkedlist
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.ListSort(ll);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for sorting an linkedlist: " + listTest.totaltime/iteration + " ns");

        //sort with custom comparator
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.ListSortComparator(al);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for sorting an arraylist using comparator: " + listTest.totaltime/iteration + " ns");

        //sort with custom comparator
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.ListSortComparator(ll);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for sorting an linkedlist using comparator: : " + listTest.totaltime/iteration + "ns");

        //remove an element
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionRemove(al, i+1);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for removing an element in arraylist: " + listTest.totaltime/iteration + " ns");

        //remove an element
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionRemove(ll, i+1);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time  for removing an element in linkedlist: " + listTest.totaltime/iteration + " ns");

        //add at particular index
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionAddIndex(al, i, i+1);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time for adding an element at particular index in arraylist: " + listTest.totaltime/iteration + " ns");

        //add at particular index
        for(int i = 0; i < iteration; i++) {
            long startTime = System.nanoTime();
            p.collectionAddIndex(ll, i, i+1);
            long endTime = System.nanoTime();
            listTest.totaltime += endTime - startTime;
        }
        System.out.println("Average time  for adding an element at particular index in linkedlist: : " + listTest.totaltime/iteration + " ns");


    }
}
