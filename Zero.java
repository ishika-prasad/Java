/*
 * Zero.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program can determine if given a set of numbers
 * has a subset that sums to zero.
 *
 * @author  Ishika Prasad
 */

public class Zero {
    static int[] testSet = {-1, -1, 2, 5, 6};


    /**
     * This method determines all possible combinations
     * of the given set and print subset which sums to zero.
     * Otherwise, print statement which shows subset doesn't exist.
     *
     */
    public static void checkSet(int[] testSet) {
        int combinations, sum, flag=0, n, indexTS;
        for(combinations = 1; combinations < 32; combinations++)
        {
            n = combinations;
            indexTS = 0;
            sum = 0;
            while (n > 0) {
                sum = sum + testSet[indexTS] * (n % 2);
                indexTS++;
                n /= 2;
            }
            if (sum == 0) {
                flag = 1;
                System.out.print("Found subset that sums to zero: ");
                if(testSet[indexTS-1] == 0) {
                    System.out.print(testSet[indexTS-1]);
                }
                else {
                    n = combinations;
                    indexTS = 0;
                    while (n > 0) {
                        if (testSet[indexTS] * (n % 2) != 0)
                            System.out.print(testSet[indexTS] + " ");
                        indexTS++;
                        n /= 2;
                    }
                }
                System.out.println();
            }

            if (flag == 1)
                break;
        }
        if (flag != 1)
            System.out.println("Unable to find subset that sums to zero");
    }

    /**
     * The main program.
     *
     *@param args command line arguments (ignored)
     */

    public static void main(String args[]) {
        checkSet(testSet);
    }
}

