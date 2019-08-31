/*
 * GenericStorageTest.java
 *
 * Version:
 *     1
 */

/**
 * Test class for Integer type and String type
 *
 * @author  Ishika Prasad
 */
public class GenericStorageTest
{
    //Test cases for Integer
    public static void individualTest(StorageInterface<Integer> is)
    {
        System.out.println("Testing " + is.getClass());
        System.out.println("Should be\n\t0\n\t" + is.size());
        is.add(1);
        is.addAll((new Integer[]{2, 3, 4}));
        System.out.println("Should be\n\t0\n\t" + is.size());
        is.addAll(0, new Integer[]{8, 6});
        System.out.println("Should be\n\t{8, 6}\n\t" + is);
        is.sort();
        System.out.println("Should be\n\t{6, 8}\n\t" + is);
        is.add(0, 4);
        System.out.println("Should be\n\t{4, 6, 8}\n\t" + is);
        System.out.println("Should be\n\t0\n\t" + is.indexOf(4));
        System.out.println("Should be\n\t2\n\t" + is.lastIndexOf(8));
        is.remove(new Integer(8));
        System.out.println("Should be\n\t{4, 6}\n\t" + is);
        is.addAll(2, new Integer[]{4, 8, 6, 2, 6, 10});
        System.out.println("Should be\n\t{4, 6, 4, 8, 6, 2, 6, 10}\n\t" + is);
        System.out.println("Should be\n\tfalse\n\t" + is.contains(7));
        System.out.println("Should be\n\ttrue\n\t" + is.contains(4));
        System.out.println("Should be\n\tfalse\n\t" + is.containsAll(new Integer[]{4, 5, 6, 7}));
        System.out.println("Should be\n\tfalse\n\t" + is.removeAll(new Integer[]{7, 3, 9}));
        System.out.println("Should be\n\t{4, 6, 4, 8, 6, 2, 6, 10}\n\t" + is);
        System.out.println("Should be\n\ttrue\n\t" + is.removeAll(new Integer[]{6, 4, 2}));
        System.out.println("Should be\n\t{4, 8, 6, 6, 10}\n\t" + is);
        is.clear();
        System.out.println("should be\n\tempty\n\t" + is.isEmpty() + " : " + is);
        System.out.println("Should be\n\t0\n\t" + is.hashCode());
        //is.add(-1,80);

    }

    //Test cases for String
    public static void stringIndividualTest(StorageInterface<String> is)
    {
        System.out.println("Testing " + is.getClass());
        System.out.println("Should be\n\t0\n\t" + is.size());
        is.add("1");
        is.addAll((new String[]{"2", "3", "4"}));
        System.out.println("Should be\n\t0\n\t" + is.size());
        is.addAll(0, new String[]{"80", "60"});
        System.out.println("Should be\n\t{80, 60}\n\t" + is);
        is.sort();
        System.out.println("Should be\n\t{60, 80}\n\t" + is);
        is.add(0, "40");
        System.out.println("Should be\n\t{40, 60, 80}\n\t" + is);
        System.out.println("Should be\n\t0\n\t" + is.indexOf("40"));
        System.out.println("Should be\n\t2\n\t" + is.lastIndexOf("80"));
        is.remove(new String("80"));
        System.out.println("Should be\n\t{40, 60}\n\t" + is);
        is.addAll(2, new String[]{"40", "80", "60"});
        System.out.println("Should be\n\t{40, 60, 40, 80, 60}\n\t" + is);
        System.out.println("Should be\n\tfalse\n\t" + is.contains("70"));
        System.out.println("Should be\n\ttrue\n\t" + is.contains("40"));
        System.out.println("Should be\n\tfalse\n\t" + is.containsAll(new String[]{"40", "50", "60", "70"}));
        System.out.println("Should be\n\tfalse\n\t" + is.removeAll(new String[]{"70", "30", "90"}));
        System.out.println("Should be\n\t{40, 60, 40, 80, 60}\n\t" + is);
        System.out.println("Should be\n\ttrue\n\t" + is.removeAll(new String[]{"80", "70"}));
        System.out.println("Should be\n\t{40, 60, 40, 60}\n\t" + is);
        is.clear();
        System.out.println("should be\n\tempty\n\t" + is.isEmpty() + " : " + is);
        System.out.println("Should be\n\t0\n\t" + is.hashCode());
        is.add(-1,"80");
    }

    public static void main(String[] args)
    {
        //For Integer
        StorageInterface<Integer> ill = new LinkedList<Integer>();
        individualTest(ill);
        StorageInterface<Integer> ial = new ArrayList<Integer>();
        individualTest(ial);
        System.out.println("Should be\n\ttrue\n\t" + ill.contentEquals(ial));
        ial.add(2);
        System.out.println("Should be\n\tfalse\n\t" + ill.contentEquals(ial));
        ill.add(2);
        System.out.println("Should be\n\ttrue\n\t" + ill.contentEquals(ial));

        //For String
        StorageInterface<String> illString = new LinkedList<String>();
        stringIndividualTest(illString);
        StorageInterface<String> ialString = new ArrayList<String>();
        stringIndividualTest(ialString);
        System.out.println("Should be\n\ttrue\n\t" + illString.contentEquals(ialString));
        ialString.add("14");
        System.out.println("Should be\n\tfalse\n\t" + illString.contentEquals(ialString));
        illString.add("14");
        System.out.println("Should be\n\ttrue\n\t" + illString.contentEquals(ialString));

    }
}

